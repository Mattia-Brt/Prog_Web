/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class invalidateEventServlet extends HttpServlet {

    Map<String, List<String>> messages;
    Map<String, List<String>> viewers;
    
    /*
    elimino l'evento dalla lista con i suoi messaggi
    elimino l'evento dalla lista con i suoi visitatori
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
        
        //scarico gli eventi e poi lo elimino e ricarico nel ctx la lista degli eventi ancora attivi
        messages = (Map<String, List<String>>) ctx.getAttribute("eventList");
        viewers = (Map<String, List<String>>) ctx.getAttribute("viewList");
        
        messages.remove(nomeEvento);
        viewers.remove(nomeEvento);
        ctx.setAttribute("eventList", messages);
        ctx.setAttribute("viewList", viewers);
        out.print("Event removed");
        
    }
}
