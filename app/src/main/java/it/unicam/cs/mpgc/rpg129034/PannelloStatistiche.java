package it.unicam.cs.mpgc.rpg129034;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class PannelloStatistiche {


    private final Label statisticheEroe;
    private final Label statisticheNemico;
    private final ImageView immagineNemicoPannello;
    private final Label messaggioCura;



    public PannelloStatistiche(Label statisticheEroe, Label statisticheNemico, ImageView immagineNemicoPannello,
                               Label messaggioCura) {

        if(statisticheEroe == null || statisticheNemico == null ||  immagineNemicoPannello == null || messaggioCura == null){
            throw new IllegalArgumentException("i componenti del pannello non possono essere nulli");
        }

        this.statisticheEroe = statisticheEroe;
        this.statisticheNemico = statisticheNemico;
        this.immagineNemicoPannello = immagineNemicoPannello;
        this.messaggioCura = messaggioCura;

    }



    public void aggiornaPannelloEroe(Eroe eroe) {

        if(eroe == null){
            throw new IllegalArgumentException("l'eroe non può essere nullo");
        }

        statisticheEroe.setText("EROE \n" + "Vita: " + eroe.getPuntiVita() +
                "/" + eroe.getMaxVita() + "\n" +
                "Attacco: " + eroe.getAttacco() + "\n" +
                "Difesa: " + eroe.getDifesa() );

    }



    public void aggiornaPannelloNemico(Nemico nemicoCorrente) {

        if (nemicoCorrente == null) {

            statisticheNemico.setText("nessun nemico");
            immagineNemicoPannello.setImage(null);
            return;
        }

        statisticheNemico.setText(
                nemicoCorrente.getNome() + "\nVita: " + nemicoCorrente.getPuntiVita() +
                        "/" + nemicoCorrente.getMaxVita() + "\n" +
                        "Attacco: " + nemicoCorrente.getAttacco() + "\n" +
                        "Difesa: " + nemicoCorrente.getDifesa() );

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

        Image immagine = new Image( getClass().getResourceAsStream(percorso) );

        immagineNemicoPannello.setImage(immagine);
    }



    public void mostraMessaggioCura() {

        messaggioCura.setVisible(true);
        messaggioCura.setManaged(true);

        PauseTransition pausa = new PauseTransition(Duration.seconds(2));

        pausa.setOnFinished(evento -> { messaggioCura.setVisible(false); messaggioCura.setManaged(false); } );

        pausa.play();
    }



}
