import java.io.Serializable;
import java.util.ArrayList;

public class Giocatore implements Serializable {
    private String nome;
    private ArrayList<Chiave> chiavi;
    private final static int MAX_CHIAVI = 5;
    private int punteggio;

    public Giocatore() {
        chiavi = new ArrayList<Chiave>();
        this.punteggio = 10;
    }



    public void aggiungiChiave(Chiave c) {
        chiavi.add(c);
    }

    public void rimuoviChiave(Chiave c) {
        chiavi.remove(c);
    }

    public boolean checkChiaveRaccoglibile(Chiave c) {
        if (this.chiavi.size() < MAX_CHIAVI && pesoTotaleChiavi() + c.getPeso() <= 50)
            return true;
        return false;
    }

    public int pesoTotaleChiavi() {
        int pesoTotale = 0;
        for (Chiave c : chiavi) {
            pesoTotale += c.getPeso();
        }
        return pesoTotale;
    }

    public void modificaPunteggio(int punteggio) {
        if (punteggio <= 0 || this.punteggio + punteggio < 0) this.punteggio = 0;
        else this.punteggio += punteggio;
    }


    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }
}
