package it.unicam.cs.mpgc.rpg129034;

import java.util.Arrays;

public class MappaPiano3 {

    public static String[] creaMappa() {
        String[] mappa = new String[45];

        Arrays.fill(mappa, "#".repeat(45));

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
        mappa[23]=" ".repeat(45);
        mappa[24]=" ".repeat(45);

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


    public static Piano creaPiano() {
        Piano piano = new Piano(3);

        piano.impostaMappa(creaMappa());

        Stanza StanzaIniziale = new Stanza("Stanza Iniziale",1,31,7,7);
        piano.aggiungiAmbiente(StanzaIniziale);


        Stanza stanza1 = new Stanza("Stanza Bassa Sinistra", 9, 39,9,6);
        piano.aggiungiAmbiente(stanza1);
        stanza1.aggiungiNemico(new Nemico("Zombie",16,7,2),11,41);
        stanza1.aggiungiNemico(new Nemico("Zombie",16,7,2),14,41);
        stanza1.aggiungiNemico(new Nemico("Scheletro",12,7,2),13,42);


        Stanza stanza2 = new Stanza("Stanza Bassa Centrale", 17, 39, 7, 6);
        piano.aggiungiAmbiente(stanza2);
        stanza2.aggiungiNemico(new Nemico("Pipistrello",8,6,2),19,41);
        stanza2.aggiungiNemico(new Nemico("Zombie",16,7,2),21,41);
        stanza2.aggiungiNemico(new Nemico("Scheletro",12,7,2),20,42);


        Stanza stanza3 = new Stanza("Stanza Bassa Destra", 23, 39, 9, 6);
        piano.aggiungiAmbiente(stanza3);
        stanza3.aggiungiNemico(new Nemico("Pipistrello",8,6,2),25,41);
        stanza3.aggiungiNemico(new Nemico("Pipistrello",8,6,2),28,41);
        stanza3.aggiungiNemico(new Nemico("Scheletro",12,7,2),27,42);


        Stanza stanza4 = new Stanza("Stanza Media Destra", 33, 32, 8, 6);
        piano.aggiungiAmbiente(stanza4);
        stanza4.aggiungiNemico(new Nemico("Goblin",20,8,2),35,34);
        stanza4.aggiungiNemico(new Nemico("Zombie",16,7,2),37,34);
        stanza4.aggiungiNemico(new Nemico("Zombie",16,7,2),36,35);


        Stanza stanza5 = new Stanza("Stanza Media Centrale", 26, 32, 7, 6);
        piano.aggiungiAmbiente(stanza5);
        stanza5.aggiungiNemico(new Nemico("Zombie", 16,7,2),28,34);
        stanza5.aggiungiNemico(new Nemico("Goblin",20,8,2),30,34);
        stanza5.aggiungiNemico(new Nemico("Scheletro",12,7,2),29,35);


        Stanza stanza6 = new Stanza("Stanza Media Sinistra", 18, 32, 8, 6);
        piano.aggiungiAmbiente(stanza6);
        stanza6.aggiungiNemico(new Nemico("Goblin",20,8,2),20,34);
        stanza6.aggiungiNemico(new Nemico("Goblin",20,8,2),23,34);
        stanza6.aggiungiNemico(new Nemico("Pipistrello",8,6,2),22,35);


        Stanza stanza7 = new Stanza("Stanza alta", 26, 25, 6, 6);
        piano.aggiungiAmbiente(stanza7);
        stanza7.aggiungiNemico(new Nemico("Goblin",20,8,2),28,27);
        stanza7.aggiungiNemico(new Nemico("Goblin",20,8,2),30,27);
        stanza7.aggiungiNemico(new Nemico("Zombie",16,7,2),29,28);


        Stanza stanzaBoss = new Stanza("Stanza Boss", 32, 25, 9, 6);
        piano.aggiungiAmbiente(stanzaBoss);
        stanzaBoss.aggiungiNemico(new Boss("Mega Goblin",25,8,3),36,27);


        Stanza stanzaFinale = new Stanza("Stanza della Vittoria", 40, 25, 5, 6);
        piano.aggiungiAmbiente(stanzaFinale);


        Corridoio corridoio1 = new Corridoio("Corridoio Basso Sinistra", 2, 38, 7, 6);
        piano.aggiungiAmbiente(corridoio1);


        Corridoio corridoio2 = new Corridoio("Corridoio Basso Destra", 32, 37, 7, 7);
        piano.aggiungiAmbiente(corridoio2);


        Corridoio corridoio3 = new Corridoio("Corridoio Alto Sinistra", 20, 25, 6, 7);
        piano.aggiungiAmbiente(corridoio3);





        return piano;
    }


}
