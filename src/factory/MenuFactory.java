package factory;

import mondo.Luogo;
import mondo.Mondo;

public class MenuFactory extends AbstractFactory {

    @Override
    public AbstractProductMenu createMenu(TipoPartita tipoPartita) {
        switch(tipoPartita) {
            case NUOVAPARTITA: return new NuovaPartita();
            case CARICAPARTITA: return new CaricaPartita();
            case TUTORIAL: return new Tutorial();
        }
        return null;
    }


}

