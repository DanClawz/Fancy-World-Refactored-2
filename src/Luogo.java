import java.io.Serializable;
import java.util.ArrayList;

public class Luogo implements Serializable {
    private Coordinata start, goal, posCorrente;
    private ArrayList<Coordinata> ostacoli;
    private ArrayList<Passaggio> lista_passaggi;
    private char[][] mappa;
    private Mappa mappaIniziale;
    private boolean passaggioRaggiunto, goalRaggiunto, chiavePresente, provaRaggiunta, provaSostenuta;
    private int piano;
    private String nomeLuogo;
    private boolean chiaveDepositata;
    private ArrayList<Prova> prove;

    private ArrayList<Chiave> chiavi;

    public Luogo(String nomeFile, int piano, String nomeLuogo) {
        this.piano = piano;
        //this.nomeLuogo = nomeFile.split("_")[1];
        this.nomeLuogo = nomeLuogo;


        mappaIniziale = new Mappa(nomeFile);

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


    public String stampaMappa() {

        String mappaContorno = "";

        mappaContorno += "╔";
        for (int i = 0; i < Mappa.NCOLONNE; i++)
            mappaContorno += "═";
        mappaContorno += "╗" + "\n";

        for (int i = 0; i < Mappa.NRIGHE; i++) {
            mappaContorno += "║";
            for (int j = 0; j < Mappa.NCOLONNE; j++)
                mappaContorno += mappa[i][j];
            mappaContorno += "║" + "\n";
        }

        mappaContorno += "╚";
        for (int i = 0; i < Mappa.NCOLONNE; i++)
            mappaContorno += "═";
        mappaContorno += "╝" + "\n";

        return mappaContorno.replace(".", " ");
    }

    public void muovi(Coordinata posNew) {
        if (Passaggio.compareListaPassaggi(lista_passaggi, posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '○';
        else if (!chiavi.isEmpty() && Chiave.isChiavePresente(chiavi, posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = '¶';
        else if (goal.equals(posCorrente)) mappa[posCorrente.getX()][posCorrente.getY()] = 'X';
        else mappa[posCorrente.getX()][posCorrente.getY()] = '.';
        mappa[posNew.getX()][posNew.getY()] = '●';
        this.posCorrente = posNew;
    }       // il metodo sostituisce il pallino del giocatore con uno spazio vuoto, e lo spazio vuoto con il pallino del giocatore

    public void resetPassaggi() {
        for (Passaggio p : lista_passaggi)
            mappa[p.getCoordinata().getX()][p.getCoordinata().getY()] = '○';
    }


    public void aggiornaMappa(char input) {
        boolean mossaPossibile = false, bordoToccato = false;
        Coordinata posNuova = posizioneNuova(input);

        if (posNuova.getX() < 0) posNuova.setX(0);
        if (posNuova.getY() < 0) posNuova.setY(0);
        if (posNuova.getX() >= Mappa.NRIGHE) posNuova.setX(Mappa.NRIGHE-1);
        if (posNuova.getY() >= Mappa.NCOLONNE) posNuova.setY(Mappa.NCOLONNE-1);

        if (posNuova.equals(posCorrente)) bordoToccato = true;

        /*
        Pseudocodice:
        ...
         */

        for (int i = (posCorrente.getX()-1 < 0 ? 0 : posCorrente.getX()-1); i <= (posCorrente.getX()+1 > Mappa.NRIGHE ? posCorrente.getX() : posCorrente.getX()+1); i++) {          //riscrivere eventualmente
            for (int j = (posCorrente.getY()-1 < 0 ? 0 : posCorrente.getY()-1); j <= (posCorrente.getY()+1 > Mappa.NCOLONNE ? posCorrente.getY() : posCorrente.getY()+1); j++) {    //riscrivere eventualmente

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

        if (!mossaPossibile || bordoToccato) System.out.println("Mossa non possibile!");
        else System.out.println("Mossa possibile!");

        if (passaggioRaggiunto) {
            if (Passaggio.compareListaPass(lista_passaggi, posCorrente).getTipoPassaggio() != null)
                System.out.println("Ti trovi su un passaggio! Tipo: " + Passaggio.compareListaPass(lista_passaggi, posCorrente).getTipoPassaggio());
            else System.out.println("Ti trovi su un passaggio aperto!");
        }

    }

    private Coordinata posizioneNuova(char input) {
        if (input == 'n') return new Coordinata(posCorrente.getX()-1, posCorrente.getY());
        if (input == 's') return new Coordinata(posCorrente.getX()+1, posCorrente.getY());
        if (input == 'e') return new Coordinata(posCorrente.getX(), posCorrente.getY()+1);
        if (input == 'w') return new Coordinata(posCorrente.getX(), posCorrente.getY()-1);
        return null;
    }

    public void apriPassaggio(Coordinata c, boolean aperto) {
        for (Passaggio p : lista_passaggi) {
            if (p.getCoordinata().equals(c)) {
                p.setAperto(aperto);
            }
        }
    }
    public Passaggio passaggioSuCoordinata(Coordinata c) {
        for (Passaggio p : lista_passaggi)
            if (p.getCoordinata().equals(c))
                return p;
        return null;
    }

    public boolean posLibera(Chiave c) {
        if (!lista_passaggi.contains(c.getPosChiave()))
            return true;
        return false;
    }

    public void aggiungiChiave(Chiave c) {
        if (posLibera(c)) {
            chiavi.add(c);
            //this.chiaveDepositata = true;
        }
        else System.out.println("La chiave non può essere depositata qui!");
    }

    public void rimuoviChiave(Chiave c) {

        for (int i = 0; i < chiavi.size(); i++) {
            if (chiavi.get(i).equals(c) && chiavi.get(i).getPosChiave().equals(c.getPosChiave()))
                chiavi.remove(i);
        }
    }

    public Chiave getChiave(Coordinata c) {
        for (Chiave chiave : chiavi)
            if (chiave.getPosChiave().equals(c)) return chiave;
        return null;
    }

    public void setChiaviDepositate() {
        for (Chiave c : chiavi) {
            c.setDepositata(false);
        }
    }

    public Prova getProva() {
        for (Prova p : prove) {
            if (p.equals(posCorrente)) return p;
        }
        return null;
    }

    public boolean isProvaPresente() {
        for (Prova p : prove) {
            if (p.getC().equals(posCorrente)) return true;
        }
        return false;
    }


    public boolean isProvaSostenuta() {
        return provaSostenuta;
    }

    public void setProvaSostenuta(boolean provaSostenuta) {
        this.provaSostenuta = provaSostenuta;
    }

    public ArrayList<Prova> getProve() {
        return prove;
    }

    public void setProve(ArrayList<Prova> prove) {
        this.prove = prove;
    }

    public boolean isProvaRaggiunta() {
        return provaRaggiunta;
    }

    public void setProvaRaggiunta(boolean provaRaggiunta) {
        this.provaRaggiunta = provaRaggiunta;
    }

    public boolean isChiaveDepositata() {
        return chiaveDepositata;
    }

    public void setChiaveDepositata(boolean chiaveDepositata) {
        this.chiaveDepositata = chiaveDepositata;
    }

    public ArrayList<Chiave> getChiavi() {
        return chiavi;
    }

    public void setChiavi(ArrayList<Chiave> chiavi) {
        this.chiavi = chiavi;
    }

    public int getPiano() {
        return piano;
    }

    public boolean isGoalRaggiunto() {
        return goalRaggiunto;
    }

    public void setGoalRaggiunto(boolean goalRaggiunto) {
        this.goalRaggiunto = goalRaggiunto;
    }

    public boolean isPassaggioRaggiunto() {
        return passaggioRaggiunto;
    }

    public void setPassaggioRaggiunto(boolean passaggioRaggiunto) {
        this.passaggioRaggiunto = passaggioRaggiunto;
    }

    public Coordinata getPosCorrente() {
        return posCorrente;
    }

    public void setPosCorrente(Coordinata nuovaPosizione) {
        this.posCorrente = nuovaPosizione;
    }

    public char[][] getMappa() {
        return mappa;
    }

    public void setMappa(char[][] mappa) {
        this.mappa = mappa;
    }

    public Coordinata getStart() {
        return start;
    }

    public void setStart(Coordinata start) {
        this.start = start;
    }

    public Coordinata getGoal() {
        return goal;
    }

    public boolean isGoalPresente() {
        if (goal.getX() != -1 && goal.getY() != -1) return true;
        return false;
    }

    public ArrayList<Passaggio> getLista_passaggi() {
        return lista_passaggi;
    }

    public ArrayList<Coordinata> getOstacoli() {
        return ostacoli;
    }

    public Mappa getMappaIniziale() {
        return mappaIniziale;
    }

    public String getNomeLuogo() {
        return nomeLuogo;
    }

    public boolean isChiavePresente() {
        return chiavePresente;
    }

    public void setChiavePresente(boolean chiavePresente) {
        this.chiavePresente = chiavePresente;
    }

}
