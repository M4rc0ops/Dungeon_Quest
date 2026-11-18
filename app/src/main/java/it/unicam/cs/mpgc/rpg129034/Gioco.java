package it.unicam.cs.mpgc.rpg129034;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.image.*;
import java.io.*;
import java.util.*;

public class Gioco extends Application{

    private Piano piano;
    private Eroe eroe;
    private Label informazioniCombattimento;
    private GestoreCombattimento gestoreCombattimento;
    private Stage finestraGioco;
    private GestoreSchermate gestoreSchermate = new GestoreSchermate();


    @Override
    public void start(Stage finestra){

        this.finestraGioco = finestra;

        Label testo = new Label("DUNGEON QUEST");
        testo.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: #4b3621");

        Button nuovaPartita = new Button("Nuova partita");
        Button caricaPartita = new Button("Carica partita");
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


        nuovaPartita.setPrefSize(240,48);
        caricaPartita.setPrefSize(240,48);
        esci.setPrefSize(240,48);

        nuovaPartita.setStyle(stileNormale);
        caricaPartita.setStyle(stileNormale);
        esci.setStyle(stileNormale);


        nuovaPartita.setOnMouseEntered(evento -> nuovaPartita.setStyle(stileHover));
        nuovaPartita.setOnMouseExited(evento -> nuovaPartita.setStyle(stileNormale));

        caricaPartita.setOnMouseEntered(evento -> caricaPartita.setStyle(stileHover));
        caricaPartita.setOnMouseExited(evento -> caricaPartita.setStyle(stileNormale));

        esci.setOnMouseEntered(evento -> esci.setStyle(stileHover));
        esci.setOnMouseExited(evento -> esci.setStyle(stileNormale));


        nuovaPartita.setOnAction(evento -> mostraMappa(finestra) );

        caricaPartita.setOnAction(evento -> mostraSlotCaricamento(finestra) );

        esci.setOnAction(evento -> finestra.close() );

        VBox contenitore = new VBox(12);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 30;");


        contenitore.getChildren().addAll(testo,nuovaPartita, caricaPartita, esci);

        Scene scena = new Scene(contenitore, 600, 400);

        finestra.setTitle("Dungeon");
        finestra.setScene(scena);
        finestra.show();
        finestra.centerOnScreen();
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
                () -> mostraSlotSalvataggio(true),
                () -> mostraSlotSalvataggio(false),
                nuovoPiano -> piano = nuovoPiano
        );

        schermata.mostraSchermataGioco();
    }


    private void mostraSlotSalvataggio(boolean vittoria){

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

        VBox contenitore = new VBox(15);
        Stage finestraSlot = new Stage();


        slot1.setOnAction(evento -> { salvaPartita(1);
        finestraSlot.close(); if(vittoria)start(finestraGioco); } );

        slot2.setOnAction(evento -> { salvaPartita(2);
            finestraSlot.close(); if(vittoria)start(finestraGioco); });

        slot3.setOnAction(evento -> { salvaPartita(3);
            finestraSlot.close(); if(vittoria)start(finestraGioco); });

        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        finestraSlot.setTitle("Salvataggi");
        finestraSlot.setScene(new Scene(contenitore, 300, 250));
        finestraSlot.show();

    }


    private void mostraSlotCaricamento(Stage finestra){

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

        slot1.setOnAction(evento -> { finestraSlot.close(); caricaPartita(finestra, 1); } );
        slot2.setOnAction(evento -> { finestraSlot.close(); caricaPartita(finestra, 2); } );
        slot3.setOnAction(evento -> { finestraSlot.close(); caricaPartita(finestra, 3); } );

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo, slot1, slot2, slot3);

        finestraSlot.setScene(new Scene(contenitore, 330, 280));
        finestraSlot.show();

    }


    private void caricaPartita(Stage finestra, int slot){
        File file = new File("salvataggio" + slot + ".dat");

        try(DataInputStream ingresso = new DataInputStream(new FileInputStream(file))){
            int numeroPiano = ingresso.readInt();
            int x = ingresso.readInt();
            int y = ingresso.readInt();
            int vitaEroe = ingresso.readInt();

            switch(numeroPiano){
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
                    throw new IOException("Piano non valido");
            }

            eroe = new Eroe("Eroe");
            eroe.getPosizione().sposta(x, y);
            eroe.impostaVita(vitaEroe);


            int numeroStanze = ingresso.readInt();

            for(int i = 0; i < numeroStanze; i++){
                String nomeStanza = ingresso.readUTF();
                boolean completata = ingresso.readBoolean();
                int numeroNemici = ingresso.readInt();

                boolean trovata = false;

                for(Ambiente ambiente : piano.getAmbienti()){

                    if(ambiente instanceof Stanza){
                        Stanza stanza = (Stanza) ambiente;

                        if(stanza.getNome().equals(nomeStanza)){
                            stanza.impostaCompletata(completata);
                            List<Nemico> nemici = stanza.getNemici();

                            if(nemici.size() != numeroNemici){
                                throw new IOException("numero nemici non valido");
                            }
                            for(Nemico nemico : nemici){
                                nemico.impostaVita(ingresso.readInt());
                            }
                            trovata = true;
                            break;
                        }
                    }
                }
                if(!trovata){
                    throw new IOException("stanza non trovata " + nomeStanza);
                }
            }

            gestoreCombattimento = new GestoreCombattimento(eroe);

            mostraSchermataGioco(finestra);
            informazioniCombattimento.setText("Partita Caricata");
        } catch(IOException e){
            Alert avviso = new Alert (Alert.AlertType.ERROR );

            avviso.setTitle("Errore");
            avviso.setHeaderText("Impossibile Caricare la Partita");
            avviso.setContentText("Il savataggio non esiste o non è valido");
            avviso.showAndWait();
        }
    }


    private void mostraMenuPausa(Stage finestra){
        gestoreSchermate.mostraMenuPausa(
                finestra,
                () -> mostraSlotSalvataggio(false),
                () -> start(finestra)
        );
    }


    private void salvaPartita(int slot) {
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

            informazioniCombattimento.setText("partita salvata nello slot " + slot);

        } catch (IOException e) {
            informazioniCombattimento.setText("Errore durante il salvataggio");
        }


    }



    public static void main(String[] args) {
        launch(args);
    }

}
