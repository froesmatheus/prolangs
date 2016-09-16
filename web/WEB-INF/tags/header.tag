<%-- 
    Document   : topo
    Created on : Aug 23, 2016, 8:09:44 PM
    Author     : mathe
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@attribute name="message"%>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/4-col-portfolio.css" rel="stylesheet">

<%
    boolean autenticado = session.getAttribute("login") != null;
%>
<%-- any content can be specified here e.g.: --%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="linguagens.jsp">Prolangs</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="linguagens.jsp">Linguagens</a>
                </li>
                <li>
                    <a href="frameworks.jsp">Frameworks</a>
                </li>

                <% if (autenticado) {%>
                <li>
                    <a href="usuarios.jsp">Usuários</a>
                </li>                
                <%}%>

                <li>
                    <% if (autenticado) {%>
                    <a href="LoginController"><b>Logout</b></a>
                    <%} else {%>
                    <a href="login.jsp"><b>Login</b></a>
                    <%}%>
                </li>
            </ul>

            <!--            <form action="listarResultado.jsp" method="get" class="navbar-form navbar-right">
                            <div class="form-group">
                                <input type="text" name="query" class="form-control" >
                            </div>
                            <button type="submit" class="btn btn-success">Pesquisar</button>
                        </form>-->

        </div>
    </div>
</nav>

<%


%>