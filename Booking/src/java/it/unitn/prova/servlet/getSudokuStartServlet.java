/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mattiabirti
 */
public class getSudokuStartServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int nSudokuClicked = Integer.parseInt(request.getParameter("index"));
        
        HttpSession session = request.getSession();
        session.setAttribute("nSudoku", nSudokuClicked);
        
        /**
         * connessione al DB ------------------------------------------------------
         */
            String url = "jdbc:derby://localhost:1527/ExamDerbyDB";
            String username = "WEBENGINE";
            String password = "WEBENGINE";

            // Risultati null se errore
            ResultSet results = null;

            // Connessione al db
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn = DriverManager.getConnection(url, username, password);
                String sqlQuery = "SELECT * FROM WEBENGINE.\"DATA\" WHERE ID=" + nSudokuClicked;
                Statement stmt = conn.createStatement();
                results = stmt.executeQuery(sqlQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        //-------------------------------------------------------------------------
        
        //ora scarico i file dal db
        String res = null;
        try(PrintWriter out = response.getWriter()){
            //salvo i dati (json) in un array
            res = "[ ";
            
            //se connessione avvenuta con successo
            if (results != null){
                while(results.next()){
                    String[] celleFisse = results.getString(2).split(" ");
                    String[] valoreCelle = results.getString(3).split(" ");

                    for (int i = 0; i<celleFisse.length; i++){
                        int row = Integer.parseInt(celleFisse[i].split("")[0]);
                        int col = Integer.parseInt(celleFisse[i].split("")[1]);
                        int value = Integer.parseInt(valoreCelle[((row-1)*9) + (col-1)]);
                        res += "{\"riga\" : " + (row-1) + ", \"colonna\" : "+ (col-1) + ", \"valore\" : "+ value +"}"; //dati in JSON
                        if(i < celleFisse.length-1){
                            res += ", ";
                        }
                    }
                }
            }
            res += " ]";
            out.println(res);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
