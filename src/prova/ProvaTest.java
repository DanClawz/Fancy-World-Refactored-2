package prova;

import mondo.Coordinata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProvaTest {
    // Per questo test verranno usate le versioni test dei metodi, che avranno già impostata la risposta corretta;
    // il metodo dovrà ritornare il punteggio esatto della prova

    @Test
    void prova1Test() {
        assertEquals(10, new Prova(new Coordinata(1, 1), 1).prova1Test(0));
    }

    @Test
    void prova2Test() {
        assertEquals(20, new Prova(new Coordinata(2, 1), 2).prova2Test(0));
    }

    @Test
    void prova3Test() {
        assertEquals(30, new Prova(new Coordinata(3, 1), 3).prova3Test(0));
    }

}