import java.io.Serializable;

public class Prova implements Serializable{
    private Coordinata c;
    private int tipo;
    private LeggiProva leggi;
    private int punteggio;

    private int nProve;
    private int punteggioMax;


    public Prova(Coordinata c) {
        this.c = c;
        this.nProve = 3;
        leggi = new LeggiProva();
        this.tipo = MyUtil.randomInt(1, 3);
        this.punteggio = this.tipo*10;
        this.punteggioMax = 50;
        leggiProva();

    }

    public Prova(Coordinata c, int nProve, int valoreProva) {
        this.c = c;
        leggi = new LeggiProva();
        this.tipo = MyUtil.randomInt(1, nProve);
        this.punteggio = valoreProva;
        leggiProva();

    }

    public void inizializzaProva() {
        this.tipo = MyUtil.randomInt(1, nProve);
        leggiProva();
    }

    private void leggiProva() {
        switch(this.tipo) {
            case 1: leggi.leggiProva1(); break;
            case 2: leggi.leggiProva2(); break;
            case 3: leggi.leggiProva3(); break;
        }
    }

    private int prova1() {
        System.out.println("\nDomanda a risposta chiusa");
        int tentativi = 2;
        int indiceDomanda = MyUtil.randomInt(0, leggi.getProva1().size()-1);

        while(tentativi > 0) {
            int scelta = MyUtil.myMenu(leggi.getProva1().get(indiceDomanda).getDomanda(), leggi.getProva1().get(indiceDomanda).getOpzioni());
            if (leggi.checkRispostaProva1(indiceDomanda, leggi.getProva1().get(indiceDomanda).getOpzioni()[scelta-1])) {
                System.out.println("Risposta corretta!");
                return punteggio;
            }
            else {
                System.out.println("Risposta non corretta! Hai ancora " + --tentativi + " tentativi");
            }
        }

        System.out.println("Tentativi esauriti! Risposta esatta: " + leggi.getProva1().get(indiceDomanda).getRisposta());
        return -punteggio;
    }

    private int prova2() {
        System.out.println("\nIndovina la parola");
        int tentativi = 15;
        int indiceParola = MyUtil.randomInt(0, leggi.getProva2().size()-1);


        String risposta = leggi.nascondiCaratteri(indiceParola);
        System.out.println("Inserisci risposta: ");

        while(tentativi > 0) {
            String inputUtente = MyUtil.stringInputNonVuoto(leggi.stringaNascostaConSpazi(risposta));
            if (leggi.checkRispostaProva2(indiceParola, inputUtente))
                break;
            else
                risposta = leggi.match(indiceParola, inputUtente, risposta);
            System.out.println("Hai ancora " + --tentativi);
        }
        if (tentativi == 0) {
            System.out.println("Prova non superata! Risposta: " + leggi.getProva2().get(indiceParola).getRisposta());
            return -punteggio;
        }
        else {
            System.out.println("Risposta corretta!");
            return punteggio;
        }
    }

    private int prova3() {
        System.out.println("\nIndovinello");
        int tentativi = 3;
        int indiceRisposta = MyUtil.randomInt(0, leggi.getProva3().size()-1);

        while(!leggi.checkRispostaProva3(indiceRisposta, MyUtil.stringInputNonVuoto(leggi.getProva3().get(indiceRisposta).getDomanda())) && --tentativi > 0) {
            System.out.println("Risposta non corretta! Hai ancora " + tentativi + " tentativi a disposizione.");
        }
        if (tentativi == 0) {
            System.out.println("Tentativi esauriti! La risposta corretta e' " + leggi.getProva3().get(indiceRisposta).getRisposta());
            return -punteggio;
        }
        else {
            System.out.println("Risposta corretta!");
            return punteggio;
        }

    }

    public int prova() {
        switch(this.tipo) {
            case 1: return prova1();
            case 2: return prova2();
            case 3: return prova3();
        }
        return 0;
    }


    public int getPunteggioMax() {
        return punteggioMax;
    }

    public void setPunteggioMax(int punteggioMax) {
        this.punteggioMax = punteggioMax;
    }

    public int getnProve() {
        return nProve;
    }

    public void setnProve(int nProve) {
        this.nProve = nProve;
        inizializzaProva();
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Coordinata getC() {
        return c;
    }

    public void setC(Coordinata c) {
        this.c = c;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public LeggiProva getLeggi() {
        return leggi;
    }

    public void setLeggi(LeggiProva leggi) {
        this.leggi = leggi;
    }

    @Override
    public String toString() {
        return "Tipo prova: " + this.tipo + ", punteggio:" + this.punteggio + ", pMax:" + this.punteggioMax + ", nProve: " + this.nProve;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinata) {
            Coordinata c = (Coordinata)o;
            if (this.c.equals(c)) return true;
        }
        return false;
    }

    public static void main (String args[]) {
        Prova p = new Prova(new Coordinata(0, 0));
        Giocatore g = new Giocatore();
        g.modificaPunteggio(p.prova());
        System.out.println("Hai " + g.getPunteggio() + " punti");
    }
}
