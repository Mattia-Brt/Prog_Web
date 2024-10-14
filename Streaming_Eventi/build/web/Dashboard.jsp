<%-- 
    Document   : Dashboard
    Created on : 17-mag-2022, 16.58.59
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var EventList = null;
            
            function popolaListaEventi(){
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.status == 200 && this.readyState == 4){
                        res = oob_request.responseText;
                        //console.log(res);
                        var s="";
                        if(res == "NOEVENTS"){
                            s = "<p style=\"color: red\">Non ci sono eventi disponibili</p>";
                            document.getElementById("ListaEventiDisponibili").innerHTML = s;
                        } else{
                            if (res == "NONEW"){
                                s = "<p style=\"color: red\">Hai visto tutti gli eventi</p>";
                            } else{
                                console.log(res);
                                var risultato = JSON.parse(res);
                                EventList = new Array();
                                
                                for (var x in risultato){
                                    var evento = risultato[x].nomeEvento;
                                    EventList[x]=evento;
                                    s += "<a href=\"#\" onclick=\"showEvento("+x+")\">";
                                    s += evento;
                                    s += "</a>";
                                    s += "<hr>";
                                }
                            }
                            s+= "<button onclick=\"resettaEventi()\"> Elimina eventi visti</button>";
                            document.getElementById("ListaEventiDisponibili").innerHTML = s;
                        }
                    }
                }
                oob_request.open("GET", "getListaEvents?user=<%=session.getAttribute("username")%>", true);
                oob_request.send();
            }
            
            function showEvento(ris){
                console.log("cliccato"+ris);
                console.log(EventList[ris]);
                
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.status == 200 && this.readyState == 4){
                        res = oob_request.responseText;
                        //console.log(res);
                        window.location.replace("EventPage.jsp");
                    }
                }
                oob_request.open("GET", "aggiornaSessionEvent?event="+EventList[ris], true);
                oob_request.send();                
            }
            
            function resettaEventi(){
                console.log("cliccato resetta eventi");
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.status == 200 && this.readyState == 4){
                        window.location.reload();
                    }
                }
                oob_request.open("GET", "resettaEventi", true);
                oob_request.send();
            }
            
        </script>
    </head>
    <body onload="popolaListaEventi()">
        <h1>Benvenuto <%=session.getAttribute("username")%></h1>
        
        <div id="ListaEventiDisponibili">

        </div>
    </body>
</html>
