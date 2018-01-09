package factory;

import giocatore.Giocatore;
import mondo.Mondo;
import partita.Partita;

import java.util.ArrayList;

public class Tutorial extends AbstractProductMenu {
    @Override
    public Partita getPartita() {
        return new Partita(getGiocatore(), getMondoTutorial());
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getNomePartita() {
        return null;
    }

    @Override
    public Giocatore getGiocatore() {
        return new Giocatore(0, 10);
    }

    @Override
    public boolean getAutosave() {
        return false;
    }

    @Override
    public ArrayList<Mondo> getMondi() {
        return null;
    }

    @Override
    public int getSceltaMondo() {
        return 0;
    }

    @Override
    public boolean getAbilitaCambiaMondo() {
        return false;
    }

    public Mondo getMondoTutorial() {
        return new Mondo("tutorial", 99);
    }
}
