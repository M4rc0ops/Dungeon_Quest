package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BossTest {

    @Test
    public void testCreazioneBoss(){
    Boss boss= new Boss("nome",200,20,10);

    assertEquals("nome", boss.getNome());
    assertEquals(200, boss.getPuntiVita());
    assertEquals(200, boss.getMaxVita());
    assertEquals(20, boss.getAttacco());
    assertEquals(10, boss.getDifesa());

    }


    @Test
    void testBossSconfitto(){
        Boss boss = new Boss("nome",200,20,10);

        boss.subisciDanno(200);
        assertTrue(boss.sconfitta());
    }




}
