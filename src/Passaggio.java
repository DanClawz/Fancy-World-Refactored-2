import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe Passaggio.
 */
public class Passaggio implements Serializable {
    
    /**  coordinata. */
    private Coordinata coordinata;
    
    /**  aperto. */
    private boolean aperto;
    
    /** il piano dest. */
    private int pianoDest;
    
    /** il tipo passaggio. */
    private String tipoPassaggio;


    /**
     * Costruttore della classe.
     *
     * @param coordinata  coordinata
     * @param pianoDest  piano destinazione
     * @param aperto  aperto
     */
    public Passaggio(Coordinata coordinata, int pianoDest, boolean aperto) {
        this.coordinata = coordinata;
        this.aperto = aperto;
        this.pianoDest = pianoDest;
    }


    /**
     * Compare passaggio.
     *
     * @param c the coordinata
     * @return true, ha successo
     */
    public boolean comparePassaggio(Coordinata c) {
        if (this.coordinata.equals(c)) return true;
        return false;
    }

    /**
     * Compare passaggio 1.
     *
     * @param c la coordinata
     * @return la passaggio
     */
    public Passaggio comparePassaggio1(Coordinata c) {
        if (this.coordinata.equals(c)) return this;
        return null;
    }

    /**
     * Assegna il tipo passaggio.
     */
    public void assegnaTipoPassaggio() {
        switch (pianoDest) {
            case 3: this.tipoPassaggio = "legno"; this.aperto = false; break;
            case 4: this.tipoPassaggio = "ferro"; this.aperto = false; break;
            case 5: this.tipoPassaggio = "bronzo"; this.aperto = false; break;
            case 6: this.tipoPassaggio = "argento"; this.aperto = false; break;
            case 7: this.tipoPassaggio = "oro"; this.aperto = false; break;
            case 8: this.tipoPassaggio = "titanite"; this.aperto = false; break;
            case 9: this.tipoPassaggio = "cristallo"; this.aperto = false; break;
            case 10: this.tipoPassaggio = "diamante"; this.aperto = false; break;
            case 11: this.tipoPassaggio = "vibranio"; this.aperto = false; break;
            case 12: this.tipoPassaggio = "misteriosa"; this.aperto = false; break;
        }
    }

    /**
     * Compare la lista passaggi.
     *
     * @param passaggi  passaggi
     * @param c la  coordinata
     * @return true, ha successo
     */
    public static boolean compareListaPassaggi(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return true;
        }
        return false;
    }       // controlla che in una lista di passaggi ci sia la coordinata di una di questi passaggi

    /**
     * Piano dest passaggio.
     *
     * @param passaggi I passaggi
     * @param c La coordinata
     * @return  int
     */
    public static int pianoDestPassaggio(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return p.getDest();
        }
        return -1;
    }

    /**
     * Match chiavi.
     *
     * @param chiavi le chiavi
     * @param p il passaggio
     * @return true, ha successo
     */
    public static boolean matchChiavi(ArrayList<Chiave> chiavi, Passaggio p) {
        for (Chiave c : chiavi) {
            if (c.getTipoChiave().equals(p.getTipoPassaggio())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compare lista passaggi.
     *
     * @param passaggi i passaggi
     * @param c la coordinata
     * @return il passaggio
     */
    public static Passaggio compareListaPass(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return p;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {       //override equals per oggetti Passaggio
        if (o instanceof Passaggio) {
            Passaggio p = (Passaggio)o;
            if (this.coordinata.equals(p.getCoordinata())) return true;
        }
        else if (o instanceof Coordinata) {
            Coordinata c = (Coordinata)o;
            if (this.coordinata.equals(c)) return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Passaggio: " + coordinata + ", " + pianoDest;
    }


    /**
     * Restituisce la coordinata.
     *
     * @return la coordinata
     */
    public Coordinata getCoordinata() {
        return coordinata;
    }

    /**
     * Assegna la coordinata.
     *
     * @param coordinata la nuova coordinata
     */
    public void setCoordinata(Coordinata coordinata) {
        this.coordinata = coordinata;
    }

    /**
     * Controlla se è aperto.
     *
     * @return true, se è aperto
     */
    public boolean isAperto() {
        return aperto;
    }

    /**
     * Assegna  aperto.
     *
     * @param aperto nuovo aperto
     */
    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    /**
     * Assegna la destinazione.
     *
     * @param pianoDest la nuova destinazione
     */
    public void setDest(int pianoDest) {
        this.pianoDest = pianoDest;
    }

    /**
     * Restituisce la destinazione.
     *
     * @return la destinazione
     */
    public int getDest() {
        return pianoDest;
    }

    /**
     * Restituisce il tipo passaggio.
     *
     * @return il tipo passaggio
     */
    public String getTipoPassaggio() {
        return tipoPassaggio;
    }

    /**
     * Assegna il tipo passaggio.
     *
     * @param tipoPassaggio il nuovo tipo passaggio
     */
    public void setTipoPassaggio(String tipoPassaggio) {
        this.tipoPassaggio = tipoPassaggio;
    }



    /**
     * The main method.
     *
     * @param args  arguments
     */
    public static void main(String args[]) {
        System.out.println(new Mappa("./src/Mappe/mondo1_luogo1").passaggi());
    }
}
