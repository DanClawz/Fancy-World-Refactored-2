import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Passaggio implements Serializable {
    private Coordinata coordinata;
    private boolean aperto;
    private int pianoDest;
    private String tipoPassaggio;


    public Passaggio(Coordinata coordinata, int pianoDest, boolean aperto) {
        this.coordinata = coordinata;
        this.aperto = aperto;
        this.pianoDest = pianoDest;
    }


    public boolean comparePassaggio(Coordinata c) {
        if (this.coordinata.equals(c)) return true;
        return false;
    }

    public Passaggio comparePassaggio1(Coordinata c) {
        if (this.coordinata.equals(c)) return this;
        return null;
    }

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

    public static boolean compareListaPassaggi(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return true;
        }
        return false;
    }       // controlla che in una lista di passaggi ci sia la coordinata di una di questi passaggi

    public static int pianoDestPassaggio(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return p.getDest();
        }
        return -1;
    }

    public static boolean matchChiavi(ArrayList<Chiave> chiavi, Passaggio p) {
        for (Chiave c : chiavi) {
            if (c.getTipoChiave().equals(p.getTipoPassaggio())) {
                return true;
            }
        }
        return false;
    }

    public static Passaggio compareListaPass(ArrayList<Passaggio> passaggi, Coordinata c) {
        for (Passaggio p : passaggi) {
            if (p.getCoordinata().equals(c)) return p;
        }
        return null;
    }

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

    public String toString() {
        return "Passaggio: " + coordinata + ", " + pianoDest;
    }


    public Coordinata getCoordinata() {
        return coordinata;
    }

    public void setCoordinata(Coordinata coordinata) {
        this.coordinata = coordinata;
    }

    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }

    public void setDest(int pianoDest) {
        this.pianoDest = pianoDest;
    }

    public int getDest() {
        return pianoDest;
    }

    public String getTipoPassaggio() {
        return tipoPassaggio;
    }

    public void setTipoPassaggio(String tipoPassaggio) {
        this.tipoPassaggio = tipoPassaggio;
    }



    public static void main(String args[]) {
        System.out.println(new Mappa("./src/Mappe/mondo1_luogo1").passaggi());
    }
}
