package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NemicoTest {

    @Test
    void testCreazioneNemico() {
        Nemico nemico = new Nemico("nome", 50, 5, 3);

        assertEquals("nome", nemico.getNome());
        assertEquals(50, nemico.getPuntiVita());
        assertEquals(50, nemico.getMaxVita());
        assertEquals(5, nemico.getAttacco());
        assertEquals(3, nemico.getDifesa());

    }


}
