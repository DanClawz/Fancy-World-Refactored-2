package giocatore;

import mondo.Chiave;
import mondo.Coordinata;
import mondo.Luogo;
import mondo.Mondo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GiocatoreTest {

    // Per questo test ci servirà istanziato un oggetto di tipo Giocatore
    Giocatore g = new Giocatore();

    // Qui viene testato il metodo con i valori di default del giocatore (max n chiavi: 5, max peso chiavi: 50)
    @Test
    void checkChiaveRaccoglibileDefault() {
        Chiave c = new Chiave(new Coordinata(2, 4));
        assertEquals(true, g.checkChiaveRaccoglibile(c));
    }

    // Qui viene testato il metodo supponendo che il giocatore possa avere al max 1 chiave
    @Test
    void checkChiaveRaccoglibileMax1Chiave() {
        ArrayList<Chiave> chiavi = new ArrayList<Chiave>();     // viene creata una lista locale di chiavi, che successivamente verrà assegnata al giocatore
        chiavi.add(new Chiave(new Coordinata(4, 5)));
        g.setMaxNumChiavi(1);   // viene imposto il vincolo di max 1 chiave
        g.setChiavi(chiavi);
        Chiave c1 = new Chiave(new Coordinata(15, 6));  // questa chiave non potrà essere raccolta, per via del vincolo imposto
        assertEquals(false, g.checkChiaveRaccoglibile(c1));
    }

    // Qui viene testato il metodo supponendo che il peso cumulabile delle chiavi sia di massimo 2
    @Test
    void checkChiaveRaccoglibilePesoMax2() {
        ArrayList<Chiave> chiavi2 = new ArrayList<Chiave>();    // viene creata una lista locale vuota di chiavi, che successivamente verrà assegnata al giocatore
        g.setChiavi(chiavi2);
        g.setCapacitaInventario(2);     // viene imposto il vincolo del peso cumulabile di 2
        Chiave c2 = new Chiave(new Coordinata(9, 2));
        c2.setPassaggioDaAprire('C');   // questa chiave è di tipo Cristallo, quindi di peso 8; il giocatore non potrà raccoglierla
        assertEquals(false, g.checkChiaveRaccoglibile(c2));
    }

}


