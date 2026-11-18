package it.unicam.cs.mpgc.rpg129034;

public class Porta {

    private boolean aperta;

    public Porta(){
        this.aperta = true;
    }

    public boolean isAperta(){
        return aperta;
    }

    public void apri(){
        aperta = true;
    }

}
