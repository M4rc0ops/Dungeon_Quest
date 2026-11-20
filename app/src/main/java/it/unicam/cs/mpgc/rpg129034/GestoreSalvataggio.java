package it.unicam.cs.mpgc.rpg129034;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GestoreSalvataggio {



    public void salvaPartita(Piano piano, Eroe eroe, int slot) throws IOException {

        if(piano == null || eroe == null){
            throw new IllegalArgumentException("piano ed eroe non possono essere nulli");
        }

        if(slot < 1 || slot > 3){
            throw new IllegalArgumentException("lo slot deve essere compreso tra 1 e 3");
        }


        File file = new File("salvataggio" + slot + ".dat");
        List<Ambiente> ambienti = piano.getAmbienti();

        try (DataOutputStream uscita = new DataOutputStream(new FileOutputStream(file))) {
            Posizione posizione = eroe.getPosizione();

            uscita.writeInt(piano.getNumero());
            uscita.writeInt(posizione.getX());
            uscita.writeInt(posizione.getY());
            uscita.writeInt(eroe.getPuntiVita());

            int numeroStanze = 0;

            for (Ambiente ambiente : ambienti) {
                if (ambiente instanceof Stanza) {
                    numeroStanze++;
                }
            }

            uscita.writeInt(numeroStanze);

            for (Ambiente ambiente : ambienti) {
                if (ambiente instanceof Stanza) {
                    Stanza stanza = (Stanza) ambiente;
                    List<Nemico> nemici = stanza.getNemici();

                    uscita.writeUTF(stanza.getNome());
                    uscita.writeBoolean(stanza.isCompletata());
                    uscita.writeInt(nemici.size());

                    for (Nemico nemico : nemici) {
                        uscita.writeInt(nemico.getPuntiVita());
                    }
                }
            }

        }


    }


}
