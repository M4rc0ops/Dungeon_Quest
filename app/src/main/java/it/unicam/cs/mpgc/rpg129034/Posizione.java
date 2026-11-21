package it.unicam.cs.mpgc.rpg129034;

public class Posizione {


    private int x;
    private int y;



    //Costruttore. Assegna i valori
    public Posizione(int x,int y){
        this.x=x;
        this.y=y;
    }



    //Getter
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



    // Aggiorna la posizione assegnando le nuove coordinate.
    public void sposta(int nuovaX, int nuovaY){
        this.x=nuovaX;
        this.y=nuovaY;
    }


}