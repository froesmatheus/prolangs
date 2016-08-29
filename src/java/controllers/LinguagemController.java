/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.daos.LinguagemDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
            editarLinguagem();
            return;
        }

        if (request.getParameter("btn_excluir") != null) {
            excluirLinguagem();
            return;
        }

        if (request.getContentType().contains("multipart/form-data")) {
            adicionarLinguagem();
        }
    }

    private void editarLinguagem() throws IOException {
        String nome = request.getParameter("btn_editar");
        response.sendRedirect("editarLinguagem.jsp?nome=" + nome);
    }

    private void excluirLinguagem() throws IOException {
        int linguagemId = Integer.parseInt(request.getParameter("btn_excluir"));
        dao.delete(linguagemId);
        response.sendRedirect("linguagens.jsp");
    }

    private void adicionarLinguagem() throws IOException {
        String anoLancamento, nome, licenca, descricao, caminhoLogo;
        anoLancamento = nome = licenca = descricao = caminhoLogo = null;

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
                            String name = fileName.substring(fileName.lastIndexOf("\\"), fileName.lastIndexOf("."));
                            name = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            file = new File(filePath + name);
                        } else {
                            String name = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
                            name = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            file = new File(filePath + name);
                        }
                        caminhoLogo = file.getName();
                        fi.write(file);
                    } else {
                        String campo = fi.getFieldName();
                        String valor = fi.getString("UTF-8");

                        if (campo.equals("nome")) {
                            nome = valor;
                        } else if (campo.equals("ano_lancamento")) {
                            anoLancamento = valor;
                        } else if (campo.equals("licenca")) {
                            licenca = valor;
                        } else if (campo.equals("descricao")) {
                            descricao = valor;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        Linguagem linguagem = new Linguagem(nome, anoLancamento, licenca, descricao, caminhoLogo);

        dao.insert(linguagem);

        response.sendRedirect("linguagens.jsp");
    }
}
