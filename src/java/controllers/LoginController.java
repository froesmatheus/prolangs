/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author matheus
 */
public class LoginController extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

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
        String login = "admin";
        String senha = "1234";

        String loginForm = request.getParameter("login");
        String senhaForm = request.getParameter("password");

        if (loginForm.equals(login) && senhaForm.equals(senha)) {
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", senha);
            response.sendRedirect("linguagens.jsp");
        } else {
            response.getWriter().print("<script>window.alert('Usuário não autorizado'); window.location.href='linguagens.jsp';</script>");
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
