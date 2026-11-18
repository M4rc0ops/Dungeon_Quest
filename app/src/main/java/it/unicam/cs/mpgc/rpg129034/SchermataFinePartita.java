package it.unicam.cs.mpgc.rpg129034;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class SchermataFinePartita {

    private Stage finestraGioco;
    private Runnable tornaAlMenu;
    private Runnable salvaEdEsci;
    private ImageView rappresentazioneEroe;



    public SchermataFinePartita(Stage finestraGioco, Runnable tornaAlMenu, Runnable salvaEdEsci) {
        if(finestraGioco == null || tornaAlMenu == null ||  salvaEdEsci == null){
            throw new IllegalArgumentException("i parametri non possono essere nulli");
        }
        this.finestraGioco = finestraGioco;
        this.tornaAlMenu = tornaAlMenu;
        this.salvaEdEsci = salvaEdEsci;
    }


    public void mostraSchermataSconfitta(){
        Label messaggio = new  Label("Sconfitta!");

        messaggio.setStyle("-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;" );

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

        esci.setPrefSize(220,50);
        esci.setStyle(stileNormale);

        esci.setOnMouseEntered(evento -> esci.setStyle(stileHover) );
        esci.setOnMouseExited(evento -> esci.setStyle(stileNormale) );

        esci.setOnAction(evento -> tornaAlMenu.run() );

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 40;" );
        contenitore.getChildren().addAll(messaggio,esci);

        Scene scena = new Scene(contenitore, 700, 350);
        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Sconfitta");
        finestraGioco.show();


    }


    public void mostraSchermataVittoria(){
        Label messaggio = new Label("Hai completato il Dungeon! Complimenti!");

        messaggio.setStyle("-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;");

        Button salvaEdEsci = new  Button("Salva ed Esci");

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

        salvaEdEsci.setPrefSize(220,50);
        salvaEdEsci.setStyle(stileNormale);

        salvaEdEsci.setOnMouseEntered(evento -> salvaEdEsci.setStyle(stileHover) );
        salvaEdEsci.setOnMouseExited(evento -> salvaEdEsci.setStyle(stileNormale) );


        salvaEdEsci.setOnAction(evento -> this.salvaEdEsci.run());

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 40;");

        contenitore.getChildren().addAll(messaggio,salvaEdEsci);

        Scene scena = new Scene(contenitore, 700, 350);

        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Vittoria!");
        finestraGioco.show();

    }







}
