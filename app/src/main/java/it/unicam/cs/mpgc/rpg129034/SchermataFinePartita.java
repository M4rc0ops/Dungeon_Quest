package it.unicam.cs.mpgc.rpg129034;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SchermataFinePartita {

    private final Stage finestraGioco;
    private final Runnable tornaAlMenu;
    private final Runnable salvaEdEsci;



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

        messaggio.getStyleClass().add("titolo-sconfitta");

        Button esci = new Button("Esci");

        esci.setPrefSize(220,50);
        esci.getStyleClass().add("pulsante-menu");

        esci.setOnAction(evento -> tornaAlMenu.run() );

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-schermata-finale");
        contenitore.getChildren().addAll(messaggio,esci);

        Scene scena = new Scene(contenitore, 700, 350);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Sconfitta");
        finestraGioco.show();


    }



    public void mostraSchermataVittoria(){
        Label messaggio = new Label("Hai completato il Dungeon! Complimenti!");

        messaggio.getStyleClass().add("titolo-sconfitta");

        Button salvaEdEsci = new  Button("Salva ed Esci");

        salvaEdEsci.setPrefSize(220,50);
        salvaEdEsci.getStyleClass().add("pulsante-menu");


        salvaEdEsci.setOnAction(evento -> this.salvaEdEsci.run());

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.getStyleClass().add("contenitore-schermata-finale");

        contenitore.getChildren().addAll(messaggio,salvaEdEsci);

        Scene scena = new Scene(contenitore, 700, 350);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());

        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Vittoria!");
        finestraGioco.show();

    }







}
