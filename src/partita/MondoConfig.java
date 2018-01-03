package partita;// TODO: Auto-generated Javadoc

import mondo.*;
import giocatore.*;
import utility.*;
/**
 * La Classe partita.MondoConfig.
 */
public class MondoConfig {

    /** Il mondo. */
    private Mondo mondo;
    
    /** Il giocatore. */
    private Giocatore giocatore;

    /**
     * Il costruttore della classe.
     *
     * @param mondo the mondo
     * @param giocatore the giocatore
     */
    public MondoConfig(Mondo mondo, Giocatore giocatore) {
        this.mondo = mondo;
        this.giocatore = giocatore;
    }


    /**
     * Menu config.
     */
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


    /**
     * Punteggio iniziale.
     */
    public void punteggioIniziale() {
        giocatore.setPunteggio(MyUtil.intInput("\nInserisci il punteggio iniziale del giocatore (valore corrente: " + giocatore.getPunteggio() + ")"));
        System.out.println("Punteggio iniziale modificato in " + giocatore.getPunteggio());
    }

    /**
     * Punteggio vittoria.
     */
    public void punteggioVittoria() {
        giocatore.setPunteggioVittoria(MyUtil.controlledIntInput("\nInserisci il punteggio vittoria (valore corrente: " + giocatore.getPunteggioVittoria() + ")", giocatore.getPunteggio(), Integer.MAX_VALUE));
        System.out.println("Punteggio vittoria modificato in " + giocatore.getPunteggioVittoria());
    }

    /**
     * Numero tipologie prove.
     */
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

    /**
     * Valore massimo prova.
     */
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

    /**
     * Valore prova.
     */
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

    /**
     * Peso massimo chiavi.
     */
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

    /**
     * Peso chiave.
     */
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

    /**
     * Numumero massimo chiavi.
     */
    public void numMaxChiavi() {
        giocatore.setMaxNumChiavi(MyUtil.intInput("\nInserisci il numero massimo di chiavi che il giocatore puo' possedere (valore corrente: " + giocatore.getMaxNumChiavi() + ")"));
        System.out.println("Numero massimo di chiavi in possesso modificato in " + giocatore.getMaxNumChiavi());
    }

    /**
     * Capacita inventario chiavi.
     */
    public void capacitaInventarioChiavi() {
        giocatore.setCapacitaInventario(MyUtil.controlledIntInput("\nInserisci il max peso delle chiavi possedute contemporaneamente (valore corrente: " + giocatore.getCapacitaInventario() + ")", mondo.getMondo().get(1).getChiavi().get(0).getPesoMax(), Integer.MAX_VALUE));
        System.out.println("Capacita' inventario modificato in " + giocatore.getCapacitaInventario());
    }

    /**
     * Numero tipi chiavi.
     */
    public void nTipiChiavi() { // da rivedere
        int input = MyUtil.controlledIntInput("\nInserisci quanti tipi di chiavi possono essere presenti nel mondo (tipi di chiavi presenti nel mondo: " + mondo.getTipiChiave() + ")", 0, Integer.parseInt(MyUtil.leggiFile(mondo.getPathMondo() + "num_chiavi").get(0)));  // da correggere

        //int pianoMax = input+2;
        /*mondo.Coordinata g = mondo.getMondo().get(mondo.getMondo().size()-1).getGoal();
        mondo.getMondo().get(input+1).setGoal(g);
        for (int j = 0; j < mondo.getMondo().size(); j++) {
            for (int i = 0; i < mondo.getMondo().get(j).getLista_passaggi().size(); i++) {
                if (mondo.getMondo().get(j).getLista_passaggi().get(i).getDest() > pianoMax) {
                    mondo.getMondo().get(j).getLista_passaggi().get(i).setDest(j+1);
                    mondo.getMondo().get(j).getLista_passaggi().get(i).setAperto(true);
                }
            }
        }*/
        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getLista_passaggi().size(); j++) {
                mondo.getMondo().get(i).getLista_passaggi().get(j).setAperto(true);
                System.out.println(mondo.getMondo().get(i).getLista_passaggi().get(j).getDest() + ":" + mondo.getMondo().get(i).getLista_passaggi().get(j).isAperto());

            }
        }

        for (int i = mondo.getMondo().size()-1; i > mondo.getTipiChiave() - input; i--) {
            for (int j = 0; j < mondo.getMondo().get(i).getLista_passaggi().size(); j++) {
                mondo.getMondo().get(i).getLista_passaggi().get(j).setAperto(false);
                System.out.println(mondo.getMondo().get(i).getLista_passaggi().get(j).getDest() + ":" + mondo.getMondo().get(i).getLista_passaggi().get(j).isAperto());
            }
        }
        mondo.setTipiChiave(input);
    }




    /**
     * Restituisce il mondo.
     *
     * @return il mondo
     */
    public Mondo getMondo() {
        return mondo;
    }

    /**
     * Assegna il mondo.
     *
     * @param mondo il nuovo mondo
     */
    public void setMondo(Mondo mondo) {
        this.mondo = mondo;
    }

    /**
     * Restituisce il giocatore.
     *
     * @return il giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * Assegna il giocatore.
     *
     * @param giocatore il nuovo giocatore
     */
    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }


}
