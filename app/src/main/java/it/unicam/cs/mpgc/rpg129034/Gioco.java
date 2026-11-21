package it.unicam.cs.mpgc.rpg129034;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Gioco extends Application{

    private Piano piano;
    private Eroe eroe;
    private GestoreCombattimento gestoreCombattimento;
    private final GestoreSchermate gestoreSchermate = new GestoreSchermate();
    private final GestoreSalvataggio gestoreSalvataggio = new GestoreSalvataggio();
    private final GestoreCaricamento  gestoreCaricamento = new GestoreCaricamento();


    // Avvia l'applicazione mostrando il menu principale del gioco.
    @Override
    public void start(Stage finestra){

        gestoreSchermate.mostraMenuPrincipale(finestra, () -> mostraMappa(finestra), () -> mostraSlotCaricamento(finestra));

    }



    // Inizializza il primo piano, l'eroe e il gestore del combattimento, quindi mostra le schermate introduttive.
    private void mostraMappa(Stage finestra) {
        piano = MappaPiano1.creaPiano();
        eroe = new Eroe("Eroe");

        gestoreCombattimento = new GestoreCombattimento(eroe);

        gestoreSchermate.mostraIntroduzione(finestra,
                () -> gestoreSchermate.mostraComandi(finestra, () -> mostraSchermataGioco(finestra)));

    }



    // Crea e mostra la schermata di gioco collegandola ai gestori e alle operazioni disponibili.
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



    // Crea l'azione da eseguire per salvare la partita nello slot indicato e gestire l'eventuale vittoria.
    private Runnable creaAzioneSalvataggio(Stage finestra, int slot, boolean vittoria){

        return () -> {
            boolean salvato = salvaPartita(slot);
            if (salvato && vittoria) {
                start(finestra);
            }
        };

    }



    // Mostra la finestra per scegliere lo slot in cui salvare la partita.
    private void mostraSlotSalvataggio(Stage finestra, boolean vittoria){

        gestoreSchermate.mostraSlotSalvataggio( creaAzioneSalvataggio(finestra, 1, vittoria),
                creaAzioneSalvataggio(finestra, 2, vittoria),
                creaAzioneSalvataggio(finestra, 3, vittoria) );

    }



    // Mostra la finestra per scegliere lo slot da cui caricare una partita.
    private void mostraSlotCaricamento(Stage finestra){

       gestoreSchermate.mostraSlotCaricamento( () -> caricaPartita(finestra,1),
               () -> caricaPartita(finestra,2),
               () -> caricaPartita(finestra, 3) );

    }



    // Carica la partita dallo slot indicato e inizializza nuovamente l'eroe e il gestore del combattimento.
    private void caricaPartita(Stage finestra, int slot){

        try{

            Eroe nuovoEroe = new Eroe("Eroe");
            Piano nuovoPiano = gestoreCaricamento.caricaPartita(nuovoEroe, slot);
            GestoreCombattimento nuovoGestore = new GestoreCombattimento(nuovoEroe);

            eroe = nuovoEroe;
            piano = nuovoPiano;
            gestoreCombattimento = nuovoGestore;

            mostraSchermataGioco(finestra);

        } catch(IOException e){
            gestoreSchermate.mostraErrore("Errore",
                    "Caricamento non riuscito",
                    "il salvataggio non esiste o non è valido");


        }
    }



    // Mostra il menu di pausa e collega le opzioni per salvare la partita o tornare al menu principale.
    private void mostraMenuPausa(Stage finestra){
        gestoreSchermate.mostraMenuPausa(
                finestra,
                () -> mostraSlotSalvataggio(finestra, false),
                () -> start(finestra)
        );
    }



    // Salva la partita nello slot indicato e mostra un messaggio in base all'esito dell'operazione.
    private boolean salvaPartita(int slot) {

        try{
            gestoreSalvataggio.salvaPartita(piano, eroe, slot);
            gestoreSchermate.mostraAvvisoSalvataggio(slot);

            return true;

        } catch (IOException e) {
            gestoreSchermate.mostraErrore("Errore",
                    "Salvataggio non riuscito",
                    "Errore durante il salvataggio");

            return false;
        }

    }



    // Avvia l'applicazione.
    public static void main(String[] args) {
        launch(args);
    }

}
