public class CaricaPartita {

    private ListaPartite partite;
    private boolean isNuovaPartita;

    public CaricaPartita() {
        partite = LetturaScritturaPartita.leggi();
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
                m = new Mondo("mondo1");
                g = new Giocatore();
                nome = MyUtil.stringInput("Inserisci nome salvataggio");
                autoSave = MyUtil.controlledCharInput("Abilitare autosalvataggio? [s-n]", 's', 'n') == 's' ? true : false;
                break;

            }
            else if (!partite.getPartite().isEmpty()) {
                id = sceltaUtente() - 1;
                if(id < partite.getPartite().size()) {
                    m = partite.getPartite().get(id).getM();
                    g = partite.getPartite().get(id).getGiocatore();
                    nome = partite.getPartite().get(id).getNomePartita();
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

        p = new Partita(id, nome, g, m);
        p.autoSalvataggio(autoSave);
        p.gioca();
    }

    public int sceltaUtente() {

        String[] opzioni = new String[partite.getPartite().size()+1];
        for (int i = 0; i < partite.getPartite().size(); i++) {
            opzioni[i] = partite.getPartite().get(i).toString();
        }
        opzioni[opzioni.length-1] = "Annulla";

        return MyUtil.myMenu("Carica partita", opzioni);

    }

    public int generaId() {
        if (!partite.getPartite().isEmpty()) return partite.getPartite().get(partite.getPartite().size()-1).getId()+1;
        return 0;
    }

    public void menu() {
        switch(MyUtil.myMenu("Gioca", "Nuova partita", "Carica partita")) {
            case 1: isNuovaPartita = true; break;
            case 2: isNuovaPartita = false; break;
        }
    }


}
