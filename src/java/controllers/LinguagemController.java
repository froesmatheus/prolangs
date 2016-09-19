/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.daos.LinguagemDAO;
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
import models.Linguagem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author matheus
 */
public class LinguagemController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private final LinguagemDAO dao;

    public LinguagemController() throws SQLException, ClassNotFoundException {
        this.dao = new LinguagemDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.request = request;
        this.response = response;

        if (request.getParameter("btn_editar") != null) {
            getEditarLinguagemView();
            return;
        }

        if (request.getParameter("btn_excluir") != null) {
            excluirLinguagem();
            return;
        }

        if (request.getContentType().contains("multipart/form-data")) {
            adicionarOuEditarLinguagem();
        }
    }

    private void getEditarLinguagemView() throws IOException {
        String nome = request.getParameter("btn_editar");
        response.sendRedirect("editarLinguagem.jsp?nome=" + nome);
    }

    private void excluirLinguagem() throws IOException {
        int linguagemId = Integer.parseInt(request.getParameter("btn_excluir"));
        Linguagem linguagem = dao.select(linguagemId);
        dao.delete(linguagemId);
        String filePath = getServletContext().getInitParameter("file-upload");
        File file = new File(filePath + linguagem.getCaminhoLogo());
        file.delete();
        response.sendRedirect("linguagens.jsp");
    }

    private void adicionarOuEditarLinguagem() throws IOException {
        String anoLancamento, nome, id, licenca, descricao, caminhoLogo;
        anoLancamento = nome = licenca = descricao = caminhoLogo = id = "";

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
                        if (fileName != null) {
                            String name = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            file = new File(filePath + name);

                            caminhoLogo = file.getName();
                            fi.write(file);
                        }
                    } else {
                        String campo = fi.getFieldName();
                        String valor = fi.getString("UTF-8");

                        switch (campo) {
                            case "nome":
                                nome = valor;
                                break;
                            case "ano_lancamento":
                                anoLancamento = valor;
                                break;
                            case "licenca":
                                licenca = valor;
                                break;
                            case "descricao":
                                descricao = valor;
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
            Linguagem linguagem = dao.select(Integer.parseInt(id));

            linguagem.setAnoLancamento(anoLancamento);
            linguagem.setDescricao(descricao);
            linguagem.setLicenca(licenca);
            linguagem.setNome(nome);

            if (!caminhoLogo.isEmpty()) {
                File imagemAntiga = new File(filePath + linguagem.getCaminhoLogo());
                imagemAntiga.delete();

                linguagem.setCaminhoLogo(caminhoLogo);
            }

            dao.update(linguagem);

        } else {
            Linguagem linguagem = new Linguagem(nome, anoLancamento, licenca, descricao, caminhoLogo);

            dao.insert(linguagem);    
        }
        
               

        response.sendRedirect("linguagens.jsp");
    }
}
