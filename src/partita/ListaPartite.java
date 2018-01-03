package partita;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe  partita.ListaPartite.
 */
public class ListaPartite implements Serializable {

    /** Le partite. */
    private ArrayList<Partita> partite;


    /**
     * Costruttore della classe.
     */
    public ListaPartite() {
        partite = new ArrayList<Partita>();
    }

    /**
     * Aggiungi partita.
     *
     * @param p la partita da aggiungere
     */
    public void aggiungiPartita(Partita p) {
        for (int i = 0; i < partite.size(); i++) {
            if (partite.get(i).getId() == p.getId()) {
                partite.set(i, p);
                return;
            }
        }
        partite.add(p);
    }

    /**
     * Rimuovi partita.
     *
     * @param p la partita da rimuovere
     */
    public void rimuoviPartita(Partita p) {
        for (int i = 0; i < partite.size(); i++) {
            if (partite.get(i).getId() == p.getId()) {
                partite.remove(p);
                return;
            }
        }
    }

    /**
     * Restituisce le partite.
     *
     * @return le partite
     */
    public ArrayList<Partita> getPartite() {
        return partite;
    }

    /**
     * Assegna le partite.
     *
     * @param partite le nuove partite
     */
    public void setPartite(ArrayList<Partita> partite) {
        this.partite = partite;
    }
}
