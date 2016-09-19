/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.daos.UsuarioDAO;
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
import models.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author matheus
 */
public class UsuarioController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private final UsuarioDAO dao;

    public UsuarioController() throws SQLException, ClassNotFoundException {
        this.dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        this.request = request;
        this.response = response;

        if (request.getParameter("get_editar") != null) {
            getEditarUsuario();
            return;
        }
       
        if (request.getParameter("btn_excluir") != null) {
            excluirUsuario();
            return;
        }
        
        if (request.getParameter("btn_cadastrar") != null) {
            cadastrarUsuario();
            return;
        }
        
        if (request.getParameter("btn_atualizar") != null) {
            atualizarUsuario();
        }

    }



    private void excluirUsuario() throws IOException {
        int usuarioId = Integer.parseInt(request.getParameter("btn_excluir"));
        dao.delete(usuarioId);

        response.sendRedirect("usuarios.jsp");
    }

    private void cadastrarUsuario() throws IOException {
        String login, senha;
        
        login = request.getParameter("login");
        senha = request.getParameter("senha");
        
        Usuario usuario = new Usuario(login, senha);
        
        dao.insert(usuario);

        response.sendRedirect("usuarios.jsp");
    }

    private void atualizarUsuario() throws IOException {
        int id = Integer.parseInt(request.getParameter("id_usuario"));
        String login, senha;
        
        login = request.getParameter("login");
        senha = request.getParameter("senha");
        
        Usuario usuario = dao.select(id);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        
        dao.update(usuario);
        
        response.sendRedirect("usuarios.jsp");
    }

    private void getEditarUsuario() throws IOException {
        String id = request.getParameter("get_editar");
        response.sendRedirect("editarUsuario.jsp?id=" + id);
    }
}
