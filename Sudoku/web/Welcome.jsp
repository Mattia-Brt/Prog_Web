<%-- 
    Document   : Welcome
    Created on : 8-mag-2022, 15.51.58
    Author     : mattiabirti
--%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Sudoku</title>
        <% 
        // Stringhe di connessione
        String url = "jdbc:derby://localhost:1527/ExamDerbyDB";
        String username = "WEBENGINE";
        String password = "WEBENGINE";
    
        // Risultati null se errore
        ResultSet results = null;
    
        // Connessione al db
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection(url, username, password);
            String sqlQuery = "SELECT * FROM WEBENGINE.\"DATA\"";
            Statement stmt = conn.createStatement();
            results = stmt.executeQuery(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        %> 
    </head>
    <body>
        <h1>Welcome to sudoku Game new User!!</h1>
        <img class="text-center" width="50%" src="imgFolder/sudoku.jpg">
        <!-- qui va stampato l'elenco delle istanze del gioco in maniera dinamica chiedendolo al DB-->
        <hr>
        <br>
        <ul>
            <%
              //stampo la lista dei sudoku
              /*
              result è un array
              pos(1) -> numero sudoku 1, 2, 3
              pos(2) -> posizioni delle celle fisse
              pos(3) -> valori delle celle 11, 12, 13, 14....
              */
              try{
                  while(results.next()){
                      %>
                      <li>
                          <a href="Game.jsp?index=<%=results.getInt(1)%>"> Sudoku n: <%=results.getInt(1)%></a>
                      </li>
                      <%
                  }
              } catch (NullPointerException e){
                  %>
                  <p>Errore di connessione al DB</p> 
                  <%
              }
            %>
        </ul>
        
    </body>
</html>
