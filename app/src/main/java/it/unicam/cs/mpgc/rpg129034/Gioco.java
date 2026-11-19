package it.unicam.cs.mpgc.rpg129034;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Gioco extends Application{

    private Piano piano;
    private Eroe eroe;
    private GestoreCombattimento gestoreCombattimento;
    private GestoreSchermate gestoreSchermate = new GestoreSchermate();
    private GestoreSalvataggio gestoreSalvataggio = new GestoreSalvataggio();
    private GestoreCaricamento  gestoreCaricamento = new GestoreCaricamento();


    @Override
    public void start(Stage finestra){

        gestoreSchermate.mostraMenuPrincipale(finestra, () -> mostraMappa(finestra), () -> mostraSlotCaricamento(finestra));

    }


    private void mostraMappa(Stage finestra) {
        piano = MappaPiano1.creaPiano();
        eroe = new Eroe("Eroe");

        gestoreCombattimento = new GestoreCombattimento(eroe);

        gestoreSchermate.mostraIntroduzione(finestra,
                () -> gestoreSchermate.mostraComandi(finestra, () -> mostraSchermataGioco(finestra)));

    }

    private void mostraSchermataGioco(Stage finestra) {

        SchermataGioco schermata = new SchermataGioco(
                finestra,
                piano,
                eroe,
                gestoreCombattimento,
                () -> start(finestra),
                () -> mostraMenuPausa(finestra),
                () -> mostraSlotSalvataggio(finestra, true),
                () -> mostraSlotSalvataggio(finestra, false),
                nuovoPiano -> piano = nuovoPiano
        );

        schermata.mostraSchermataGioco();
    }


    private Runnable creaAzioneSalvataggio(Stage finestra, int slot, boolean vittoria){

        return () -> {salvaPartita(slot); if(vittoria)start(finestra); };

    }


    private void mostraSlotSalvataggio(Stage finestra, boolean vittoria){

        gestoreSchermate.mostraSlotSalvataggio( creaAzioneSalvataggio(finestra, 1, vittoria),
                creaAzioneSalvataggio(finestra, 2, vittoria),
                creaAzioneSalvataggio(finestra, 3, vittoria) );

    }


    private void mostraSlotCaricamento(Stage finestra){

       gestoreSchermate.mostraSlotCaricamento( () -> caricaPartita(finestra,1),
               () -> caricaPartita(finestra,2),
               () -> caricaPartita(finestra, 3) );

    }


    private void caricaPartita(Stage finestra, int slot){

        try{

            eroe = new Eroe("Eroe");
            piano = gestoreCaricamento.caricaPartita(eroe, slot);
            gestoreCombattimento = new GestoreCombattimento(eroe);
            mostraSchermataGioco(finestra);

        } catch(IOException e){
            gestoreSchermate.mostraErrore("Errore",
                    "Caricamento non riuscito",
                    "il salvataggio non esiste o non è valido");


        }
    }


    private void mostraMenuPausa(Stage finestra){
        gestoreSchermate.mostraMenuPausa(
                finestra,
                () -> mostraSlotSalvataggio(finestra, false),
                () -> start(finestra)
        );
    }


    private void salvaPartita(int slot) {

        try{
            gestoreSalvataggio.salvaPartita(piano, eroe, slot);
            gestoreSchermate.mostraAvvisoSalvataggio(slot);

        } catch (IOException e) {
            gestoreSchermate.mostraErrore("Errore",
                    "Salvataggio non riuscito",
                    "Errore durante il salvataggio");

        }


    }



    public static void main(String[] args) {
        launch(args);
    }

}
