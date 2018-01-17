package mondo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MondoTest {
    // In questo test ci servirà una variabile di tipo Mondo
    Mondo m = new Mondo("mondo1", 1);

    // Qui controlliamo se il nome del luogo goal è effettivamente identico a quello ritornato dal metodo
    @Test
    void luogoGoal() {
        assertEquals("La Spirale", m.luogoGoal());
    }

    // Qui controlliamo se il metodo ritorna effettivamente la stessa chiave raccolta dal giocatore
    @Test
    void raccogliChiave() {
        Luogo l = m.getMondo().get(0);  // prendiamo in considerazione il luogo1
        ArrayList<Chiave> chiavi = new ArrayList<Chiave>();     // creiamo la nostra ArrayList di chiavi, da assegnare successivamente al luogo
        l.setPosCorrente(new Coordinata(2,5));  // settiamo la nostra posizione corrente

        Chiave c = new Chiave(new Coordinata(2, 5));    // creiamo una chiave che sia sulla stessa coordinata, in modo da poterla raccogliere
        c.setPassaggioDaAprire('L');    // settiamo a tale chiave un tipo, per ottenere un riscontro nel metodo assertEquals

        chiavi.add(c);  // aggiungiamo la chiave alla lista
        l.setChiavi(chiavi);
        // il tipo della chiave creata prima combacia per forza con la chiave raccolta, ovvero l'abbiamo effettivamente raccolta
        assertEquals(c.getTipoChiave(), m.raccogliChiave().getTipoChiave());

    }

}


