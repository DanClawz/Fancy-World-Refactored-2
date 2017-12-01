import java.io.Serializable;
import java.util.ArrayList;

public class Chiave implements Serializable {
    private Coordinata posChiave;
    private String tipoChiave;
    private int passaggioDaAprire;
    private boolean depositata;
    private int peso;
    private int pesoMax;
    private int nTipiChiave;

    private String[] tipiChiave;
    private int[] pesiChiave;


    public Chiave(Coordinata posChiave) {
        this.posChiave = posChiave;
        this.depositata = false;
        this.pesoMax = 25;
        this.nTipiChiave = 10;
        this.tipiChiave = new String[]{"legno", "ferro", "bronzo", "argento", "oro", "titanite", "cristallo", "diamante", "vibranio", "misteriosa"};
        this.pesiChiave = new int[]{2, 4, 6, 8, 10, 13, 16, 19, 22, 25};
    }

    public int getPassaggioDaAprire() {
        return passaggioDaAprire;
    }

    public void setPassaggioDaAprire(char tipo) {
        Character[] tipoInput = new Character[]{'l', 'f', 'b', 'a', 'o', 't', 'c', 'd', 'v', 'm'};

        for (int i = 0; i < tipoInput.length; i++) {
            if (tipo == tipoInput[i]) {
                this.tipoChiave = tipiChiave[i];
                this.passaggioDaAprire = i+3;
                this.peso = pesiChiave[i];
                break;
            }
        }
    }

    public void setPesi(int index, int peso) {
        this.pesiChiave[index] = peso;
        this.peso = peso;

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
        return "Chiave " + (!this.tipoChiave.equals("misteriosa") ? "di " : "") + this.tipoChiave + ", peso: " + this.peso;
    }

    public void setPesoIndice(int indice) {

    }


    public String[] getTipiChiave() {
        return tipiChiave;
    }

    public void setTipiChiave(String[] tipiChiave) {
        this.tipiChiave = tipiChiave;
    }

    public int[] getPesiChiave() {
        return pesiChiave;
    }

    public void setPesiChiave(int[] pesiChiave) {
        this.pesiChiave = pesiChiave;
    }

    public int getnTipiChiave() {
        return nTipiChiave;
    }

    public void setnTipiChiave(int nTipiChiave) {
        this.nTipiChiave = nTipiChiave;
    }

    public int getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(int pesoMax) {
        this.pesoMax = pesoMax;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setPesoIndice(int peso, int indice) {
        pesiChiave[indice] = peso;
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
