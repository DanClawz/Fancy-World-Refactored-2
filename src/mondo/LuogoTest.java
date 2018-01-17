package mondo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LuogoTest {
    // In questo test avremo bisogno principalmente dell'oggetto Luogo
    Mondo m = new Mondo("mondo1", 1);
    Luogo l = m.getMondo().get(0);

    // Nei prossimi metodi testeremo tutte le posizioni, per verificare se il metodo considerato ritorna le coordinate giuste
    @Test
    void posizioneNuovaSud() {
        l.setPosCorrente(new Coordinata(3, 3));
        assertEquals(new Coordinata(4, 3).getX(), l.posizioneNuova('s').getX());
        assertEquals(new Coordinata(4, 3).getY(), l.posizioneNuova('s').getY());
    }
    @Test
    void posizioneNuovaNord() {
        l.setPosCorrente(new Coordinata(3, 3));
        assertEquals(new Coordinata(2, 3).getX(), l.posizioneNuova('n').getX());
        assertEquals(new Coordinata(2, 3).getY(), l.posizioneNuova('n').getY());
    }
    @Test
    void posizioneNuovaEst() {
        l.setPosCorrente(new Coordinata(3, 3));
        assertEquals(new Coordinata(3, 4).getX(), l.posizioneNuova('e').getX());
        assertEquals(new Coordinata(3, 4).getY(), l.posizioneNuova('e').getY());
    }
    @Test
    void posizioneNuovaOvest() {
        l.setPosCorrente(new Coordinata(3, 3));
        assertEquals(new Coordinata(3, 2).getX(), l.posizioneNuova('w').getX());
        assertEquals(new Coordinata(3, 2).getY(), l.posizioneNuova('w').getY());
    }

    //  Qui controlliamo se il metodo riconosce uno dei passaggi presenti nella mappa, ritornando la sua precisa coordinata
    @Test
    void passaggioSuCoordinata() {
        assertEquals(13, l.passaggioSuCoordinata(new Coordinata(13, 19)).getCoordinata().getX());
        assertEquals(19, l.passaggioSuCoordinata(new Coordinata(13, 19)).getCoordinata().getY());
    }

}


