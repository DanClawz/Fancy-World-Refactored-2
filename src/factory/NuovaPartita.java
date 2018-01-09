package factory;

import giocatore.Giocatore;
import mondo.Mondo;
import partita.LetturaScritturaPartita;
import partita.ListaPartite;
import partita.MondoConfig;
import partita.Partita;
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
        return (!partite.getPartite().isEmpty() ? partite.getPartite().get(partite.getPartite().size()-1).getId()+1 : 0);
    }

    @Override
    public String getNomePartita() {
        return MyUtil.stringInputNonVuoto("\nInserisci nome salvataggio");
    }

    @Override
    public Giocatore getGiocatore() {
        return new Giocatore();
    }

    @Override
    public boolean getAutosave() {
        return (MyUtil.controlledCharInput("Abilitare autosalvataggio? [s-n]", 's', 'n') == 's' ? true : false);
    }

    @Override
    public ArrayList<Mondo> getMondi() {
        boolean menu = true;
        mondi = new ArrayList<Mondo>();
        for (int i = 1; i <= N_MONDI; i++)
            mondi.add(new Mondo("mondo" + i, i));

        // configurazione mondi
        while(menu) {
            if (MyUtil.controlledCharInput("Vuoi configurare i mondi?", 's', 'n') == 's') {
                String opzioni[] = new String[mondi.size()];
                for (int i = 0; i < N_MONDI; i++) opzioni[i] = mondi.get(i).getNomeMondo();
                new MondoConfig(mondi.get(MyUtil.myMenu("Scegli il mondo da configurare", opzioni)-1), getGiocatore()).menuConfigMondo();
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
            return MyUtil.myMenu("\nScegli il mondo da giocare", opzioni);
        }
    }

    @Override
    public boolean getAbilitaCambiaMondo() {
        this.cambiaMondo = (MyUtil.controlledCharInput("Vuoi giocare tutti i mondi in ordine?", 's', 'n') == 's' ? true : false);
        return this.cambiaMondo;
    }
}
