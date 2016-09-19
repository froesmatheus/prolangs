<%-- 
    Document   : login
    Created on : Aug 26, 2016, 7:32:35 PM
    Author     : mathe
--%>

<%@page contentType="text/html" isErrorPage="true" session="true" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <c:header/>


        <form action="LoginController" method="post">
            <div class="container" style="width: 500px;">
                <div class="list-group">
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Login</h4>
                        <input name="login" class="form-control" required autofocus/>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Senha</h4>
                        <input name="password" type="password" class="form-control" required/>
                    </div>

                    <div class="list-group-item">
                        <input value="Logar" type="submit" class="btn btn-success"/>
                    </div>
                </div> 
            </div>  
        </form>
    </body>
</html>
