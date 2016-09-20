/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.daos.FrameworkDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Framework;
import models.Linguagem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author matheus
 */
public class FrameworkController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private final FrameworkDAO dao;

    public FrameworkController() throws SQLException, ClassNotFoundException {
        this.dao = new FrameworkDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.request = request;
        this.response = response;

        if (request.getContentType().contains("multipart/form-data")) {
            adicionarOuEditarFramework();
            return;
        }

        if (request.getParameter("btn_editar") != null) {
            editarFramework();
            return;
        }

        if (request.getParameter("btn_excluir") != null) {
            excluirFramework();
            return;
        }

    }

    private void adicionarOuEditarFramework() throws IOException {
        String nome, genero, paginaOficial, id, descricao, caminhoLogo;
        int idLinguagem = -1;
        genero = nome = paginaOficial = descricao = id = caminhoLogo = "";

        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        ServletContext context = getServletContext();
        String filePath = context.getInitParameter("file-upload");

        String contentType = request.getContentType();

        if ((contentType.contains("multipart/form-data"))) {

            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File("c:\\temp"));

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(maxFileSize);
            try {
                List fileItems = upload.parseRequest(request);

                Iterator i = fileItems.iterator();

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fileName = fi.getName();
                        if (fileName.lastIndexOf("\\") >= 0) {
                            //String name = fileName.substring(fileName.lastIndexOf("\\"), fileName.lastIndexOf("."));
                            String name = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            file = new File(filePath + name);
                        } else {
                            //String name = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
                            String name = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            file = new File(filePath + name);
                        }
                        caminhoLogo = file.getName();
                        fi.write(file);
                    } else {
                        String campo = fi.getFieldName();
                        String valor = fi.getString("UTF-8");

                        switch (campo) {
                            case "nome":
                                nome = valor;
                                break;
                            case "genero":
                                genero = valor;
                                break;
                            case "pagina_oficial":
                                paginaOficial = valor;
                                break;
                            case "descricao":
                                descricao = valor;
                                break;
                            case "linguagem":
                                idLinguagem = Integer.parseInt(valor);
                                break;
                            case "id":
                                id = valor;
                                break;
                            default:
                                break;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        boolean atualizando = !id.isEmpty();

        if (atualizando) {
            Framework framework = dao.select(Integer.parseInt(id));

            framework.setDescricao(descricao);
            framework.setGenero(genero);
            framework.setIdLinguagem(idLinguagem);
            framework.setNome(nome);
            framework.setPaginaOficial(paginaOficial);

            if (!caminhoLogo.isEmpty()) {
                File imagemAntiga = new File(filePath + framework.getCaminhoLogo());
                imagemAntiga.delete();

                framework.setCaminhoLogo(caminhoLogo);
            }

            dao.update(framework);

        } else {
            Framework framework = new Framework(nome, descricao, genero, paginaOficial, idLinguagem, caminhoLogo);

            dao.insert(framework);
        }
        
        response.sendRedirect("frameworks.jsp");
        //response.getWriter().print("<script>window.location.href='frameworks.jsp';</script>");
    }

    private void editarFramework() throws IOException {
        String nome = request.getParameter("btn_editar");
        response.sendRedirect("editarFramework.jsp?nome=" + nome);
    }

    private void excluirFramework() throws IOException {
        int frameworkId = Integer.parseInt(request.getParameter("btn_excluir"));
        Framework framework = dao.select(frameworkId);
        dao.delete(frameworkId);

        String filePath = getServletContext().getInitParameter("file-upload");
        File file = new File(filePath + framework.getCaminhoLogo());
        file.delete();
        response.sendRedirect("frameworks.jsp");
    }
}
