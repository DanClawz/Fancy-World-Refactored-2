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

            System.out.println(this.mondo);

            switch(MyUtil.myMenu("Configura " + mondo.getNomeMondo(),
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
                case 11: ciclo = false; break;
            }
        }

    }


    public void punteggioIniziale() {
        giocatore.setPunteggio(MyUtil.intInput("Inserisci il punteggio iniziale del giocatore"));
        System.out.println(giocatore);
    }

    public void punteggioVittoria() {
        giocatore.setPunteggioVittoria(MyUtil.controlledIntInput("Inserisci il punteggio vittoria", giocatore.getPunteggio(), Integer.MAX_VALUE));
        System.out.println(giocatore);
    }

    public void nTipologieProve() {
        if (!mondo.isProvaPresente()) {
            System.out.println("In questo mondo non sono presenti le prove!");
            return;
        }

        int nTipi = MyUtil.controlledIntInput("Inserisci numero di tipologie di prove disponibili", 1, 3);

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
            System.out.println("In questo mondo non sono presenti le prove!");
            return;
        }

        int valMax = MyUtil.intInput("Inserisci valore max di una prova");

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                mondo.getMondo().get(i).getProve().get(j).setPunteggioMax(valMax);
                if (valMax < mondo.getMondo().get(i).getProve().get(j).getPunteggio()) {
                    System.out.println("Il valore massimo impostato e' minore del valore di una delle prove! Riconfigura i valori");
                    valoreProva();
                }
            }
        }
    }

    public void valoreProva() {

        if (!mondo.isProvaPresente()) {
            System.out.println("In questo mondo non sono presenti le prove!");
            return;
        }

        for (int k = 1; k <= mondo.getMondo().get(0).getProve().get(0).getnProve(); k++) {
            int valProva = MyUtil.controlledIntInput("Inserisci il nuovo valore della prova " + k, 1, mondo.getMondo().get(0).getProve().get(0).getPunteggioMax());

            for (int i = 0; i < mondo.getMondo().size(); i++) {
                for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                    if (mondo.getMondo().get(i).getProve().get(j).getTipo() == k) {
                        mondo.getMondo().get(i).getProve().get(j).setPunteggio(valProva);
                        System.out.println(mondo.getMondo().get(i).getProve().get(j));
                    }

                }
            }
        }
    }

    public void pesoMaxChiavi() {
        int pesoMax = MyUtil.intInput("Inserisci peso max di una chiave: ");
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

        String opzioni[] = new String[mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave().length+1];
        opzioni = mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave();


        int indiceChiave = MyUtil.myMenu("Seleziona una chiave", opzioni);
        int peso = MyUtil.controlledIntInput("Inserisci il nuovo peso", 1, mondo.getMondo().get(1).getChiavi().get(0).getPesoMax());

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getChiavi().size(); j++) {
                /*if(mondo.getMondo().get(i).getChiavi().get(j).get)
                    MyUtil.controlledIntInput("Inserisci il peso della chiave di " + mondo.getMondo().get(0).getChiavi().get(1).getTipoChiave(),
                            1, mondo.getMondo().get(0).getChiavi().get(1).getPesoMax());*/
                if (mondo.getMondo().get(i).getChiavi().get(j).getTipoChiave().equalsIgnoreCase(opzioni[indiceChiave-1])) {
                    mondo.getMondo().get(i).getChiavi().get(j).setPeso(peso);
                }
            }
        }

    }

    public void numMaxChiavi() {
        giocatore.setMaxNumChiavi(MyUtil.intInput("Inserisci il numero massimo di chiavi che il giocatore puo' possedere"));
    }

    public void capacitaInventarioChiavi() {
        giocatore.setCapacitaInventario(MyUtil.controlledIntInput("Inserisci il max peso delle chiavi possedute contemporaneamente", 2, Integer.MAX_VALUE));
    }

    public void nTipiChiavi() {
        int input = MyUtil.controlledIntInput("Inserisci quanti tipi di chiavi possono essere presenti nel mondo (questo influenzera' il numero di luoghi giocabili)", 0, 10);
        int pianoMax = input+2;
        Coordinata g = mondo.getMondo().get(mondo.getMondo().size()-1).getGoal();
        mondo.getMondo().get(input+1).setGoal(g);
        for (int j = 0; j < mondo.getMondo().size(); j++) {
            for (int i = 0; i < mondo.getMondo().get(j).getLista_passaggi().size(); i++) {
                /*if (mondo.getMondo().get(input+1).getLista_passaggi().get(i).getDest() > pianoMax) {
                    mondo.getMondo().get(input+1).getLista_passaggi().get(i).setDest(input+1);
                }*/

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
    

    public static void main(String args[]) {

        MondoConfig mondoC = new MondoConfig(new Mondo("mondo2", 2), new Giocatore());
        mondoC.menuConfigMondo();

    }
}
