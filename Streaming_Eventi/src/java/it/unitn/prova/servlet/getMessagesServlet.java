package it.unitn.prova.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattiabirti
 */
public class getMessagesServlet extends HttpServlet {

    Map<String, List<String>> messages;
    
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
        String nomeEvento = request.getParameter("event");

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        
        messages = (Map<String, List<String>>) ctx.getAttribute("eventList");
        List<String> listMessage = messages.get(nomeEvento);
        Set listEvent = messages.keySet();
        if(!listEvent.contains(nomeEvento)){
            out.print("DELETE");
            return;
        }
        if(listMessage == null || listMessage.size()== 0){
            out.print("NoMessage");
        } else {
            String res = "[ ";

            for (int i = 0 ; i < listMessage.size(); i++){
                res += "{\"messaggio\" : \"" + listMessage.get(i) + "\" }";
                if (i < listMessage.size()-1){
                    res += ", ";
                }
            }
            res += "]";
            out.print(res);
        }        
    }
}
