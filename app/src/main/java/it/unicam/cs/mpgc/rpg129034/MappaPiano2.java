package it.unicam.cs.mpgc.rpg129034;

import java.util.*;

public class MappaPiano2 {

    public static String[] creaMappa(){
        String[] mappa = new String[45];

        Arrays.fill(mappa,"#".repeat(45));


        mappa[0]=" ".repeat(45);
        mappa[1]=" ".repeat(45);
        mappa[2]=" ".repeat(45);
        mappa[3]=" ".repeat(45);
        mappa[4]=" ".repeat(45);
        mappa[5]=" ".repeat(45);
        mappa[6]=" ".repeat(45);
        mappa[7]=" ".repeat(45);
        mappa[8]=" ".repeat(45);
        mappa[9]=" ".repeat(45);
        mappa[10]=" ".repeat(45);
        mappa[11]=" ".repeat(45);
        mappa[12]=" ".repeat(45);
        mappa[13]=" ".repeat(45);
        mappa[14]=" ".repeat(45);
        mappa[15]=" ".repeat(45);
        mappa[16]=" ".repeat(45);
        mappa[17]=" ".repeat(45);
        mappa[18]=" ".repeat(45);
        mappa[19]=" ".repeat(45);
        mappa[20]=" ".repeat(45);
        mappa[21]=" ".repeat(45);
        mappa[22]=" ".repeat(45);
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


    public static Piano creaPiano(){
        Piano piano = new Piano(2);

        piano.impostaMappa(creaMappa());

        Stanza stanzaIniziale = new Stanza("Stanza Iniziale",4,38,7,7);
        piano.aggiungiAmbiente(stanzaIniziale);


        Stanza stanza1 = new Stanza("Stanza Vicino Stanza Iniziale",10,39,8,6);
        piano.aggiungiAmbiente(stanza1);
        stanza1.aggiungiNemico(new Nemico("Pipistrello",8,6,2),12,41);
        stanza1.aggiungiNemico(new Nemico("Scheletro",12,7,2),15,41);
        stanza1.aggiungiNemico(new Nemico("Scheletro",12,7,2),14,42);


        Stanza stanza2 = new Stanza("Stanza Bassa Destra",27,40,9,5);
        piano.aggiungiAmbiente(stanza2);
        stanza2.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,41);
        stanza2.aggiungiNemico(new Nemico("Pipistrello",8,6,2),32,41);
        stanza2.aggiungiNemico(new Nemico("Zombie",16,7,2),31,42);


        Stanza stanza3 = new Stanza("Stanza Sopra Stanza Bassa Destra",27,35,9,5);
        piano.aggiungiAmbiente(stanza3);
        stanza3.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,37);
        stanza3.aggiungiNemico(new Nemico("Scheletro",12,7,2),32,37);
        stanza3.aggiungiNemico(new Nemico("Zombie",16,7,2),31,38);


        Stanza stanza4 = new Stanza("Stanza Centrale",18,27,9,6);
        piano.aggiungiAmbiente(stanza4);
        stanza4.aggiungiNemico(new Nemico("Zombie",16,7,2),20,29);
        stanza4.aggiungiNemico(new Nemico("Zombie",16,7,2),23,29);
        stanza4.aggiungiNemico(new Nemico("Goblin",20,8,2),22,30);


        Stanza stanza5 = new Stanza("Stanza Sinistra",11,27,8,6);
        piano.aggiungiAmbiente(stanza5);
        stanza5.aggiungiNemico(new Nemico("Goblin",20,8,2),13,29);
        stanza5.aggiungiNemico(new Nemico("Goblin",20,8,2),16,29);
        stanza5.aggiungiNemico(new Nemico("Pipistrello",8,6,2),15,30);


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
