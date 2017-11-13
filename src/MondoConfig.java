public class MondoConfig {

    private Mondo mondo;
    private Giocatore giocatore;

    public MondoConfig(Mondo mondo, Giocatore giocatore) {
        this.mondo = mondo;
        this.giocatore = giocatore;
    }


    public void menuConfigMondo() {
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

            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
            case 8: break;
            case 9: punteggioIniziale(); break;
            case 10: punteggioVittoria(); break;
            case 11: break;
        }
    }


    public void punteggioIniziale() {
        giocatore.setPunteggio(MyUtil.intInput("Inserisci il punteggio iniziale del giocatore"));
    }

    public void punteggioVittoria() {
        giocatore.setPunteggioVittoria(MyUtil.controlledIntInput("Inserisci il punteggio vittoria", giocatore.getPunteggio(), Integer.MAX_VALUE));
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
