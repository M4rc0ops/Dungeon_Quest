package it.unicam.cs.mpgc.rpg129034;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.function.Consumer;

public class SchermataGioco {

    private Piano piano;
    private Eroe eroe;
    private GestoreCombattimento gestoreCombattimento;
    private Ambiente ambienteCorrente;
    private Stanza stanzaCorrente;
    private Nemico nemicoCorrente;
    private String direzioneEroe = "GIU";

    private GridPane griglia;
    private ImageView rappresentazioneEroe;
    private Label informazioniCombattimento;
    private Label statisticheEroe;
    private Label statisticheNemico;
    private Label indicatorePiano;
    private Label messaggioCura;
    private ImageView immagineEroePannello;
    private ImageView immagineNemicoPannello;
    private HBox intestazionePiano;

    private Stage finestraGioco;
    private Runnable tornaAlMenu;
    private Runnable mostraMenuPausa;
    private Runnable salvaPerVittoria;
    private Runnable salvaPerSconfitta;
    private Consumer<Piano> aggiornaPianoInGioco;
    private GestorePiani gestorePiani;

    private VisualizzatoreMappa visualizzatoreMappa;

    public SchermataGioco(
            Stage finestraGioco,
            Piano piano,
            Eroe eroe,
            GestoreCombattimento gestoreCombattimento,
            Runnable tornaAlMenu,
            Runnable mostraMenuPausa,
            Runnable salvaPerVittoria,
            Runnable salvaPerSconfitta,
            Consumer<Piano> aggiornaPianoInGioco) {

        if (finestraGioco == null || piano == null || eroe == null ||
                gestoreCombattimento == null || tornaAlMenu == null ||
                mostraMenuPausa == null || salvaPerVittoria == null ||
                salvaPerSconfitta == null || aggiornaPianoInGioco == null) {

            throw new IllegalArgumentException(
                    "I parametri non possono essere null");
        }

        this.finestraGioco = finestraGioco;
        this.piano = piano;
        this.eroe = eroe;
        this.gestoreCombattimento = gestoreCombattimento;
        this.tornaAlMenu = tornaAlMenu;
        this.mostraMenuPausa = mostraMenuPausa;
        this.salvaPerVittoria = salvaPerVittoria;
        this.salvaPerSconfitta = salvaPerSconfitta;
        this.aggiornaPianoInGioco = aggiornaPianoInGioco;

        this.ambienteCorrente = piano.trovaAmbiente(
                eroe.getPosizione().getX(),
                eroe.getPosizione().getY()
        );

        this.stanzaCorrente = null;
        this.nemicoCorrente = null;
        this.visualizzatoreMappa = new  VisualizzatoreMappa();
        this.gestorePiani = new GestorePiani();
    }

    public void mostraSchermataGioco() {

        griglia = new GridPane();
        informazioniCombattimento = new Label();

        String testoPiano = "";

        if (piano.getNumero() == 1) {
            testoPiano = "SEI AL PRIMO PIANO";
        }
        else if (piano.getNumero() == 2) {
            testoPiano = "SEI AL SECONDO PIANO";
        }
        else if (piano.getNumero() == 3) {
            testoPiano = "SEI AL TERZO PIANO";
        }

        indicatorePiano = new Label(testoPiano);

        indicatorePiano.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: gold;" +
                        "-fx-background-color: #2b2118;" +
                        "-fx-padding: 10px;" +
                        "-fx-background-radius: 8px;"
        );

        indicatorePiano.setVisible(true);

        disegnaGriglia();

        griglia.setAlignment(Pos.CENTER);

        StackPane areaMappa = new StackPane(griglia);
        areaMappa.setStyle("-fx-background-color: black;");
        areaMappa.setPrefSize(760, 520);

        ScrollPane scorrimento = new ScrollPane(areaMappa);
        scorrimento.setPannable(true);
        scorrimento.setFitToWidth(true);
        scorrimento.setFitToHeight(true);
        scorrimento.setStyle(
                "-fx-background: black; -fx-background-color: black;"
        );

        informazioniCombattimento.setText("esplora il dungeon");

        VBox pannelloInferiore = new VBox(5);
        pannelloInferiore.setAlignment(Pos.CENTER);
        pannelloInferiore.setPadding(new Insets(5));
        pannelloInferiore.setPrefHeight(175);
        pannelloInferiore.setStyle(
                "-fx-background-color: #2b2118;" +
                        "-fx-border-color: #9b8060;" +
                        "-fx-border-width: 2;"
        );

        HBox rigaPannello = new HBox(10);
        rigaPannello.setAlignment(Pos.CENTER);

        immagineEroePannello = new ImageView();
        immagineEroePannello.setFitWidth(100);
        immagineEroePannello.setFitHeight(120);
        immagineEroePannello.setPreserveRatio(true);

        String percorsoEroe = "/immagini/EROE_GIU.png";
        Image immagineEroe = new Image(
                getClass().getResourceAsStream(percorsoEroe)
        );

        immagineEroePannello.setImage(immagineEroe);

        statisticheEroe = new Label();

        statisticheEroe.setText(
                "EROE\n" +
                        "Vita: " + eroe.getPuntiVita() + "/" +
                        eroe.getMaxVita() + "\n" +
                        "Attacco: " + eroe.getAttacco() + "\n" +
                        "Difesa: " + eroe.getDifesa()
        );

        statisticheEroe.setStyle(
                "-fx-text-fill: white; -fx-font-size: 15px;"
        );

        messaggioCura = new Label("+10 PV!");
        messaggioCura.setStyle(
                "-fx-text-fill: #55ff55;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        messaggioCura.setVisible(false);
        messaggioCura.setManaged(false);

        Separator divisore = new Separator();
        divisore.setOrientation(Orientation.VERTICAL);
        divisore.setPrefHeight(110);
        divisore.setStyle("-fx-background-color: #9b8060");

        statisticheNemico = new Label();
        statisticheNemico.setStyle(
                "-fx-text-fill: white; -fx-font-size: 15px;"
        );
        statisticheNemico.setText("Nessun nemico");

        immagineNemicoPannello = new ImageView();
        immagineNemicoPannello.setFitWidth(100);
        immagineNemicoPannello.setFitHeight(120);
        immagineNemicoPannello.setPreserveRatio(true);

        VBox statisticheEroeContenitore = new VBox(5);
        statisticheEroeContenitore.setAlignment(Pos.CENTER_LEFT);

        statisticheEroeContenitore.getChildren().addAll(
                messaggioCura,
                statisticheEroe
        );

        rigaPannello.getChildren().addAll(
                immagineEroePannello,
                statisticheEroeContenitore,
                divisore,
                statisticheNemico,
                immagineNemicoPannello
        );

        pannelloInferiore.getChildren().add(rigaPannello);

        Label istruzioniAttacco = new Label("Premi Z per Attaccare!");
        istruzioniAttacco.setStyle(
                "-fx-text-fill: gold;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );

        pannelloInferiore.getChildren().add(istruzioniAttacco);

        intestazionePiano = new HBox(indicatorePiano);
        intestazionePiano.setAlignment(Pos.CENTER);
        intestazionePiano.setPadding(new Insets(8));
        intestazionePiano.setStyle(
                "-fx-background-color: #2b2118;"
        );

        VBox contenitore = new VBox(5);

        contenitore.getChildren().addAll(
                intestazionePiano,
                scorrimento,
                pannelloInferiore
        );

        VBox.setVgrow(scorrimento, Priority.ALWAYS);

        Scene scena = new Scene(contenitore, 800, 600);
        contenitore.setFocusTraversable(true);

        scena.setOnKeyPressed(evento -> {

            KeyCode tasto = evento.getCode();

            switch (tasto) {

                case ESCAPE:
                    mostraMenuPausa.run();
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
        });

        finestraGioco.setScene(scena);
        finestraGioco.centerOnScreen();
        contenitore.requestFocus();

        PauseTransition attesa =
                new PauseTransition(Duration.seconds(2));

        attesa.setOnFinished(evento -> {
            indicatorePiano.setVisible(false);
            intestazionePiano.setVisible(false);
            intestazionePiano.setManaged(false);
        });

        attesa.play();
    }

    private void disegnaGriglia() {
        rappresentazioneEroe = visualizzatoreMappa.disegnaGriglia(
                griglia,
                piano,
                ambienteCorrente,
                eroe,
                direzioneEroe
        );
    }

    private void gestisciMovimento(String direzione) {

        if (gestoreCombattimento.isCombattimentoAttivo()) {
            return;
        }

        int nuovaX = eroe.getPosizione().getX();
        int nuovaY = eroe.getPosizione().getY();

        switch (direzione) {

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

        if (rappresentazioneEroe != null) {

            Image nuovaImmagine = new Image(
                    getClass().getResourceAsStream(
                            "/immagini/EROE_" + direzioneEroe + ".png"
                    )
            );

            rappresentazioneEroe.setImage(nuovaImmagine);
        }

        if (nuovaX < 0 || nuovaX >= 45 ||
                nuovaY < 0 || nuovaY >= 45) {
            return;
        }

        TipoCella tipoDestinazione =
                piano.getTipoCella(nuovaX, nuovaY);

        Ambiente ambienteDestinazione =
                piano.trovaAmbiente(nuovaX, nuovaY);

        if ((tipoDestinazione == TipoCella.PAVIMENTO ||
                tipoDestinazione == TipoCella.PORTA_COMUNE) &&
                ambienteDestinazione == null) {
            return;
        }

        if (tipoDestinazione != TipoCella.PAVIMENTO &&
                tipoDestinazione != TipoCella.PORTA_COMUNE &&
                tipoDestinazione != TipoCella.USCITA_PIANO &&
                tipoDestinazione != TipoCella.PORTA_BOSS &&
                tipoDestinazione != TipoCella.PORTA_SCALE) {
            return;
        }

        eroe.getPosizione().sposta(nuovaX, nuovaY);

        if (tipoDestinazione == TipoCella.USCITA_PIANO) {

            if (piano.getNumero() == 1) {
                passaAlSecondoPiano();
            }
            else if (piano.getNumero() == 2) {
                passaAlTerzoPiano();
            }

            return;
        }

        Ambiente ambiente = ambienteDestinazione;

        if (ambiente != null) {

            if (ambiente != ambienteCorrente) {

                ambienteCorrente = ambiente;
                disegnaGriglia();
            }
            else {

                int origineX =
                        ambienteCorrente.getCoordinataX();

                int origineY =
                        ambienteCorrente.getCoordinataY();

                GridPane.setColumnIndex(
                        rappresentazioneEroe,
                        nuovaX - origineX
                );

                GridPane.setRowIndex(
                        rappresentazioneEroe,
                        nuovaY - origineY
                );
            }
        }

        if (piano.getNumero() == 3 &&
                nuovaX >= 40 && nuovaX < 45 &&
                nuovaY >= 25 && nuovaY < 31) {

            SchermataFinePartita schermataFine =
                    new SchermataFinePartita(
                            finestraGioco,
                            tornaAlMenu,
                            salvaPerVittoria
                    );

            schermataFine.mostraSchermataVittoria();
            return;
        }

        if (ambiente instanceof Stanza) {

            Stanza nuovaStanza = (Stanza) ambiente;

            if (nuovaStanza != stanzaCorrente) {

                stanzaCorrente = nuovaStanza;

                nemicoCorrente =
                        gestoreCombattimento.avviaCombattimento(
                                stanzaCorrente
                        );

                if (nemicoCorrente != null) {

                    aggiornaPannelloNemico();

                    informazioniCombattimento.setText(
                            "Nemico: " + nemicoCorrente.getNome() +
                                    " | Vita: " + nemicoCorrente.getPuntiVita() +
                                    "\npremere Z per attaccare!"
                    );
                }
            }
        }
        else {
            stanzaCorrente = null;
        }
    }

    private void passaAlSecondoPiano() {

        piano = gestorePiani.creaSecondoPiano();

        aggiornaPianoInGioco.accept(piano);

        eroe.getPosizione().sposta(5, 41);

        mostraIndicatorePiano("SEI AL SECONDO PIANO");

        stanzaCorrente = null;
        nemicoCorrente = null;

        ambienteCorrente = piano.trovaAmbiente(
                eroe.getPosizione().getX(),
                eroe.getPosizione().getY()
        );

        disegnaGriglia();

        informazioniCombattimento.setText(
                "Sei arrivato al secondo piano"
        );
    }

    private void passaAlTerzoPiano() {

        piano = gestorePiani.creaTerzoPiano();

        aggiornaPianoInGioco.accept(piano);

        eroe.getPosizione().sposta(4, 32);

        mostraIndicatorePiano("SEI AL TERZO PIANO");

        stanzaCorrente = null;
        nemicoCorrente = null;

        ambienteCorrente = piano.trovaAmbiente(
                eroe.getPosizione().getX(),
                eroe.getPosizione().getY()
        );

        disegnaGriglia();

        informazioniCombattimento.setText(
                "Sei arrivato al terzo piano"
        );
    }

    private void mostraIndicatorePiano(String messaggio) {

        indicatorePiano.setText(messaggio);

        intestazionePiano.setVisible(true);
        intestazionePiano.setManaged(true);
        indicatorePiano.setVisible(true);

        PauseTransition attesa =
                new PauseTransition(Duration.seconds(2));

        attesa.setOnFinished(evento -> {
            intestazionePiano.setVisible(false);
            intestazionePiano.setManaged(false);
        });

        attesa.play();
    }

    private void eseguiTurnoCombattimento() {

        EsitoCombattimento esito =
                gestoreCombattimento.eseguiTurnoCombattimento();

        nemicoCorrente =
                gestoreCombattimento.getNemicoCorrente();

        aggiornaPannelloNemico();
        aggiornaPannelloEroe();

        switch (esito) {

            case EROE_SCONFITTO:

                SchermataFinePartita schermataFine =
                        new SchermataFinePartita(
                                finestraGioco,
                                tornaAlMenu,
                                salvaPerSconfitta
                        );

                schermataFine.mostraSchermataSconfitta();
                return;

            case NEMICO_SCONFITTO:
                disegnaGriglia();
                break;

            case STANZA_COMPLETATA:

                disegnaGriglia();

                if (stanzaCorrente.tuttiNemiciSconfitti()) {
                    mostraMessaggioCura();
                }

                informazioniCombattimento.setText(
                        "Stanza Completata! Recupero di 10 punti vita" +
                                "\nVita: " + eroe.getPuntiVita()
                );

                return;

            case IN_CORSO:
                break;
        }

        informazioniCombattimento.setText(
                "Nemico: " + nemicoCorrente.getNome() +
                        " | Vita: " + nemicoCorrente.getPuntiVita() +
                        "\nVita eroe: " + eroe.getPuntiVita() +
                        "\npremere Z per attaccare!"
        );
    }

    private void aggiornaPannelloNemico() {

        if (nemicoCorrente == null) {

            statisticheNemico.setText("nessun nemico");
            immagineNemicoPannello.setImage(null);
            return;
        }

        statisticheNemico.setText(
                nemicoCorrente.getNome() + "\n" +
                        "Vita: " + nemicoCorrente.getPuntiVita() +
                        "/" + nemicoCorrente.getMaxVita() + "\n" +
                        "Attacco: " + nemicoCorrente.getAttacco() + "\n" +
                        "Difesa: " + nemicoCorrente.getDifesa()
        );

        String percorso;

        if (nemicoCorrente.getNome().contains("Pipistrello")) {
            percorso = "/immagini/PIPISTRELLO.png";
        }
        else if (nemicoCorrente.getNome().contains("Scheletro")) {
            percorso = "/immagini/SCHELETRO.png";
        }
        else if (nemicoCorrente.getNome().contains("Zombie")) {
            percorso = "/immagini/ZOMBIE.png";
        }
        else if (nemicoCorrente.getNome().contains("Goblin")) {
            percorso = "/immagini/GOBLIN.png";
        }
        else {
            immagineNemicoPannello.setImage(null);
            return;
        }

        Image immagine = new Image(
                getClass().getResourceAsStream(percorso)
        );

        immagineNemicoPannello.setImage(immagine);
    }

    private void aggiornaPannelloEroe() {

        statisticheEroe.setText(
                "EROE \n" +
                        "Vita: " + eroe.getPuntiVita() +
                        "/" + eroe.getMaxVita() + "\n" +
                        "Attacco: " + eroe.getAttacco() + "\n" +
                        "Difesa: " + eroe.getDifesa()
        );
    }

    private void mostraMessaggioCura() {

        messaggioCura.setVisible(true);
        messaggioCura.setManaged(true);

        PauseTransition pausa =
                new PauseTransition(Duration.seconds(2));

        pausa.setOnFinished(evento -> {
            messaggioCura.setVisible(false);
            messaggioCura.setManaged(false);
        });

        pausa.play();
    }
}