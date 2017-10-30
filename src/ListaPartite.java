import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaPartite implements Serializable {

    private ArrayList<Partita> partite;


    public ListaPartite() {
        partite = new ArrayList<Partita>();
    }

    public void aggiungiPartita(Partita p) {
        for (int i = 0; i < partite.size(); i++) {
            if (partite.get(i).getId() == p.getId()) {
                partite.set(i, p);
                return;
            }
        }
        partite.add(p);
    }

    public void rimuoviPartita(Partita p) {
        for (int i = 0; i < partite.size(); i++) {
            if (partite.get(i).getId() == p.getId()) {
                partite.remove(p);
                return;
            }
        }
    }

    public ArrayList<Partita> getPartite() {
        return partite;
    }

    public void setPartite(ArrayList<Partita> partite) {
        this.partite = partite;
    }
}
