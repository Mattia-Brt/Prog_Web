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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mattiabirti
 */
public class getValueServlet extends HttpServlet {


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
        
        //------------------- prendo i dati che mi servono -------------------
        HttpSession session = request.getSession();
        int nSudokuClicked = (Integer) session.getAttribute("nSudoku");
        
        int row = Integer.parseInt(request.getParameter("rowSel"));
        int col = Integer.parseInt(request.getParameter("colSel"));
        
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
            
        //------------------ scarico i dati e invio il valore della cella selezionata ---------------------
        try(PrintWriter out = response.getWriter()){
            //se connessione avvenuta con successo
            if (results != null){
                while(results.next()){
                    String[] valoreCelle = results.getString(3).split(" ");

                    int value =Integer.parseInt(valoreCelle[((row-1)*9) + (col-1)]);
                    out.println(value);
                }
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        
    }

}
