package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class StanzaTest {

    @Test
    void testCreazioneStanza(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",0,0,5,5);

        assertEquals("nome",stanza.getNome());
        assertTrue(stanza.getNemici().isEmpty());
    }



    @Test
    void testAggiungiNemico(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",0,0,5,5);
        Nemico nemico = new Nemico("nome",20,10,5);

        stanza.aggiungiNemico(nemico,1,1);

        assertEquals(1,stanza.getNemici().size());
        assertTrue(stanza.getNemici().contains(nemico));

        assertEquals(1, stanza.getPosizioneNemico(nemico).getX());
        assertEquals(1, stanza.getPosizioneNemico(nemico).getY());
    }


    @Test
    void testNemiciVivi(){
        Nemico nemico = new Nemico("nome",20,10,5);
        Stanza stanza = new Stanza("nome",0,0,5,5);

        stanza.aggiungiNemico(nemico,1,1);

        assertFalse(stanza.tuttiNemiciSconfitti());
    }


    @Test
    void testNemiciSconfitti(){
        Nemico nemico1 = new Nemico("nome1",20,10,5);
        Nemico  nemico2 = new Nemico("nome2",30,15,7);

        Stanza stanza = new Stanza("nome",0,0,5,5);

        nemico1.subisciDanno(20);
        nemico2.subisciDanno(30);


        assertTrue(stanza.tuttiNemiciSconfitti());

    }


    @Test
    void testStanzaSenzaNemici(){
        Stanza stanza = new Stanza("nome",0,0,5,5);

        assertTrue(stanza.tuttiNemiciSconfitti());
    }


    @Test
    void testCreazioneStanzaNomeNullo(){

        assertThrows(IllegalArgumentException.class, ()-> new Stanza(null,0,0,5,5));
    }


    @Test
    void testCreazioneStanzaNomeVuoto(){

        assertThrows(IllegalArgumentException.class, ()-> new Stanza("",0,0,5,5));
    }


    @Test
    void testAggiungiNemicoNullo(){
        Stanza stanza = new Stanza("nome",0,0,5,5);

        assertThrows(IllegalArgumentException.class, ()-> stanza.aggiungiNemico(null,1,1));
        assertTrue(stanza.getNemici().isEmpty());
    }


    @Test
    void testGetCopiaNemici(){
        Nemico nemico = new Nemico("nome",20,10,5);
        Stanza  stanza = new Stanza("nome",0,0,5,5);

        stanza.aggiungiNemico(nemico,1,1);

        List<Nemico> nemiciOttenuti =  stanza.getNemici();
        nemiciOttenuti.clear();

        assertEquals(1,stanza.getNemici().size());
        assertTrue(stanza.getNemici().contains(nemico));
    }


    @Test
    void testCoordinateStanza(){
        Stanza stanza = new Stanza("nome",3,5,5,5);

        assertEquals(3,stanza.getCoordinataX());
        assertEquals(5,stanza.getCoordinataY());
    }


    @Test
    void testAggiungiNemicoPosizioneFuoriStanza(){
        Stanza stanza = new Stanza("nome", 0, 0, 5, 5);
        Nemico nemico = new Nemico("nome", 20, 10, 5);

        assertThrows(IllegalArgumentException.class,
                () -> stanza.aggiungiNemico(nemico, 5, 1));

        assertTrue(stanza.getNemici().isEmpty());
    }

}
