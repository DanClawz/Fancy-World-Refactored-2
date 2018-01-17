package partita;

import factory.AbstractFactory;
import factory.TipoPartita;
import giocatore.Giocatore;
import mondo.Mondo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PartitaTest {
    // In questo test useremo la partita Tutorial per testare i seguenti metodi
    Partita p = new Partita(new Giocatore(), new Mondo("tutorial", 99));

    // Qui testiamo se il metodo effettivamente restituisce 0, ovvero dopo 20 mosse eseguite, il counter viene resettato
    @Test
    void salvarePartita() {
        assertEquals(0, p.salvarePartita(19));
    }

    // Qui testiamo se il nome del luogo di destinazione corrisponde a ciò che ritorna il metodo
    @Test
    void nomeDestinazione() {
        assertEquals("Molto spesso incontrerai dei passaggi bloccati," +
                        " per aprirli devi raccogliere le loro rispettive chiavi " +
                        "(attento al tipo di chiave e del passaggio!). Le chiavi " +
                        "possono essere anche depositate.",
                p.nomeDestinazione(2));
    }

}

