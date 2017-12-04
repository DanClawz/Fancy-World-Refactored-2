import java.io.Serializable;
import java.util.ArrayList;

public class Giocatore implements Serializable {
    private String nome;
    private ArrayList<Chiave> chiavi;
    private int maxNumChiavi;
    private int punteggio;
    private int punteggioVittoria;
    private int capacitaInventario;

    public Giocatore() {
        chiavi = new ArrayList<Chiave>();
        this.punteggio = 10;
        this.punteggioVittoria = 100;
        this.capacitaInventario = 50;
        this.maxNumChiavi = 5;
    }

    public Giocatore(int punteggioIniziale, int punteggioVittoria) {
        this.chiavi = new ArrayList<Chiave>();
        this.punteggio = punteggioIniziale;
        this.punteggioVittoria = punteggioVittoria;
    }



    public void aggiungiChiave(Chiave c) {
        chiavi.add(c);
    }

    public void rimuoviChiave(Chiave c) {
        chiavi.remove(c);
    }

    public boolean checkChiaveRaccoglibile(Chiave c) {
        if (this.chiavi.size() < maxNumChiavi && pesoTotaleChiavi() + c.getPeso() <= this.capacitaInventario)
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
        if (this.punteggio + punteggio < 0) this.punteggio = 0;
        else this.punteggio += punteggio;
    }

    @Override
    public String toString() {
        return "punteggio:" + this.punteggio + ", pVittoria: " + this.punteggioVittoria;
    }

    public int getMaxNumChiavi() {
        return maxNumChiavi;
    }

    public void setMaxNumChiavi(int maxNumChiavi) {
        this.maxNumChiavi = maxNumChiavi;
    }

    public int getCapacitaInventario() {
        return capacitaInventario;
    }

    public void setCapacitaInventario(int capacitaInventario) {
        this.capacitaInventario = capacitaInventario;
    }

    public int getPunteggioVittoria() {
        return punteggioVittoria;
    }

    public void setPunteggioVittoria(int punteggioVittoria) {
        this.punteggioVittoria = punteggioVittoria;
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
