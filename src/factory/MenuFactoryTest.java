package factory;

import mondo.Tipo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuFactoryTest {

    /** In questo test si cerca di assicurarsi che il metodo non ritorni
     *  alcun oggetto nullo.
     */

    @Test
    void createMenu() {
        AbstractFactory factory = AbstractFactory.createFactory();
        assertNotNull(factory.createMenu(TipoPartita.NUOVAPARTITA));
        assertNotNull(factory.createMenu(TipoPartita.CARICAPARTITA));
        assertNotNull(factory.createMenu(TipoPartita.TUTORIAL));
    }

}

