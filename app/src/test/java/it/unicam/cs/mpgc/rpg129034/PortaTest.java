package it.unicam.cs.mpgc.rpg129034;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PortaTest {

    @Test
    void testPortaCreata(){
        Porta porta = new Porta();
        assertTrue(porta.isAperta());
    }

    @Test
    void testApriPorta(){
        Porta porta = new Porta();
        porta.apri();

        assertTrue(porta.isAperta());
    }


}
