package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class PianoTest {

    @Test
    void testCreazionePiano(){
        Piano piano = new Piano(1);

        assertEquals(1,piano.getNumero());
        assertTrue(piano.getAmbienti().isEmpty());
    }


    @Test
    void testCreazionePiano0(){
        assertThrows(IllegalArgumentException.class, () -> new Piano(0));
    }


    @Test
    void testCreazionePiano4(){
        assertThrows(IllegalArgumentException.class, () -> new Piano(4));
    }


    @Test
    void testAggiungiAmbiente(){
        Piano piano = new Piano(1);
        Stanza stanza = new Stanza("nome",0,0,5,5);

        piano.aggiungiAmbiente(stanza);

        assertEquals(1,piano.getAmbienti().size());
        assertTrue(piano.getAmbienti().contains(stanza));
    }


    @Test
    void testAggiungiAmbienteNullo(){
        Piano piano = new Piano(1);

        assertThrows(IllegalArgumentException.class, () -> piano.aggiungiAmbiente(null));
        assertTrue(piano.getAmbienti().isEmpty());
    }


    @Test
    void testGetAmbientiCopia(){
        Piano piano = new Piano(1);
        Stanza stanza = new Stanza("nome",0,0,5,5);
        piano.aggiungiAmbiente(stanza);

        List<Ambiente> ambienti = piano.getAmbienti();
        ambienti.clear();

        assertEquals(1,piano.getAmbienti().size());
        assertTrue(piano.getAmbienti().contains(stanza));
    }


    @Test
    void testGetNumeroAmbienti(){
        Piano piano = new Piano(1);

        assertEquals(0,piano.getNumeroAmbienti());

        piano.aggiungiAmbiente(new Stanza("nome",0,0,5,5));
        assertEquals(1,piano.getNumeroAmbienti());
    }


    @Test
    void testAggiungiAmbienteDuplicato(){
        Piano piano = new Piano(1);
        Stanza stanza = new Stanza("nome",0,0,5,5);
        piano.aggiungiAmbiente(stanza);

        assertThrows(IllegalArgumentException.class, () -> piano.aggiungiAmbiente(stanza));
        assertEquals(1,piano.getNumeroAmbienti());
    }


    @Test
    void testAggiungiAmbientiConStessoNome(){
        Piano piano = new Piano(1);

        Stanza stanza1 = new Stanza("nome",0,0,5,5);
        Stanza stanza2 = new Stanza("nome",5,0,5,5);

        piano.aggiungiAmbiente(stanza1);
        piano.aggiungiAmbiente(stanza2);

        assertEquals(2,piano.getNumeroAmbienti());
    }


    @Test
    void testContieneAmbiente(){
        Piano piano = new Piano(1);
        Stanza stanza = new Stanza("nome",0,0,5,5);

        assertFalse(piano.contieneAmbiente(stanza));
        piano.aggiungiAmbiente(stanza);
        assertTrue(piano.contieneAmbiente(stanza));
    }


    @Test
    void testAggiungiAmbienteStesseCoordinate(){
        Piano piano = new Piano(1);
        Stanza stanza1= new Stanza("nome1",1,2,5,5);
        Stanza stanza2= new Stanza("nome2",1,2,5,5);

        piano.aggiungiAmbiente(stanza1);

        assertThrows(IllegalArgumentException.class, () -> piano.aggiungiAmbiente(stanza2));
        assertEquals(1, piano.getNumeroAmbienti());
    }


    @Test
    void testTrovaAmbienteEsistente(){
        Piano piano = new Piano(1);
        Stanza stanza= new  Stanza("nome",2,3,5,5);

        piano.aggiungiAmbiente(stanza);

        assertEquals(stanza,piano.trovaAmbiente(2,3));
    }


    @Test
    void testTrovaAmbienteNonEsistente(){
        Piano piano = new Piano(1);

        assertNull(piano.trovaAmbiente(2,3));
    }


    @Test
    void testTrovaAmbienteCoordinateInterne(){
        Piano piano = new Piano(1);
        Stanza stanza= new Stanza("nome",2,3,5,5);

        piano.aggiungiAmbiente(stanza);
        assertEquals(stanza,piano.trovaAmbiente(4,5));
    }


    @Test
    void testGrigliaInizialeConMuri(){
        Piano piano = new Piano(1);

        assertEquals(TipoCella.MURO, piano.getTipoCella(0,0));
        assertEquals(TipoCella.MURO, piano.getTipoCella(25,25));
        assertEquals(TipoCella.MURO, piano.getTipoCella(44,44   ));
    }


    @Test
    void testCoordinateNonValide(){
        Piano piano = new Piano(1);

        assertThrows(IllegalArgumentException.class, ()-> piano.getTipoCella(-1,0));
        assertThrows(IllegalArgumentException.class, ()-> piano.getTipoCella(50,0));
        assertThrows(IllegalArgumentException.class, ()-> piano.getTipoCella(0,-1));
    }


    @Test
    void testAggiungiCorridoio(){
        Piano piano = new Piano(1);

        Corridoio corridoio= new Corridoio("nome",5,5,5,5);
        piano.aggiungiAmbiente(corridoio);

        assertEquals(1, piano.getNumeroAmbienti());
        assertTrue(piano.getAmbienti().contains(corridoio));
    }


    @Test
    void testAggiungiCorridoioEStanza(){
        Piano piano = new Piano(1);
        Stanza  stanza= new Stanza("nome",2,3,5,5);
        Corridoio corridoio= new Corridoio("nome",7,3,5,5);

        piano.aggiungiAmbiente(stanza);
        piano.aggiungiAmbiente(corridoio);

        assertEquals(2,piano.getNumeroAmbienti());
        assertTrue(piano.contieneAmbiente(stanza));
        assertTrue(piano.contieneAmbiente(stanza));
    }


    @Test
    void testImpostaMappa(){
        Piano piano = new Piano(1);

        String[]mappa = new String[45];

        for(int i=0; i<45;i++){
            mappa[i] = "#".repeat(45);
        }

        char[] caratteri= {'#','_','p','P','s'};
        StringBuilder riga = new  StringBuilder();

        for(char carattere : caratteri){
            riga.append(carattere);
        }

        riga.append("#".repeat(40));

        mappa[10] =  riga.toString();
        piano.impostaMappa(mappa);

        assertEquals(TipoCella.MURO, piano.getTipoCella(0,10));
        assertEquals(TipoCella.PAVIMENTO, piano.getTipoCella(1,10));
        assertEquals(TipoCella.PORTA_COMUNE, piano.getTipoCella(2,10));
        assertEquals(TipoCella.PORTA_BOSS, piano.getTipoCella(3,10));
        assertEquals(TipoCella.PORTA_SCALE, piano.getTipoCella(4,10));

    }


    @Test
    void testImpostaMappaNumRigheSbagliate(){
        Piano piano = new Piano(1);

        String[]mappa = new String[44];

        assertThrows(IllegalArgumentException.class, ()-> piano.impostaMappa(mappa));
    }


    @Test
    void testImpostaMappaLughezzaRigaErrata(){
        Piano piano = new Piano(1);
        String[]mappa = new String[45];

        for(int i=0; i<45;i++){
            mappa[i] = "#".repeat(45);
        }
        mappa[10] = "#".repeat(44);

        assertThrows(IllegalArgumentException.class, ()-> piano.impostaMappa(mappa));
    }


    @Test
    void testCarattereNonValido(){
        Piano piano = new Piano(1);
        String[]mappa = new String[45];

        for (int i = 0; i < 45; i++) {
            mappa[i]= "#".repeat(45);
        }
        mappa[10]= "X"+"#".repeat(44);

        assertThrows(IllegalArgumentException.class, ()-> piano.impostaMappa(mappa));
    }


    @Test
    void testMappaPiano1(){
        Piano piano = new Piano(1);

        piano.impostaMappa(MappaPiano1.creaMappa());

        assertEquals(TipoCella.VUOTO, piano.getTipoCella(0,0));
        assertEquals(TipoCella.MURO, piano.getTipoCella(22,22));
        assertEquals(TipoCella.VUOTO, piano.getTipoCella(44,44));
    }


    @Test
    void testImpostaMappaVuota(){
        Piano piano = new Piano(1);

        String[]mappa = new String[45];

            for (int i = 0; i <45; i++) {
            mappa[i]=  "#".repeat(45);
        }
            mappa[10] = " "+"#".repeat(44);

            piano.impostaMappa(mappa);

            assertEquals(TipoCella.VUOTO, piano.getTipoCella(0,10));
            assertEquals(TipoCella.MURO, piano.getTipoCella(1,10));
    }


    @Test
    void testGetTipoCella(){
        Piano piano = new Piano(1);
        String[]mappa = new String[45];

        for (int i = 0; i <45; i++) {
            mappa[i]="#".repeat(45);
        }
        mappa[10] = "#_pPs"+"#".repeat(40);

        piano.impostaMappa(mappa);

        assertEquals(TipoCella.MURO, piano.getTipoCella(0,10));
        assertEquals(TipoCella.PAVIMENTO, piano.getTipoCella(1,10));
        assertEquals(TipoCella.PORTA_COMUNE, piano.getTipoCella(2,10));
        assertEquals(TipoCella.PORTA_BOSS, piano.getTipoCella(3,10));
        assertEquals(TipoCella.PORTA_SCALE, piano.getTipoCella(4,10));
    }


    


}
