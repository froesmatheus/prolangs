
<%@page import="db.daos.FrameworkDAO"%>
<%@page import="models.Framework"%>
<%@page import="models.Linguagem"%>
<%@page import="db.daos.LinguagemDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>
<%@include file="autenticar.jspf" %>
<!DOCTYPE html>
<%
    LinguagemDAO linguagemDAO = new LinguagemDAO();
    FrameworkDAO dao = new FrameworkDAO();
    Framework framework = dao.select(request.getParameter("nome"));

    if (framework == null) {
        response.sendRedirect("frameworks.jsp");
    }

%> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Framework</title>
    </head>
    <body>
    <c:header/>

    <div class="container">

        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Editar Framework
                </h1>

            </div>
        </div>
        <!-- /.row -->

        <form method="post" action="FrameworkController" enctype="multipart/form-data">
            <div class="list-group">
                <input type="hidden" name="id" value="<%=framework.getId()%>"/>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Nome</h4>
                    <input name="nome" value="<%=framework.getNome()%>" class="form-control" required/>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Gênero</h4>
                    <input name="genero" value="<%=framework.getGenero()%>" class="form-control" required/>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Página Oficial</h4>
                    <input name="pagina_oficial" value="<%=framework.getPaginaOficial()%>" class="form-control" required/>

                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Descrição</h4>
                    <textarea name="descricao" class="form-control" rows="10" required><%=framework.getDescricao()%></textarea>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Linguagem</h4>
                    <select class="form-control" style="width: 200px;" name="linguagem">
                        <% for (Linguagem linguagem : linguagemDAO.select()) {%>
                        
                        <% if (linguagem.getId() == framework.getIdLinguagem()) {%>
                        <option selected value="<%=linguagem.getId()%>"><%=linguagem.getNome()%></option>
                        <%} else {%>
                        <option value="<%=linguagem.getId()%>"><%=linguagem.getNome()%></option>
                        <%}%>
                        
                        <%}%>
                    </select>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Logo</h4>
                    <input type="file" accept="image/*" name="logo"/>
                </div>

                <div class="list-group-item">
                    <input class="btn btn-primary" name="btn_cadastrar" type="submit" value="Atualizar">
                </div>
            </div>
        </form>

        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Prolangs 2016</p>
                </div>
            </div>
        </footer>

    </div>
</body>
</html>
