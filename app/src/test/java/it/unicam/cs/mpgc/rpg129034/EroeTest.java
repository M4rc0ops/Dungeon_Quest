package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EroeTest {

    @Test
    void testCreazioneEroe(){
        Eroe eroe = new Eroe("nome");

        assertEquals(50, eroe.getPuntiVita());
        assertEquals(50, eroe.getMaxVita());
        assertEquals(8, eroe.getAttacco());
        assertEquals(5, eroe.getDifesa());
    }


    @Test
    void testPosizioneIniziale(){
        Eroe eroe = new Eroe("nome");

        assertEquals(25, eroe.getPosizione().getX());
        assertEquals(41, eroe.getPosizione().getY());
    }


    @Test
    void testCura(){
        Eroe eroe = new Eroe("nome");

        eroe.subisciDanno(30);
        eroe.cura(20);
        assertEquals(40, eroe.getPuntiVita());
    }


    @Test
    void testCuraMax(){
        Eroe eroe = new Eroe("nome");

        eroe.cura(20);
        assertEquals(50, eroe.getPuntiVita());
    }


    @Test
    void testCuraNegativo(){
        Eroe eroe = new Eroe("nome");

        assertThrows(IllegalArgumentException.class, () -> eroe.cura(-10));
    }


    @Test
    void testRecuperaVitaStanza(){
        Eroe eroe = new Eroe("nome");

        eroe.subisciDanno(40);
        eroe.recuperaVitaStanza();

        assertEquals(20, eroe.getPuntiVita());
    }



}
