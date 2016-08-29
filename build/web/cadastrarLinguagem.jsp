<%-- 
    Document   : cadastrarLinguagem
    Created on : Aug 26, 2016, 7:01:00 PM
    Author     : mathe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:header/>

        <div class="container">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Cadastrar Linguagem
                    </h1>

                </div>
            </div>
            <!-- /.row -->

            <form method="post" action="LinguagemController" enctype="multipart/form-data">
                <div class="list-group">
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Nome</h4>
                        <input name="nome" class="form-control" required/>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Ano de lançamento</h4>
                        <input name="ano_lancamento" type="number" min="1800" max="2016" class="form-control" required/>

                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Licença</h4>
                        <input name="licenca" class="form-control" required/>
                    </div>

                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Descrição</h4>
                        <textarea name="descricao" class="form-control" rows="10" required></textarea>
                    </div>
                    
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">Logo</h4>
                        <input type="file" accept="image/*" name="logo" />
                    </div>

                    <div class="list-group-item">
                        <input class="btn btn-primary" name="btn_cadastrar" type="submit" value="Cadastrar">
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
