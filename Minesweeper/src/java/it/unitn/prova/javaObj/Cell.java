/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.prova.javaObj;

/**
 *
 * @author mattiabirti
 */
public class Cell {
    
    int txtNearBomb = 0; // sarebbe il numero di bombe vicine
    boolean isABomb = false;
    boolean isClicked = false;
    
    public Cell(){    }
    
    public void setTxt(int s){
        this.txtNearBomb = s;
    }
    public void setisABomb(boolean b){
        this.isABomb = b;
    }
    public void setClicked(){
        this.isClicked = true;
    }
    
    public int getTxtNearBomb(){
        return this.txtNearBomb;
    }
    public boolean isBomb(){
        return this.isABomb;
    }
    public boolean isClicked(){
        return this.isClicked;
    }
}
