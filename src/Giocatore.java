import java.io.Serializable;
import java.util.ArrayList;

public class Giocatore implements Serializable {
    private String nome;
    private ArrayList<Chiave> chiavi;
    private final static int MAX_CHIAVI = 5;

    public Giocatore() {
        chiavi = new ArrayList<Chiave>();
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






    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }
}
