<%@page import="java.util.Date"%>
<%@page import="models.Linguagem"%>
<%@page import="db.daos.LinguagemDAO"%>
<!DOCTYPE html>

<%@page session="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>

<%
    boolean autenticado = session.getAttribute("login") != null;
    LinguagemDAO dao = new LinguagemDAO();
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
                        Linguagens
                        <% if (autenticado) {%>
                            <a href="cadastrarLinguagem.jsp"><button class="btn btn-xs btn-success">Adicionar</button></a>
                        <%}%>
                    </h1>

                </div>
            </div>
            <!-- /.row -->

            <!-- Projects Row -->

            <div class="row">

                <% for (Linguagem linguagem : dao.select()) {%>

                <div class="col-md-2">

                    <div class="thumbnail">
                        <a href="linguagem.jsp?nome=<%=linguagem.getNome()%>">

                            <img src="images/<%=linguagem.getCaminhoLogo()%>" alt="...">
                            <div class="caption">
                                <h3><%=linguagem.getNome()%></h3>
                            </div>
                        </a>
                        <% if (autenticado) {%>
                        <p style="text-align: center;">
                        <form style="text-align: center;" method="post" action="LinguagemController">
                            <button name="btn_editar" value="<%=linguagem.getNome()%>" class="btn btn-success">
                                <span class="glyphicon glyphicon-edit"></span>
                            </button>
                            
                            <button name="btn_excluir" value="<%=linguagem.getId()%>" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </form>
                        </p>
                        <%}%>

                    </div>                 

                </div> 


                <%}%>

            </div>

<!--            <nav style="text-align: center;">
                <ul class="pagination">
                    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                    <li class="disabled"><a href="#">2 <span class="sr-only"></span></a></li>
                    <li class="disabled"><a href="#">3 <span class="sr-only"></span></a></li>
                    <li class="disabled"><a href="#">4 <span class="sr-only"></span></a></li>
                    <li class="disabled"><a href="#">5 <span class="sr-only"></span></a></li>
                    <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
                </ul>
            </nav>-->

            <hr>
            <hr>

            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Prolangs</p>
                    </div>
                </div>
            </footer>

        </div>



        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>
