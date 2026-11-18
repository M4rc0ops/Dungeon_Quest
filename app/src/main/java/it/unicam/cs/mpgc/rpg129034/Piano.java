package it.unicam.cs.mpgc.rpg129034;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Piano {

    private int numero;
    private List<Ambiente> ambienti;
    private TipoCella[][] griglia;
    private static final int DIMENSIONE_GRIGLIA = 45;


    public  Piano(int numero){
        if(numero<1 || numero>3){
            throw new IllegalArgumentException("il numero del piano deve essere compreso tra 1 e 3");
        }

        this.numero = numero;
        this.ambienti = new ArrayList<>();
        this.griglia = new TipoCella[DIMENSIONE_GRIGLIA][DIMENSIONE_GRIGLIA];

        for(TipoCella[] riga: griglia){
            Arrays.fill(riga,TipoCella.MURO);
        }
    }



    public int getNumero() {
        return numero;
    }
    public List<Ambiente> getAmbienti() {
        return new ArrayList<>(ambienti);
    }



    private void controllaAmbiente(Ambiente ambiente){

        if(ambiente == null){
            throw new IllegalArgumentException("la stanza non può essere null");
        }

        if(ambiente.getCoordinataX() < 0 || ambiente.getCoordinataY() < 0
                || ambiente.getCoordinataX() + ambiente.getLarghezza() > DIMENSIONE_GRIGLIA ||
                ambiente.getCoordinataY() + ambiente.getAltezza() > DIMENSIONE_GRIGLIA){
            throw new IllegalArgumentException("l'ambiente supera i limiti della griglia");
        }

        if(ambienti.contains(ambiente)){
            throw new IllegalArgumentException("la stanza è gia presente");
        }

    }



    public void aggiungiAmbiente(Ambiente ambiente){

        controllaAmbiente(ambiente);

        for(Ambiente ambientePresente : ambienti){

            if(ambiente instanceof Corridoio && ambientePresente instanceof Corridoio){
                continue;
            }

            int larghezzaSovrapposizione = Math.min(ambiente.getCoordinataX() + ambiente.getLarghezza(),
                    ambientePresente.getCoordinataX() + ambientePresente.getLarghezza() )
                    - Math.max(ambiente.getCoordinataX(), ambientePresente.getCoordinataX());

            int altezzaSovrapposizione = Math.min(ambiente.getCoordinataY() + ambiente.getAltezza(),
                    ambientePresente.getCoordinataY() + ambientePresente.getAltezza() )
                    - Math.max(ambiente.getCoordinataY(), ambientePresente.getCoordinataY() );

            boolean sovrapposto = larghezzaSovrapposizione > 1 && altezzaSovrapposizione > 1;

            if(sovrapposto){
                throw new IllegalArgumentException("gli ambienti non possono essere sovrapposti");
            }
        }

        ambienti.add(ambiente);
    }



    public int getNumeroAmbienti(){
        return ambienti.size();
    }



    public boolean contieneAmbiente(Ambiente ambiente){
        return ambienti.contains(ambiente);
    }



    public Ambiente trovaAmbiente(int x, int y){
        for(Ambiente ambiente : ambienti){

            if(x >= ambiente.getCoordinataX() && x < ambiente.getCoordinataX() + ambiente.getLarghezza()
               && y >= ambiente.getCoordinataY() && y < ambiente.getCoordinataY() + ambiente.getAltezza()){
                return ambiente;
            }

        }
        return null;
    }



    private void controllaValoriXY(int x, int y){
        if(x<0 || x>= DIMENSIONE_GRIGLIA || y<0 || y>= DIMENSIONE_GRIGLIA){
            throw new IllegalArgumentException("coordinate non valide");
        }
    }



    public TipoCella getTipoCella(int x, int y){
        controllaValoriXY(x,y);
        return griglia[y][x];
    }



    public void impostaMappa(String[] mappa){
        if(mappa==null || mappa.length != DIMENSIONE_GRIGLIA){
            throw new IllegalArgumentException("La mappa deve avere 45 righe");
        }

        TipoCella[][] nuovaGriglia = new TipoCella[DIMENSIONE_GRIGLIA][DIMENSIONE_GRIGLIA];

        for(int riga = 0; riga< DIMENSIONE_GRIGLIA; riga++){
            if(mappa[riga]== null || mappa[riga].length() != DIMENSIONE_GRIGLIA){
                throw new IllegalArgumentException("ogni riga deve avere 45 caratteri");
            }
            for(int colonna = 0; colonna< DIMENSIONE_GRIGLIA; colonna++){
                char carattere = mappa[riga].charAt(colonna);

                switch(carattere){
                    case '#':
                        nuovaGriglia[riga][colonna] = TipoCella.MURO;
                        break;

                    case '_':
                        nuovaGriglia[riga][colonna] = TipoCella.PAVIMENTO;
                        break;

                    case 'p':
                        nuovaGriglia[riga][colonna] = TipoCella.PORTA_COMUNE;
                        break;

                    case 'P':
                        nuovaGriglia[riga][colonna] = TipoCella.PORTA_BOSS;
                        break;

                    case 's':
                        nuovaGriglia[riga][colonna] = TipoCella.PORTA_SCALE;
                        break;

                    case 'u':
                        nuovaGriglia[riga][colonna] = TipoCella.USCITA_PIANO;
                        break;

                    case ' ':
                        nuovaGriglia[riga][colonna] = TipoCella.VUOTO;
                        break;

                    default:
                        throw new IllegalArgumentException(carattere+" non è valido");
                }
            }
        }
        this.griglia = nuovaGriglia;
    }



}
