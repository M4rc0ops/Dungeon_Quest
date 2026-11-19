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

    private GestoreMovimento gestoreMovimento;

    private PannelloStatistiche pannelloStatistiche;

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
        this.gestoreMovimento = new GestoreMovimento(piano,eroe);
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

        String percorsoEroe = "/immagini/EROE_" + direzioneEroe + ".png";
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

        pannelloStatistiche = new PannelloStatistiche(statisticheEroe,statisticheNemico,immagineNemicoPannello, messaggioCura);

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

        Posizione nuovaPosizione = gestoreMovimento.calcolaNuovaPosizione(direzione);

        int nuovaX = nuovaPosizione.getX();
        int nuovaY = nuovaPosizione.getY();

        direzioneEroe = gestoreMovimento.calcolaDirezioneEroe(direzione);

        if (rappresentazioneEroe != null && direzioneEroe != null) {

            Image nuovaImmagine = new Image( getClass().getResourceAsStream("/immagini/EROE_" + direzioneEroe + ".png") );

            rappresentazioneEroe.setImage(nuovaImmagine);
        }

        if (!gestoreMovimento.posizioneDentroMappa(nuovaPosizione)) {
            return;
        }

        Ambiente ambienteDestinazione = gestoreMovimento.trovaAmbiente(nuovaPosizione);

        if(!gestoreMovimento.casellaAttraversabile(nuovaPosizione)){
            return;
        }

        gestoreMovimento.spostaEroe(nuovaPosizione);

        if (gestoreMovimento.isUscitaPiano(nuovaPosizione)) {

            if (piano.getNumero() == 1) {
                passaAlSecondoPiano();
            }
            else if (piano.getNumero() == 2) {
                passaAlTerzoPiano();
            }

            return;
        }


        if (ambienteDestinazione != null) {

            if (ambienteDestinazione != ambienteCorrente) {

                ambienteCorrente = ambienteDestinazione;
                disegnaGriglia();
            }
            else {

                if (rappresentazioneEroe != null) {

                    int origineX =
                            ambienteCorrente.getCoordinataX();

                    int origineY =
                            ambienteCorrente.getCoordinataY();

                    GridPane.setColumnIndex(rappresentazioneEroe, nuovaX - origineX);

                    GridPane.setRowIndex(rappresentazioneEroe, nuovaY - origineY);
                }
            }
        }

        if (gestoreMovimento.isCasellaVittoria(nuovaPosizione)) {

            SchermataFinePartita schermataFine =
                    new SchermataFinePartita(
                            finestraGioco,
                            tornaAlMenu,
                            salvaPerVittoria
                    );

            schermataFine.mostraSchermataVittoria();
            return;
        }

        if (ambienteDestinazione instanceof Stanza) {

            Stanza nuovaStanza = (Stanza) ambienteDestinazione;

            if (nuovaStanza != stanzaCorrente) {

                stanzaCorrente = nuovaStanza;

                nemicoCorrente =
                        gestoreCombattimento.avviaCombattimento(
                                stanzaCorrente
                        );

                if (nemicoCorrente != null) {

                    pannelloStatistiche.aggiornaPannelloNemico(nemicoCorrente);

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

        cambiaPiano(gestorePiani.passaAlSecondoPiano(eroe), "SEI AL SECONDO PIANO");

    }

    private void passaAlTerzoPiano() {

        cambiaPiano(gestorePiani.passaAlTerzoPiano(eroe), "SEI AL TERZO PIANO");

    }


    public void cambiaPiano(Piano nuovoPiano, String messaggio){
        piano = nuovoPiano;
        aggiornaPianoInGioco.accept(piano);
        gestoreMovimento.aggiornaPiano(nuovoPiano);
        mostraIndicatorePiano(messaggio);

        stanzaCorrente = null;
        nemicoCorrente = null;

        ambienteCorrente = gestoreMovimento.trovaAmbiente(eroe.getPosizione());

        disegnaGriglia();

        informazioniCombattimento.setText("Sei arrivato al " + messaggio.toLowerCase() );
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

        pannelloStatistiche.aggiornaPannelloNemico(nemicoCorrente);
        pannelloStatistiche.aggiornaPannelloEroe(eroe);

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
                    pannelloStatistiche.mostraMessaggioCura();
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



}