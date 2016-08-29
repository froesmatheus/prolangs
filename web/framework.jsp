<%-- 
    Document   : linguagem
    Created on : Aug 23, 2016, 7:45:53 PM
    Author     : mathe
--%>

<%@page import="java.util.List"%>
<%@page import="models.Framework"%>
<%@page import="db.daos.FrameworkDAO"%>
<%@page import="models.Linguagem"%>
<%@page import="db.daos.LinguagemDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>

<%
    FrameworkDAO dao = new FrameworkDAO();
    Framework framework = dao.select(request.getParameter("nome"));

    if (framework == null) {
        response.sendRedirect("frameworks.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=framework.getNome()%></title>
    </head>
    <body>
    <c:header/> 

    <div class="container">
        <div class="row">
            <div class="col-lg-12">

                <h1 class="page-header">
                    <img width="50" height="50" src="images/<%=framework.getCaminhoLogo()%>"/>

                    <%=framework.getNome()%>

                </h1>
            </div>
        </div>

        <div class="list-group">
            <div class="list-group-item">
                <h4 class="list-group-item-heading">Descrição</h4>
                <p style="text-align: justify" class="list-group-item-text"><%=framework.getDescricao()%></p>
            </div>

            <div class="list-group-item">
                <h4 class="list-group-item-heading">Página Oficial</h4>
                <p class="list-group-item-text">
                    <a target="_blank" href="<%=framework.getPaginaOficial()%>"><%=framework.getPaginaOficial()%></a>
                </p>
            </div>
            
            <div class="list-group-item">
                <h4 class="list-group-item-heading">Gênero</h4>
                <p class="list-group-item-text"><%=framework.getGenero()%></p>
            </div>
        </div>
    </div>
</body>
</html>
