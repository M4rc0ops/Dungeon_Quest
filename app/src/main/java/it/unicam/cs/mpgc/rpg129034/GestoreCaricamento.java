package it.unicam.cs.mpgc.rpg129034;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


public class GestoreCaricamento {



    public Piano caricaPartita(Eroe eroe, int slot) throws IOException{

        if(eroe == null){
            throw new IllegalArgumentException("l'eroe non può essere nullo");
        }

        if(slot < 1 || slot > 3){
            throw new IllegalArgumentException("lo slot deve essere compreso tra 1 e 3");
        }


        File file = new File("salvataggio" + slot + ".dat");
        Piano piano;


        try(DataInputStream ingresso = new DataInputStream(new FileInputStream(file))) {
            int numeroPiano = ingresso.readInt();


            switch (numeroPiano) {
                case 1:
                    piano = MappaPiano1.creaPiano();
                    break;

                case 2:
                    piano = MappaPiano2.creaPiano();
                    break;

                case 3:
                    piano = MappaPiano3.creaPiano();
                    break;

                default:
                    throw new IOException("piano non valido");
            }

            List<Ambiente> ambienti = piano.getAmbienti();



            int x = ingresso.readInt();
            int y = ingresso.readInt();
            int vitaEroe = ingresso.readInt();

            if(x < 0 || x >= piano.getDimensioneGriglia() || y < 0 || y >= piano.getDimensioneGriglia()){
                throw new IOException("posizione dell'eroe non valida");
            }

            TipoCella tipoCasella = piano.getTipoCella(x, y);

            if(tipoCasella != TipoCella.PAVIMENTO && tipoCasella != TipoCella.PORTA_COMUNE &&
            tipoCasella != TipoCella.USCITA_PIANO && tipoCasella != TipoCella.PORTA_SCALE &&
            tipoCasella != TipoCella.PORTA_BOSS){
                throw new IOException("la posizione salvata non è percorribile");
            }

            if((tipoCasella == TipoCella.PAVIMENTO || tipoCasella == TipoCella.PORTA_COMUNE) &&
                    piano.trovaAmbiente(x,y) == null){
                throw new IOException("la posizione salvata non appartiene ad un ambiente");
            }


            int numeroStanze = ingresso.readInt();

            long stanzeDisponibili = ambienti.stream().filter(ambiente -> ambiente instanceof Stanza).count();

            if(numeroStanze < 0  || numeroStanze != stanzeDisponibili){
                throw new IOException("numero stanza non valido");
            }

            Set<String> stanzeCaricate = new HashSet<>();

            for (int i = 0; i < numeroStanze; i++) {
                String nomeStanza = ingresso.readUTF();

                if(!stanzeCaricate.add(nomeStanza)){
                   throw new IOException("stanza duplicata nel salvataggio: " + nomeStanza);
                }

                boolean completata = ingresso.readBoolean();
                int numeroNemici = ingresso.readInt();

                boolean trovata = false;

                for (Ambiente ambiente : ambienti) {

                    if (ambiente instanceof Stanza) {
                        Stanza stanza = (Stanza) ambiente;

                        if (stanza.getNome().equals(nomeStanza)) {
                            stanza.impostaCompletata(completata);
                            List<Nemico> nemici = stanza.getNemici();

                            if (nemici.size() != numeroNemici) {
                                throw new IOException("numero nemici non valido");
                            }
                            for (Nemico nemico : nemici) {
                                int vitaNemico = ingresso.readInt();

                                try{
                                    nemico.impostaVita(vitaNemico);
                                }
                                catch (IllegalArgumentException e){
                                    throw new IOException("punti vita del nemico non validi",e);
                                }

                            }
                            trovata = true;
                            break;
                        }
                    }
                }
                if (!trovata) {
                    throw new IOException("stanza non trovata " + nomeStanza);
                }
            }


            try{
                eroe.impostaVita(vitaEroe);
            }
            catch (IllegalArgumentException e){
                throw new IOException("punti vita del eroe non validi",e);
            }
            eroe.aggiornaPosizione(x, y);

        }
        return piano;


    }


}
