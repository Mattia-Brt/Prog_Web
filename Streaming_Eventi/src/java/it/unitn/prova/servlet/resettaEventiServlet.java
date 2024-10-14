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
import java.util.List;
import java.util.Map;
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
public class resettaEventiServlet extends HttpServlet {
    
    Map<String, List<String>> viewers;

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
        
        //elimino questo nome utente da tutte le liste eventi
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        String user = (String) session.getAttribute("username");
        
        viewers = new HashMap();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        
        //scarico la lista degli utenti
        viewers = (Map<String, List<String>>) ctx.getAttribute("viewList");
        
        ArrayList<String> eventList = new ArrayList<String>(viewers.keySet()); //lista nomi eventi

        for (int i = 0 ; i < viewers.size(); i++){
            if (viewers.get(i) == null || viewers.get(i).size()==0){ //nessuno lo ha ancora visto

            } else{
                List<String> viewrList = viewers.get(i);

                if (viewrList.contains(user)){  //questo è quello che ho visto e devo togliere il nome
                    viewrList.remove(user);
                }
            }
        }
        out.print("Reset");
    }


}
