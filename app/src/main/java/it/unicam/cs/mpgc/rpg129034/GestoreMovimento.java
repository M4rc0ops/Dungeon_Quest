package it.unicam.cs.mpgc.rpg129034;


public class GestoreMovimento {

    private Piano piano;
    private final Eroe eroe;



    private void controllaValore(Object obj){
        if(obj == null){
            throw new IllegalArgumentException("il valore passato non può essere nullo");
        }
    }



    public GestoreMovimento(Piano piano, Eroe eroe) {
        controllaValore(piano);
        controllaValore(eroe);

        this.piano = piano;
        this.eroe = eroe;

    }



    public void aggiornaPiano(Piano nuovoPiano){
        controllaValore(nuovoPiano);

        this.piano=nuovoPiano;
    }



    public Ambiente trovaAmbiente(Posizione posizione){
        controllaValore(posizione);

        return piano.trovaAmbiente(posizione.getX(), posizione.getY());
    }



    public Posizione calcolaNuovaPosizione(String direzione){
        controllaValore(direzione);

        Posizione posizioneAttuale = eroe.getPosizione();

        int nuovaX = posizioneAttuale.getX();
        int nuovaY = posizioneAttuale.getY();

        switch (direzione){
            case "W":
                nuovaY--;
                break;

            case "S":
                nuovaY++;
                break;

            case "A":
                nuovaX--;
                break;

            case "D":
                nuovaX++;
                break;

            default:
                throw new IllegalArgumentException("direzione non valida");

        }

        return new Posizione(nuovaX,nuovaY);

    }



    public String calcolaDirezioneEroe(String direzione){
        controllaValore(direzione);

        switch (direzione){
            case "W":
                return "SU";

            case "S":
                return "GIU";

            case "A":
                return "SINISTRA";

            case "D":
                return "DESTRA";

            default:
                throw new IllegalArgumentException("direzione non valida");
        }
    }



    public boolean posizioneDentroMappa(Posizione posizione){
        controllaValore(posizione);

        int x = posizione.getX();
        int y = posizione.getY();

        int dimensione = piano.getDimensioneGriglia();

        return x >= 0 && x < dimensione && y >= 0 && y < dimensione;
    }



    public boolean casellaAttraversabile(Posizione posizione){
        controllaValore(posizione);

        TipoCella tipo = piano.getTipoCella(posizione.getX(), posizione.getY());
        Ambiente ambiente = piano.trovaAmbiente(posizione.getX(), posizione.getY());

        if((tipo == TipoCella.PAVIMENTO || tipo == TipoCella.PORTA_COMUNE) && ambiente == null){
            return false;
        }

        return tipo == TipoCella.PAVIMENTO || tipo == TipoCella.PORTA_COMUNE ||
                tipo == TipoCella.USCITA_PIANO || tipo == TipoCella.PORTA_SCALE || tipo == TipoCella.PORTA_BOSS;


    }



    public boolean isUscitaPiano(Posizione posizione){
        controllaValore(posizione);

        return piano.getTipoCella(posizione.getX(), posizione.getY()) == TipoCella.USCITA_PIANO;
    }



    public boolean isCasellaVittoria(Posizione posizione){
        controllaValore(posizione);

        return piano.getNumero() == 3 && posizione.getX() >= 40 && posizione.getX() < 45 &&
                posizione.getY() >= 25 && posizione.getY() < 31;
    }



    public void spostaEroe(Posizione nuovaPosizione){
        controllaValore(nuovaPosizione);

        eroe.aggiornaPosizione(nuovaPosizione.getX(), nuovaPosizione.getY());
    }


}
