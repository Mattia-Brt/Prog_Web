/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mattiabirti
 */
public class invalidateSessionServlet extends HttpServlet {

    Map<String, String> user;
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
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();
        
        //elimina l'utente uscente dalla lista
        user = new HashMap();
        user =(Map<String, String>) ctx.getAttribute("userOnline");
        user.remove(session.getAttribute("username"));
        ctx.setAttribute("userOnline", user);
        
        //rendo disponibile il posto
        int n = Integer.parseInt((String)ctx.getAttribute("availablePlaces"));
        n = n+1;
        String n1 = Integer.toString(n);
        ctx.setAttribute("availablePlaces", n1);
        
        //assegno il turno all'altro giocatore se esiste
        //user è gia stato rimosso dalla Map
        if(ctx.getAttribute("availablePlaces").equals("1")){
            if(session.getId() == ctx.getAttribute("turno")){   //se il turno era di questo giocatore uscente allora lo assegno all'altro
                //ArrayList<String> keyList = new ArrayList<String>(user.keySet());
                ArrayList<String> valueList = new ArrayList<String>(user.values());
                String sessionID = valueList.get(0);
                ctx.setAttribute("turno", sessionID);
            } else {
            }
        } else{
            ctx.setAttribute("turno", null);
        }
        
        ctx.setAttribute("loginErrorMessage", "Arrivederci "+ session.getAttribute("username") + ", sei stato scollegato");
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
        
    }

}
