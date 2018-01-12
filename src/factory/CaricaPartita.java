package factory;

import giocatore.Giocatore;
import mondo.Mondo;
import partita.LetturaScritturaPartita;
import partita.ListaPartite;
import partita.Partita;
import system_msg.Msg;
import utility.MyUtil;

import java.util.ArrayList;

public class CaricaPartita extends AbstractProductMenu {

    private ListaPartite partite = LetturaScritturaPartita.leggi();
    private int id;

    @Override
    public Partita getPartita() {
        return new Partita(getId(), getNomePartita(), getGiocatore(), getAutosave(), getMondi(), getAbilitaCambiaMondo(), getSceltaMondo());
    }

    @Override
    public int getId() {

        String[] opzioni = new String[this.partite.getPartite().size()];
        for (int i = 0; i < this.partite.getPartite().size(); i++)
            opzioni[i] = this.partite.getPartite().get(i).toString();

        this.id = MyUtil.myMenu(Msg.msgCaricaPartita(), opzioni)-1;
        return id;

    }

    @Override
    public String getNomePartita() {
        return partite.getPartite().get(id).getNomePartita();
    }

    @Override
    public Giocatore getGiocatore() {
        return partite.getPartite().get(id).getGiocatore();
    }

    @Override
    public boolean getAutosave() {
        return partite.getPartite().get(id).isAutoSave();
    }

    @Override
    public ArrayList<Mondo> getMondi() {
        return partite.getPartite().get(id).getMondi();
    }

    @Override
    public int getSceltaMondo() {
        return partite.getPartite().get(id).getScelta();
    }

    @Override
    public boolean getAbilitaCambiaMondo() {
        return partite.getPartite().get(id).isAbilitaCambiaMondo();
    }
}


