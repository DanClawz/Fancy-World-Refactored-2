package mondo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChiaveTest {
    // Per questo test ci serve un oggetto di tipo Chiave
    Chiave c = new Chiave(new Coordinata(1, 2));

    // Nei prossimi due metodi testiamo il metodo equals con i passaggi (in entrambi i casi di true e false)
    @Test
    void equalsPassaggioTrue() {
        c.setPassaggioDaAprire('L');
        Passaggio p = new Passaggio(new Portale(new Coordinata(1, 2), 1, 2, Tipo.LEGNO), true);
        assertTrue(c.equals(p));
    }

    @Test
    void equalsPassaggioFalse() {
        c.setPassaggioDaAprire('L');
        Passaggio p1 = new Passaggio(new Portale(new Coordinata(1, 2), 1, 2, Tipo.ARGENTO), true);
        assertFalse(c.equals(p1));
    }

    // Nei prossimi due metodi testiamo il metodo equals con le coordinate (in entrambi i casi di true e false)

    @Test
    void equalsCoordinataTrue() {
        Coordinata coord = new Coordinata(1, 2);
        assertTrue(c.equals(coord));
    }

    @Test
    void equalsCoordinataFalse() {
        Coordinata coord = new Coordinata(5, 6);
        assertFalse(c.equals(coord));
    }

}

