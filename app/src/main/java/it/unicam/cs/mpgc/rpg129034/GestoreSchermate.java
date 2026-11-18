package it.unicam.cs.mpgc.rpg129034;



import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.util.*;

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



}