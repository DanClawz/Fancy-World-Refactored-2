import java.util.ArrayList;

public class Chiave {
    private Coordinata posChiave;
    private String tipoChiave;
    private int passaggioDaAprire;
    private boolean depositata;
    private int peso;


    public Chiave(Coordinata posChiave) {
        this.posChiave = posChiave;
        this.depositata = false;
    }

    public int getPassaggioDaAprire() {
        return passaggioDaAprire;
    }

    public void setPassaggioDaAprire(char tipo) {
        switch(tipo) {
            case 'l': this.tipoChiave = "legno"; this.passaggioDaAprire = 3; this.peso = 2; break;
            case 'f': this.tipoChiave = "ferro"; this.passaggioDaAprire = 4; this.peso = 4; break;
            case 'b': this.tipoChiave = "bronzo"; this.passaggioDaAprire = 5; this.peso = 6; break;
            case 'a': this.tipoChiave = "argento"; this.passaggioDaAprire = 6; this.peso = 8; break;
            case 'o': this.tipoChiave = "oro"; this.passaggioDaAprire = 7; this.peso = 10; break;
            case 't': this.tipoChiave = "titanite"; this.passaggioDaAprire = 8; this.peso = 13; break;
            case 'c': this.tipoChiave = "cristallo"; this.passaggioDaAprire = 9; this.peso = 16; break;
            case 'd': this.tipoChiave = "diamante"; this.passaggioDaAprire = 10; this.peso = 19; break;
            case 'v': this.tipoChiave = "vibranio"; this.passaggioDaAprire = 11; this.peso = 22; break;
            case 'm': this.tipoChiave = "misteriosa"; this.passaggioDaAprire = 12; this.peso = 25; break;
        }
    }

    public static boolean isChiavePresente(ArrayList<Chiave> chiavi, Coordinata coordinata) {
        for (Chiave c : chiavi) {
            if (c.getPosChiave().equals(coordinata)) return true;
        }
        return false;
    }


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

    @Override
    public String toString() {
        return "Chiave di " + this.tipoChiave + ", peso: " + this.peso;
    }


    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isDepositata() {
        return depositata;
    }

    public void setDepositata(boolean depositata) {
        this.depositata = depositata;
    }

    public Coordinata getPosChiave() {
        return posChiave;
    }

    public void setPosChiave(Coordinata posChiave) {
        this.posChiave = posChiave;
    }

    public String getTipoChiave() {
        return tipoChiave;
    }

    public void setTipoChiave(String tipoChiave) {
        this.tipoChiave = tipoChiave;
    }
}
