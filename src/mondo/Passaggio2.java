package mondo;

import java.io.Serializable;
import java.util.ArrayList;

public class Passaggio2 implements Serializable {
    private Portale p;
    private boolean aperto;

    public Passaggio2(Portale p, boolean aperto) {
        this.p = p;
        this.aperto = aperto;
    }


    /**
     * Compare la lista passaggi.
     *
     * @param passaggi  passaggi
     * @param c la  coordinata
     * @return true, ha successo
     */
    public static boolean compareListaPassaggi(ArrayList<Passaggio2> passaggi, Coordinata c) {
        for (Passaggio2 p : passaggi) {
            if (p.getP().getC().equals(c)) return true;
        }
        return false;
    }       // controlla che in una lista di passaggi ci sia la coordinata di una di questi passaggi


    /**
     * Compare lista passaggi.
     *
     * @param passaggi i passaggi
     * @param c la coordinata
     * @return il passaggio
     */
    public static Passaggio2 compareListaPass(ArrayList<Passaggio2> passaggi, Coordinata c) {
        for (Passaggio2 p : passaggi) {
            if (p.getCoordinata().equals(c)) return p;
        }
        return null;
    }

    /**
     * Piano dest passaggio.
     *
     * @param passaggi I passaggi
     * @param c La coordinata
     * @return  int
     */
    public static int pianoDestPassaggio(ArrayList<Passaggio2> passaggi, Coordinata c) {
        for (Passaggio2 p : passaggi) {
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
    public static boolean matchChiavi(ArrayList<Chiave> chiavi, Passaggio2 p) {
        for (Chiave c : chiavi) {
            if (c.getTipoChiave().equals(p.getTipoPassaggio())) {
                return true;
            }
        }
        return false;
    }



    public int getDest() {
        return p.getPiano2();
    }

    public String getTipoPassaggio() {
        return p.getTipo().getTipo();
    }

    public Coordinata getCoordinata() {
        return p.getC();
    }

    public Portale getP() {
        return p;
    }

    public void setP(Portale p) {
        this.p = p;
    }

    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }
}
