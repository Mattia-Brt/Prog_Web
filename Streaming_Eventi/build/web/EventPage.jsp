<%-- 
    Document   : EventPage
    Created on : 18-mag-2022, 13.48.27
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var refreshData = null;
            var event = null;
            
            function showMessages(){
                event = "<%=session.getAttribute("eventoSelect")%>";
                refreshData = setInterval(getMessages, 1000);
            }
            
            function getMessages(){
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.status == 200 && this.readyState == 4){
                        res = oob_request.responseText;
                        console.log(res);
                        if(res == "DELETE"){
                            document.getElementById("errorMessageEvent").innerHTML = "L'evento è terminato";
                            clearInterval(refreshData);
                        } else {
                            if(res == "NoMessage"){
                                document.getElementById("errorMessageEvent").innerHTML = "Non ci sono messaggi  al momento";
                            } else{
                                var risultato = JSON.parse(res);
                                var s = "";

                                for (var x in risultato){
                                    var mex = risultato[x].messaggio;
                                    s += "<p>" + mex + "</p>";
                                    s += "<hr>";
                                }
                                document.getElementById("errorMessageEvent").innerHTML = "";
                                document.getElementById("elencoMessaggi").innerHTML = s;
                            } 
                        }
                    }
                }
                oob_request.open("GET", "getMessages?event="+event, true);
                oob_request.send();
            }
            
            function cambiaRefreshTime(){
                clearInterval(refreshData); //prima stoppo l'altro automatismo
                
                var time = document.getElementById("time").value;
                refreshData = setInterval(getMessages, (time*1000));
            }
        </script>
    </head>
    <body onload="showMessages()">
        <h1 style="background-color: aquamarine"> Benvenuto <%=session.getAttribute("username")%>,
            stai guardando <span style="color: red"><%=session.getAttribute("eventoSelect") %></span> 
            <button onclick="window.location.replace('Dashboard.jsp')"> Indietro </button> </h1>
        
        <div style="background-color: gainsboro">
            <h5>Cambia il tempo di refresh della pagina</h5> <input type="number" min="1" max="10" id="time">
            <button onclick="cambiaRefreshTime()">Cambia refresh</button>
        </div>
        <div id="elencoMessaggi">
            
        </div>
        <p id="errorMessageEvent" style="color: red"></p>
    </body>
</html>
