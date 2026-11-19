package it.unicam.cs.mpgc.rpg129034;

import java.util.*;

public class Stanza extends Ambiente{

    private List<Nemico> nemici;
    private boolean completata;
    private Map<Nemico, Posizione> posizioneNemici;


    public Stanza(String nome,int coordinataX, int coordinataY, int larghezza, int altezza){
        super(nome, coordinataX, coordinataY, larghezza, altezza);

        this.nemici = new ArrayList<>();
        this.posizioneNemici = new HashMap<>();
        this.completata = false;

    }



    public List<Nemico> getNemici() {
        return new ArrayList<>(nemici);
    }
    public boolean isCompletata() {
        return completata;
    }


    public void impostaCompletata(boolean completata){
        this.completata = completata;
    }



    public void aggiungiNemico(Nemico nemico, int x, int y){

        if(nemico == null){
            throw new IllegalArgumentException("il nemico non può essere nullo");
        }

        controllaPosizioneNemico(x,y);
        this.nemici.add(nemico);
        posizioneNemici.put(nemico, new Posizione(x,y));
    }



    public boolean tuttiNemiciSconfitti(){
        for(Nemico nemico : this.nemici){
            if(!nemico.sconfitta()){
                return false;
            }
        }
        return true;
    }



    public void completa(){
        if(tuttiNemiciSconfitti()){
            completata = true;
        }
    }



    private void controllaPosizioneNemico(int x, int y){
        if(x < getCoordinataX() || x >= getCoordinataX() + getLarghezza() ||
                y < getCoordinataY() || y >= getCoordinataY() + getAltezza()){
            throw new IllegalArgumentException("la posizione del nemico deve essere interna alla stanza");
        }

    }



    public Posizione getPosizioneNemico(Nemico nemico){
        Posizione posizione = posizioneNemici.get(nemico);

        if(posizione == null){
            return null;
        }

        return new Posizione(posizione.getX(),posizione.getY()  );
    }


}
