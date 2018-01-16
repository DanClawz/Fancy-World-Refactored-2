package mondo;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe mondo.Chiave.
 */
public class Chiave implements Serializable {
    
    /** La posizione della chiave. */
    private Coordinata posChiave;
    
    /** Il tipo chiave. */
    private String tipoChiave;
    
    /** La chiave depositata. */
    private boolean depositata;
    
    /** Il peso. */
    private int peso;
    
    /** Il peso massimo. */
    private int pesoMax;
    
    /** I tipi delle chiave. */
    private int nTipiChiave;

    /** I tipi delle chiave. */
    private String[] tipiChiave;
    
    /** Il  peso chiave. */
    private int[] pesiChiave;


    /**
     * Costuttore della classe  chiave.
     *
     * @param posChiave la posizione della chiave
     */
    public Chiave(Coordinata posChiave) {
        this.posChiave = posChiave;
        this.depositata = false;
        this.pesoMax = 25;
        this.nTipiChiave = 10;
    }


    /**
     * Assegna il passaggio da aprire.
     *
     * @param tipo il  nuovo passaggio da aprire
     */
    public void setPassaggioDaAprire(char tipo) {
        this.tipoChiave = Tipo.assegnaPassaggio(tipo).getTipo();
        this.peso = Tipo.assegnaPassaggio(tipo).getPeso();
    }

    /**
     * Assegna i  pesi.
     *
     * @param index L'index
     * @param peso Il peso
     */
    public void setPesi(int index, int peso) {
        this.pesiChiave[index] = peso;
        this.peso = peso;

    }

    /**
     * Controlla se la chiave è  presente.
     *
     * @param chiavi the chiavi
     * @param coordinata the coordinata
     * @return true, if is chiave presente
     */
    public static boolean isChiavePresente(ArrayList<Chiave> chiavi, Coordinata coordinata) {
        for (Chiave c : chiavi) {
            if (c.getPosChiave().equals(coordinata)) return true;
        }
        return false;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Chiave) {
            Chiave c = (Chiave)o;
            if (this.tipoChiave.equals(c.getTipoChiave())) return true;
        }
        else if (o instanceof Passaggio) {
            Passaggio p = (Passaggio)o;
            if (this.tipoChiave.equals(p.getTipoPassaggio())) return true;
        }

        else if (o instanceof Coordinata) {
            Coordinata coord = (Coordinata)o;
            if (this.posChiave.equals(coord)) {
                return true;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Chiave " + (!this.tipoChiave.equals("misteriosa") ? "di " : "") + this.tipoChiave + ", peso: " + this.peso;
    }

    /**
     * Assegna il peso dell'indice.
     *
     * @param indice the new peso indice
     */
    public void setPesoIndice(int indice) {

    }


    /**
     * Restituisce i tipi della chiave.
     *
     * @return i tipi della chiave
     */
    public String[] getTipiChiave() {
        return tipiChiave;
    }

    /**
     * Assegna i  tipi della chiave.
     *
     * @param tipiChiave the new tipi chiave
     */
    public void setTipiChiave(String[] tipiChiave) {
        this.tipiChiave = tipiChiave;
    }

    /**
     * Restitusice i pesi della chiave.
     *
     * @return i pesi della chiave
     */
    public int[] getPesiChiave() {
        return pesiChiave;
    }

    /**
     * Assegna i pesi della chiave.
     *
     * @param pesiChiave the new pesi chiave
     */
    public void setPesiChiave(int[] pesiChiave) {
        this.pesiChiave = pesiChiave;
    }

    /**
     * Restituisce i tipi della chiave.
     *
     * @return tipi chiave
     */
    public int getnTipiChiave() {
        return nTipiChiave;
    }

    /**
     * Assegna the n tipi chiave.
     *
     * @param nTipiChiave the new n tipi chiave
     */
    public void setnTipiChiave(int nTipiChiave) {
        this.nTipiChiave = nTipiChiave;
    }

    /**
     * Resituisce il peso max.
     *
     * @return il peso max
     */
    public int getPesoMax() {
        return pesoMax;
    }

    /**
     * Assegna il peso max.
     *
     * @param pesoMax il  peso max
     */
    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    /**
     * Resituisce il peso.
     *
     * @return il peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Assegna il peso.
     *
     * @param peso il new peso
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Assegna il peso indice.
     *
     * @param peso il peso
     * @param indice il indice
     */
    public void setPesoIndice(int peso, int indice) {
        pesiChiave[indice] = peso;
    }

    /**
     * Controlla se è depositata.
     *
     * @return true, se è depositata
     */
    public boolean isDepositata() {
        return depositata;
    }

    /**
     * Assegna  depositata.
     *
     * @param depositata ldepositata
     */
    public void setDepositata(boolean depositata) {
        this.depositata = depositata;
    }

    /**
     * Resituisce la posizione della  chiave.
     *
     * @return la posizione della chiave
     */
    public Coordinata getPosChiave() {
        return posChiave;
    }

    /**
     * Assegna la posizione della  chiave.
     *
     * @param posChiave la posizione chiave
     */
    public void setPosChiave(Coordinata posChiave) {
        this.posChiave = posChiave;
    }

    /**
     * Resituisce il tipo della chiave.
     *
     * @return il tipo della chiave
     */
    public String getTipoChiave() {
        return tipoChiave;
    }

    /**
     * Assegna il tipo della chiave.
     *
     * @param tipoChiave il  tipo della chiave
     */
    public void setTipoChiave(String tipoChiave) {
        this.tipoChiave = tipoChiave;
    }
}
