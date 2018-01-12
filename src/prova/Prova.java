package prova;

import java.io.Serializable;

import mondo.*;
import giocatore.*;
import utility.*;

// TODO: Auto-generated Javadoc
/**
 * La classe prova.Prova.
 */
public class Prova implements Serializable{
    
    /** La coordinata. */
    private Coordinata c;
    
    /** Il tipo. */
    private int tipo;
    
    /**  leggi. */
    private LeggiProva leggi;
    
    /** Il punteggio. */
    private int punteggio;

    /** TIl numero di  prove. */
    private int nProve;
    
    /** il punteggio max. */
    private int punteggioMax;


    /**
     * Costuttore della classe.
     *
     * @param c la coordinata
     */
    public Prova(Coordinata c) {
        this.c = c;
        this.nProve = 3;
        leggi = new LeggiProva();
        this.tipo = MyUtil.randomInt(1, 3);
        this.punteggio = this.tipo*10;
        this.punteggioMax = 50;
        leggiProva();

    }

    /**
     * Costuttore della classe.
     *
     * @param c la coordinata
     * @param nProve il numero di  prove
     * @param valoreProva il valore prova
     */
    public Prova(Coordinata c, int nProve, int valoreProva) {
        this.c = c;
        leggi = new LeggiProva();
        this.tipo = MyUtil.randomInt(1, nProve);
        this.punteggio = valoreProva;
        leggiProva();

    }

    /**
     * Inizializza prova.
     */
    public void inizializzaProva() {
        this.tipo = MyUtil.randomInt(1, nProve);
        leggiProva();
    }

    /**
     * Leggi prova.
     */
    private void leggiProva() {
        switch(this.tipo) {
            case 1: leggi.leggiProva1(); break;
            case 2: leggi.leggiProva2(); break;
            case 3: leggi.leggiProva3(); break;
        }
    }

    /**
     * prova.Prova 1.
     *
     * @return  int
     */
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

    /**
     * prova.Prova 2.
     *
     * @return  int
     */
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

    /**
     * prova.Prova 3.
     *
     * @return  int
     */
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

    /**
     * prova.Prova.
     *
     * @return  int
     */
    public int prova() {
        switch(this.tipo) {
            case 1: return prova1();
            case 2: return prova2();
            case 3: return prova3();
        }
        return 0;
    }


    /**
     * Restituisce il punteggio max.
     *
     * @return il punteggio max
     */
    public int getPunteggioMax() {
        return punteggioMax;
    }

    /**
     * Assegna il punteggio max.
     *
     * @param punteggioMax il nuovo punteggio max
     */
    public void setPunteggioMax(int punteggioMax) {
        this.punteggioMax = punteggioMax;
    }

    /**
     * Restituisce il numero di prove.
     *
     * @return il numero di prove
     */
    public int getnProve() {
        return nProve;
    }

    /**
     * Assegna  il numero di prove.
     *
     * @param nProve il numero di prove
     */
    public void setnProve(int nProve) {
        this.nProve = nProve;
        inizializzaProva();
    }

    /**
     * Restituisce il punteggio.
     *
     * @return il punteggio
     */
    public int getPunteggio() {
        return punteggio;
    }

    /**
     * Assegna il punteggio.
     *
     * @param punteggio  il punteggio
     */
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    /**
     * Restituisce la coordinata.
     *
     * @return la coordinata
     */
    public Coordinata getC() {
        return c;
    }

    /**
     * Assegna la coordinata.
     *
     * @param c la nuova coordinata
     */
    public void setC(Coordinata c) {
        this.c = c;
    }

    /**
     * Restituisce the tipo.
     *
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Assegna il tipo.
     *
     * @param tipo il nuovo tipo
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Restituisce  leggi.
     *
     * @return  leggi
     */
    public LeggiProva getLeggi() {
        return leggi;
    }

    /**
     * Assegna  leggi.
     *
     * @param leggi  leggi
     */
    public void setLeggi(LeggiProva leggi) {
        this.leggi = leggi;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Tipo prova: " + this.tipo + ", punteggio:" + this.punteggio + ", pMax:" + this.punteggioMax + ", nProve: " + this.nProve;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinata) {
            Coordinata c = (Coordinata)o;
            if (this.c.equals(c)) return true;
        }
        return false;
    }

    /**
     * The main method.
     *
     * @param args  arguments
     */
    public static void main (String args[]) {
        Prova p = new Prova(new Coordinata(0, 0));
        Giocatore g = new Giocatore();
        g.modificaPunteggio(p.prova());
        System.out.println("Hai " + g.getPunteggio() + " punti");
    }
}
