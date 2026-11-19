package it.unicam.cs.mpgc.rpg129034;

public class GestorePiani {

    public Piano creaSecondoPiano() {
        return MappaPiano2.creaPiano();
    }

    public Piano creaTerzoPiano() {
        return MappaPiano3.creaPiano();
    }


    public Piano passaAlSecondoPiano(Eroe eroe){
        Piano nuovoPiano = creaSecondoPiano();
        eroe.aggiornaPosizione(5,41);
        return nuovoPiano;
    }


    public Piano passaAlTerzoPiano(Eroe eroe){
        Piano  nuovoPiano = creaTerzoPiano();
        eroe.aggiornaPosizione(4,32);
        return nuovoPiano;
    }


}
