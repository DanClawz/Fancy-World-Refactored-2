package factory;

import mondo.Luogo;
import mondo.Mondo;

public abstract class AbstractFactory {

    public static AbstractFactory createFactory() {
        return new MenuFactory();
    }

    public abstract AbstractProductMenu createMenu(TipoPartita tipoPartita);
}
