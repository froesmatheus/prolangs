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
    LinguagemDAO dao = new LinguagemDAO();
    FrameworkDAO frameworkDAO = new FrameworkDAO();
    Linguagem linguagem = dao.select(request.getParameter("nome"));
    List<Framework> frameworks = frameworkDAO.selectPorLinguagem(linguagem.getId());    
    
    if (linguagem == null) {
        response.sendRedirect("linguagens.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=linguagem.getNome()%></title>
    </head>
    <body>
        <c:header/> 
        
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    
                    <h1 class="page-header">
                        <img width="50" height="50" src="images/<%=linguagem.getCaminhoLogo()%>"/>

                        <%=linguagem.getNome()%>
                    
                    </h1>
                </div>
            </div>
                    
            <div class="list-group">
                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Ano de lançamento</h4>
                    <p class="list-group-item-text"><%=linguagem.getAnoLancamento()%></p>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Licença</h4>
                    <p class="list-group-item-text"><%=linguagem.getLicenca()%></p>
                </div>

                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Descrição</h4>
                    <p style="text-align: justify" class="list-group-item-text"><%=linguagem.getDescricao()%></p>
                </div>
                
                <% if (frameworks.size() > 0) { %>
                <div class="list-group-item">
                    <h4 class="list-group-item-heading">Frameworks</h4>
                    <div style="text-align: justify" class="list-group-item-text">
                        <% for (Framework framework : frameworks) {%>
                        <p><a href="framework.jsp?nome=<%=framework.getNome()%>"><%=framework.getNome()%></a></p>
                            <%}%>
                    </div>
                </div> 
                <%}%>
                
            </div>
        </div>
    </body>
</html>
