package it.unicam.cs.mpgc.rpg129034;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        titolo.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: gold;");

        Label descrizione = new Label("Benvenuto in Dungeon Quest! \n\n" +
                "Ti trovi all'interno di un dungeon misterioso, composto da tre piani.\n\n" +
                "Esplora le stanze, affronta i nemici per proseguire\ne raggiungere l'uscita!");

        descrizione.setWrapText(true);
        descrizione.setMaxWidth(550);
        descrizione.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-text-alignment: center;");

        Label istruzione = new Label("Premi INVIO per continuare");
        istruzione.setStyle("-fx-font-size: 16px; -fx-text-fill: gold");

        VBox contenitore = new VBox(25);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #111111; -fx-padding: 35;");
        contenitore.getChildren().addAll(titolo, descrizione, istruzione);

        Scene scena = new Scene(contenitore, 800, 600);

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
        titolo.setStyle("-fx-font-size: 28px; -fx-text-fill: gold; -fx-font-weight: bold;");

        Label comandi = new Label("WASD - Movimento \n\n" +
                "Z - attacco \n\n" +
                "ESC - pausa");

        comandi.setStyle("-fx-font-size: 18px; -fx-text-fill:white; -fx-text-alignment: center;");

        Label istruzione = new Label("Premi INVIO per iniziare");
        istruzione.setStyle("-fx-font-size: 16px; -fx-text-fill: gold");

        VBox contenitore = new VBox(25);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #111111; -fx-padding: 35;");
        contenitore.getChildren().addAll(titolo, comandi, istruzione);

        Scene scena = new Scene(contenitore, 800, 600);

        scena.setOnKeyPressed(evento -> {
            if (evento.getCode() == KeyCode.ENTER) {
                avviaGioco.run();
            }
        });

        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();
    }

    public void mostraMenuPausa(
            Stage finestra,
            Runnable salvaPartita,
            Runnable tornaAlMenu) {

        Label titolo = new Label("PAUSA");
        titolo.setStyle("-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;");

        Button salva = new Button("Salva partita");
        Button continua = new Button("Continua");
        Button esci = new Button("Esci");

        String stileNormale = "-fx-background-color: #f3e6cc;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";

        String stileHover = "-fx-background-color: #dfc49b;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";

        salva.setPrefSize(220, 42);
        continua.setPrefSize(220, 42);
        esci.setPrefSize(220, 42);

        salva.setStyle(stileNormale);
        continua.setStyle(stileNormale);
        esci.setStyle(stileNormale);

        salva.setOnMouseEntered(evento -> salva.setStyle(stileHover));
        salva.setOnMouseExited(evento -> salva.setStyle(stileNormale));

        continua.setOnMouseEntered(evento -> continua.setStyle(stileHover));
        continua.setOnMouseExited(evento -> continua.setStyle(stileNormale));

        esci.setOnMouseEntered(evento -> esci.setStyle(stileHover));
        esci.setOnMouseExited(evento -> esci.setStyle(stileNormale));

        salva.setOnAction(evento -> salvaPartita.run());

        continua.setOnAction(evento ->
                ((Stage) continua.getScene().getWindow()).close());

        esci.setOnAction(evento -> {
            ((Stage) esci.getScene().getWindow()).close();
            tornaAlMenu.run();
        });

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo, continua, salva, esci);

        Stage menuPausa = new Stage();
        menuPausa.initOwner(finestra);
        menuPausa.initModality(Modality.WINDOW_MODAL);
        menuPausa.setTitle("Menu di pausa");
        menuPausa.setScene(new Scene(contenitore, 300, 280));
        menuPausa.show();
    }


    public void mostraSlotSalvataggio(Runnable salvaSlot1, Runnable salvaSlot2, Runnable salvaSlot3) {

        Label titolo = new Label("SCEGLI LO SLOT");
        titolo.setStyle("-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;");

        Button slot1 = new Button("Slot 1");
        Button slot2 = new Button("Slot 2");
        Button slot3 = new Button("Slot 3");


        String stileNormale = "-fx-background-color: #f3e6cc;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";


        String stileHover = "-fx-background-color: #dfc49b;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";

        Button[] pulsanti = {slot1, slot2, slot3};

        for(Button pulsante : pulsanti){
            pulsante.setPrefSize(200,40);
            pulsante.setStyle(stileNormale);

            pulsante.setOnMouseEntered(evento -> pulsante.setStyle(stileHover) );
            pulsante.setOnMouseExited(evento -> pulsante.setStyle(stileNormale) );

        }

        Stage finestraSlot = new Stage();


        slot1.setOnAction(evento -> { salvaSlot1.run(); finestraSlot.close(); } );

        slot2.setOnAction(evento -> { salvaSlot2.run();finestraSlot.close(); } );

        slot3.setOnAction(evento -> { salvaSlot3.run(); finestraSlot.close(); } );

        VBox contenitore = new VBox(15);

        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        finestraSlot.setTitle("Salvataggi");
        finestraSlot.setScene(new Scene(contenitore, 300, 250));
        finestraSlot.show();

    }


    public void mostraSlotCaricamento(Runnable caricaSlot1,  Runnable caricaSlot2, Runnable caricaSlot3) {

        Label titolo = new Label("CARICARA PARTITA");
        titolo.setStyle("-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;" );

        Button slot1 = new Button("Slot 1");
        Button slot2 = new Button("Slot 2");
        Button slot3 = new Button("Slot 3");


        String stileNormale = "-fx-background-color: #f3e6cc;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";


        String stileHover = "-fx-background-color: #dfc49b;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";

        Button[] pulsanti = {slot1, slot2, slot3};

        for(Button pulsante : pulsanti){
            pulsante.setPrefSize(200,40);
            pulsante.setStyle(stileNormale);

            pulsante.setOnMouseEntered(evento -> pulsante.setStyle(stileHover) );
            pulsante.setOnMouseExited(evento -> pulsante.setStyle(stileNormale) );
        }

        Stage finestraSlot =  new Stage();
        finestraSlot.setTitle("Carica Partita");

        slot1.setOnAction(evento -> { finestraSlot.close(); caricaSlot1.run(); } );
        slot2.setOnAction(evento -> { finestraSlot.close(); caricaSlot2.run(); } );
        slot3.setOnAction(evento -> { finestraSlot.close(); caricaSlot3.run(); } );

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        finestraSlot.setScene(new Scene(contenitore, 330, 280));
        finestraSlot.show();

    }


    public void mostraMenuPrincipale(Stage finestra, Runnable nuovaPartita, Runnable caricaPartita){

        Label testo = new Label("DUNGEON QUEST");
        testo.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: #4b3621");

        Button pulsanteNuovaPartita = new Button("Nuova partita");
        Button pulsanteCaricaPartita = new Button("Carica partita");
        Button esci = new Button("Esci");

        String stileNormale = "-fx-background-color: #f3e6cc;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";


        String stileHover = "-fx-background-color: #dfc49b;" +
                "-fx-text-fill: #4b3621;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #8b6b45;" +
                "-fx-border-width: 2px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;";


        pulsanteNuovaPartita.setPrefSize(240,48);
        pulsanteCaricaPartita.setPrefSize(240,48);
        esci.setPrefSize(240,48);

        pulsanteNuovaPartita.setStyle(stileNormale);
        pulsanteCaricaPartita.setStyle(stileNormale);
        esci.setStyle(stileNormale);


        pulsanteNuovaPartita.setOnMouseEntered(evento -> pulsanteNuovaPartita.setStyle(stileHover));
        pulsanteNuovaPartita.setOnMouseExited(evento -> pulsanteNuovaPartita.setStyle(stileNormale));

        pulsanteCaricaPartita.setOnMouseEntered(evento -> pulsanteCaricaPartita.setStyle(stileHover));
        pulsanteCaricaPartita.setOnMouseExited(evento -> pulsanteCaricaPartita.setStyle(stileNormale));

        esci.setOnMouseEntered(evento -> esci.setStyle(stileHover));
        esci.setOnMouseExited(evento -> esci.setStyle(stileNormale));


        pulsanteNuovaPartita.setOnAction(evento -> nuovaPartita.run() );

        pulsanteCaricaPartita.setOnAction(evento -> caricaPartita.run() );

        esci.setOnAction(evento -> finestra.close() );

        VBox contenitore = new VBox(12);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 30;");


        contenitore.getChildren().addAll(testo,pulsanteNuovaPartita, pulsanteCaricaPartita, esci);

        Scene scena = new Scene(contenitore, 600, 400);

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
        avviso.showAndWait();
    }


    public void mostraErrore(String titolo, String intestazione, String messaggio){

        Alert avviso = new  Alert(Alert.AlertType.ERROR);
        avviso.setTitle(titolo);
        avviso.setHeaderText(intestazione);
        avviso.setContentText(messaggio);
        avviso.showAndWait();

    }


}