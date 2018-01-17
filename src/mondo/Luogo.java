package mondo;

import prova.Prova;
import system_msg.Msg;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe luogo.
 */
public class Luogo implements Serializable {
    
    /** La posizione corrente. */
    private Coordinata start, goal, posCorrente;
    
    /** La lista degli ostacoli. */
    private ArrayList<Coordinata> ostacoli;
    
    /** La lista dei passaggi. */
    private ArrayList<Passaggio> lista_passaggi;
    
    /** La mappa. */
    private char[][] mappa;
    
    /** La mappa iniziale. */
    private LeggiMappa mappaIniziale;
    
    /** La prova sostenuta. */
    private boolean passaggioRaggiunto, goalRaggiunto, chiavePresente, provaRaggiunta, provaSostenuta;
    
    /** Il piano. */
    private int piano;
    
    /** Il nome luogo. */
    private String nomeLuogo;
    
    /** La chiave depositata. */
    private boolean chiaveDepositata;
    
    /** Le prove. */
    private ArrayList<Prova> prove;
    
    /** Le chiavi. */
    private ArrayList<Chiave> chiavi;

    /**
     * Il costruttore della classe.
     *
     * @param nomeFile Il nome file
     * @param piano Il piano
     * @param nomeLuogo Il nome del luogo
     * @param tutorial Il tutorial
     */
    public Luogo(String nomeFile, String pathLuogo, int piano, String nomeLuogo, boolean tutorial) {
        this.piano = piano;
        //this.nomeLuogo = nomeFile.split("_")[1];
        this.nomeLuogo = nomeLuogo;

        if (tutorial)
            mappaIniziale = new LeggiMappa(nomeFile, pathLuogo, true);
        else mappaIniziale = new LeggiMappa(nomeFile, pathLuogo);


        mappa = mappaIniziale.getMap();

        lista_passaggi = mappaIniziale.passaggi();

        ostacoli = mappaIniziale.posizioneOstacoli();
        start = mappaIniziale.posizioneIniziale();
        goal = mappaIniziale.posizioneGoal();
        chiavi = mappaIniziale.posizioniChiavi();
        prove = mappaIniziale.posizioniProve();


        posCorrente = start;
        passaggioRaggiunto = false;
        goalRaggiunto = false;
        chiavePresente = false;
        chiaveDepositata = false;
        provaRaggiunta = false;
        provaSostenuta = false;
    }


    /**
     * Stampa mappa.
     *
     * @return la string
     */
    public String stampaMappa() {

        String mappaContorno = "";

        mappaContorno += "╔";
        for (int i = 0; i < mappaIniziale.getNCOLONNE(); i++)
            mappaContorno += "═";
        mappaContorno += "╗" + "\n";

        for (int i = 0; i < mappaIniziale.getNRIGHE(); i++) {
            mappaContorno += "║";
            for (int j = 0; j < mappaIniziale.getNCOLONNE(); j++)
                mappaContorno += mappa[i][j];
            mappaContorno += "║" + "\n";
        }

        mappaContorno += "╚";
        for (int i = 0; i < mappaIniziale.getNCOLONNE(); i++)
            mappaContorno += "═";
        mappaContorno += "╝" + "\n";

        return mappaContorno.replace(".", " ");
    }

    /**
     * Muovi.
     *
     * @param posNew La nuova posizione
     */
    public void muovi(Coordinata posNew) {
        if (Passaggio.compareListaPassaggi(lista_passaggi, posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '○';
        else if (!chiavi.isEmpty() && Chiave.isChiavePresente(chiavi, posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '¶';
        else if (goal.equals(posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '⌂';
        else mappa[posCorrente.getX()][posCorrente.getY()] = '.';
        mappa[posNew.getX()][posNew.getY()] = '●';
        this.posCorrente = posNew;
    }       // il metodo sostituisce il pallino del giocatore con uno spazio vuoto, e lo spazio vuoto con il pallino del giocatore

    /**
     * Reset passaggi.
     */
    public void resetPassaggi() {
        /*for (Passaggio p : lista_passaggi)
            mappa[p.getCoordinata().getX()][p.getCoordinata().getY()] = '○';*/

        for (Passaggio p : lista_passaggi)
            mappa[p.getCoordinata().getX()][p.getCoordinata().getY()] = '○';
    }


    /**
     * Aggiorna mappa.
     *
     * @param input input
     */
    public void aggiornaMappa(char input) {
        boolean mossaPossibile = false, bordoToccato = false;
        Coordinata posNuova = posizioneNuova(input);

        if (posNuova.getX() < 0) posNuova.setX(0);
        if (posNuova.getY() < 0) posNuova.setY(0);
        if (posNuova.getX() >= mappaIniziale.getNRIGHE()) posNuova.setX(mappaIniziale.getNRIGHE()-1);
        if (posNuova.getY() >= mappaIniziale.getNCOLONNE()) posNuova.setY(mappaIniziale.getNCOLONNE()-1);

        if (posNuova.equals(posCorrente)) bordoToccato = true;

        /*
        Pseudocodice:
        ...
         */

        for (int i = (posCorrente.getX()-1 < 0 ? 0 : posCorrente.getX()-1); i <= (posCorrente.getX()+1 > mappaIniziale.getNRIGHE() ? posCorrente.getX() : posCorrente.getX()+1); i++) {          //riscrivere eventualmente
            for (int j = (posCorrente.getY()-1 < 0 ? 0 : posCorrente.getY()-1); j <= (posCorrente.getY()+1 > mappaIniziale.getNCOLONNE() ? posCorrente.getY() : posCorrente.getY()+1); j++) {    //riscrivere eventualmente

                if (!ostacoli.contains(posNuova)) {
                    muovi(posNuova);
                    mossaPossibile = true;
                }

                if (!chiavi.isEmpty() && Chiave.isChiavePresente(chiavi, posCorrente))
                    chiavePresente = true;
                else chiavePresente = false;

                if (Passaggio.compareListaPassaggi(lista_passaggi, posNuova))
                    passaggioRaggiunto = true;
                else passaggioRaggiunto = false;

                if (goal.equals(posNuova))
                    goalRaggiunto = true;
                else goalRaggiunto = false;

                if (isProvaPresente())
                    provaRaggiunta = true;
                else provaRaggiunta = false;
            }
        }

        if (!mossaPossibile || bordoToccato) System.out.println(Msg.msgMossaNonPossibile());
        else System.out.println(Msg.msgMossaPossibile());

        if (passaggioRaggiunto) {
            if (Passaggio.compareListaPass(lista_passaggi, posCorrente).getTipoPassaggio() != null)
                System.out.println(String.format(Msg.msgTipoPassaggio(), Passaggio.compareListaPass(lista_passaggi, posCorrente).getTipoPassaggio()));

            else System.out.println(Msg.msgPassaggioAperto());
        }

    }

    /**
     * La Posizione nuova.
     *
     * @param input  input
     * @return la coordinata
     */
    public Coordinata posizioneNuova(char input) {
        if (input == 'n') return new Coordinata(posCorrente.getX()-1, posCorrente.getY());
        if (input == 's') return new Coordinata(posCorrente.getX()+1, posCorrente.getY());
        if (input == 'e') return new Coordinata(posCorrente.getX(), posCorrente.getY()+1);
        if (input == 'w') return new Coordinata(posCorrente.getX(), posCorrente.getY()-1);
        return null;
    }

    /**
     * Apri passaggio.
     *
     * @param c La coordinata
     * @param aperto aperto
     */
    public void apriPassaggio(Coordinata c, boolean aperto) {
        for (Passaggio p : lista_passaggi) {
            if (p.getCoordinata().equals(c)) {
                p.setAperto(aperto);
            }
        }
    }
    
    /**
     * mondo.Passaggio su coordinata.
     *
     * @param c the c
     * @return the passaggio
     */
    public Passaggio passaggioSuCoordinata(Coordinata c) {
        for (Passaggio p : lista_passaggi)
            if (p.getCoordinata().equals(c))
                return p;
        return null;
    }

    /**
     * Posizione libera.
     *
     * @param c la coordinata
     * @return true, se ha successo
     */
    public boolean posLibera(Chiave c) {
        if (!lista_passaggi.contains(c.getPosChiave()))
            return true;
        return false;
    }

    /**
     * Aggiungi chiave.
     *
     * @param c la coordinata
     */
    public void aggiungiChiave(Chiave c) {
        if (posLibera(c)) {
            chiavi.add(c);
            //this.chiaveDepositata = true;
        }
        else System.out.println(Msg.msgChiaveNonDepositata());
    }

    /**
     * Rimuove la chiave chiave.
     *
     * @param c la coordinata
     */
    public void rimuoviChiave(Chiave c) {
        for (int i = 0; i < chiavi.size(); i++) {
            if (chiavi.get(i).equals(c) && chiavi.get(i).getPosChiave().equals(c.getPosChiave()))
                chiavi.remove(i);
        }
    }

    /**
     * Restituisce la chiave.
     *
     * @param c la coordinata
     * @return la chiave
     */
    public Chiave getChiave(Coordinata c) {
        for (Chiave chiave : chiavi)
            if (chiave.getPosChiave().equals(c)) return chiave;
        return null;
    }

    /**
     * Assegna le chiavi depositate.
     */
    public void setChiaviDepositate() {
        for (Chiave c : chiavi) {
            c.setDepositata(false);
        }
    }

    /**
     * Restituisce la prova.
     *
     * @return la prova
     */
    public Prova getProva() {
        for (Prova p : prove) {
            if (p.equals(posCorrente)) return p;
        }
        return null;
    }

    /**
     * Controlla se la prova è presente.
     *
     * @return true,se la  prova è presente
     */
    public boolean isProvaPresente() {
        for (Prova p : prove) {
            if (p.getC().equals(posCorrente)) return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "nomeLuogo:" + this.nomeLuogo + "\n" +
                "prove:" + this.prove + "\n" +
                "chiavi:" + this.chiavi;
    }


    /**
     * Assegna il goal.
     *
     * @param goal il nuovo goal
     */
    public void setGoal(Coordinata goal) {
        this.goal = goal;
        this.mappa[goal.getX()][goal.getY()] = '⌂';
    }

    /**
     * Controlla se è una prova sostenuta.
     *
     * @return true,se è una prova sostenuta
     */
    public boolean isProvaSostenuta() {
        return provaSostenuta;
    }

    /**
     * Assegna la prova sostenuta.
     *
     * @param provaSostenuta la nuova prova sostenuta
     */
    public void setProvaSostenuta(boolean provaSostenuta) {
        this.provaSostenuta = provaSostenuta;
    }

    /**
     * Restituisce le prove.
     *
     * @return le prove
     */
    public ArrayList<Prova> getProve() {
        return prove;
    }

    /**
     * Assegna le prove.
     *
     * @param prove le nuove prove
     */
    public void setProve(ArrayList<Prova> prove) {
        this.prove = prove;
    }

    /**
     * Controlla se è una  prova raggiunta.
     *
     * @return true, se è una prova raggiunta
     */
    public boolean isProvaRaggiunta() {
        return provaRaggiunta;
    }

    /**
     * Assegna la prova raggiunta.
     *
     * @param provaRaggiunta la nuova prova raggiunta
     */
    public void setProvaRaggiunta(boolean provaRaggiunta) {
        this.provaRaggiunta = provaRaggiunta;
    }

    /**
     * Controlla se è una chiave depositata.
     *
     * @return true, se è una chiave depositata
     */
    public boolean isChiaveDepositata() {
        return chiaveDepositata;
    }

    /**
     * Assegna la chiave depositata.
     *
     * @param chiaveDepositata la nuova  chiave depositata
     */
    public void setChiaveDepositata(boolean chiaveDepositata) {
        this.chiaveDepositata = chiaveDepositata;
    }

    /**
     * Restituisce le chiavi.
     *
     * @return le chiavi
     */
    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    /**
     * Assegna le chiavi.
     *
     * @param chiavi le nuove chiavi
     */
    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }

    /**
     * Restituisce il piano.
     *
     * @return il piano
     */
    public int getPiano() {
        return piano;
    }

    /**
     * Controlla se è il goal è raggiunto.
     *
     * @return true, se il  goal è raggiunto
     */
    public boolean isGoalRaggiunto() {
        return goalRaggiunto;
    }

    /**
     * Assegna il goal raggiunto.
     *
     * @param goalRaggiunto il nuovo goal raggiunto
     */
    public void setGoalRaggiunto(boolean goalRaggiunto) {
        this.goalRaggiunto = goalRaggiunto;
    }

    /**
     * Controlla se è il passaggio raggiunto.
     *
     * @return true, se il passaggio è raggiunto
     */
    public boolean isPassaggioRaggiunto() {
        return passaggioRaggiunto;
    }

    /**
     * Assegna il passaggio raggiunto.
     *
     * @param passaggioRaggiunto il nuovo passaggio raggiunto
     */
    public void setPassaggioRaggiunto(boolean passaggioRaggiunto) {
        this.passaggioRaggiunto = passaggioRaggiunto;
    }

    /**
     * Restituisce la posizione corrente.
     *
     * @return la posizione corretta corrente
     */
    public Coordinata getPosCorrente() {
        return posCorrente;
    }

    /**
     * Assegna la posizione corrente.
     *
     * @param nuovaPosizione la nuova posizione corrente
     */
    public void setPosCorrente(Coordinata nuovaPosizione) {
        this.posCorrente = nuovaPosizione;
    }

    /**
     * Restituisce la mappa.
     *
     * @return la mappa
     */
    public char[][] getMappa() {
        return mappa;
    }

    /**
     * Assegna la mappa.
     *
     * @param mappa la nuova mappa
     */
    public void setMappa(char[][] mappa) {
        this.mappa = mappa;
    }

    /**
     * Restituisce lo start.
     *
     * @return lo start
     */
    public Coordinata getStart() {
        return start;
    }

    /**
     * Assegna lo start.
     *
     * @param start il nuovo start
     */
    public void setStart(Coordinata start) {
        this.start = start;
    }

    /**
     * Restituisce il goal.
     *
     * @return il goal
     */
    public Coordinata getGoal() {
        return goal;
    }

    /**
     * Controlla se  il goal è presente.
     *
     * @return true, se il goal è presente
     */
    public boolean isGoalPresente() {
        if (goal.getX() != -1 && goal.getY() != -1) return true;
        return false;
    }

    /**
     * Restituisce la lista passaggi.
     *
     * @return la lista passaggi
     */
    public ArrayList<Passaggio> getLista_passaggi() {
        return lista_passaggi;
    }

    /**
     * Restituisce gli ostacoli.
     *
     * @return gli ostacoli
     */
    public ArrayList<Coordinata> getOstacoli() {
        return ostacoli;
    }

    /**
     * Restituisce la mappa iniziale.
     *
     * @return la mappa iniziale
     */
    public LeggiMappa getMappaIniziale() {
        return mappaIniziale;
    }

    /**
     * Restituisce il nome del luogo.
     *
     * @return il nome del  luogo
     */
    public String getNomeLuogo() {
        return nomeLuogo;
    }

    /**
     * Controlla se è una chiave presente.
     *
     * @return true, se è una  chiave presente
     */
    public boolean isChiavePresente() {
        return chiavePresente;
    }

    /**
     * Assegna la chiave presente.
     *
     * @param chiavePresente la nuova chiave presente
     */
    public void setChiavePresente(boolean chiavePresente) {
        this.chiavePresente = chiavePresente;
    }

    public static void main(String args[]) {
        Mondo m = new Mondo("mondo2", 1);
        System.out.println(m.getMondo().get(0).stampaMappa());
        System.out.println(m.getMondo().get(1).stampaMappa());
        System.out.println(m.getMondo().get(2).stampaMappa());
        System.out.println(m.getMondo().get(3).stampaMappa());
        System.out.println(m.getMondo().get(4).stampaMappa());
        System.out.println(m.getMondo().get(5).stampaMappa());
        System.out.println(m.getMondo().get(6).stampaMappa());
    }

}
