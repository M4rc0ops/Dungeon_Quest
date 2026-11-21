package it.unicam.cs.mpgc.rpg129034;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Stanza extends Ambiente{

    private final List<Nemico> nemici;
    private boolean completata;
    private final Map<Nemico, Posizione> posizioneNemici;



    //Costruttore. Assegna i valori ereditati dalla classe Ambiente
    public Stanza(String nome,int coordinataX, int coordinataY, int larghezza, int altezza){
        super(nome, coordinataX, coordinataY, larghezza, altezza);

        this.nemici = new ArrayList<>();
        this.posizioneNemici = new HashMap<>();
        this.completata = false;

    }



    //Getter
    public List<Nemico> getNemici() {
        return new ArrayList<>(nemici);
    }
    public boolean isCompletata() {
        return completata;
    }



    // Imposta lo stato di completamento della stanza.
    public void impostaCompletata(boolean completata){
        this.completata = completata;
    }



    // Aggiunge un nemico alla stanza e ne registra la posizione dopo averla verificata.
    public void aggiungiNemico(Nemico nemico, int x, int y){

        if(nemico == null){
            throw new IllegalArgumentException("il nemico non può essere nullo");
        }

        controllaPosizioneNemico(x,y);
        this.nemici.add(nemico);
        posizioneNemici.put(nemico, new Posizione(x,y));
    }



    // Verifica se tutti i nemici presenti nella stanza sono stati sconfitti.
    public boolean tuttiNemiciSconfitti(){
        for(Nemico nemico : this.nemici){
            if(!nemico.sconfitta()){
                return false;
            }
        }
        return true;
    }



    // Segna la stanza come completata se tutti i nemici sono stati sconfitti.
    public void completa(){
        if(tuttiNemiciSconfitti()){
            completata = true;
        }
    }



    // Controlla che le coordinate indicate appartengano all'interno della stanza.
    private void controllaPosizioneNemico(int x, int y){
        if(x < getCoordinataX() || x >= getCoordinataX() + getLarghezza() ||
                y < getCoordinataY() || y >= getCoordinataY() + getAltezza()){
            throw new IllegalArgumentException("la posizione del nemico deve essere interna alla stanza");
        }

    }



    // Restituisce una copia della posizione del nemico indicato, oppure null.
    public Posizione getPosizioneNemico(Nemico nemico){
        Posizione posizione = posizioneNemici.get(nemico);

        if(posizione == null){
            return null;
        }

        return new Posizione(posizione.getX(),posizione.getY()  );
    }


}
