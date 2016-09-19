<%-- 
    Document   : cadastrarLinguagem
    Created on : Aug 26, 2016, 7:01:00 PM
    Author     : mathe
--%>

<%@page import="models.Linguagem"%>
<%@page import="db.daos.LinguagemDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>
<%@include file="autenticar.jspf" %>

<!DOCTYPE html>
<%
    LinguagemDAO linguagemDAO = new LinguagemDAO();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Usuário</title>
    </head>
    <body>
    <c:header/>

    <div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Cadastrar Usuário
                </h1>

            </div>
        </div>
        <!-- /.row -->

        <form method="post" action="UsuarioController">
            <div class="list-group">
                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Login</h4>
                    <input name="login" class="form-control" required/>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Senha</h4>
                    <input name="senha" type="password" class="form-control" required/>
                </div>

                <div class="list-group-item">
                    <input class="btn btn-primary" name="btn_cadastrar" type="submit" value="Cadastrar">
                </div>
            </div>
        </form>

        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Prolangs</p>
                </div>
            </div>
        </footer>

    </div>
</body>
</html>
