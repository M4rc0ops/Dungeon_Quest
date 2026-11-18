package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EntitaTest {

    private static class EntitaDiProva extends Entita {

        public EntitaDiProva(String nome, int puntiVita, int attacco, int difesa) {
            super(nome, puntiVita, attacco, difesa);
        }

        public void provaImpostaVita(int puntiVita){
            curaPuntiVita(puntiVita);
        }
    }


    @Test
    void testNomeNullo(){ assertThrows(IllegalArgumentException.class, () -> new  EntitaDiProva(null,100,10,5)); }


    @Test
    void testNomeVuoto(){ assertThrows(IllegalArgumentException.class, () -> new  EntitaDiProva(" ",100,10,5)); }


    @Test
    void testVitaNulla(){ assertThrows(IllegalArgumentException.class, () -> new  EntitaDiProva("nome",0,10,5)); }


    @Test
    void testAttaccoNullo(){ assertThrows(IllegalArgumentException.class, () -> new  EntitaDiProva("nome",100,0,5)); }


    @Test
    void testDifesaNulla(){ assertThrows(IllegalArgumentException.class, () -> new EntitaDiProva("nome", 100, 10, -1)); }


    @Test
    void testCreazioneEntita(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        assertEquals("nome",entita.getNome());
        assertEquals(100,entita.getPuntiVita());
        assertEquals(100, entita.getMaxVita());
        assertEquals(10,entita.getAttacco());
        assertEquals(5,entita.getDifesa());
    }


    @Test
    void testDanno(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        entita.subisciDanno(20);
        assertEquals(80,entita.getPuntiVita());
    }


    @Test
    void testDannoNegativo(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        assertThrows(IllegalArgumentException.class, ()->entita.subisciDanno(-10));
    }


    @Test
    void testVitaNegativa(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        entita.subisciDanno(150);
        assertEquals(0,entita.getPuntiVita());
    }


    @Test
    void testSconfitta(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        assertFalse(entita.sconfitta());
        entita.subisciDanno(100);
        assertTrue(entita.sconfitta());
    }


    @Test
    void testCura(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        entita.subisciDanno(30);
        entita.provaImpostaVita(80);
        assertEquals(80,entita.getPuntiVita());
    }


    @Test
    void testCuraOltreMassimo(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        entita.provaImpostaVita(150);
        assertEquals(100,entita.getPuntiVita());
    }


    @Test
    void testCuraNegativa(){
        EntitaDiProva entita= new EntitaDiProva("nome",100,10,5);

        assertThrows(IllegalArgumentException.class, ()->entita.provaImpostaVita(-10));
    }


}
