package it.unicam.cs.mpgc.rpg129034;

import java.util.Arrays;

public class MappaPiano2 {


    private static final int DIMENSIONE_GRIGLIA = 45;



    // Crea e restituisce la rappresentazione testuale della mappa del secondo piano.
    public static String[] creaMappa(){
        String[] mappa = new String[DIMENSIONE_GRIGLIA];

        Arrays.fill(mappa,"#".repeat(DIMENSIONE_GRIGLIA));

        for (int i = 0; i <= 22; i++) {
            mappa[i] = " ".repeat(DIMENSIONE_GRIGLIA);
        }


        mappa[23]="                  #############              ";
        mappa[24]="                  #_______#__u#              ";
        mappa[25]="                  #_______s__u#              ";
        mappa[26]="                  #_______#__u#              ";
        mappa[27]="           ###########P####__u#              ";
        mappa[28]="           #______#_______########           ";
        mappa[29]="           #______#_______#______#           ";
        mappa[30]="           #______p_______p______#           ";
        mappa[31]="           #______#_______#______#           ";
        mappa[32]="           ###########p#######___#           ";
        mappa[33]="                    #___#    #___#           ";
        mappa[34]="                    #___#    #___#           ";
        mappa[35]="                    #___#  ####p####         ";
        mappa[36]="                    #___#  #_______#         ";
        mappa[37]="                    #___#  #_______#         ";
        mappa[38]="    #######         #___#  #_______#         ";
        mappa[39]="    #_____########  #___#  #########         ";
        mappa[40]="    #_____#______####___############         ";
        mappa[41]="    #_____#______#_________#_______#         ";
        mappa[42]="    #_____p______p_________p_______#         ";
        mappa[43]="    #_____#______#_________#_______#         ";
        mappa[44]="    ################################         ";

        return mappa;
    }



    // Crea il secondo piano del dungeon, definendo la mappa e gli ambienti con i relativi nemici.
    public static Piano creaPiano(){
        Piano piano = new Piano(2);

        piano.impostaMappa(creaMappa());

        Stanza stanzaIniziale = new Stanza("Stanza Iniziale",4,38,7,7);
        piano.aggiungiAmbiente(stanzaIniziale);


        Stanza stanzaVicinoStanzaIniziale = new Stanza("Stanza Vicino Stanza Iniziale",10,39,8,6);
        piano.aggiungiAmbiente(stanzaVicinoStanzaIniziale);
        stanzaVicinoStanzaIniziale.aggiungiNemico(new Nemico("Pipistrello",8,6,2),12,41);
        stanzaVicinoStanzaIniziale.aggiungiNemico(new Nemico("Scheletro",12,7,2),15,41);
        stanzaVicinoStanzaIniziale.aggiungiNemico(new Nemico("Scheletro",12,7,2),14,42);


        Stanza stanzaBassaDestra = new Stanza("Stanza Bassa Destra",27,40,9,5);
        piano.aggiungiAmbiente(stanzaBassaDestra);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,41);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),32,41);
        stanzaBassaDestra.aggiungiNemico(new Nemico("Zombie",16,7,2),31,42);


        Stanza stanzaSopraStanzaBassaDestra = new Stanza("Stanza Sopra Stanza Bassa Destra",27,35,9,5);
        piano.aggiungiAmbiente(stanzaSopraStanzaBassaDestra);
        stanzaSopraStanzaBassaDestra.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,37);
        stanzaSopraStanzaBassaDestra.aggiungiNemico(new Nemico("Scheletro",12,7,2),32,37);
        stanzaSopraStanzaBassaDestra.aggiungiNemico(new Nemico("Zombie",16,7,2),31,38);


        Stanza stanzaCentrale = new Stanza("Stanza Centrale",18,27,9,6);
        piano.aggiungiAmbiente(stanzaCentrale);
        stanzaCentrale.aggiungiNemico(new Nemico("Zombie",16,7,2),20,29);
        stanzaCentrale.aggiungiNemico(new Nemico("Zombie",16,7,2),23,29);
        stanzaCentrale.aggiungiNemico(new Nemico("Goblin",20,8,2),22,30);


        Stanza stanzaSinistra = new Stanza("Stanza Sinistra",11,27,8,6);
        piano.aggiungiAmbiente(stanzaSinistra);
        stanzaSinistra.aggiungiNemico(new Nemico("Goblin",20,8,2),13,29);
        stanzaSinistra.aggiungiNemico(new Nemico("Goblin",20,8,2),16,29);
        stanzaSinistra.aggiungiNemico(new Nemico("Pipistrello",8,6,2),15,30);


        Stanza stanzaBoss = new Stanza("Stanza Boss",18,23,9,5);
        piano.aggiungiAmbiente(stanzaBoss);
        stanzaBoss.aggiungiNemico(new Boss("Mega Zombie",25,8,3),22,25);


        Stanza stanzaScale = new Stanza("Stanza Scale",26,23,5,6);
        piano.aggiungiAmbiente(stanzaScale);


        Corridoio corridoioBasso = new Corridoio("Corridoio Basso",17,33,11,12);
        piano.aggiungiAmbiente(corridoioBasso);


        Corridoio corridoioAlto = new Corridoio("Corridoio Alto",26,28,8,7);
        piano.aggiungiAmbiente(corridoioAlto);



        return piano;
    }


}
