<%-- 
    Document   : game
    Created on : 4-mag-2022, 15.14.24
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game</title>
        <script>
            var refreshData = null;
            
            function FirstcheckTurno(){
                if( "<%= application.getAttribute("turno") %>" == "<%= session.getId() %>") {  //se è il mio turno
                    document.getElementById("turnoTXT").innerHTML = "È il tuo turno";
                }else{
                    passaIlTurno();
                }
            }
            
            function checkAuto(){
                refreshData = setInterval(getTurno, 2000);
            }
            
            function getTurno(){
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        res = oob_request.responseText;
                        if(res == "Your"){
                            //mio turno 
                            clearInterval(refreshData);
                            document.getElementById("turnoTXT").innerHTML = "È il tuo turno!"
                            document.getElementById("but").disabled = false;
                        }
                    }
                }
                oob_request.open("GET", "getTurno", true);
                oob_request.send();
            }
            
            function passaIlTurno(){
                document.getElementById("turnoTXT").innerHTML = "Attendi";
                document.getElementById("but").disabled = true;
                checkAuto();
            }
            
            function buyRandom(){
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        passaIlTurno(); //solo se è andato a buon fine
                    }
                }
                oob_request.open("GET", "buyLoco", true);
                oob_request.send();
            }
            
        </script>
    </head>
    <body style="background: yellow" onload="FirstcheckTurno()">
        <h2>Connected as <%=session.getAttribute("username")%> - session id : <%=session.getId()%> - <a href="invalidateSession">Logout</a></h2>
        

        <p style="font: italic"><span id="turnoTXT">  </span> </p>
        <p> Hai a disposizione <span id="nFiorini"> 100 </span> fiorini</p>
        <p> Il coinvoglio da comporre è il seguente:</p>
        <hr>
        <h2><span id="convoglioTXT"></span>Qui viene scritto il convoglio da creare</h2>
        <button id="but" onclick="buyRandom()">Acquista</button>
        <hr>
        <p>Il parco vetture in eccesso è il seguente: </p>
        <span id="vetture_eccesso"></span>
    </body>
</html>
