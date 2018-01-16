package mondo;

import java.io.Serializable;
import java.util.ArrayList;

import system_msg.Msg;
import utility.*;

// TODO: Auto-generated Javadoc
/**
 * La classe mondo.Mondo.
 */
public class Mondo implements Serializable {

    /** Il numero di luoghi. */
    private int NLUOGHI;
    
    /** Il mondo. */
    private ArrayList<Luogo> mondo;
    
    /** Il  piano di partenza. */
    private int pianoCorrente, pianoPartenza;
    
    /** Il tutorial. */
    private boolean tutorial;

    /** La lista dei piani */
    private int pianoArrayList;
    
    /** Il  nome del mondo. */
    private String nomeMondo;
    
    /** L'id. */
    private int id;
    
    /**  path del mondo. */
    private String pathMondo;

    private int tipiChiave;

    /**
     * costruttore della classe.
     *
     * @param nome the nome
     * @param id the id
     */
    public Mondo(String nome, int id) {
        this.pathMondo = "./Mappe/" + nome + "/";
        NLUOGHI = Integer.parseInt(MyUtil.leggiFile("./Mappe/" + nome + "/" + "num_luoghi").get(0));
        ArrayList<String> parametri = MyUtil.leggiFile("./Mappe/" + nome + "/" + "nomi_luoghi");
        this.nomeMondo = parametri.get(0);
        this.id = id;
        mondo = new ArrayList<Luogo>();
        pianoPartenza = Integer.parseInt(MyUtil.leggiFile("./Mappe/" + nome + "/" + "piano_partenza").get(0));  // ATTENZIONE AGLI INDICI!!!!!

        for (int i = 1; i <= NLUOGHI; i++) {
            String nomeLuogo = "luogo" + i;
            String nomeFile = "";
            nomeFile += "./Mappe/" + nome + "/" + nomeLuogo + "/" + nome + "_" + nomeLuogo;
            String pathLuogo = "./Mappe/" + nome + "/" + nomeLuogo + "/";
            if (nome.equals("tutorial")) this.tutorial = true;
            else this.tutorial = false;
            mondo.add(new Luogo(nomeFile, pathLuogo, i, parametri.get(i), this.tutorial));
        }
        pianoCorrente = pianoPartenza;

        this.tipiChiave = Integer.parseInt(MyUtil.leggiFile(this.pathMondo + "num_chiavi").get(0));
    }

    /**
     * Cambio luogo.
     *
     * @param input l' input
     * @param chiavi le chiavi
     */
    public void cambioLuogo(char input, ArrayList<Chiave> chiavi) {
        int indice = pianoCorrente - pianoPartenza;
        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(indice).getLista_passaggi(), mondo.get(indice).getPosCorrente());
        int pianoUpDown;
        Coordinata coordinataPassaggio = mondo.get(indice).getPosCorrente();

        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente))      // se input è u (d) e se il piano di destinazione è maggiore (minore) del piano corrente
                && (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio)) // se il passaggio  è contenuto nella lista passaggi del luogo
                && (((mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto()))    // se il passaggio di quella coordinata è aperto
                || Passaggio.matchChiavi(chiavi, mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente())))) {  // se il giocatore possiede la chiave per aprire il passaggio

            this.pianoCorrente = nuovoPiano;
            mondo.get(pianoCorrente-pianoPartenza).apriPassaggio(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente(), true);        // apre il passaggio da a (partenza) verso b (destinazione)
            mondo.get(pianoCorrente-pianoPartenza).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-pianoPartenza).resetPassaggi();
            mondo.get(pianoCorrente-pianoPartenza).setChiaviDepositate();
            mondo.get(pianoCorrente-pianoPartenza).setProvaSostenuta(false);
        }

        // controllo se mi trovo su un passaggio e se input è u (d) e se il piano di destinazione è maggiore (minore) del piano corrente
        else if ((Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) && (input == 'u' && nuovoPiano <= pianoCorrente) || (input == 'd' && nuovoPiano >= pianoCorrente))) {
            // messaggio di direzione errata
            char c = input == 'u' ? 'd' : 'u';
            System.out.println(String.format(Msg.msgDirezioneErrata(), c));
        }

        // controllo se il passaggio non è aperto e se il giocatore è in possesso della chiave richiesta
        else if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) &&  (!mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto()))
            System.out.println(String.format(Msg.msgChiaveRichiesta(), mondo.get(pianoCorrente-pianoPartenza).passaggioSuCoordinata(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente()).getTipoPassaggio()));


        mondo.get(pianoCorrente-pianoPartenza).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente-pianoPartenza).muovi(coordinataPassaggio);

        if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio)) {
            pianoUpDown = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-pianoPartenza).getLista_passaggi(), mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
            mondo.get(pianoCorrente-pianoPartenza).apriPassaggio(mondo.get(pianoUpDown-pianoPartenza).getPosCorrente(), true);        // apre il passaggio da b (destinazione) verso a (partenza)
        }


    }



    /**
     *Controlla se la  prova è presente.
     *
     * @return true, se la  prova è presente
     */
    public boolean isProvaPresente() {
        for (Luogo l : mondo) {
            if (!l.getProve().isEmpty()) return true;
        }
        return false;
    }


    /**
     * Obbiettivo raggiunto.
     *
     * @return true, se è verificato
     */
    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente-pianoPartenza).isGoalRaggiunto();
    }

    /**
     * mondo.Luogo goal.
     *
     * @return la stringa
     *
     */
    public String luogoGoal() {
        for (Luogo l : mondo) {
            if (l.isGoalPresente()) return l.getNomeLuogo();
        }
        return null;
    }

    /**
     * Stampa mappa.
     *
     * @return la stringa
     */
    public String stampaMappa() {
        return nomeMondo.toUpperCase() + "\n" +
                mondo.get(pianoCorrente-pianoPartenza).getNomeLuogo() + "\n" +
                (this.tutorial ? "" : String.format(Msg.msgLuogoGoal(), luogoGoal()) + "\n") +
                mondo.get(pianoCorrente-pianoPartenza).stampaMappa();

    }

    /**
     * Resituisce il mondo.
     *
     * @return il mondo
     */
    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    /**
     * Assegna il mondo.
     *
     * @param mondo il nuovo mondo
     */
    public void setMondo(ArrayList<Luogo> mondo) {
        this.mondo = mondo;
    }

    /**
     * Resituisce il piano corrente.
     *
     * @return il piano corrente
     */
    public int getPianoCorrente() {
        return pianoCorrente;
    }

    /**
     * Deposita la chiave.
     *
     * @param chiave the chiave
     */
    public void depositaChiave(Chiave chiave) {
        chiave.setPosChiave(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
        mondo.get(pianoCorrente-pianoPartenza).aggiungiChiave(chiave);
        chiave.setDepositata(true);
    }

    /**
     * Raccogli chiave.
     *
     * @return la chiave
     */
    public Chiave raccogliChiave() {
        Chiave c = mondo.get(pianoCorrente-pianoPartenza).getChiave(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
        mondo.get(pianoCorrente-pianoPartenza).rimuoviChiave(c);
        return c;
    }

    /**
     * Controlla se c'è il tutorial.
     *
     * @return true, se c'è il tutorial
     */
    public boolean isTutorial() {
        return tutorial;
    }

    /**
     * Assegna il tutorial.
     *
     * @param tutorial il nuovo tutorial
     */
    public void setTutorial(boolean tutorial) {
        this.tutorial = tutorial;
    }

    /**
     * Resituisce  path mondo.
     *
     * @return path mondo
     */
    public String getPathMondo() {
        return pathMondo;
    }

    /**
     * Assegna  path mondo.
     *
     * @param pathMondo path mondo
     */
    public void setPathMondo(String pathMondo) {
        this.pathMondo = pathMondo;
    }

    /**
     * Resituisce il piano di partenza.
     *
     * @return il  piano di partenza
     */
    public int getPianoPartenza() {
        return pianoPartenza;
    }

    /**
     * Assegna il piano di partenza.
     *
     * @param pianoPartenza il  piano di partenza
     */
    public void setPianoPartenza(int pianoPartenza) {
        this.pianoPartenza = pianoPartenza;
    }

    /**
     * Resituisce il nome mondo.
     */
    public String getNomeMondo() {
        return nomeMondo;
    }

    /**
     * Assegna il nome mondo.
     *
     * @param nomeMondo il nuovo nome mondo
     */
    public void setNomeMondo(String nomeMondo) {
        this.nomeMondo = nomeMondo;
    }

    /**
     * Resituisce l'id.
     *
     * @return l' id
     */
    public int getId() {
        return id;
    }

    /**
     * Assegna L'id.
     *
     * @param id il nuovo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    public int getTipiChiave() {
        return tipiChiave;
    }

    public void setTipiChiave(int tipiChiave) {
        this.tipiChiave = tipiChiave;
    }

    @Override
    public String toString() {
        return "nome mondo:" + this.nomeMondo + "\n" +
                "luoghi:" + mondo;
    }
}
