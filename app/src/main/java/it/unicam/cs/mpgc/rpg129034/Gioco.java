package it.unicam.cs.mpgc.rpg129034;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.scene.input.KeyCode;
import javafx.scene.image.*;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class Gioco extends Application{

    private Piano piano;
    private Eroe eroe;
    private GridPane griglia;
    private ImageView rappresentazioneEroe;
    private Combattimento combattimento;
    private Stanza stanzaCorrente;
    private Nemico nemicoCorrente;
    private Label informazioniCombattimento;
    private boolean combattimentoAttivo;
    private Stage finestraGioco;
    private Ambiente ambienteCorrente;
    private String direzioneEroe = "GIU";
    private Label statisticheEroe;
    private Label statisticheNemico;
    private ImageView immagineEroePannello;
    private ImageView immagineNemicoPannello;
    private Label messaggioCura;
    private Label indicatorePiano;
    private HBox intestazionePiano;


    @Override
    public void start(Stage finestra){

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


    private void mostraMappa(Stage finestra){
        piano = MappaPiano1.creaPiano();
        eroe = new Eroe("Eroe");
        combattimento = new Combattimento();
        stanzaCorrente = null;
        ambienteCorrente = piano.trovaAmbiente(eroe.getPosizione().getX() ,  eroe.getPosizione().getY());
        nemicoCorrente = null;
        combattimentoAttivo = false;

        mostraIntroduzione(finestra);
    }


    private void mostraIntroduzione(Stage finestra){
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
        contenitore.getChildren().addAll(titolo,descrizione,istruzione);

        Scene scena = new Scene(contenitore, 800, 600);

        scena.setOnKeyPressed(evento -> {
            if(evento.getCode() == KeyCode.ENTER){
                mostraComandi(finestra);
            }
        });

        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();
    }


    private void mostraComandi(Stage finestra){
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
        contenitore.getChildren().addAll(titolo,comandi,istruzione);

        Scene scena = new Scene(contenitore, 800, 600);

        scena.setOnKeyPressed(evento -> {
            if(evento.getCode() == KeyCode.ENTER){
                mostraSchermataGioco(finestra);
            }
        });
        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();

    }


    private void mostraSchermataGioco(Stage finestra){
        finestraGioco = finestra;

        griglia = new GridPane();
        informazioniCombattimento = new Label();

        String testoPiano = "";
        if(piano.getNumero() == 1){
            testoPiano = "SEI AL PRIMO PIANO";
        }
        else if(piano.getNumero() == 2){
            testoPiano = "SEI AL SECONDO PIANO";
        }
        else if(piano.getNumero() == 3){
            testoPiano = "SEI AL TERZO PIANO";
        }

        indicatorePiano = new Label(testoPiano);
        indicatorePiano.setStyle("-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: gold;" +
                "-fx-background-color: #2b2118;" +
                "-fx-padding: 10px;" +
                "-fx-background-radius: 8px;" );

        indicatorePiano.setVisible(true);
        PauseTransition attesa = new PauseTransition(Duration.seconds(2));
        attesa.setOnFinished(evento -> { indicatorePiano.setVisible(false);
            intestazionePiano.setVisible(false); intestazionePiano.setManaged(false); });
        attesa.play();

        disegnaGriglia();

        griglia.setAlignment(Pos.CENTER);
        StackPane areaMappa = new StackPane(griglia);
        areaMappa.setStyle("-fx-background-color: black;");
        areaMappa.setPrefSize(760,520);

        ScrollPane scorrimento = new ScrollPane(areaMappa);
        scorrimento.setPannable(true);
        scorrimento.setFitToWidth(true);
        scorrimento.setFitToHeight(true);
        scorrimento.setStyle("-fx-background: black; -fx-background-color: black;");

        informazioniCombattimento.setText("esplora il dungeon");


        VBox pannelloInferiore = new VBox(5);
        pannelloInferiore.setAlignment(Pos.CENTER);
        pannelloInferiore.setPadding(new Insets(5));
        pannelloInferiore.setPrefHeight(175);
        pannelloInferiore.setStyle("-fx-background-color: #2b2118; -fx-border-color: #9b8060; -fx-border-width: 2;");

        HBox rigaPannello = new HBox(10);
        rigaPannello.setAlignment(Pos.CENTER);


        immagineEroePannello = new ImageView();
        immagineEroePannello.setFitWidth(100);
        immagineEroePannello.setFitHeight(120);
        immagineEroePannello.setPreserveRatio(true);

        String percorsoEroe = "/immagini/EROE_GIU.png";
        Image imamgineEroe = new Image(getClass().getResourceAsStream(percorsoEroe));
        immagineEroePannello.setImage(imamgineEroe);


        statisticheEroe = new Label();
        statisticheEroe.setText("EROE\n" + "Vita: " + eroe.getPuntiVita() + "/" + eroe.getMaxVita() + "\n" +
                "Attacco: " + eroe.getAttacco() + "\n" + "Difesa: " + eroe.getDifesa());

        statisticheEroe.setStyle("-fx-text-fill: white;  -fx-font-size: 15px;");

        messaggioCura = new Label("+10 PV!");
        messaggioCura.setStyle("-fx-text-fill: #55ff55;" + "-fx-font-size: 18px;" + "-fx-font-weight: bold;");
        messaggioCura.setVisible(false);
        messaggioCura.setManaged(false);


        Separator divisore = new Separator();
        divisore.setOrientation(Orientation.VERTICAL);
        divisore.setPrefHeight(110);
        divisore.setStyle("-fx-background-color: #9b8060");

        statisticheNemico = new Label();
        statisticheNemico.setStyle("-fx-text-fill: white;  -fx-font-size: 15px;");
        statisticheNemico.setText("Nessun nemico");

        immagineNemicoPannello = new ImageView();
        immagineNemicoPannello.setFitWidth(100);
        immagineNemicoPannello.setFitHeight(120);
        immagineNemicoPannello.setPreserveRatio(true);


        VBox statisticheEroeContenitore = new VBox(5);
        statisticheEroeContenitore.setAlignment(Pos.CENTER_LEFT);
        statisticheEroeContenitore.getChildren().addAll(messaggioCura, statisticheEroe);

        rigaPannello.getChildren().addAll(immagineEroePannello,statisticheEroeContenitore,divisore,statisticheNemico,immagineNemicoPannello);
        pannelloInferiore.getChildren().add(rigaPannello);

        Label istruzioniAttacco = new Label("Premi Z per Attaccare!");
        istruzioniAttacco.setStyle("-fx-text-fill: gold;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" );
        pannelloInferiore.getChildren().add(istruzioniAttacco);


        intestazionePiano = new HBox(indicatorePiano);
        intestazionePiano.setAlignment(Pos.CENTER);
        intestazionePiano.setPadding(new Insets(8));
        intestazionePiano.setStyle("-fx-background-color: #2b2118;");


        VBox contenitore = new VBox(5);
        contenitore.getChildren().addAll(intestazionePiano, scorrimento, pannelloInferiore);
        VBox.setVgrow(scorrimento, Priority.ALWAYS);


        Scene scena = new Scene(contenitore, 800, 600);
        contenitore.setFocusTraversable(true);

        scena.setOnKeyPressed(evento -> {
            KeyCode tasto = evento.getCode();

            switch (tasto){
                case ESCAPE:
                    mostraMenuPausa(finestra);
                    break;

                case W:
                    gestisciMovimento("W");
                    break;

                case S:
                    gestisciMovimento("S");
                    break;

                case A:
                    gestisciMovimento("A");
                    break;

                case D:
                    gestisciMovimento("D");
                    break;

                case Z:
                    eseguiTurnoCombattimento();
                    break;
            }

        } );

        finestra.setScene(scena);
        finestra.centerOnScreen();
        contenitore.requestFocus();
    }


    private void disegnaGriglia(){
        int dimensioneCella= 30;

        griglia.getChildren().clear();

        if(ambienteCorrente == null){
            return;
        }

        int origineX = ambienteCorrente.getCoordinataX();
        int origineY = ambienteCorrente.getCoordinataY();

        int larghezza = ambienteCorrente.getLarghezza();
        int altezza = ambienteCorrente.getAltezza();

        Image immaginePavimento = new Image("/immagini/PAVIMENTO.png");
        Image immagineMuro = new Image("/immagini/MURO.png");
        Image immaginePortaComune = new Image("/immagini/PORTA_COMUNE_FRONTALE.png");
        Image immaginePipistrello = new Image("/immagini/PIPISTRELLO.png");
        Image immagineScheletro = new Image("/immagini/SCHELETRO.png");
        Image immagineZombie = new Image("/immagini/ZOMBIE.png");
        Image immagineGoblin =  new Image("/immagini/GOBLIN.png");
        Image immagineScale = new Image("/immagini/SCALE.png");
        Image immaginePortaBoss = new Image("/immagini/PORTA_BOSS.png");

        for(int y = origineY; y < origineY + altezza; y++){
            for(int x = origineX; x < origineX + larghezza;x++){

                TipoCella tipo = piano.getTipoCella(x, y);
                Rectangle cella = new Rectangle(dimensioneCella, dimensioneCella);


                switch (tipo){
                    case MURO:
                        ImageView muro = new ImageView(immagineMuro);
                        muro.setFitWidth(dimensioneCella);
                        muro.setFitHeight(dimensioneCella);
                        muro.setSmooth(false);
                        griglia.add(muro, x - origineX, y - origineY);

                        break;

                    case PAVIMENTO:
                        ImageView pavimento = new ImageView(immaginePavimento);
                        pavimento.setFitHeight(dimensioneCella);
                        pavimento.setFitWidth(dimensioneCella);
                        pavimento.setSmooth(false);
                        griglia.add(pavimento, x - origineX, y - origineY);

                        break;

                    case PORTA_COMUNE:
                        ImageView portaComune = new ImageView(immaginePortaComune);
                        portaComune.setFitHeight(dimensioneCella);
                        portaComune.setFitWidth(dimensioneCella);
                        portaComune.setSmooth(false);

                        griglia.add(portaComune, x - origineX, y - origineY);

                        break;

                    case PORTA_BOSS:
                        ImageView portaBoss = new ImageView(immaginePortaBoss);
                        portaBoss.setFitHeight(dimensioneCella);
                        portaBoss.setFitWidth(dimensioneCella);
                        portaBoss.setSmooth(false);
                        portaBoss.setPreserveRatio(false);

                        griglia.add(portaBoss, x - origineX, y - origineY);

                        break;

                    case PORTA_SCALE:
                        ImageView portaScale = new ImageView(immaginePortaComune);
                        portaScale.setFitHeight(dimensioneCella);
                        portaScale.setFitWidth(dimensioneCella);
                        portaScale.setSmooth(false);

                        griglia.add(portaScale, x - origineX, y - origineY);

                        break;

                    case USCITA_PIANO:
                        ImageView scale = new ImageView(immagineScale);
                        scale.setFitHeight(dimensioneCella);
                        scale.setFitWidth(dimensioneCella);
                        scale.setSmooth(false);
                        scale.setPreserveRatio(false);

                        griglia.add(scale,  x - origineX, y - origineY);

                        break;

                    case VUOTO:
                        cella.setFill(Color.BLACK);
                        break;
                }

                if(tipo != TipoCella.PAVIMENTO && tipo != TipoCella.MURO &&
                        tipo !=  TipoCella.PORTA_COMUNE && tipo != TipoCella.PORTA_SCALE &&
                        tipo != TipoCella.USCITA_PIANO && tipo != TipoCella.PORTA_BOSS){
                    cella.setStroke(Color.GRAY);
                    griglia.add(cella, x - origineX, y - origineY);
                }

            }
        }

        if(ambienteCorrente instanceof  Stanza){
            Stanza stanzaVisualizzata =  (Stanza) ambienteCorrente;

            for(Nemico nemico : stanzaVisualizzata.getNemici()){
                if(nemico.sconfitta()){
                    continue;
                }

                Posizione posizione = stanzaVisualizzata.getPosizioneNemico(nemico);
                if(posizione == null){
                    return;
                }

                Image immagineNemico;
                String nomeNemico = nemico.getNome();

                if(nomeNemico.contains("Pipistrello")){
                    immagineNemico = immaginePipistrello;
                }
                else if(nomeNemico.contains("Scheletro")){
                    immagineNemico = immagineScheletro;
                }
                else if(nomeNemico.contains("Zombie")){
                    immagineNemico = immagineZombie;
                }
                else if(nomeNemico.contains("Goblin")){
                    immagineNemico = immagineGoblin;
                }
                else{
                    continue;
                }

                ImageView rappresentazioneNemico =  new ImageView(immagineNemico);
                rappresentazioneNemico.setFitHeight(dimensioneCella);
                rappresentazioneNemico.setFitWidth(dimensioneCella);
                rappresentazioneNemico.setSmooth(false);

                griglia.add(rappresentazioneNemico, posizione.getX() - origineX, posizione.getY() - origineY);

            }
        }


        String percorso = "/immagini/EROE_" + direzioneEroe + ".png";

        Image immagineEroe = new Image(getClass().getResourceAsStream(percorso));

        rappresentazioneEroe = new ImageView(immagineEroe);
        rappresentazioneEroe.setFitWidth(dimensioneCella);
        rappresentazioneEroe.setFitHeight(dimensioneCella);
        rappresentazioneEroe.setSmooth(false);

        griglia.add(rappresentazioneEroe, eroe.getPosizione().getX() - origineX, eroe.getPosizione().getY() - origineY);


    }


    private void passaAlSecondoPiano(){
        piano = MappaPiano2.creaPiano();
        eroe.getPosizione().sposta(5,41);

        mostraIndicatorePiano("SEI AL SECONDO PIANO");

        stanzaCorrente = null;
        nemicoCorrente = null;
        combattimentoAttivo = false;

        ambienteCorrente = piano.trovaAmbiente(eroe.getPosizione().getX(), eroe.getPosizione().getY());

        disegnaGriglia();

        informazioniCombattimento.setText("Sei arrivato al secondo piano");

    }


    private void passaAlTerzoPiano(){
        piano = MappaPiano3.creaPiano();
        eroe.getPosizione().sposta(4,32);

        mostraIndicatorePiano("SEI AL TERZO PIANO");

        stanzaCorrente = null;
        nemicoCorrente = null;
        combattimentoAttivo = false;

        ambienteCorrente = piano.trovaAmbiente(eroe.getPosizione().getX(), eroe.getPosizione().getY());

        disegnaGriglia();

        informazioniCombattimento.setText("Sei arrivato al terzo piano");
    }



    private void gestisciMovimento(String direzione){


        if(combattimentoAttivo){
            return;
        }

        int nuovaX = eroe.getPosizione().getX();
        int nuovaY = eroe.getPosizione().getY();

        switch (direzione){
            case "W":
                nuovaY--;
                direzioneEroe = "SU";
                break;

            case "S":
                nuovaY++;
                direzioneEroe = "GIU";
                break;

            case "A":
                nuovaX--;
                direzioneEroe = "SINISTRA";
                break;

            case "D":
                nuovaX++;
                direzioneEroe = "DESTRA";
                break;
        }

        if(rappresentazioneEroe != null){
            Image nuovaImmagine = new Image(getClass().getResourceAsStream("/immagini/EROE_" + direzioneEroe + ".png") );

            rappresentazioneEroe.setImage(nuovaImmagine);
        }



        if(nuovaX < 0 || nuovaX >= 45 ||  nuovaY < 0 || nuovaY >= 45){
            return;
        }


        TipoCella tipoDestinazione = piano.getTipoCella(nuovaX,nuovaY);


        Ambiente ambienteDestinazione = piano.trovaAmbiente(nuovaX,nuovaY);

        if((tipoDestinazione == TipoCella.PAVIMENTO ||
                tipoDestinazione == TipoCella.PORTA_COMUNE) &&
        ambienteDestinazione == null){
            return;
        }

        if(tipoDestinazione != TipoCella.PAVIMENTO && tipoDestinazione != TipoCella.PORTA_COMUNE &&
                tipoDestinazione!= TipoCella.USCITA_PIANO && tipoDestinazione != TipoCella.PORTA_BOSS &&
                tipoDestinazione != TipoCella.PORTA_SCALE ){
            return;
        }

        eroe.getPosizione().sposta(nuovaX,nuovaY);

        if(tipoDestinazione == TipoCella.USCITA_PIANO){

            if(piano.getNumero() == 1){
                passaAlSecondoPiano();
            }
            else if(piano.getNumero() == 2){
                passaAlTerzoPiano();
            }

            return;
        }

        Ambiente ambiente = ambienteDestinazione;

        if(ambiente != null){
            if(ambiente != ambienteCorrente){
                ambienteCorrente = ambiente;
                disegnaGriglia();
            }
            else{
                int origineX = ambienteCorrente.getCoordinataX();
                int origineY = ambienteCorrente.getCoordinataY();

                GridPane.setColumnIndex(rappresentazioneEroe, nuovaX - origineX);
                GridPane.setRowIndex(rappresentazioneEroe, nuovaY - origineY);
            }
        }


        if(piano.getNumero() == 3 && nuovaX >= 40 && nuovaX < 45
        && nuovaY >= 25 && nuovaY < 31){
            mostraSchermataVittoria();
            return;
        }


        if(ambiente instanceof Stanza){
            Stanza nuovaStanza = (Stanza) ambiente;

            if(nuovaStanza != stanzaCorrente){
                stanzaCorrente = nuovaStanza;

                if(!combattimentoAttivo){
                    avviaCombattimento(stanzaCorrente);
                }
            }
        }
        else{
            stanzaCorrente = null;
        }

    }


    private void avviaCombattimento(Stanza stanza){
        if(stanza == null || stanza.isCompletata() || stanza.getNemici().isEmpty()){
            return;
        }

        for(Nemico nemico : stanza.getNemici()){

            if(!nemico.sconfitta()){
                nemicoCorrente = nemico;
                combattimentoAttivo = true;
                aggiornaPannelloNemico();

                informazioniCombattimento.setText("Nemico: " + nemico.getNome() + " | Vita: " + nemico.getPuntiVita() + "\npremere Z per attaccare!");
                return;
            }
        }

    }


    private void eseguiTurnoCombattimento(){
        if(!combattimentoAttivo || nemicoCorrente == null){
            return;
        }

        combattimento.eseguiTurno(eroe, nemicoCorrente);
        aggiornaPannelloNemico();
        aggiornaPannelloEroe();

        if(eroe.sconfitta()){
            combattimentoAttivo = false;
            mostraSchermataSconfitta();
            return;
        }

        if(nemicoCorrente.sconfitta()){

            disegnaGriglia();

            nemicoCorrente = null;

            for(Nemico nemico : stanzaCorrente.getNemici()){
                if(!nemico.sconfitta()){
                    nemicoCorrente = nemico;
                    break;
                }
            }

            aggiornaPannelloNemico();


            if(nemicoCorrente == null){

                boolean stanzaCompletata = !stanzaCorrente.isCompletata() && stanzaCorrente.tuttiNemiciSconfitti();

                if(stanzaCompletata){
                    stanzaCorrente.completa();
                    eroe.recuperaVitaStanza();
                }

                combattimentoAttivo = false;

                aggiornaPannelloEroe();

                if(stanzaCompletata){
                    mostraMessaggioCura();
                }


                informazioniCombattimento.setText("Stanza Completata! Recupero di 10 punti vita" + "\nVita: " + eroe.getPuntiVita());
                return;
            }
        }

        informazioniCombattimento.setText("Nemico: " + nemicoCorrente.getNome() + " | Vita: " + nemicoCorrente.getPuntiVita() + "\nVita eroe: " + eroe.getPuntiVita() + "\npremere Z per attaccare!");

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

            combattimento = new Combattimento();
            stanzaCorrente = null;
            nemicoCorrente = null;
            combattimentoAttivo = false;

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
        Label titolo = new Label("PAUSA");
        titolo.setStyle("-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4b3621;" );


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

        salva.setPrefSize(220,42);
        continua.setPrefSize(220,42);
        esci.setPrefSize(220,42);

        salva.setStyle(stileNormale);
        continua.setStyle(stileNormale);
        esci.setStyle(stileNormale);

        salva.setOnMouseEntered(evento -> salva.setStyle(stileHover) );
        salva.setOnMouseExited(evento -> salva.setStyle(stileNormale) );

        continua.setOnMouseEntered(evento -> continua.setStyle(stileHover) );
        continua.setOnMouseExited(evento -> continua.setStyle(stileNormale) );

        esci.setOnMouseEntered(evento -> esci.setStyle(stileHover) );
        esci.setOnMouseExited(evento -> esci.setStyle(stileNormale) );



        salva.setOnAction(evento -> mostraSlotSalvataggio(false) );
        continua.setOnAction(evento -> ((Stage) continua.getScene().getWindow()).close() );
        esci.setOnAction(evento -> { ((Stage) esci.getScene().getWindow() ).close(); start(finestra); } );

        VBox contenitore = new VBox(15);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 25;");
        contenitore.getChildren().addAll(titolo,continua,salva,esci);

        Stage menuPausa = new Stage();
        menuPausa.initOwner(finestra);
        menuPausa.initModality(Modality.WINDOW_MODAL);
        menuPausa.setTitle("Menu di pausa");
        menuPausa.setScene(new Scene(contenitore,300,280));
        menuPausa.show();
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


    private void aggiornaPannelloNemico(){
        if(nemicoCorrente == null){
            statisticheNemico.setText("nessun nemico");
            immagineNemicoPannello.setImage(null);
            return;
        }

        statisticheNemico.setText(nemicoCorrente.getNome() + "\n" +
                "Vita: " + nemicoCorrente.getPuntiVita() + "/" + nemicoCorrente.getMaxVita() + "\n" +
                "Attacco: " + nemicoCorrente.getAttacco() + "\n" +
                "Difesa: " + nemicoCorrente.getDifesa());


        String percorso;

        if(nemicoCorrente.getNome().contains("Pipistrello")){
            percorso = "/immagini/PIPISTRELLO.png";
        }
        else if(nemicoCorrente.getNome().contains("Scheletro")){
            percorso = "/immagini/SCHELETRO.png";
        }
        else if(nemicoCorrente.getNome().contains("Zombie")){
            percorso = "/immagini/ZOMBIE.png";
        }
        else if (nemicoCorrente.getNome().contains("Goblin")){
            percorso = "/immagini/GOBLIN.png";
        }
        else{
            immagineNemicoPannello.setImage(null);
            return;
        }

        Image immagine = new Image(getClass().getResourceAsStream(percorso));
        immagineNemicoPannello.setImage(immagine);

    }


    private void aggiornaPannelloEroe(){
        statisticheEroe.setText("EROE \n" +
                "Vita: " + eroe.getPuntiVita() + "/" +  eroe.getMaxVita() + "\n" +
                "Attacco: " + eroe.getAttacco() + "\n" +
                "Difesa: " + eroe.getDifesa());
    }


    private void mostraMessaggioCura(){
        messaggioCura.setVisible(true);
        messaggioCura.setManaged(true);

        PauseTransition pausa = new PauseTransition(Duration.seconds(2));
        pausa.setOnFinished(evento -> { messaggioCura.setVisible(false); messaggioCura.setManaged(false); } );
        pausa.play();
    }


    private void mostraIndicatorePiano(String messaggio){
        indicatorePiano.setText(messaggio);

        intestazionePiano.setVisible(true);
        intestazionePiano.setManaged(true);
        indicatorePiano.setVisible(true);


        PauseTransition attesa = new PauseTransition(Duration.seconds(2));

        attesa.setOnFinished(evento ->{ intestazionePiano.setVisible(false); intestazionePiano.setManaged(false); } );

        attesa.play();
    }


    private void mostraSchermataVittoria(){
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


        salvaEdEsci.setOnAction(evento -> mostraSlotSalvataggio(true));

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 40;");

        contenitore.getChildren().addAll(messaggio,salvaEdEsci);

        Scene scena = new Scene(contenitore, 700, 350);

        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Vittoria!");
        finestraGioco.show();

    }


    private void mostraSchermataSconfitta(){
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

        esci.setOnAction(evento -> start(finestraGioco) );

        VBox contenitore =  new VBox(35);
        contenitore.setAlignment(Pos.CENTER);
        contenitore.setStyle("-fx-background-color: #e8dcc8; -fx-padding: 40;" );
        contenitore.getChildren().addAll(messaggio,esci);

        Scene scena = new Scene(contenitore, 700, 350);
        finestraGioco.setScene(scena);
        finestraGioco.setTitle("Sconfitta");
        finestraGioco.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

}
