/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import db.daos.UsuarioDAO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Usuario;

/**
 *
 * @author matheus
 */
public class LoginController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private UsuarioDAO dao;

    public LoginController() throws SQLException, ClassNotFoundException {
        this.dao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepararRequestResponse(request, response);

        if (request.getParameter("login") != null) {
            autenticarUsuario();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        prepararRequestResponse(request, response);

        desautenticarUsuario();
    }

    private void autenticarUsuario() throws IOException {
        String loginForm = request.getParameter("login");
        String senhaForm = request.getParameter("password");

        Usuario usuario = dao.select(loginForm, senhaForm);
        if (usuario != null) {
            request.getSession().setAttribute("login", usuario.getLogin());
            request.getSession().setAttribute("password", usuario.getSenha());
            response.sendRedirect("linguagens.jsp");
        } else {
            response.getWriter().print("<script>window.alert('Usuário não autorizado'); window.location.href='login.jsp';</script>");
        }
    }

    private void desautenticarUsuario() throws IOException {
        request.getSession().setAttribute("login", null);
        request.getSession().setAttribute("password", null);
        response.sendRedirect("linguagens.jsp");
    }

    private void prepararRequestResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        this.request = request;
        this.response = response;
    }
}
