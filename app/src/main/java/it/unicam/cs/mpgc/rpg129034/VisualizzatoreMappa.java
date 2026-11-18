package it.unicam.cs.mpgc.rpg129034;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.function.Consumer;

public class VisualizzatoreMappa {

    public ImageView disegnaGriglia(GridPane griglia, Piano piano, Ambiente ambienteCorrente, Eroe eroe, String direzioneEroe){
            int dimensioneCella = 30;

        griglia.getChildren().clear();

        if (ambienteCorrente == null) {
        return null;
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
    Image immagineGoblin = new Image("/immagini/GOBLIN.png");
    Image immagineScale = new Image("/immagini/SCALE.png");
    Image immaginePortaBoss = new Image("/immagini/PORTA_BOSS.png");

        for (int y = origineY; y < origineY + altezza; y++) {
        for (int x = origineX; x < origineX + larghezza; x++) {

            TipoCella tipo = piano.getTipoCella(x, y);

            Rectangle cella = new Rectangle(dimensioneCella, dimensioneCella);

            switch (tipo) {

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
            rappresentazioneNemico.setFitHeight(dimensioneCella);
            rappresentazioneNemico.setFitWidth(dimensioneCella);
            rappresentazioneNemico.setSmooth(false);

            griglia.add(
                    rappresentazioneNemico,
                    posizione.getX() - origineX,
                    posizione.getY() - origineY
            );
        }
    }

    String percorso = "/immagini/EROE_" + direzioneEroe + ".png";

    Image immagineEroe = new Image(
            getClass().getResourceAsStream(percorso)
    );

    ImageView rappresentazioneEroe = new ImageView(immagineEroe);
        rappresentazioneEroe.setFitWidth(dimensioneCella);
        rappresentazioneEroe.setFitHeight(dimensioneCella);
        rappresentazioneEroe.setSmooth(false);

        griglia.add(
    rappresentazioneEroe,
            eroe.getPosizione().getX() - origineX,
            eroe.getPosizione().getY() - origineY
        );

        return rappresentazioneEroe;
    }


}