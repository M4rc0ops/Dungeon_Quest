package it.unicam.cs.mpgc.rpg129034;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class GestoreCaricamento {

    public Piano caricaPartita(Eroe eroe, int slot) throws IOException{

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


            int x = ingresso.readInt();
            int y = ingresso.readInt();
            int vitaEroe = ingresso.readInt();


            eroe.aggiornaPosizione(x, y);
            eroe.impostaVita(vitaEroe);


            int numeroStanze = ingresso.readInt();

            for (int i = 0; i < numeroStanze; i++) {
                String nomeStanza = ingresso.readUTF();
                boolean completata = ingresso.readBoolean();
                int numeroNemici = ingresso.readInt();

                boolean trovata = false;

                for (Ambiente ambiente : piano.getAmbienti()) {

                    if (ambiente instanceof Stanza) {
                        Stanza stanza = (Stanza) ambiente;

                        if (stanza.getNome().equals(nomeStanza)) {
                            stanza.impostaCompletata(completata);
                            List<Nemico> nemici = stanza.getNemici();

                            if (nemici.size() != numeroNemici) {
                                throw new IOException("numero nemici non valido");
                            }
                            for (Nemico nemico : nemici) {
                                nemico.impostaVita(ingresso.readInt());
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
        }
        return piano;


    }


}
