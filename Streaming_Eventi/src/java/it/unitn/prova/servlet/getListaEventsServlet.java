package it.unitn.prova.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class getListaEventsServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");
        String user = request.getParameter("username");
        
        viewers = new HashMap();
        //scarico gli eventi e poi lo elimino e ricarico nel ctx la lista degli eventi ancora attivi
        viewers = (Map<String, List<String>>) ctx.getAttribute("viewList");
        
        if (viewers == null || viewers.size()== 0){
            out.print("NOEVENTS");
            return;
        } else {
            ArrayList<String> eventList = new ArrayList<String>(viewers.keySet()); //lista nomi eventi
            String res = "[ ";
            
            for (int i = 0 ; i < viewers.size(); i++){
                if (viewers.get(i) == null || viewers.get(i).size()==0){ //nessuno lo ha ancora visto
                    String nomeEvento = eventList.get(i).toString();
                    res += "{\"nomeEvento\" : \"" + nomeEvento + "\" }";
                        if (i < viewers.size()-1){
                            res += ", ";
                        }
                } else{
                    if (viewers.get(i).contains(user)){  //gia visto non lo restituisco

                    } else{ //non visto (almeno non da me)
                        String nomeEvento = eventList.get(i).toString();
                        res += "{\"nomeEvento\" : \"" + nomeEvento + "\" }";
                            if (i < viewers.size()-1){
                                res += ", ";
                            }
                    }
                }
                
            }
            res += "]";
            
            if(res == "[ ]"){   //gia visti tutti
                out.print("NONEW");
                return;
            }
            out.println(res);
            
        }
    }

}
