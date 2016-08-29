<%@page import="db.ConnectionFactory"%>
<%@ page language="java" import="java.sql.*" %>

<html>

    <head><title>Aprendendo JSP</title>

    </head>

    <body>



        <%

            Connection conn = ConnectionFactory.getConnection();

            int limit = 5; // quantidade de resultados por p�gina                

            // obt�m a quantidade de registros
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT COUNT(*) AS c FROM linguagens");

            ResultSet rs = pstmt.executeQuery();

            rs.next();

            int total_rows = Integer.parseInt(rs.getString("c"));

            String pagina = request.getParameter("pagina"); // p�gina atual

            if (pagina == null) {

                pagina = "1";

            }

            int limitValue = (Integer.parseInt(pagina) * limit) - limit;

            PreparedStatement pstmt2 = conn.prepareStatement(
                    "SELECT * FROM linguagens LIMIT " + limitValue + ", " + limit);

            ResultSet rs2 = pstmt2.executeQuery();

            while (rs2.next()) {
                int id = rs2.getInt("id_linguagem");

                out.println("ID " + id + "</br>");

                String nome = rs2.getString("nome");

                out.println("Nome: " + nome + "</br>");
                
                
            }

            int anterior;

            if (Integer.parseInt(pagina) != 1) {

                anterior = Integer.parseInt(pagina) - 1;

                out.println("<a href=?pagina=" + anterior + ">" + limit + " Anteriores</a>");

            } else {
                out.println(limit + " Anteriores ");
            }

            int numOfPages = total_rows / limit;

            int i;

            for (i = 1; i <= numOfPages; i++) {

                if (i == Integer.parseInt(pagina)) {

                    out.println("<b>" + i + "</b> ");

                } else {

                    out.println("<a href=?pagina=" + i + ">" + i + "</a> ");

                }

            }

            if ((total_rows % limit) != 0) {

                if (i == Integer.parseInt(pagina)) {

                    out.println(i + " ");

                } else {

                    out.println("<a href=?pagina=" + i + ">" + i + "</a> ");

                }

            }

            int proxima;

            if ((total_rows - (limit * Integer.parseInt(pagina))) > 0) {

                proxima = Integer.parseInt(pagina) + 1;

                out.println("<a href=?pagina=" + proxima + ">Pr�ximos " + limit + "</a>");

            } else {
                out.println("Pr�ximos " + limit);
            }

        %>



    </body>

</html>