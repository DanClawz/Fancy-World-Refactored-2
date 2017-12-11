public class MondoConfig {

    private Mondo mondo;
    private Giocatore giocatore;

    public MondoConfig(Mondo mondo, Giocatore giocatore) {
        this.mondo = mondo;
        this.giocatore = giocatore;
    }


    public void menuConfigMondo() {
        boolean ciclo = true;
        while(ciclo) {

            switch(MyUtil.myMenu("\nConfigura " + mondo.getNomeMondo(),
                    "Numero tipi di chiavi",
                    "Peso max di una chiave",
                    "Peso di ogni chiave",
                    "Numero max di chiavi possedute",
                    "Capacità max inventario chiavi",
                    "Numero tipologie di prove",
                    "Valore max di una prova",
                    "Valore di una prova",
                    "Punteggio iniziale",
                    "Punteggio vittoria",
                    "Procedi"
            )) {

                case 1: nTipiChiavi(); break;
                case 2: pesoMaxChiavi(); break;
                case 3: pesoChiave(); break;
                case 4: numMaxChiavi(); break;
                case 5: capacitaInventarioChiavi(); break;
                case 6: nTipologieProve(); break;
                case 7: valoreMaxProva(); break;
                case 8: valoreProva(); break;
                case 9: punteggioIniziale(); break;
                case 10: punteggioVittoria(); break;
                case 11: ciclo = false; System.out.println("\n"); break;
            }

        }

    }


    public void punteggioIniziale() {
        giocatore.setPunteggio(MyUtil.intInput("\nInserisci il punteggio iniziale del giocatore (valore corrente: " + giocatore.getPunteggio() + ")"));
        System.out.println("Punteggio iniziale modificato in " + giocatore.getPunteggio());
    }

    public void punteggioVittoria() {
        giocatore.setPunteggioVittoria(MyUtil.controlledIntInput("\nInserisci il punteggio vittoria (valore corrente: " + giocatore.getPunteggioVittoria() + ")", giocatore.getPunteggio(), Integer.MAX_VALUE));
        System.out.println("Punteggio vittoria modificato in " + giocatore.getPunteggioVittoria());
    }

    public void nTipologieProve() {
        if (!mondo.isProvaPresente()) {
            System.out.println("\nIn questo mondo non sono presenti le prove!");
            return;
        }

        int nTipi = MyUtil.controlledIntInput("\nInserisci numero di tipologie di prove disponibili (valore corrente: " + mondo.getMondo().get(0).getProve().get(0).getnProve() + ")", 1, 3);

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                mondo.getMondo().get(i).getProve().get(j).setnProve(nTipi);
            }
        }
        valoreMaxProva();
        valoreProva();
    }

    public void valoreMaxProva() {
        if (!mondo.isProvaPresente()) {
            System.out.println("\nIn questo mondo non sono presenti le prove!");
            return;
        }

        int valMax = MyUtil.intInput("\nInserisci valore max di una prova (valore corrente: " + mondo.getMondo().get(0).getProve().get(0).getPunteggioMax() + ")");

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                mondo.getMondo().get(i).getProve().get(j).setPunteggioMax(valMax);
                if (valMax < mondo.getMondo().get(i).getProve().get(j).getPunteggio()) {
                    System.out.println("Il valore massimo impostato e' minore del valore di una delle prove! Riconfigura i valori\n");
                    valoreProva();
                }
            }
        }
    }

    public void valoreProva() {

        if (!mondo.isProvaPresente()) {
            System.out.println("\nIn questo mondo non sono presenti le prove!");
            return;
        }

        for (int k = 1; k <= mondo.getMondo().get(0).getProve().get(0).getnProve(); k++) { // k =  tipi Prove
            int valProva = MyUtil.controlledIntInput("\nInserisci il nuovo valore della prova " + k, 1, mondo.getMondo().get(0).getProve().get(0).getPunteggioMax());

            for (int i = 0; i < mondo.getMondo().size(); i++) { // i = luoghi
                for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) { // j = prove
                    if (mondo.getMondo().get(i).getProve().get(j).getTipo() == k) {
                        mondo.getMondo().get(i).getProve().get(j).setPunteggio(valProva);

                    }

                }
            }
        }
    }

    public void pesoMaxChiavi() {
        int pesoMax = MyUtil.intInput("\nInserisci peso max di una chiave (valore corrente: " + mondo.getMondo().get(1).getChiavi().get(0).getPesoMax() + ")");
        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getChiavi().size(); j++) {
                mondo.getMondo().get(i).getChiavi().get(j).setPesoMax(pesoMax);
                if (pesoMax < mondo.getMondo().get(i).getChiavi().get(j).getPeso()) {
                    System.out.println("Il valore massimo impostato e' minore del valore di una delle chiavi! Riconfigura i valori");
                    pesoChiave();
                }
            }
        }
    }

    public void pesoChiave() {

        String opzioni[] = new String[mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave().length];
        for (int i = 0; i < opzioni.length; i++) {
            String temp = mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave()[i] + ", " + mondo.getMondo().get(1).getChiavi().get(0).getPesiChiave()[i];
            opzioni[i] = temp;
        }

        int indiceChiave = MyUtil.myMenu("\nSeleziona una chiave", opzioni);
        int peso = MyUtil.controlledIntInput("Inserisci il nuovo peso", 1, mondo.getMondo().get(1).getChiavi().get(0).getPesoMax());

        for (int i = 0; i < mondo.getMondo().size(); i++) {

            for (int j = 0; j < mondo.getMondo().get(i).getChiavi().size(); j++) {
                if (mondo.getMondo().get(i).getChiavi().get(j).getTipoChiave().equalsIgnoreCase(mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave()[indiceChiave-1])) {
                    mondo.getMondo().get(i).getChiavi().get(j).setPeso(peso);
                }
                mondo.getMondo().get(i).getChiavi().get(j).setPesoIndice(peso, indiceChiave-1);
            }
        }
    }

    public void numMaxChiavi() {
        giocatore.setMaxNumChiavi(MyUtil.intInput("\nInserisci il numero massimo di chiavi che il giocatore puo' possedere (valore corrente: " + giocatore.getMaxNumChiavi() + ")"));
        System.out.println("Numero massimo di chiavi in possesso modificato in " + giocatore.getMaxNumChiavi());
    }

    public void capacitaInventarioChiavi() {
        giocatore.setCapacitaInventario(MyUtil.controlledIntInput("\nInserisci il max peso delle chiavi possedute contemporaneamente (valore corrente: " + giocatore.getCapacitaInventario() + ")", mondo.getMondo().get(1).getChiavi().get(0).getPesoMax(), Integer.MAX_VALUE));
        System.out.println("Capacita' inventario modificato in " + giocatore.getCapacitaInventario());
    }

    public void nTipiChiavi() { // da rivedere
        int input = MyUtil.controlledIntInput("\nInserisci quanti tipi di chiavi possono essere presenti nel mondo (questo influenzera' il numero di luoghi giocabili) (valore corrente: " + mondo.getMondo().get(1).getChiavi().get(0).getnTipiChiave() + ")", 0, Integer.parseInt(MyUtil.leggiFileStringa(mondo.getPathMondo() + "num_luoghi")));  // da correggere
        int pianoMax = input+2;
        Coordinata g = mondo.getMondo().get(mondo.getMondo().size()-1).getGoal();
        mondo.getMondo().get(input+1).setGoal(g);
        for (int j = 0; j < mondo.getMondo().size(); j++) {
            for (int i = 0; i < mondo.getMondo().get(j).getLista_passaggi().size(); i++) {
                if (mondo.getMondo().get(j).getLista_passaggi().get(i).getDest() > pianoMax) {
                    mondo.getMondo().get(j).getLista_passaggi().get(i).setDest(j+1);
                    mondo.getMondo().get(j).getLista_passaggi().get(i).setAperto(true);
                }
            }
        }
    }




    public Mondo getMondo() {
        return mondo;
    }

    public void setMondo(Mondo mondo) {
        this.mondo = mondo;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }


}
