/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unint.prova.servlet;

import it.unitn.prova.javaObj.Board;
import java.io.IOException;
import java.io.PrintWriter;
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
        HttpSession session = request.getSession();
        Board board =(Board) session.getAttribute("gioco");
        int r = Integer.parseInt(request.getParameter("riga"));
        int c = Integer.parseInt(request.getParameter("colonna"));
        
        response.setContentType("application/json;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print(board.toSrtringJson(r, c));   //riceve il Json gia codificato
        board.clickCell(r, c);
        
        session.setAttribute("gioco", board);
    }

}
