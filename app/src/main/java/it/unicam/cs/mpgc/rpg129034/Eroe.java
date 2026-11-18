package it.unicam.cs.mpgc.rpg129034;

public class Eroe extends Entita {

    private Posizione posizione;


    public Eroe(String nome){
        super(nome,50,8,5);
        this.posizione = new Posizione(25,41);
    }


    public  Posizione getPosizione(){
        return posizione;
    }


    private void controllaValori(int quantita){
        if(quantita<0){
            throw new IllegalArgumentException("il valore deve essere maggiore o uguale a zero");
        }
    }


    public void cura(int quantita){
        controllaValori(quantita);
        curaPuntiVita(getPuntiVita() + quantita);
    }


    public void recuperaVitaStanza(){
        int quantita = (getMaxVita() * 20 / 100);
        cura(quantita);
    }

}
