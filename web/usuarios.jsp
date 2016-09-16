<%-- 
    Document   : usuarios
    Created on : Sep 16, 2016, 7:48:32 PM
    Author     : matheusfroes
--%>

<%@page import="models.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="db.daos.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>
<%@include file="autenticar.jspf" %>
<!DOCTYPE html>
<%    UsuarioDAO dao = new UsuarioDAO();

    List<Usuario> listaUsuarios = dao.select();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários</title>
    </head>
    <body>
        <c:header/>

        <div class="container">
            <div class="row">

                <div class="col-md-12">
                    <h1 class="page-header">
                        Usuários
                        <% if (autenticado) {%>
                        <a href="cadastrarUsuario.jsp"><button class="btn btn-xs btn-success">Adicionar</button></a>
                        <%}%>
                    </h1>
                    
                    <form method="post" action="UsuarioController">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <th style="text-align: center">ID</th>
                                <th style="text-align: center">Login</th>
                                <th style="text-align: center">Senha</th>
                                <th></th>
                                <th></th>
                            </tr>

                            <% for (Usuario usuario : listaUsuarios) {%>

                            <tr>
                                <td style="vertical-align: middle; text-align: center">
                                    <%=usuario.getId()%>
                                </td>

                                <td style="vertical-align: middle; text-align: center">
                                    <%=usuario.getLogin()%>

                                </td>

                                <td style="vertical-align: middle; text-align: center">
                                    <%=usuario.getSenha()%>
                                </td>

                                <td style="text-align: center; width: 60px; vertical-align: central">
                                    <button name="get_editar" value="<%=usuario.getId()%>" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Editar" class="glyphicon glyphicon-edit btn btn-sm btn-success"></button>
                                </td>

                                <td style="text-align: center; width: 60px; vertical-align: central">
                                    <button name="btn_excluir" value="<%=usuario.getId()%>" aria-hidden="true" data-toggle="tooltip" data-placement="top" title="Excluir" class="glyphicon glyphicon-remove btn btn-sm btn-danger"></button>
                                </td>

                            </tr>
                            <%}%>

                        </table>
                    </form>
                </div>
            </div>

            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Prolangs</p>
                    </div>
                </div>
            </footer>
        </div>
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script>
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        </script>

    </body>
</html>
