package it.unicam.cs.mpgc.rpg129034;

public class GestorePiani {



    // Crea e restituisce il secondo piano
    public Piano creaSecondoPiano() {
        return MappaPiano2.creaPiano();
    }



    // Crea e restituisce il terzo piano
    public Piano creaTerzoPiano() {
        return MappaPiano3.creaPiano();
    }



    // Verifica che l'eroe ricevuto non sia nullo.
    private void controllaValore(Eroe eroe){
        if(eroe == null){
            throw new IllegalArgumentException("l'eroe non può essere nullo");
        }
    }



    // Crea il secondo piano e aggiorna la posizione iniziale dell'eroe al suo interno.
    public Piano passaAlSecondoPiano(Eroe eroe){
        controllaValore(eroe);
        Piano nuovoPiano = creaSecondoPiano();
        eroe.aggiornaPosizione(5,41);
        return nuovoPiano;
    }



    // Crea il terzo piano e aggiorna la posizione iniziale dell'eroe al suo interno.
    public Piano passaAlTerzoPiano(Eroe eroe){
        controllaValore(eroe);
        Piano  nuovoPiano = creaTerzoPiano();
        eroe.aggiornaPosizione(4,32);
        return nuovoPiano;
    }


}
