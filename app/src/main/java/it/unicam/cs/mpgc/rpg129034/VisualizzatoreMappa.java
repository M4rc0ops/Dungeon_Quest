package it.unicam.cs.mpgc.rpg129034;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class VisualizzatoreMappa {


    private static final int DIMENSIONE_CELLA = 30;
    private final Image immaginePavimento;
    private final Image immagineMuro;
    private final Image immaginePortaComune;
    private final Image immaginePipistrello;
    private final Image immagineScheletro;
    private final Image immagineZombie;
    private final Image immagineGoblin;
    private final Image immagineScale;
    private final Image immaginePortaBoss;
    private final Image immagineEroeSu;
    private final Image immagineEroeGiu;
    private final Image immagineEroeDestra;
    private final Image immagineEroeSinistra;



    public VisualizzatoreMappa(){

        immaginePavimento = caricaImmagine("PAVIMENTO.png");
        immagineMuro = caricaImmagine("MURO.png");
        immaginePortaComune = caricaImmagine("PORTA_COMUNE_FRONTALE.png");
        immaginePipistrello = caricaImmagine("PIPISTRELLO.png");
        immagineScheletro = caricaImmagine("SCHELETRO.png");
        immagineZombie = caricaImmagine("ZOMBIE.png");
        immagineGoblin = caricaImmagine("GOBLIN.png");
        immagineScale = caricaImmagine("SCALE.png");
        immaginePortaBoss = caricaImmagine("PORTA_BOSS.png");
        immagineEroeSu = caricaImmagine("EROE_SU.png");
        immagineEroeGiu = caricaImmagine("EROE_GIU.png");
        immagineEroeDestra = caricaImmagine("EROE_DESTRA.png");
        immagineEroeSinistra = caricaImmagine("EROE_SINISTRA.png");

    }



    private Image caricaImmagine(String nomeFile){

        java.net.URL risorsa = getClass().getResource("/immagini/" + nomeFile);

        if(risorsa == null){
            throw new IllegalStateException("immagine non trovata: " + nomeFile);
        }

        return new Image(risorsa.toExternalForm());

    }



    private Image selezionaImmagineEroe(String direzioneEroe){

        switch (direzioneEroe){
            case "SU":
                return immagineEroeSu;

            case "GIU":
                return immagineEroeGiu;

            case "DESTRA":
                return immagineEroeDestra;

            case "SINISTRA":
                return immagineEroeSinistra;

            default:
                throw new IllegalArgumentException("direzione non valida: " + direzioneEroe);
        }

    }



    public ImageView disegnaGriglia(GridPane griglia, Piano piano, Ambiente ambienteCorrente, Eroe eroe, String direzioneEroe){

        griglia.getChildren().clear();

        if (ambienteCorrente == null) {
        return null;
    }

    int origineX = ambienteCorrente.getCoordinataX();
    int origineY = ambienteCorrente.getCoordinataY();
    int larghezza = ambienteCorrente.getLarghezza();
    int altezza = ambienteCorrente.getAltezza();


        for (int y = origineY; y < origineY + altezza; y++) {
        for (int x = origineX; x < origineX + larghezza; x++) {

            TipoCella tipo = piano.getTipoCella(x, y);

            Rectangle cella = new Rectangle(DIMENSIONE_CELLA, DIMENSIONE_CELLA);

            switch (tipo) {

                case MURO:
                    ImageView muro = new ImageView(immagineMuro);
                    muro.setFitWidth(DIMENSIONE_CELLA);
                    muro.setFitHeight(DIMENSIONE_CELLA);
                    muro.setSmooth(false);
                    griglia.add(muro, x - origineX, y - origineY);
                    break;

                case PAVIMENTO:
                    ImageView pavimento = new ImageView(immaginePavimento);
                    pavimento.setFitHeight(DIMENSIONE_CELLA);
                    pavimento.setFitWidth(DIMENSIONE_CELLA);
                    pavimento.setSmooth(false);
                    griglia.add(pavimento, x - origineX, y - origineY);
                    break;

                case PORTA_COMUNE:
                    ImageView portaComune = new ImageView(immaginePortaComune);
                    portaComune.setFitHeight(DIMENSIONE_CELLA);
                    portaComune.setFitWidth(DIMENSIONE_CELLA);
                    portaComune.setSmooth(false);
                    griglia.add(portaComune, x - origineX, y - origineY);
                    break;

                case PORTA_BOSS:
                    ImageView portaBoss = new ImageView(immaginePortaBoss);
                    portaBoss.setFitHeight(DIMENSIONE_CELLA);
                    portaBoss.setFitWidth(DIMENSIONE_CELLA);
                    portaBoss.setSmooth(false);
                    portaBoss.setPreserveRatio(false);
                    griglia.add(portaBoss, x - origineX, y - origineY);
                    break;

                case PORTA_SCALE:
                    ImageView portaScale = new ImageView(immaginePortaComune);
                    portaScale.setFitHeight(DIMENSIONE_CELLA);
                    portaScale.setFitWidth(DIMENSIONE_CELLA);
                    portaScale.setSmooth(false);
                    griglia.add(portaScale, x - origineX, y - origineY);
                    break;

                case USCITA_PIANO:
                    ImageView scale = new ImageView(immagineScale);
                    scale.setFitHeight(DIMENSIONE_CELLA);
                    scale.setFitWidth(DIMENSIONE_CELLA);
                    scale.setSmooth(false);
                    scale.setPreserveRatio(false);
                    griglia.add(scale, x - origineX, y - origineY);
                    break;

                case VUOTO:
                    cella.setFill(Color.BLACK);
                    break;
            }

            if (tipo != TipoCella.PAVIMENTO &&
                    tipo != TipoCella.MURO &&
                    tipo != TipoCella.PORTA_COMUNE &&
                    tipo != TipoCella.PORTA_SCALE &&
                    tipo != TipoCella.USCITA_PIANO &&
                    tipo != TipoCella.PORTA_BOSS) {

                cella.setStroke(Color.GRAY);
                griglia.add(cella, x - origineX, y - origineY);
            }
        }
    }

        if (ambienteCorrente instanceof Stanza) {

        Stanza stanzaVisualizzata = (Stanza) ambienteCorrente;

        for (Nemico nemico : stanzaVisualizzata.getNemici()) {

            if (nemico.sconfitta()) {
                continue;
            }

            Posizione posizione = stanzaVisualizzata.getPosizioneNemico(nemico);

            if (posizione == null) {
                return null;
            }

            Image immagineNemico;
            String nomeNemico = nemico.getNome();

            if (nomeNemico.contains("Pipistrello")) {
                immagineNemico = immaginePipistrello;
            }
            else if (nomeNemico.contains("Scheletro")) {
                immagineNemico = immagineScheletro;
            }
            else if (nomeNemico.contains("Zombie")) {
                immagineNemico = immagineZombie;
            }
            else if (nomeNemico.contains("Goblin")) {
                immagineNemico = immagineGoblin;
            }
            else {
                continue;
            }

            ImageView rappresentazioneNemico = new ImageView(immagineNemico);
            rappresentazioneNemico.setFitHeight(DIMENSIONE_CELLA);
            rappresentazioneNemico.setFitWidth(DIMENSIONE_CELLA);
            rappresentazioneNemico.setSmooth(false);

            griglia.add(
                    rappresentazioneNemico,
                    posizione.getX() - origineX,
                    posizione.getY() - origineY
            );
        }
    }

        Image immagineEroe = selezionaImmagineEroe(direzioneEroe);


    ImageView rappresentazioneEroe = new ImageView(immagineEroe);
        rappresentazioneEroe.setFitWidth(DIMENSIONE_CELLA);
        rappresentazioneEroe.setFitHeight(DIMENSIONE_CELLA);
        rappresentazioneEroe.setSmooth(false);

        griglia.add(
    rappresentazioneEroe,
            eroe.getPosizione().getX() - origineX,
            eroe.getPosizione().getY() - origineY
        );

        return rappresentazioneEroe;
    }


}