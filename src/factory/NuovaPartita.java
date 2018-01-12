package factory;

import giocatore.Giocatore;
import mondo.Mondo;
import partita.LetturaScritturaPartita;
import partita.ListaPartite;
import partita.MondoConfig;
import partita.Partita;
import system_msg.Msg;
import utility.MyUtil;

import java.util.ArrayList;

public class NuovaPartita extends AbstractProductMenu {

    private final static int N_MONDI = Integer.parseInt(MyUtil.leggiFile("./Mappe/n_mondi").get(0));
    private boolean cambiaMondo;
    private ArrayList<Mondo> mondi;

    @Override
    public Partita getPartita() {
        return new Partita(getId(), getNomePartita(), getGiocatore(), getAutosave(), getMondi(), getAbilitaCambiaMondo(), getSceltaMondo());
    }

    @Override
    public int getId() {
        ListaPartite partite = LetturaScritturaPartita.leggi();

        // si controlla se ci sono dei salvataggi sul file: se sì, viene letto l'id dell'ultimo salvataggio e viene incrementato di 1 per ottenere l'id della nuova partita
        // altrimenti l'id viene impostato a 0
        return (!partite.getPartite().isEmpty() ? partite.getPartite().get(partite.getPartite().size()-1).getId()+1 : 0);
    }

    @Override
    public String getNomePartita() {
        return MyUtil.stringInputNonVuoto(Msg.msgNomeSalvataggio());
    }

    @Override
    public Giocatore getGiocatore() {
        return new Giocatore();
    }

    @Override
    public boolean getAutosave() {
        return MyUtil.controlledBoolInput(Msg.msgAbilitaAutosave(), Msg.opzioniSN());
    }

    @Override
    public ArrayList<Mondo> getMondi() {
        boolean menu = true;
        mondi = new ArrayList<Mondo>();
        for (int i = 1; i <= N_MONDI; i++)
            mondi.add(new Mondo("mondo" + i, i));

        // configurazione mondi
        while(menu) {
            if (MyUtil.controlledBoolInput(Msg.msgConfigMondi(), Msg.opzioniSN())) {
                String opzioni[] = new String[mondi.size()];
                for (int i = 0; i < N_MONDI; i++) opzioni[i] = mondi.get(i).getNomeMondo();
                new MondoConfig(mondi.get(MyUtil.myMenu(Msg.msgConfigSceltaMondo(), opzioni)-1), getGiocatore()).menuConfigMondo();
            }
            else menu = false;
        }

        return mondi;
    }

    @Override
    public int getSceltaMondo() {
        if (this.cambiaMondo) return 1;
        else {
            String[] opzioni = new String[mondi.size()];
            for (int i = 0; i < mondi.size(); i++) {
                opzioni[i] = mondi.get(i).getNomeMondo();
            }
            return MyUtil.myMenu(Msg.msgGiocaSceltaMondo(), opzioni);
        }
    }

    @Override
    public boolean getAbilitaCambiaMondo() {
        this.cambiaMondo = MyUtil.controlledBoolInput(Msg.msgAbilitaCambiaMondo(), Msg.opzioniSN());
        return this.cambiaMondo;
    }
}
