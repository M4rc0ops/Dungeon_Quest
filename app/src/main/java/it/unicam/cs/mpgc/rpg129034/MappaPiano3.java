package it.unicam.cs.mpgc.rpg129034;

import java.util.Arrays;

public class MappaPiano3 {


    private static final int DIMENSIONE_GRIGLIA = 45;



    // Crea e restituisce la rappresentazione testuale della mappa del terzo piano.
    public static String[] creaMappa() {
        String[] mappa = new String[DIMENSIONE_GRIGLIA];

        Arrays.fill(mappa, "#".repeat(DIMENSIONE_GRIGLIA));

        for (int i = 0; i <= 24; i++) {
            mappa[i] = " ".repeat(DIMENSIONE_GRIGLIA);
        }

        mappa[25]="                    #########################";
        mappa[26]="                    #_____#_____#_______#___#";
        mappa[27]="                    #_____p_____P_______s___#";
        mappa[28]="                    #_____#_____#_______#___#";
        mappa[29]="                    #___###_____#_______#___#";
        mappa[30]="                    #___#####################";
        mappa[31]=" #######            #___#                    ";
        mappa[32]=" #_____#          ####p##################    ";
        mappa[33]=" #_____#          #_______#______#______#    ";
        mappa[34]=" #_____#          #_______p______p______#    ";
        mappa[35]=" #_____#          #_______#______#______#    ";
        mappa[36]=" #_____#          #_______#______#______#    ";
        mappa[37]=" ###p###          ##################p####    ";
        mappa[38]="  #___#                           #___#      ";
        mappa[39]="  #___#############################___#      ";
        mappa[40]="  #______#_______#_____#_______#______#      ";
        mappa[41]="  #______p_______p_____p_______p______#      ";
        mappa[42]="  #______#_______#_____#_______#______#      ";
        mappa[43]="  ########_______#_____#_______########      ";
        mappa[44]="         #######################             ";


        return mappa;
    }



    // Crea il terzo piano del dungeon, definendo la mappa e gli ambienti con i relativi nemici.
    public static Piano creaPiano() {
        Piano piano = new Piano(3);

        piano.impostaMappa(creaMappa());

        Stanza stanzaIniziale = new Stanza("Stanza Iniziale",1,31,7,7);
        piano.aggiungiAmbiente(stanzaIniziale);


        Stanza stanzaBassaSinistra = new Stanza("Stanza Bassa Sinistra", 9, 39,9,6);
        piano.aggiungiAmbiente(stanzaBassaSinistra);
        stanzaBassaSinistra.aggiungiNemico(new Nemico("Zombie",16,7,2),11,41);
        stanzaBassaSinistra.aggiungiNemico(new Nemico("Zombie",16,7,2),14,41);
        stanzaBassaSinistra.aggiungiNemico(new Nemico("Scheletro",12,7,2),13,42);


        Stanza stanzaBassaCentrale = new Stanza("Stanza Bassa Centrale", 17, 39, 7, 6);
        piano.aggiungiAmbiente(stanzaBassaCentrale);
        stanzaBassaCentrale.aggiungiNemico(new Nemico("Pipistrello",8,6,2),19,41);
        stanzaBassaCentrale.aggiungiNemico(new Nemico("Zombie",16,7,2),21,41);
        stanzaBassaCentrale.aggiungiNemico(new Nemico("Scheletro",12,7,2),20,42);


        Stanza stanzaBassaDestra = new Stanza("Stanza Bassa Destra", 23, 39, 9, 6);
        piano.aggiungiAmbiente(stanzaBassaDestra);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),25,41);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),28,41);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Scheletro",12,7,2),27,42);


        Stanza stanzaMediaDestra = new Stanza("Stanza Media Destra", 33, 32, 8, 6);
        piano.aggiungiAmbiente(stanzaMediaDestra);
        stanzaMediaDestra.aggiungiNemico(new Nemico("Goblin",20,8,2),35,34);
        stanzaMediaDestra.aggiungiNemico(new Nemico("Zombie",16,7,2),37,34);
        stanzaMediaDestra.aggiungiNemico(new Nemico("Zombie",16,7,2),36,35);


        Stanza stanzaMediaCentrale = new Stanza("Stanza Media Centrale", 26, 32, 7, 6);
        piano.aggiungiAmbiente(stanzaMediaCentrale);
        stanzaMediaCentrale.aggiungiNemico(new Nemico("Zombie", 16,7,2),28,34);
        stanzaMediaCentrale.aggiungiNemico(new Nemico("Goblin",20,8,2),30,34);
        stanzaMediaCentrale.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,35);


        Stanza stanzaMediaSinistra = new Stanza("Stanza Media Sinistra", 18, 32, 8, 6);
        piano.aggiungiAmbiente(stanzaMediaSinistra);
        stanzaMediaSinistra.aggiungiNemico(new Nemico("Goblin",20,8,2),20,34);
        stanzaMediaSinistra.aggiungiNemico(new Nemico("Goblin",20,8,2),23,34);
        stanzaMediaSinistra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),22,35);


        Stanza stanzaAlta = new Stanza("Stanza alta", 26, 25, 6, 6);
        piano.aggiungiAmbiente(stanzaAlta);
        stanzaAlta.aggiungiNemico(new Nemico("Goblin",20,8,2),28,27);
        stanzaAlta.aggiungiNemico(new Nemico("Goblin",20,8,2),30,27);
        stanzaAlta.aggiungiNemico(new Nemico("Zombie",16,7,2),29,28);


        Stanza stanzaBoss = new Stanza("Stanza Boss", 32, 25, 9, 6);
        piano.aggiungiAmbiente(stanzaBoss);
        stanzaBoss.aggiungiNemico(new Boss("Mega Goblin",25,8,3),36,27);


        Stanza stanzaFinale = new Stanza("Stanza della Vittoria", 40, 25, 5, 6);
        piano.aggiungiAmbiente(stanzaFinale);


        Corridoio corridoioBassoSinistra = new Corridoio("Corridoio Basso Sinistra", 2, 38, 7, 6);
        piano.aggiungiAmbiente(corridoioBassoSinistra);


        Corridoio corridoioBassoDestra = new Corridoio("Corridoio Basso Destra", 32, 37, 7, 7);
        piano.aggiungiAmbiente(corridoioBassoDestra);


        Corridoio corridoioAltoSinistra = new Corridoio("Corridoio Alto Sinistra", 20, 25, 6, 7);
        piano.aggiungiAmbiente(corridoioAltoSinistra);


        return piano;
    }


}
