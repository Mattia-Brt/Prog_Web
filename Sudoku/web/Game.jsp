<%-- 
    Document   : Game
    Created on : 8-mag-2022, 17.16.18
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sudoku</title>
        <style>
            body{
                margin: 0;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
        <script>
            function creaSudoku(){
                //---------------- genero la tabella vuota --------------------
                let s1 = "<table>";
                for (var i = 0; i<9; i++){
                    s1 += "<tr>";
                    for (var j = 0; j<9; j++){
                        s1 += "<td>";
                        if ((i<3 || i>5)&&(j<3 || j>5) || ((i>=3 && i<=5) && (j>=3 && j<=5))){
                            //quadrati nelle diagonali rossi
                            s1 += "<input style=\"background-color: mistyrose\" id=\""+i+"-"+j+"\" type=\"number\" min=\"1\" max=\"9\" />"; //aggiungo dopo i valori
                        } else{
                            //quadrati blu
                            s1 += "<input style=\"background-color: aliceblue\" id=\""+i+"-"+j+"\" type=\"number\" min=\"1\" max=\"9\" />"; //aggiungo dopo i valori
                        }
                        s1 += "</td>";
                    }
                    s1 += "</tr>";
                }
                s1 += "</table>"
                document.getElementById("tableSudoku").innerHTML = s1;
                
                popolaCelleFisse();
            }
            
            function popolaCelleFisse(){
                //prendo dalla get di wlcome il numero di sudoku che ha selezionato 
                var nSudokuClicked = <%=Integer.parseInt(request.getParameter("index"))%>
                
                //------------------------ richiedo i valori fissi ----------------
                var oob_request = new XMLHttpRequest();
                //oob_request.responseType ="json";
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        res = oob_request.responseText; //res è un array con dentro i dati codificati in json
                        //console.log(res);
                        var risultato = JSON.parse(res);
                        
                        for (var x in risultato){
                            //aggiungo il valore fisso alle celle e le rendo unclickable
                            let row = risultato[x].riga;
                            let col = risultato[x].colonna;
                            document.getElementById(row+"-"+col).value = risultato[x].valore;
                            document.getElementById(row+"-"+col).disabled = true;
                            if ((row<3 || row>5)&&(col<3 || col>5) ||
                                    ((row>=3 && row<=5) && (col>=3 && col<=5))){
                                document.getElementById(row+"-"+col).style="color: #ff0033; background-color: mistyrose";
                            } else{
                                document.getElementById(row+"-"+col).style="color: #ff0033; background-color: aliceblue";
                            }
                        }
                    }
                }
                oob_request.open("GET", "getSudokuStart?index=" + nSudokuClicked, true);
                oob_request.send();
            }
            
            function chiediVerifica(){
                let rowSelected = document.getElementById("rowSelected").value;
                let colSelected = document.getElementById("colSelected").value;

                var oob_request = new XMLHttpRequest();
                //oob_request.responseType ="json";
                oob_request.onreadystatechange = function(){
                    if(this.readyState == 4 && this.status == 200){
                        res = oob_request.responseText;     //res è il numero corretto che va in quella cella
                        verificaNumero(res.charAt(0));      //prende solo il primo numero della string di ritorno (non so perché ma inseriva un altro carattere)
                    }
                }
                oob_request.open("GET", "getValue?rowSel=" + rowSelected + "&colSel=" + colSelected, true);
                oob_request.send();
            }
            
            function verificaNumero(ris){
                let rowSelected = document.getElementById("rowSelected").value;
                let colSelected = document.getElementById("colSelected").value;
                let numberInserted = document.getElementById((rowSelected-1) + "-" + (colSelected-1)).value;
                let numberCorrect = ris;
                
                if(numberInserted == numberCorrect){
                    document.getElementById((rowSelected-1)+"-"+(colSelected-1)).value = ris;
                    document.getElementById((rowSelected-1) +"-"+(colSelected-1)).disabled = true;
                    document.getElementById("message").innerHTML="il valore inserito nella cella è giusto";
                    document.getElementById("message").style = "color:green";
                } else{
                    document.getElementById((rowSelected-1)+"-"+(colSelected-1)).value = "";
                    document.getElementById("message").innerHTML="il valore inserito nella cella è sbagliato";
                    document.getElementById("message").style = "color:red";
                }
            }
        </script>
    </head>
    <body onload="creaSudoku()">
        <h1>Sudoku</h1>
        <br>
        <div id="tableSudoku"> XXX </div>
        <br>
        <form>
            riga: <input type="number" min="1" max="9" id="rowSelected">
            colonna: <input type="number" min="1" max="9" id="colSelected">
            <input type="button" value="Verifica" onclick="chiediVerifica()">
        </form>
        <p id="message"></p>
    </body>
</html>
