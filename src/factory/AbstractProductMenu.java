package factory;

import giocatore.Giocatore;
import mondo.Mondo;
import mondo.Tipo;
import partita.Partita;

import java.util.ArrayList;

public abstract class AbstractProductMenu {

    public abstract Partita getPartita();

    public abstract int getId();
    public abstract String getNomePartita();
    public abstract Giocatore getGiocatore();
    public abstract boolean getAutosave();
    public abstract ArrayList<Mondo> getMondi();
    public abstract int getSceltaMondo();
    public abstract boolean getAbilitaCambiaMondo();

}



