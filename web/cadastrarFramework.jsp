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
        <title>Cadastrar Framework</title>
    </head>
    <body>
        <c:header/>

        <div class="container">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Cadastrar Framework
                    </h1>

                </div>
            </div>
            <!-- /.row -->

            <form method="post" action="FrameworkController" enctype="multipart/form-data">
                <div class="list-group">
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Nome</h4>
                        <input name="nome" class="form-control" required/>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Gênero</h4>
                        <input name="genero" class="form-control" required/>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Página Oficial</h4>
                        <input name="pagina_oficial" class="form-control" required/>

                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Descrição</h4>
                        <textarea name="descricao" class="form-control" rows="10" required></textarea>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Linguagem</h4>
                        <select class="form-control" style="width: 200px;" name="linguagem">
                            <% for (Linguagem linguagem : linguagemDAO.select()) {%>
                            <option value="<%=linguagem.getId()%>"><%=linguagem.getNome()%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Logo</h4>
                        <input type="file" accept="image/*" name="logo" required/>
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
