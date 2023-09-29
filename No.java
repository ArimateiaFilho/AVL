/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author filho
 */
public class No {
    private int valor;
    private No esq,dir,pai;
    private int bal;
    public No getDir() {
        return dir;
    }
    public int getBal() {
        return bal;
    }
    public void setBal(int bal) {
        this.bal = bal;
    }
    public No getEsq() {
        return esq;
    }
    public No getPai() {
        return pai;
    }
    public int getValor() {
        return valor;
    }
    public void setDir(No dir) {
        this.dir = dir;
    }
    public void setEsq(No esq) {
        this.esq = esq;
    }
    public void setPai(No pai) {
        this.pai = pai;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
}
