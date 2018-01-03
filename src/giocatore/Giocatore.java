package giocatore;

import mondo.Chiave;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe giocatore.Giocatore.
 */
public class Giocatore implements Serializable {
    
    /** Il nome. */
    private String nome;
    
    /** Le chiavi. */
    private ArrayList<Chiave> chiavi;
    
    /** Il numero massimo di chiavi. */
    private int maxNumChiavi;
    
    /** Il punteggio. */
    private int punteggio;
    
    /** Il punteggio vittoria. */
    private int punteggioVittoria;
    
    /** La capacita inventario. */
    private int capacitaInventario;

    /**
     * Costuttore della classe.
     */
    public Giocatore() {
        chiavi = new ArrayList<Chiave>();
        this.punteggio = 10;
        this.punteggioVittoria = 100;
        this.capacitaInventario = 50;
        this.maxNumChiavi = 5;
    }

    /**
     * Costruttore della classe.
     *
     * @param punteggioIniziale il punteggio iniziale
     * @param punteggioVittoria il punteggio vittoria
     */
    public Giocatore(int punteggioIniziale, int punteggioVittoria) {
        this.chiavi = new ArrayList<Chiave>();
        this.punteggio = punteggioIniziale;
        this.punteggioVittoria = punteggioVittoria;
        this.capacitaInventario = 50;
        this.maxNumChiavi = 5;
    }



    /**
     * metodo per aggiungere una  chiave.
     *
     * @param c la chiave
     */
    public void aggiungiChiave(Chiave c) {
        chiavi.add(c);
    }

    /**
     * Rimuove la chiave.
     *
     * @param c la chiave
     */
    public void rimuoviChiave(Chiave c) {
        chiavi.remove(c);
    }

    /**
     * Controlla se la  chiave è raccoglibile.
     *
     * @param c la classe
     * @return true, è eseguito
     */
    public boolean checkChiaveRaccoglibile(Chiave c) {
        if (this.chiavi.size() < maxNumChiavi && pesoTotaleChiavi() + c.getPeso() <= this.capacitaInventario)
            return true;
        return false;
    }

    /**
     * Peso totale chiavi.
     *
     * @return peso totale
     */
    public int pesoTotaleChiavi() {
        int pesoTotale = 0;
        for (Chiave c : chiavi) {
            pesoTotale += c.getPeso();
        }
        return pesoTotale;
    }

    /**
     * Modifica punteggio.
     *
     * @param punteggio il punteggio
     */
    public void modificaPunteggio(int punteggio) {
        if (this.punteggio + punteggio < 0) this.punteggio = 0;
        else this.punteggio += punteggio;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "punteggio:" + this.punteggio + ", pVittoria: " + this.punteggioVittoria;
    }

    /**
     * Restituisce numero massimo di  chiavi.
     *
     * @return numero massimo di  chiavi
     */
    public int getMaxNumChiavi() {
        return maxNumChiavi;
    }

    /**
     * Assegna il numero massimo di chiavi.
     *
     * @param maxNumChiavi numero massimo di  chiavi
     */
    public void setMaxNumChiavi(int maxNumChiavi) {
        this.maxNumChiavi = maxNumChiavi;
    }

    /*
     * Restituisce la capacita dell'inventario.
     *
     * @return la capacita dell'inventario
     */
    public int getCapacitaInventario() {
        return capacitaInventario;
    }

    /**
     * Assegna la capacita dell'inventario.
     *
     * @param capacitaInventario the new capacita inventario
     */
    public void setCapacitaInventario(int capacitaInventario) {
        this.capacitaInventario = capacitaInventario;
    }

    /**
     * Restituisce il punteggio vittoria.
     *
     * @return il punteggio vittoria
     */
    public int getPunteggioVittoria() {
        return punteggioVittoria;
    }

    /**
     * Assegna il punteggio vittoria.
     *
     * @param punteggioVittoria il punteggio vittoria
     */
    public void setPunteggioVittoria(int punteggioVittoria) {
        this.punteggioVittoria = punteggioVittoria;
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
     * @param punteggio il punteggio
     */
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    /**
     * Restituisce le chiavi.
     *
     * @return le chiavi
     */
    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    /**
     * Assegna le chiavi.
     *
     * @param chiavi le chiavi
     */
    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }
}
