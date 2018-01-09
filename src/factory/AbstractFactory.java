package factory;

import mondo.Luogo;
import mondo.Mondo;

public abstract class AbstractFactory {
    public static AbstractFactory getFactory(FancyWorld sezione) {
        AbstractFactory factory = null;

        switch(sezione) {
            case MENU: factory = new MenuFactory(); break;
        }

        return factory;
    }


    public abstract AbstractProductMenu createMenu(TipoPartita tipoPartita);
}
