package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CombattimentoTest {

    @Test
    public void testEroeAttaccaNemicoContrattacca(){
        Eroe eroe = new Eroe("nome");
        Nemico nemico = new Nemico("nome", 30, 8, 2);
        Combattimento combattimento = new Combattimento();

        combattimento.eseguiTurno(eroe,nemico);

        assertEquals(24, nemico.getPuntiVita());
        assertEquals(47, eroe.getPuntiVita());
    }


    @Test
    public void testNemicoSconfitto(){
        Eroe eroe = new Eroe("nome");
        Nemico nemico = new Nemico("nome", 5, 8, 2);
        Combattimento combattimento = new Combattimento();

        combattimento.eseguiTurno(eroe,nemico);

        assertEquals(0, nemico.getPuntiVita());
        assertEquals(50, eroe.getPuntiVita());
    }


    @Test
    public void testDannoMiniMo1(){
        Eroe eroe = new Eroe("nome");
        Nemico nemico = new Nemico("nome", 30, 8, 20);
        Combattimento combattimento = new Combattimento();

        int danno= combattimento.calcolaDanno(eroe,nemico);

        assertEquals(1,danno);
    }




}
