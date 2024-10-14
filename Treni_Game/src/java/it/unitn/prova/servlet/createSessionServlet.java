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
public class createSessionServlet extends HttpServlet {

    Map<String, String> user;
    
    //se il campo nel ctx non esiste lo inizializzo a 2 posti
    @Override
    public void init(){
        ServletContext ctx = getServletContext();
        if (ctx.getAttribute("availablePlaces") == null){
            ctx.setAttribute("availablePlaces", "2");
            
            user = new HashMap();
            ctx.setAttribute("userOnline", user);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        /*
        controllo che ci siano posti disponibili
        */
        if(ctx.getAttribute("availablePlaces").equals("2") || ctx.getAttribute("availablePlaces").equals("1")){ //posti disponibili
            String username = request.getParameter("username");
            HttpSession session = request.getSession();
            user = new HashMap();
            user =(Map<String, String>) ctx.getAttribute("userOnline");
            ArrayList<String> valueList = new ArrayList<String>(user.values());
            /*
            controllo che la sessione non esista già
            */
            if(valueList.contains(session.getId())){    //significa che esiste gia la sessione
                ctx.setAttribute("loginErrorMessage", "Errore, sessione già esistente");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            } else {
                ArrayList<String> nameList = new ArrayList<String>(user.keySet());
                /*
                controllo che lo username non sia gia presente
                */
                if(nameList.contains(username)){
                    ctx.setAttribute("loginErrorMessage", "Username già presente; Utilizzarne uno nuovo");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                } else{
                    user.put(username, session.getId());        //salvo <username, sessione>

                    if(ctx.getAttribute("availablePlaces").equals("2")){ //assegno il turno al primo che entra
                        ctx.setAttribute("turno", session.getId());
                    }
                    int n = Integer.parseInt((String)ctx.getAttribute("availablePlaces"));
                     n = n-1;
                    String n1 = Integer.toString(n);
                    ctx.setAttribute("availablePlaces", n1);    //aggiorno availableplaces nel ctx
            
                    ctx.setAttribute("userOnline", user);
                    ctx.setAttribute("loginErrorMessage", null);
                    session.setAttribute("username", username);
                    session.setAttribute("coin", 100);
            
                    RequestDispatcher rd = request.getRequestDispatcher("game.jsp");
                    rd.forward(request, response);
                }
                
            }

        } else {    //nessun posto disponibile, riprovare più tardi
            ctx.setAttribute("loginErrorMessage", "Posti occupati, riprovare più tardi");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }



}
