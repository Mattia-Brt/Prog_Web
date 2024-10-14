/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unint.prova.servlet;

import it.unitn.prova.javaObj.Board;    //importo la board
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
public class getTableServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        HttpSession session = request.getSession();
        Board board;
        
        if(session.getAttribute("gioco") != null){
            board =(Board) session.getAttribute("gioco");
        } else {
            board = new Board();
            session.setAttribute("gioco", board);
        }
        
        //qui va scritta la tabella vuota in formato html
        //per semplificarmi la vita lo scrivo prima nel file html e poi lo riporto qui
        out.println("<table id=\"mineWeeperTable\">");
        for(int i = 0; i<9; i++){   //9 righe
            out.println("<tr>");
            for (int j = 0; j<9; j++){  //9 celle (colonne)
                String posCell = ""+ i + "" +j;     //salvo le coordinate della cella
                out.println("<th id=\"" +posCell+ "\" class=\"cell\" onClick=\"getValue("+i+","+j+")\">");
                out.println("</th>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.close();
        
        /*
        <table id="mineSweeperTable">
            <tr>
                <th id="cordinateCellla"></th>
            </tr>
        </table>
        */
    }
}
