package it.unicam.cs.mpgc.rpg129034;

public class Eroe extends Entita {

    private Posizione posizione;



    //Costruttore. Assegna dei valori prestabiliti ereditati dalla classe Entita
    public Eroe(String nome){
        super(nome,50,8,5);
        this.posizione = new Posizione(25,41);
    }



    //Restituisce una copia della posizione attuale dell'eroe
    public  Posizione getPosizione(){
        return new Posizione(posizione.getX(),posizione.getY());
    }



    //Aggiorna la posizione dell'eroe, spostandolo verso le coordinate indicate
    public void aggiornaPosizione(int x, int y){
        posizione.sposta(x,y);
    }



    //Cura l'eroe della quantità indicata, controllando che non sia negativa
    public void cura(int quantita){
        if(quantita<0){
            throw new IllegalArgumentException("la quantita di cura non può essere negativa");
        }
        curaPuntiVita(getPuntiVita() + quantita);
    }



    //Ripristina il 20% dei punti vita massimi dell'eroe
    public void recuperaVitaStanza(){
        int quantita = (getMaxVita() * 20 / 100);
        cura(quantita);
    }

}
