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

    public void scegliPartita() {
        Partita p = null;
        Mondo m = null;
        Giocatore g = null;
        int id = -1;
        String nome = "";
        boolean autoSave = true;

        while(true) {
            if (isNuovaPartita) {
                id = generaId();
                for (int i = 1; i <= N_MONDI; i++) {
                    m = new Mondo("mondo" + i, i);
                    mondi.add(m);
                }
                g = new Giocatore();
                nome = MyUtil.stringInput("\nInserisci nome salvataggio");
                autoSave = MyUtil.controlledCharInput("Abilitare autosalvataggio? [s-n]", 's', 'n') == 's' ? true : false;
                p = new Partita(id, nome, g, mondi, menuSceltaMondo(), this.abilitaCambiaMondo);
                break;

            }
            else if (!partite.getPartite().isEmpty()) {
                id = sceltaUtente() - 1;
                if(id < partite.getPartite().size()) {
                    mondi = partite.getPartite().get(id).getMondi();
                    g = partite.getPartite().get(id).getGiocatore();
                    nome = partite.getPartite().get(id).getNomePartita();
                    p = new Partita(id, nome, g, mondi, partite.getPartite().get(id).getScelta(), this.abilitaCambiaMondo);
                    break;
                }
                else
                    menu();
            }
            else {
                System.out.println("Non ci sono salvataggi!");
                menu();
            }
        }


        p.autoSalvataggio(autoSave);
        p.salvaPartita();

        p.gioca();
    }

    public int sceltaUtente() {

        String[] opzioni = new String[partite.getPartite().size()+1];
        for (int i = 0; i < partite.getPartite().size(); i++) {
            opzioni[i] = partite.getPartite().get(i).toString();
        }
        opzioni[opzioni.length-1] = "Annulla";

        return MyUtil.myMenu("\nCarica partita", opzioni);

    }

    public int generaId() {
        if (!partite.getPartite().isEmpty()) return partite.getPartite().get(partite.getPartite().size()-1).getId()+1;
        return 0;
    }

    public void menu() {
        switch(MyUtil.myMenu("\nGioca", "Nuova partita", "Carica partita")) {
            case 1: isNuovaPartita = true; break;
            case 2: isNuovaPartita = false; break;
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
            abilitaCambiaMondo = false;
            return input;
        }
        else {
            abilitaCambiaMondo = true;
            return 0;
        }

    }


}
