/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.javaObj;

import java.util.Random;

/**
 *
 * @author mattiabirti
 */
public class Board {
    
    public Cell[][] board;
    
    /**
     * costruttore della board, 9*9 celle
     */
    public Board(){
        this.board = new Cell [9][9];
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                board[i][j] = new Cell();
            }
        }
        //board costruita, assegno le bombe e i valori
        this.setBombs();
        this.addNearBomb();
    }
    
    /**
     * aggiungo alla board le bomb
     */
    public void setBombs(){
        Random r = new Random();
        int i;
        for (i=0; i<10; i++){
            int riga = r.nextInt(9);    //assegno la bomba ad una cella a caso
            int col = r.nextInt(9);
            if(this.board[riga][col].isABomb){ //cella occupata già da una bomba
                i= i-1;
            } else { //cella libera -> assegno la bomba
                this.board[riga][col].isABomb = true;
            }
        }
    }
    
    /**
     * aggiungo i valori a tutte le celle calcolando il numero di bombe
     */
    public void addNearBomb(){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if(board[i][j].isABomb){    //guardo tutte le celle e ogni volta che vado sopra ad una cella bomba,
                    calculateNearBombs(i, j);      //aggiorno le celle vicine
                }
            }
        }
    }
    
    /**
     * aumento il numero di bombe delle celle vicine allla cella (riga, col)
     * non controllo se quella vicina sia una bomba perché non serve
     */
    public void calculateNearBombs(int riga, int col){
        if(riga-1 >= 0){
            this.board[riga-1][col].txtNearBomb++;  //alto
            if(col-1 >= 0){
                this.board[riga-1][col-1].txtNearBomb++;  //alto e sx
            }
            if(col+1 < 9){
                this.board[riga-1][col+1].txtNearBomb++;  //alto e dx
            }
        }
        if(col-1 >= 0){
            this.board[riga][col-1].txtNearBomb++;  //sx
        }
        if(col+1 < 9){
            this.board[riga][col+1].txtNearBomb++;  //dx
        }
        if(riga+1 < 9){
            this.board[riga+1][col].txtNearBomb++;  //basso
            if(col-1 >= 0){
                this.board[riga+1][col-1].txtNearBomb++;  //basso e sx
            }
            if(col+1 < 9){
                this.board[riga+1][col+1].txtNearBomb++;  //basso e dx
            }
        }
    }
    
    public boolean isABomb(int riga, int col){
        return this.board[riga][col].isBomb();
    }
    public int nearBomb(int riga, int col){
        return this.board[riga][col].getTxtNearBomb();
    }
    public boolean isClicked (int riga, int col){
        return this.board[riga][col].isClicked();
    }
    
    public void clickCell(int riga, int col){
        this.board[riga][col].setClicked();
    }
    
    /**
     * @return la cella in formato JSON
     */
    public String toSrtringJson(int riga, int col){
        String isBomb = null;
        if(this.board[riga][col].isABomb){
            isBomb = "true";
        } else {
            isBomb ="false";
        }
        
        String isClicked = null;
        if(this.board[riga][col].isClicked){
            isClicked = "true";
        } else {
            isClicked ="false";
        }
        
        int nearBomb = this.board[riga][col].getTxtNearBomb();
        
        String out = "{";
        out += "\"isABomb\": " + isBomb + ",";  //aggiungo alla string il testo se è o no una bomba
        out += "\"nearBomb\": " + nearBomb + ",";    //aggiungo il numero di bombe adiacenti
        out += "\"isClicked\": " + isClicked +"";
        out += "}";
        
        return out;
    }
            
            
}
