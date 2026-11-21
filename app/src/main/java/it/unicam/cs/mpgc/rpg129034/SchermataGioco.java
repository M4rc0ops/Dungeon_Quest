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
    private final Eroe eroe;
    private final GestoreCombattimento gestoreCombattimento;
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
    private final Stage finestraGioco;
    private final Runnable tornaAlMenu;
    private final Runnable mostraMenuPausa;
    private final Runnable salvaPerVittoria;
    private final Runnable salvaPerSconfitta;
    private final Consumer<Piano> aggiornaPianoInGioco;

    private final GestorePiani gestorePiani;
    private final VisualizzatoreMappa visualizzatoreMappa;
    private final GestoreMovimento gestoreMovimento;
    private PannelloStatistiche pannelloStatistiche;



    //Costruttore. Verifica la validità dei campi e assegna i valori
    public SchermataGioco( Stage finestraGioco, Piano piano, Eroe eroe, GestoreCombattimento gestoreCombattimento,
                           Runnable tornaAlMenu, Runnable mostraMenuPausa, Runnable salvaPerVittoria,
                           Runnable salvaPerSconfitta, Consumer<Piano> aggiornaPianoInGioco) {

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

        Posizione posizioneEroe = eroe.getPosizione();

        this.ambienteCorrente = piano.trovaAmbiente(
                posizioneEroe.getX(),
                posizioneEroe.getY()
        );

        this.stanzaCorrente = null;
        this.nemicoCorrente = null;
        this.visualizzatoreMappa = new  VisualizzatoreMappa();
        this.gestorePiani = new GestorePiani();
        this.gestoreMovimento = new GestoreMovimento(piano,eroe);
    }



    // Costruisce e mostra l'interfaccia di gioco, impostando la mappa, i pannelli e i comandi da tastiera.
    public void mostraSchermataGioco() {

        griglia = new GridPane();
        informazioniCombattimento = new Label();

        String testoPiano;

        switch(piano.getNumero()){
            case 1:
                testoPiano = "SEI AL PRIMO PIANO";
                break;

            case 2:
                testoPiano = "SEI AL SECONDO PIANO";
                break;

            case 3:
                testoPiano = "SEI AL TERZO PIANO";
                break;

            default:
                testoPiano = "PIANO SCONOSCIUTO";
                break;

        }

        indicatorePiano = new Label(testoPiano);

        indicatorePiano.getStyleClass().add("indicatore-piano");



        disegnaGriglia();

        griglia.setAlignment(Pos.CENTER);

        StackPane areaMappa = new StackPane(griglia);
        areaMappa.getStyleClass().add("area-mappa");
        areaMappa.setPrefSize(760, 520);

        ScrollPane scorrimento = new ScrollPane(areaMappa);
        scorrimento.setPannable(true);
        scorrimento.setFitToWidth(true);
        scorrimento.setFitToHeight(true);
        scorrimento.getStyleClass().add("scorrimento-mappa");

        informazioniCombattimento.setText("esplora il dungeon");

        VBox pannelloInferiore = new VBox(5);
        pannelloInferiore.setAlignment(Pos.CENTER);
        pannelloInferiore.setPadding(new Insets(5));
        pannelloInferiore.setPrefHeight(175);
        pannelloInferiore.getStyleClass().add("pannello-inferiore");

        HBox rigaPannello = new HBox(10);
        rigaPannello.setAlignment(Pos.CENTER);

        immagineEroePannello = new ImageView();
        immagineEroePannello.setFitWidth(100);
        immagineEroePannello.setFitHeight(120);
        immagineEroePannello.setPreserveRatio(true);

        String percorsoEroe = "/immagini/EROE_" + direzioneEroe + ".png";
        Image immagineEroe = new Image(getClass().getResourceAsStream(percorsoEroe) );

        immagineEroePannello.setImage(immagineEroe);

        statisticheEroe = new Label();


        statisticheEroe.setText(
                "EROE\n" +
                        "Vita: " + eroe.getPuntiVita() + "/" +
                        eroe.getMaxVita() + "\n" +
                        "Attacco: " + eroe.getAttacco() + "\n" +
                        "Difesa: " + eroe.getDifesa()
        );

        statisticheEroe.getStyleClass().add("statistiche-giocatore");

        messaggioCura = new Label("+10 PV!");
        messaggioCura.getStyleClass().add("messaggio-cura");

        messaggioCura.setVisible(false);
        messaggioCura.setManaged(false);

        Separator divisore = new Separator();
        divisore.setOrientation(Orientation.VERTICAL);
        divisore.setPrefHeight(110);
        divisore.getStyleClass().add("divisore-statistiche");

        statisticheNemico = new Label();
        statisticheNemico.getStyleClass().add("statistiche-giocatore");
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
        istruzioniAttacco.getStyleClass().add("istruzioni-attacco");

        pannelloInferiore.getChildren().add(istruzioniAttacco);
        pannelloInferiore.getChildren().add(informazioniCombattimento);

        intestazionePiano = new HBox(indicatorePiano);
        intestazionePiano.setAlignment(Pos.CENTER);
        intestazionePiano.setPadding(new Insets(8));
        intestazionePiano.getStyleClass().add("intestazione-piano");

        VBox contenitore = new VBox(5);

        contenitore.getChildren().addAll(
                intestazionePiano,
                scorrimento,
                pannelloInferiore
        );

        VBox.setVgrow(scorrimento, Priority.ALWAYS);

        Scene scena = new Scene(contenitore, 800, 600);
        scena.getStylesheets().add(getClass().getResource("/stili/stile.css").toExternalForm());
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



    // Disegna la griglia del piano mostrando l'ambiente corrente e la posizione dell'eroe.
    private void disegnaGriglia() {
        rappresentazioneEroe = visualizzatoreMappa.disegnaGriglia(
                griglia,
                piano,
                ambienteCorrente,
                eroe,
                direzioneEroe
        );
    }



    // Gestisce il movimento dell'eroe, aggiornando la posizione, la mappa e gli eventuali eventi della casella raggiunta.
    private void gestisciMovimento(String direzione) {

        if (gestoreCombattimento.isCombattimentoAttivo()) {
            return;
        }

        Posizione nuovaPosizione = gestoreMovimento.calcolaNuovaPosizione(direzione);

        int nuovaX = nuovaPosizione.getX();
        int nuovaY = nuovaPosizione.getY();

        direzioneEroe = gestoreMovimento.calcolaDirezioneEroe(direzione);

        if (rappresentazioneEroe != null) {

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

                nemicoCorrente = gestoreCombattimento.avviaCombattimento(stanzaCorrente);
                pannelloStatistiche.aggiornaPannelloNemico(nemicoCorrente);


                if (nemicoCorrente != null) {

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



    // Porta l'eroe al secondo piano e aggiorna la schermata di gioco.
    private void passaAlSecondoPiano() {

        cambiaPiano(gestorePiani.passaAlSecondoPiano(eroe), "SEI AL SECONDO PIANO");

    }



    // Porta l'eroe al terzo piano e aggiorna la schermata di gioco.
    private void passaAlTerzoPiano() {

        cambiaPiano(gestorePiani.passaAlTerzoPiano(eroe), "SEI AL TERZO PIANO");

    }



    // Aggiorna il piano corrente, la posizione dell'eroe e la mappa visualizzata.
    private void cambiaPiano(Piano nuovoPiano, String messaggio){
        piano = nuovoPiano;
        aggiornaPianoInGioco.accept(piano);
        gestoreMovimento.aggiornaPiano(nuovoPiano);
        mostraIndicatorePiano(messaggio);

        stanzaCorrente = null;
        nemicoCorrente = null;

        Posizione posizioneEroe = eroe.getPosizione();
        ambienteCorrente = gestoreMovimento.trovaAmbiente(posizioneEroe);

        disegnaGriglia();

        informazioniCombattimento.setText("Sei arrivato al " + messaggio.toLowerCase() );
    }



    // Mostra temporaneamente un indicatore che comunica il piano raggiunto.
    private void mostraIndicatorePiano(String messaggio) {

        indicatorePiano.setText(messaggio);
        indicatorePiano.setVisible(true);
        intestazionePiano.setVisible(true);
        intestazionePiano.setManaged(true);

        PauseTransition attesa =
                new PauseTransition(Duration.seconds(2));

        attesa.setOnFinished(evento -> {
            intestazionePiano.setVisible(false);
            intestazionePiano.setManaged(false);
            indicatorePiano.setVisible(false);
        });

        attesa.play();
    }



    // Esegue un turno di combattimento e aggiorna le statistiche e i messaggi in base all'esito.
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