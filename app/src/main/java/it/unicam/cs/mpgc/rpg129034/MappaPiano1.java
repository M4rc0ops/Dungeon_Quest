package it.unicam.cs.mpgc.rpg129034;

import java.util.Arrays;

public class MappaPiano1 {


    private static final int DIMENSIONE_GRIGLIA = 45;



    public static String[] creaMappa(){
        String[] mappa = new String[DIMENSIONE_GRIGLIA];

        Arrays.fill(mappa,"#".repeat(DIMENSIONE_GRIGLIA));


        for (int i = 0; i <= 20; i++) {
            mappa[i]= " ".repeat(DIMENSIONE_GRIGLIA);
        }

        mappa[21]="                 ##################          ";
        mappa[22]="                 #u__##_____##____#          ";
        mappa[23]="                 #u___s______P____#          ";
        mappa[24]="                 #u__##_____##____#          ";
        mappa[25]="                 #u__##_____###___#          ";
        mappa[26]="                 ##############___#          ";
        mappa[27]="                              #___#          ";
        mappa[28]="              ##################p####        ";
        mappa[29]="              #______##_____##______#        ";
        mappa[30]="              #_______p_____p_______#        ";
        mappa[31]="              #______##_____##______#        ";
        mappa[32]="              #______##_____##______#        ";
        mappa[33]="              ###########p###########        ";
        mappa[34]="                       #___#                 ";
        mappa[35]="                       #___#                 ";
        mappa[36]="                       #___#                 ";
        mappa[37]="                       #___#                 ";
        mappa[38]="                      ###p###                ";
        mappa[39]="                      #_____#                ";
        mappa[40]="                      #_____#                ";
        mappa[41]="                      #_____#                ";
        mappa[42]="                      #_____#                ";
        mappa[43]="                      #_____#                ";
        mappa[44]="                      #######                ";

        return mappa;
    }



    public static Piano creaPiano(){
        Piano piano = new Piano(1);

        piano.impostaMappa(creaMappa());

        Stanza stanzaIniziale=new Stanza("Stanza Iniziale",22,38,7,7);
        piano.aggiungiAmbiente(stanzaIniziale);


        Corridoio corridoioInferiore= new Corridoio("Corridoio Inferiore",23,34,5,4);
        piano.aggiungiAmbiente(corridoioInferiore);


        Stanza stanzaCentrale= new Stanza("Stanza Centrale",22,28,7,6);
        piano.aggiungiAmbiente(stanzaCentrale);
        stanzaCentrale.aggiungiNemico(new Nemico("Pipistrello",8,6,2),24,30);
        stanzaCentrale.aggiungiNemico(new Nemico("Pipistrello",8,6,2),26,30);
        stanzaCentrale.aggiungiNemico(new Nemico("Scheletro",12,7,2),25,31);


        Stanza stanzaSinistra= new Stanza("Stanza Sinistra",14,28,8,6);
        piano.aggiungiAmbiente(stanzaSinistra);
        stanzaSinistra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),16,30);
        stanzaSinistra.aggiungiNemico(new Nemico("Scheletro",12,7,2),19,30);
        stanzaSinistra.aggiungiNemico(new Nemico("Zombie",16,7,2),18,31);


        Stanza stanzaDestra= new Stanza("Stanza Destra",29,28,8,6);
        piano.aggiungiAmbiente(stanzaDestra);
        stanzaDestra.aggiungiNemico(new Nemico("Scheletro",12,7,2),31,30);
        stanzaDestra.aggiungiNemico(new Nemico("Zombie",16,7,2),34,30);
        stanzaDestra.aggiungiNemico(new Nemico("Goblin",20,8,2),33,31);


        Corridoio corridoioSuperiore= new Corridoio("Corridoio Superiore",29,21,6,7);
        piano.aggiungiAmbiente(corridoioSuperiore);


        Stanza stanzaBoss= new Stanza("Stanza Boss",22,21,7,6);
        piano.aggiungiAmbiente(stanzaBoss);
        stanzaBoss.aggiungiNemico(new Boss("Mega Scheletro",25,8,3),25,23);

        Stanza stanzaScale= new Stanza("Stanza Scale",17,21,5,6);
        piano.aggiungiAmbiente(stanzaScale);


        return piano;
    }


}
