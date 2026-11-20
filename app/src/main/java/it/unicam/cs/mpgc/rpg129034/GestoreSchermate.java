package it.unicam.cs.mpgc.rpg129034;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GestoreSchermate {


    public void mostraIntroduzione(Stage finestra, Runnable mostraComandi) {
        Label titolo = new Label("Dungeon Quest");
        titolo.getStyleClass().add("titolo-introduzione");

        Label descrizione = new Label("Benvenuto in Dungeon Quest! \n\n" +
                "Ti trovi all'interno di un dungeon misterioso, composto da tre piani.\n\n" +
                "Esplora le stanze, affronta i nemici per proseguire\ne raggiungere l'uscita!");

        descrizione.setWrapText(true);
        descrizione.setMaxWidth(550);
        descrizione.getStyleClass().add("descrizione-introduzione");

        Label istruzione = new Label("Premi INVIO per continuare");
        istruzione.getStyleClass().add("istruzione-introduzione");

        VBox contenitore = new VBox(25);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-scuro");

        contenitore.getChildren().addAll(titolo, descrizione, istruzione);

        Scene scena = new Scene(contenitore, 800, 600);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        scena.setOnKeyPressed(evento -> {
            if (evento.getCode() == KeyCode.ENTER) {
                mostraComandi.run();
            }
        });

        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();
    }



    public void mostraComandi(Stage finestra, Runnable avviaGioco) {
        Label titolo = new Label("Comandi di Gioco");
        titolo.getStyleClass().add("titolo-comandi");

        Label comandi = new Label("WASD - Movimento \n\n" +
                "Z - attacco \n\n" +
                "ESC - pausa");

        comandi.getStyleClass().add("testo-comandi");

        Label istruzione = new Label("Premi INVIO per iniziare");
        istruzione.getStyleClass().add("istruzione-introduzione");

        VBox contenitore = new VBox(25);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-scuro");
        contenitore.getChildren().addAll(titolo, comandi, istruzione);

        Scene scena = new Scene(contenitore, 800, 600);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        scena.setOnKeyPressed(evento -> {
            if (evento.getCode() == KeyCode.ENTER) {
                avviaGioco.run();
            }
        });

        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();
    }



    public void mostraMenuPausa(Stage finestra, Runnable salvaPartita, Runnable tornaAlMenu) {

        Label titolo = new Label("PAUSA");
        titolo.getStyleClass().add("titolo-menu");

        Button salva = new Button("Salva partita");
        Button continua = new Button("Continua");
        Button esci = new Button("Esci");


        salva.setPrefSize(220, 42);
        continua.setPrefSize(220, 42);
        esci.setPrefSize(220, 42);

        salva.getStyleClass().add("pulsante-menu");
        continua.getStyleClass().add("pulsante-menu");
        esci.getStyleClass().add("pulsante-menu");


        salva.setOnAction(evento -> salvaPartita.run());

        continua.setOnAction(evento -> ((Stage) continua.getScene().getWindow()).close() );

        esci.setOnAction(evento -> {( (Stage)esci.getScene().getWindow()).close(); tornaAlMenu.run(); } );

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-menu");
        contenitore.getChildren().addAll(titolo, continua, salva, esci);

        Stage menuPausa = new Stage();
        menuPausa.initOwner(finestra);
        menuPausa.initModality(Modality.WINDOW_MODAL);
        menuPausa.setTitle("Menu di pausa");

        Scene scena = new Scene(contenitore, 300, 280);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        menuPausa.setScene(scena);

        menuPausa.show();
    }



    public void mostraSlotSalvataggio(Runnable salvaSlot1, Runnable salvaSlot2, Runnable salvaSlot3) {

        Label titolo = new Label("SCEGLI LO SLOT");
        titolo.getStyleClass().add("titolo-slot");

        Button slot1 = new Button("Slot 1");
        Button slot2 = new Button("Slot 2");
        Button slot3 = new Button("Slot 3");

        Button[] pulsanti = {slot1, slot2, slot3};

        for(Button pulsante : pulsanti){
            pulsante.setPrefSize(200,40);
            pulsante.getStyleClass().add("pulsante-menu");
        }

        Stage finestraSlot = new Stage();


        slot1.setOnAction(evento -> { salvaSlot1.run(); finestraSlot.close(); } );

        slot2.setOnAction(evento -> { salvaSlot2.run();finestraSlot.close(); } );

        slot3.setOnAction(evento -> { salvaSlot3.run(); finestraSlot.close(); } );

        VBox contenitore = new VBox(15);

        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-menu");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        finestraSlot.setTitle("Salvataggi");
        Scene scena = new Scene(contenitore, 300, 250);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestraSlot.setScene(scena);
        finestraSlot.show();

    }



    public void mostraSlotCaricamento(Runnable caricaSlot1,  Runnable caricaSlot2, Runnable caricaSlot3) {

        Label titolo = new Label("CARICA PARTITA");
        titolo.getStyleClass().add("titolo-slot");

        Button slot1 = new Button("Slot 1");
        Button slot2 = new Button("Slot 2");
        Button slot3 = new Button("Slot 3");


        Button[] pulsanti = {slot1, slot2, slot3};

        for(Button pulsante : pulsanti){
            pulsante.setPrefSize(200,40);
            pulsante.getStyleClass().add("pulsante-menu");
        }

        Stage finestraSlot =  new Stage();
        finestraSlot.setTitle("Carica Partita");

        slot1.setOnAction(evento -> { finestraSlot.close(); caricaSlot1.run(); } );
        slot2.setOnAction(evento -> { finestraSlot.close(); caricaSlot2.run(); } );
        slot3.setOnAction(evento -> { finestraSlot.close(); caricaSlot3.run(); } );

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-menu");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        Scene scena = new Scene(contenitore, 330, 280);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestraSlot.setScene(scena);
        finestraSlot.show();

    }



    public void mostraMenuPrincipale(Stage finestra, Runnable nuovaPartita, Runnable caricaPartita){

        Label testo = new Label("DUNGEON QUEST");
        testo.getStyleClass().add("titolo-menu-principale");

        Button pulsanteNuovaPartita = new Button("Nuova partita");
        Button pulsanteCaricaPartita = new Button("Carica partita");
        Button esci = new Button("Esci");


        pulsanteNuovaPartita.setPrefSize(240,48);
        pulsanteCaricaPartita.setPrefSize(240,48);
        esci.setPrefSize(240,48);

        pulsanteCaricaPartita.getStyleClass().add("pulsante-menu");
        pulsanteNuovaPartita.getStyleClass().add("pulsante-menu");
        esci.getStyleClass().add("pulsante-menu");


        pulsanteNuovaPartita.setOnAction(evento -> nuovaPartita.run() );

        pulsanteCaricaPartita.setOnAction(evento -> caricaPartita.run() );

        esci.setOnAction(evento -> finestra.close() );

        VBox contenitore = new VBox(12);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-menu-principale");

        contenitore.getChildren().addAll(testo,pulsanteNuovaPartita, pulsanteCaricaPartita, esci);

        Scene scena = new Scene(contenitore, 600, 400);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestra.setTitle("Dungeon");
        finestra.setScene(scena);
        finestra.show();
        finestra.centerOnScreen();

    }



    public void mostraAvvisoSalvataggio(int slot){
        Alert avviso = new Alert(Alert.AlertType.INFORMATION);
        avviso.setTitle("Salvataggio");
        avviso.setHeaderText("Partita salvata");
        avviso.setContentText("Partita salvata nello slot " + slot);

        DialogPane pannello = avviso.getDialogPane();
        pannello.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        pannello.getStyleClass().add("contenitore-menu");

        avviso.showAndWait();
    }



    public void mostraErrore(String titolo, String intestazione, String messaggio){

        Label testoTitolo = new Label(intestazione);

        testoTitolo.getStyleClass().add("titolo-errore");

        Label testoMessaggio = new Label(messaggio);
        testoMessaggio.setWrapText(true);
        testoMessaggio.setMaxWidth(250);
        testoMessaggio.getStyleClass().add("messaggio-errore");

        Button conferma = new Button("Ok");


        conferma.setPrefSize(220,42);
        conferma.getStyleClass().add("pulsante-menu");

        Stage finestraErrore = new Stage();
        finestraErrore.setTitle(titolo);
        finestraErrore.initModality(Modality.APPLICATION_MODAL);

        conferma.setOnAction(evento -> finestraErrore.close());

        VBox contenitore = new VBox(20);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-menu");

        contenitore.getChildren().addAll(testoTitolo,testoMessaggio,conferma);

        Scene scena = new Scene(contenitore, 300, 280);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestraErrore.setScene(scena);
        finestraErrore.showAndWait();


    }


}