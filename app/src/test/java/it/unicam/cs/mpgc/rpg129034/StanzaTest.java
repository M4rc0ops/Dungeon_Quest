package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class StanzaTest {

    @Test
    void testCreazioneStanza(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);

        assertEquals("nome",stanza.getNome());
        assertTrue(stanza.getNemici().isEmpty());
    }



    @Test
    void testAggiungiNemico(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);
        Nemico nemico = new Nemico("nome",20,10,5);

        stanza.aggiungiNemico(nemico);

        assertEquals(1,stanza.getNemici().size());
        assertTrue(stanza.getNemici().contains(nemico));
    }


    @Test
    void testNemiciVivi(){
        List<Nemico> nemici =  new ArrayList<>();
        Nemico nemico = new Nemico("nome",20,10,5);
        nemici.add(nemico);
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);

        assertFalse(stanza.tuttiNemiciSconfitti());
    }


    @Test
    void testNemiciSconfitti(){
        List<Nemico> nemici =  new ArrayList<>();
        Nemico nemico1 = new Nemico("nome1",20,10,5);
        Nemico  nemico2 = new Nemico("nome2",30,15,7);

        nemici.add(nemico1);
        nemici.add(nemico2);

        nemico1.subisciDanno(20);
        nemico2.subisciDanno(30);

        Stanza stanza = new Stanza("nome1",nemici,0,0,5,5);
        assertTrue(stanza.tuttiNemiciSconfitti());

    }


    @Test
    void testStanzaSenzaNemici(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);

        assertTrue(stanza.tuttiNemiciSconfitti());
    }


    @Test
    void testCreazioneStanzaNomeNullo(){
        List<Nemico> nemici =  new ArrayList<>();

        assertThrows(IllegalArgumentException.class, ()-> new Stanza(null,nemici,0,0,5,5));
    }


    @Test
    void testCreazioneStanzaNomeVuoto(){
        List<Nemico> nemici =  new ArrayList<>();

        assertThrows(IllegalArgumentException.class, ()-> new Stanza("",nemici,0,0,5,5));
    }


    @Test
    void testCreazioneStanzaNemiciNull(){
        assertThrows(IllegalArgumentException.class, ()-> new Stanza("nome",null,0,0,5,5));
    }


    @Test
    void testAggiungiNemicoNullo(){
        List<Nemico> nemici =  new ArrayList<>();
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);

        assertThrows(IllegalArgumentException.class, ()-> stanza.aggiungiNemico(null));
        assertTrue(stanza.getNemici().isEmpty());
    }


    @Test
    void testCopiaListaNemici(){
        List<Nemico> nemici =  new ArrayList<>();
        Nemico nemico = new Nemico("nome",20,10,5);
        nemici.add(nemico);
        Stanza stanza = new Stanza("nome",nemici,0,0,5,5);

        nemici.clear();

        assertEquals(1, stanza.getNemici().size());
        assertTrue(stanza.getNemici().contains(nemico));
    }


    @Test
    void testGetCopiaNemici(){
        List<Nemico> nemici =  new ArrayList<>();
        Nemico nemico = new Nemico("nome",20,10,5);
        nemici.add(nemico);
        Stanza  stanza = new Stanza("nome",nemici,0,0,5,5);

        List<Nemico> nemiciOttenuti =  stanza.getNemici();
        nemiciOttenuti.clear();

        assertEquals(1,stanza.getNemici().size());
        assertTrue(stanza.getNemici().contains(nemico));
    }


    @Test
    void testCreazioneNemiciConNemicoNullo(){
        List<Nemico> nemici =  new ArrayList<>();
        nemici.add(null);

        assertThrows(IllegalArgumentException.class, ()-> new Stanza("nome",nemici,0,0,5,5));
    }


    @Test
    void testCoordinateStanza(){
        Stanza stanza = new Stanza("nome",new ArrayList<>(),3,5,5,5);

        assertEquals(3,stanza.getCoordinataX());
        assertEquals(5,stanza.getCoordinataY());
    }










}
