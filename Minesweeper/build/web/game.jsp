<%-- 
    Document   : game
    Created on : 5-mag-2022, 14.45.02
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MineSweeper Game</title>
        <style>
            table, th, td{
                border: 1px solid black;
                border-collapse: collapse;
                
            }
            th{
                text-align: center;
            }
            #mainTable{ 
                width: 500px;
                height: 500px;
            }
            .cell{
                width: 30px;
                height: 30px;
            }
            
            body{   /* solo per avere tutto il body al centro, fai copia/incolla */
                margin: 0;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
        <script>
            var RemainCells = 81-10; //81 celle -10 bombe
            function creaTable(){  //solo perché il prof vuole che la tabella venga crosturuita lato server
                //richiama la servlet che ritorna l'intera pagina scritta sotto forma di txt
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        res = oob_request.responseText;
                        document.getElementById("mainTable").innerHTML = res;
                    }
                }
                oob_request.open("GET", "getTable", true);
                oob_request.send();
            }
            
            function getValueButton(){  //richiamo solo getValue passando i parametri
                getValue(document.getElementById("rowSelected").value -1, document.getElementById("colSelected").value -1);
            }
            
            function getValue(riga, col){   //sia attraverso il button, sia cliccando la cella
                var oob_request = new XMLHttpRequest();
                oob_request.responseType ="json";
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        let isBomb = this.response.isABomb;
                        let nearBomb = this.response.nearBomb;
                        let isClicked = this.response.isClicked;
                        
                        if(isClicked){//se gia cliccata non fa nulla
                            
                        } else{
                            if(isBomb){
                                document.getElementById(""+riga+""+col).innerHTML = "B";
                                document.getElementById(""+riga+""+col).style = "color:red";
                                document.getElementById("is").innerHTML = "Hai Perso";
                                window.alert("Hai perso brutto coglione");
                                logOut();
                            } else {
                                document.getElementById(""+riga+""+col).innerHTML = nearBomb;
                                document.getElementById(""+riga+""+col).style = "color:green";
                                //document.getElementById(""+riga+""+col).style = "border-color: red";
                                document.getElementById("is").innerHTML = "Hai ottenuto: "+nearBomb;
                                checkWin();
                            }
                        }
                        
                    }
                }
                oob_request.open("GET", "getValue?riga="+riga+"&colonna="+col, true);
                oob_request.send();
                /*
                 * con il POST
                 * oob_request.open("POST", "getValue", true);
                oob_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                oob_request.send("riga=" + riga + ", colonna=" + col);
                 */
            }
            
            function checkWin(){
                RemainCells--;
                document.getElementById("mancanti").innerHTML = "Mancano: "+RemainCells;
                if (RemainCells == 0){//ha vinto
                    window.alert("Complimenti hai vinto brutto coglione");
                    logOut();
                }
            }
            
            function logOut(){
                var oob_request = new XMLHttpRequest();
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        window.location.replace("login.html");
                    }
                }
                oob_request.open("GET", "invalidateSession", true);
                oob_request.send();
            }
            
        </script>
    </head>
    <body onload="creaTable()">
        <h1>Hello <%=session.getAttribute("username")%> </h1>
        
        <span id="mainTable"> XXX </span>
        <hr>
        <form>
            Column: <select id="colSelected">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
            </select>
            Row: <select id="rowSelected">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
            </select>
            <input type="button" value="SCOPRI" onclick="getValueButton()">
        </form>
        <br>
        <p><span id="is"/></p>
        <p><span id="mancanti">Mancano: 71</span></p>
        <input type="button" value="Restart" onclick="logOut()">
    </body>
</html>
