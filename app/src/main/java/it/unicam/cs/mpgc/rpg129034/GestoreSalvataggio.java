package it.unicam.cs.mpgc.rpg129034;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GestoreSalvataggio {

    public void salvaPartita(Piano piano, Eroe eroe, int slot) throws IOException {

        File file = new File("salvataggio" + slot + ".dat");

        try (DataOutputStream uscita = new DataOutputStream(new FileOutputStream(file))) {
            uscita.writeInt(piano.getNumero());
            uscita.writeInt(eroe.getPosizione().getX());
            uscita.writeInt(eroe.getPosizione().getY());
            uscita.writeInt(eroe.getPuntiVita());

            int numeroStanze = 0;

            for (Ambiente ambiente : piano.getAmbienti()) {
                if (ambiente instanceof Stanza) {
                    numeroStanze++;
                }
            }

            uscita.writeInt(numeroStanze);

            for (Ambiente ambiente : piano.getAmbienti()) {
                if (ambiente instanceof Stanza) {
                    Stanza stanza = (Stanza) ambiente;

                    uscita.writeUTF(stanza.getNome());
                    uscita.writeBoolean(stanza.isCompletata());
                    uscita.writeInt(stanza.getNemici().size());

                    for (Nemico nemico : stanza.getNemici()) {
                        uscita.writeInt(nemico.getPuntiVita());
                    }
                }
            }

        }


    }





}
