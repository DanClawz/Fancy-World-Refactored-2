import java.util.ArrayList;

public class CaricaPartita {

    private ListaPartite partite;
    private boolean isNuovaPartita, abilitaCambiaMondo;
    private ArrayList<Mondo> mondi;
    public static final int N_MONDI = 2;

    public CaricaPartita() {
        partite = LetturaScritturaPartita.leggi();
        mondi = new ArrayList<Mondo>();
        menu();
        scegliPartita();
    }

    public CaricaPartita(boolean isNuovaPartita) {
        this.isNuovaPartita = isNuovaPartita;
        partite = LetturaScritturaPartita.leggi();
        mondi = new ArrayList<Mondo>();

        scegliPartita();
    }

    public void scegliPartita() {
        Partita p = null;
        Mondo m = null;
        Giocatore g = null;
        int id = -1;
        String nome = "";
        boolean autoSave = true;

        while(true) {
            if (isNuovaPartita || (!isNuovaPartita && partite.getPartite().isEmpty()) ) {
                id = generaId();
                for (int i = 1; i <= N_MONDI; i++) {
                    m = new Mondo("mondo" + i, i);
                    mondi.add(m);
                }

                g = new Giocatore();
                if (MyUtil.controlledCharInput("\nVuoi fare il tutorial? [s-n]", 's', 'n') == 's') new Partita(new Giocatore(0, 10), new Mondo("tutorial", 99));
                nome = MyUtil.stringInputNonVuoto("\nInserisci nome salvataggio");
                autoSave = MyUtil.controlledCharInput("Abilitare autosalvataggio? [s-n]", 's', 'n') == 's' ? true : false;

                // configurazione mondi
                for (int i = 0; i < mondi.size(); i++)
                    if (MyUtil.controlledCharInput("Vuoi configurare " + mondi.get(i).getNomeMondo(), 's', 'n') == 's')
                        new MondoConfig(mondi.get(i), g).menuConfigMondo();

                p = new Partita(id, nome, g, mondi, menuSceltaMondo(), this.abilitaCambiaMondo);
                break;

            }
            else if (!partite.getPartite().isEmpty()) {
                id = sceltaUtente() - 1;
                if(id < partite.getPartite().size()) {
                    mondi = partite.getPartite().get(id).getMondi();
                    g = partite.getPartite().get(id).getGiocatore();
                    nome = partite.getPartite().get(id).getNomePartita();
                    this.abilitaCambiaMondo = partite.getPartite().get(id).isAbilitaCambiaMondo();
                    p = new Partita(id, nome, g, mondi, partite.getPartite().get(id).getScelta(), this.abilitaCambiaMondo);
                    break;
                }
                else
                    break;
            }
        }


        p.autoSalvataggio(autoSave);
        p.salvaPartita();


        p.gioca();
    }

    public int sceltaUtente() {

        String[] opzioni = new String[partite.getPartite().size()];
        for (int i = 0; i < partite.getPartite().size(); i++)
            opzioni[i] = partite.getPartite().get(i).toString();

        return MyUtil.myMenu("\nCarica partita", opzioni);

    }

    public int generaId() {
        if (!partite.getPartite().isEmpty()) return partite.getPartite().get(partite.getPartite().size()-1).getId()+1;
        return 0;
    }

    public void menu() {
        boolean menu = true;
        while(menu) {
            switch(MyUtil.myMenu("\nGioca", "Nuova partita", "Carica partita")) {
                case 1: isNuovaPartita = true; break;
                case 2: isNuovaPartita = false; break;
            }
        }

    }


    public int menuSceltaMondo() {
        String[] opzioni = new String[mondi.size()+1];
        for (int i = 0; i < mondi.size(); i++) {
            opzioni[i] = mondi.get(i).getNomeMondo();
        }
        opzioni[mondi.size()] = "tutti";

        int input = MyUtil.myMenu("\nScegli il mondo da giocare", opzioni);

        if (input < opzioni.length) {
            this.abilitaCambiaMondo = false;
            return input;
        }
        else {
            this.abilitaCambiaMondo = true;
            return 0;
        }

    }

    public void setNuovaPartita(boolean nuovaPartita) {
        isNuovaPartita = nuovaPartita;
    }
}
