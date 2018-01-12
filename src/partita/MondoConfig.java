package partita;// TODO: Auto-generated Javadoc

import mondo.*;
import giocatore.*;
import system_msg.Msg;
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
            switch(MyUtil.myMenu(String.format(Msg.msgConfigMondiTitolo(), mondo.getNomeMondo()), Msg.opzioniConfigMondi())) {
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
        giocatore.setPunteggio(MyUtil.intInput(String.format(Msg.msgPunteggioIniziale(), giocatore.getPunteggio())));
        System.out.println(String.format(Msg.msgPunteggioInizialeModificato(), giocatore.getPunteggio()));
    }

    /**
     * Punteggio vittoria.
     */
    public void punteggioVittoria() {
        giocatore.setPunteggioVittoria(MyUtil.controlledIntInput(String.format(Msg.msgPunteggioFinale(), giocatore.getPunteggioVittoria()), giocatore.getPunteggio(), Integer.MAX_VALUE));
        System.out.println(String.format(Msg.msgPunteggioFinaleModificato(), giocatore.getPunteggioVittoria()));
    }

    /**
     * Numero tipologie prove.
     */
    public void nTipologieProve() {
        if (!mondo.isProvaPresente()) {
            System.out.println(Msg.msgNessunaProvaPresente());
            return;
        }

        int nTipi = MyUtil.controlledIntInput(String.format(Msg.msgNTipiProve(), mondo.getMondo().get(0).getProve().get(0).getnProve()), 1, 3);

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                mondo.getMondo().get(i).getProve().get(j).setnProve(nTipi);
            }
        }
        System.out.println(String.format(Msg.msgNTipiProveModificato(), mondo.getMondo().get(0).getProve().get(0).getnProve()));
        valoreMaxProva();
        valoreProva();
    }

    /**
     * Valore massimo prova.
     */
    public void valoreMaxProva() {
        if (!mondo.isProvaPresente()) {
            System.out.println(Msg.msgNessunaProvaPresente());
            return;
        }

        int valMax = MyUtil.intInput(String.format(Msg.msgValoreMaxProva(), mondo.getMondo().get(0).getProve().get(0).getPunteggioMax()));

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) {
                mondo.getMondo().get(i).getProve().get(j).setPunteggioMax(valMax);
                if (valMax < mondo.getMondo().get(i).getProve().get(j).getPunteggio()) {
                    System.out.println(Msg.msgValoreMaxProvaMinore());
                    valoreProva();
                }
            }
        }

        System.out.println(String.format(Msg.msgValoreMaxProvaModificato(), mondo.getMondo().get(0).getProve().get(0).getPunteggioMax()));
    }

    /**
     * Valore prova.
     */
    public void valoreProva() {

        if (!mondo.isProvaPresente()) {
            System.out.println(Msg.msgNessunaProvaPresente());
            return;
        }

        for (int k = 1; k <= mondo.getMondo().get(0).getProve().get(0).getnProve(); k++) { // k =  tipi Prove
            int valProva = MyUtil.controlledIntInput(String.format(Msg.msgValoreProvaNuovo(), k), 1, mondo.getMondo().get(0).getProve().get(0).getPunteggioMax());


            for (int i = 0; i < mondo.getMondo().size(); i++) { // i = luoghi
                for (int j = 0; j < mondo.getMondo().get(i).getProve().size(); j++) { // j = prove
                    if (mondo.getMondo().get(i).getProve().get(j).getTipo() == k) {
                        mondo.getMondo().get(i).getProve().get(j).setPunteggio(valProva);

                    }

                }
            }
            System.out.println(String.format(Msg.msgValoreProvaNuovoModificato(), k));
        }
    }

    /**
     * Peso massimo chiavi.
     */
    public void pesoMaxChiavi() {
        int pesoMax = MyUtil.intInput(String.format(Msg.msgPesoMaxChiave(), mondo.getMondo().get(1).getChiavi().get(0).getPesoMax()));

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getChiavi().size(); j++) {
                mondo.getMondo().get(i).getChiavi().get(j).setPesoMax(pesoMax);
                if (pesoMax < mondo.getMondo().get(i).getChiavi().get(j).getPeso()) {
                    System.out.println(Msg.msgPesoMaxChiaveMinore());
                    pesoChiave();
                }
            }
        }

        System.out.println(String.format(Msg.msgPesoMaxChiaveModificato(), mondo.getMondo().get(1).getChiavi().get(0).getPesoMax()));
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

        int indiceChiave = MyUtil.myMenu(Msg.msgSelezionaChiave(), opzioni);
        int peso = MyUtil.controlledIntInput(Msg.msgInserisciPeso(), 1, mondo.getMondo().get(1).getChiavi().get(0).getPesoMax());

        for (int i = 0; i < mondo.getMondo().size(); i++) {

            for (int j = 0; j < mondo.getMondo().get(i).getChiavi().size(); j++) {
                if (mondo.getMondo().get(i).getChiavi().get(j).getTipoChiave().equalsIgnoreCase(mondo.getMondo().get(1).getChiavi().get(0).getTipiChiave()[indiceChiave-1])) {
                    mondo.getMondo().get(i).getChiavi().get(j).setPeso(peso);
                }
                mondo.getMondo().get(i).getChiavi().get(j).setPesoIndice(peso, indiceChiave-1);
            }
        }

        System.out.println(Msg.msgPesoModificato());
    }

    /**
     * Numumero massimo chiavi.
     */
    public void numMaxChiavi() {
        giocatore.setMaxNumChiavi(MyUtil.intInput(String.format(Msg.msgNMaxChiavi(), giocatore.getMaxNumChiavi())));
        System.out.println(String.format(Msg.msgNMaxChiaviModificato(), giocatore.getMaxNumChiavi()));

    }

    /**
     * Capacita inventario chiavi.
     */
    public void capacitaInventarioChiavi() {
        giocatore.setCapacitaInventario(MyUtil.controlledIntInput(String.format(Msg.msgMaxPesoChiavi(), giocatore.getCapacitaInventario()), mondo.getMondo().get(1).getChiavi().get(0).getPesoMax(), Integer.MAX_VALUE));
        System.out.println(String.format(Msg.msgMaxPesoChiaviModificato(), giocatore.getCapacitaInventario()));
        ;
    }

    /**
     * Numero tipi chiavi.
     */
    public void nTipiChiavi() { // da rivedere
        int input = MyUtil.controlledIntInput(String.format(Msg.msgNTipiChiavi(), mondo.getTipiChiave()), 0, Integer.parseInt(MyUtil.leggiFile(mondo.getPathMondo() + "num_chiavi").get(0)));  // da correggere

        for (int i = 0; i < mondo.getMondo().size(); i++) {
            for (int j = 0; j < mondo.getMondo().get(i).getLista_passaggi().size(); j++) {
                if (mondo.getMondo().get(i).getLista_passaggi().get(j).getDest() <= (mondo.getMondo().size() - input - 1 + mondo.getPianoPartenza()))
                    mondo.getMondo().get(i).getLista_passaggi().get(j).setAperto(true);
            }
        }
        mondo.setTipiChiave(input);
        System.out.println(String.format(Msg.msgNTipiChiaviModificato(), mondo.getTipiChiave()));
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
