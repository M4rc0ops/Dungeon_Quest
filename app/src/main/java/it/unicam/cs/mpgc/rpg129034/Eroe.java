package it.unicam.cs.mpgc.rpg129034;

public class Eroe extends Entita {

    private Posizione posizione;



    public Eroe(String nome){
        super(nome,50,8,5);
        this.posizione = new Posizione(25,41);
    }



    public  Posizione getPosizione(){
        return new Posizione(posizione.getX(),posizione.getY());
    }



    public void aggiornaPosizione(int x, int y){
        posizione.sposta(x,y);
    }



    public void cura(int quantita){
        if(quantita<0){
            throw new IllegalArgumentException("la quantita di cura non può essere negativa");
        }
        curaPuntiVita(getPuntiVita() + quantita);
    }



    public void recuperaVitaStanza(){
        int quantita = (getMaxVita() * 20 / 100);
        cura(quantita);
    }

}
