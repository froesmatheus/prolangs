<%@page import="models.Framework"%>
<%@page import="db.daos.FrameworkDAO"%>
<%@page import="models.Linguagem"%>
<%@page import="db.daos.LinguagemDAO"%>
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>

<%
    boolean autenticado = session.getAttribute("login") != null;
    FrameworkDAO dao = new FrameworkDAO();
    LinguagemDAO linguagemDAO = new LinguagemDAO();
%>
<html lang="pt-BR">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Prolangs</title>
    </head>

    <body>
        <c:header/>

        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Frameworks
                        <% if (autenticado) {%>
                        <a href="cadastrarFramework.jsp"><button class="btn btn-xs btn-success">Adicionar</button></a>
                        <%}%>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Projects Row -->

            <div class="row">

                <% for (Framework framework : dao.select()) {%>

<!--                <a href="framework.jsp?nome=<%=framework.getNome()%>">
                    <div class="col-md-2">
                        <div class="thumbnail">
                            <img src="images/<%=framework.getCaminhoLogo()%>" alt="...">
                            <div class="caption">
                                <h3><%=framework.getNome()%></h3>
                                <h6><%=linguagemDAO.select(framework.getIdLinguagem())%></h6>
                            </div>
                        </div>
                    </div> 
                </a> -->

                <div class="col-md-2">

                    <div class="thumbnail">
                        <a href="framework.jsp?nome=<%=framework.getNome()%>">

                            <img src="images/<%=framework.getCaminhoLogo()%>" alt="...">
                            <div class="caption">
                                <h3><%=framework.getNome()%></h3>
                                <h6><%=linguagemDAO.select(framework.getIdLinguagem())%></h6>
                            </div>
                        </a>
                        <% if (autenticado) {%>
                        <p style="text-align: center;">
                        <form style="text-align: center;" method="post" action="FrameworkController">
                            <button name="btn_editar" value="<%=framework.getNome()%>" class="btn btn-success">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>

                            <button name="btn_excluir" value="<%=framework.getId()%>" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </form>
                        </p>
                        <%}%>

                    </div>   
                </div>
                <%}%>

            </div>

            <hr>
            <hr>

            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Prolangs 2016</p>
                    </div>
                </div>
            </footer>



            <script src="js/jquery.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.min.js"></script>

    </body>

</html>
