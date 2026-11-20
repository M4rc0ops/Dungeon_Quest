package it.unicam.cs.mpgc.rpg129034;

public class GestorePiani {



    public Piano creaSecondoPiano() {
        return MappaPiano2.creaPiano();
    }



    public Piano creaTerzoPiano() {
        return MappaPiano3.creaPiano();
    }



    private void controllaValore(Eroe eroe){
        if(eroe == null){
            throw new IllegalArgumentException("l'eroe non può essere nullo");
        }
    }



    public Piano passaAlSecondoPiano(Eroe eroe){
        controllaValore(eroe);
        Piano nuovoPiano = creaSecondoPiano();
        eroe.aggiornaPosizione(5,41);
        return nuovoPiano;
    }



    public Piano passaAlTerzoPiano(Eroe eroe){
        controllaValore(eroe);
        Piano  nuovoPiano = creaTerzoPiano();
        eroe.aggiornaPosizione(4,32);
        return nuovoPiano;
    }


}
