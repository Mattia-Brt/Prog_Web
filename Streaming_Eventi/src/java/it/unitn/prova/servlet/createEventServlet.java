/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattiabirti
 */
public class createEventServlet extends HttpServlet {
    
    Map<String, List<String>> messages;
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
        
        ServletContext ctx = request.getServletContext();
        String nomeEvento = request.getParameter("eventName");
        
        messages = new HashMap();
        viewers = new HashMap();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        
        if(ctx.getAttribute("eventList") != null){  //scarica la lista eventi
            messages = (Map<String, List<String>>) ctx.getAttribute("eventList");
            viewers = (Map<String, List<String>>) ctx.getAttribute("viewList");
            
            if (messages.containsKey(nomeEvento)){        //evento gia esistente
                out.print("L'evento esiste yet");
                return;
            }
        }
        
        messages.put(nomeEvento, new ArrayList<String>());      //aggiungo il nuovo evento alla lista con i messaggi
        viewers.put(nomeEvento, new ArrayList<String>());       //aggiungo il nuovo evento all lista con i visitatori
        ctx.setAttribute("eventList", messages); //aggiungo il nuovo evento al context
        ctx.setAttribute("viewList", viewers);  //aggiungo la lista dei visitatori al ctx
        out.print("added");
    }
}
