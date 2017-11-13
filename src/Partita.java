import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Partita implements Serializable{
    private String nomePartita;
    private Giocatore giocatore;
    private Mondo m;
    private char input;
    private int id;
    private Date date;
    private ListaPartite partite;
    private boolean autoSave;
    private ArrayList<Mondo> mondi;
    private boolean abilitaCambiaMondo;
    private int scelta;

    public Partita(int id, String nomePartita, Giocatore giocatore, ArrayList<Mondo> mondi, int scelta, boolean abilitaCambiaMondo) {
        this.abilitaCambiaMondo = abilitaCambiaMondo;
        this.nomePartita = nomePartita;
        this.mondi = mondi;
        this.scelta = scelta;
        this.m = mondi.get(scegliMondo(scelta));
        this.id = id;
        this.giocatore = giocatore;
        partite = LetturaScritturaPartita.leggi();
        date = new Date();
    }


    private int scegliMondo(int scelta) {
        switch (scelta) {
            case 0: return 0;
            default: return scelta-1;
        }
    }

    public void gioca() {
        int nMosse = 0;

        while(true) {
            System.out.println("\n\n\n\n");
            System.out.println("Punteggio: " + giocatore.getPunteggio());
            System.out.println(m.stampaMappa());


            if (m.getMondo().get(m.getPianoCorrente()-1).isProvaRaggiunta() && !m.getMondo().get(m.getPianoCorrente()-1).isProvaSostenuta()) {
                if (MyUtil.controlledCharInput("Vuoi sostenere la prova?", 's', 'n') == 's') {
                    int punti = m.getMondo().get(m.getPianoCorrente()-1).getProva().prova();
                    if (punti > 0) m.getMondo().get(m.getPianoCorrente()-1).setProvaSostenuta(true);
                    giocatore.modificaPunteggio(punti);
                }
            }

            System.out.println(giocatore.getChiavi().isEmpty() ? ("Nessuna chiave raccolta") : ("Chiavi in possesso: " + giocatore.getChiavi()));

            Chiave chiavePosCorrente = m.getMondo().get(m.getPianoCorrente()-1).getChiave(m.getMondo().get(m.getPianoCorrente()-1).getPosCorrente());

            if (m.getMondo().get(m.getPianoCorrente()-1).isChiavePresente() && !chiavePosCorrente.isDepositata()) {    // controllo: se la chiave e' presente e se e' depositata
                System.out.println(chiavePosCorrente);
                if (MyUtil.controlledCharInput("Vuoi raccogliere la chiave? [s-n]", 's', 'n') == 's' && giocatore.checkChiaveRaccoglibile(chiavePosCorrente)) {
                    Chiave c = m.raccogliChiave();
                    giocatore.aggiungiChiave(c);
                    System.out.println("Chiave raccolta: " + c);
                }

                else System.out.println("La chiave non può essere raccolta! Numero chiavi in possesso: " + giocatore.getChiavi().size() + ", Peso chiavi corrente: " + giocatore.pesoTotaleChiavi());
                m.getMondo().get(m.getPianoCorrente()-1).setChiavePresente(false);
            }

            String in = MyUtil.stringInput("Dove vuoi muoverti? [n-s-e-w-u-d][x per depositare una chiave][q per uscire]: ");
            checkInput(in);

            if(input == 'n' || input == 's' || input == 'e' || input == 'w')
                m.getMondo().get(m.getPianoCorrente()-1).aggiornaMappa(input);
            else if (input == 'u' || input == 'd')
                m.cambioLuogo(input, giocatore.getChiavi());
            else if (input == 'x') {
                if (!giocatore.getChiavi().isEmpty()) {

                    int i = MyUtil.myMenu("Scegli la chiave da depositare: ", opzioniDeposita(giocatore.getChiavi()));
                    if (i != opzioniDeposita(giocatore.getChiavi()).length) {
                        Chiave chiaveNuova = giocatore.getChiavi().get(i-1);
                        if(m.getMondo().get(m.getPianoCorrente()-1).posLibera(chiaveNuova)) {
                            m.depositaChiave(chiaveNuova); // aggiunge la chiave dalla mappa
                            giocatore.rimuoviChiave(chiaveNuova);    // rimuove la chiave dalla lista chiavi del giocatore
                            System.out.println("Chiave depositata!");
                        }
                        else System.out.println("La chiave non può essere depositata qui!");
                    }
                    else System.out.println("Nessuna chiave depositata!");
                }
                else System.out.println("Non hai nessuna chiave!");
            }
            else if (input == 'q') {
                salvaPartita();
                System.exit(1);
            }

            if (m.getMondo().get(m.getPianoCorrente()-1).isPassaggioRaggiunto())
                System.out.println("Il passaggio ti porta verso: luogo" + Passaggio.pianoDestPassaggio(m.getMondo().get(m.getPianoCorrente()-1).getLista_passaggi(), m.getMondo().get(m.getPianoCorrente()-1).getPosCorrente()));


            if (m.obbiettivoRaggiunto()) {
                System.out.println(m.stampaMappa());

                if (m.isProvaPresente()) {
                    if (giocatore.getPunteggio() >= giocatore.getPunteggioVittoria()) {
                        System.out.println("Obiettivo raggiunto!");

                        if (abilitaCambiaMondo) cambiaMondo();
                        else break;
                    }
                    else System.out.println("Devi raggiungere " + giocatore.getPunteggioVittoria() + " punti per vincere! Hai attualmente " + giocatore.getPunteggio() + " punti");
                }

                else {
                    System.out.println("Obiettivo raggiunto!");

                    if (abilitaCambiaMondo) cambiaMondo();
                    else break;
                }
            }



            nMosse++;
            if (autoSave && nMosse == 5) {
                salvaPartita();
                nMosse = 0;
            }
        }

    }

    public void cambiaMondo() {
        if (m.getId() < mondi.size()) {
            if (MyUtil.controlledCharInput("\nHai completato questo mondo! Vuoi continuare con il mondo successivo? ", 's', 'n') == 's')
                this.m = mondi.get(m.getId());
        }
        else {
            System.exit(1);
        }
    }

    public void autoSalvataggio(boolean autoSave) {
        this.autoSave = autoSave;
    }

    public void salvaPartita() {
        isPartitaPresente();
        LetturaScritturaPartita.scrivi(partite);
        System.out.println("Partita salvata");
    }


    public void isPartitaPresente() {   //aggiungo/aggiorno la partita alla lista
        for(int i=0; i<partite.getPartite().size(); i++) {
            if (partite.getPartite().get(i).getId() == this.id) {
                partite.getPartite().set(i, this);
                return;
            }
        }
        partite.aggiungiPartita(this);

    }

    private String[] opzioniDeposita(ArrayList<Chiave> chiavi) {
        String[] opzioni = new String[chiavi.size()+1];
        for (int i = 0; i < chiavi.size(); i++) {
            opzioni[i] = chiavi.get(i).getTipoChiave();
        }
        opzioni[opzioni.length-1] = "Non depositare";
        return opzioni;
    }

    private void checkInput(String input) {
        if (input.length() != 0)
            this.input = input.charAt(0);
    }

    public int getScelta() {
        return scelta;
    }

    public void setScelta(int scelta) {
        this.scelta = scelta;
    }

    public String toString() {
        return nomePartita + ", " + m.getNomeMondo() + ", " + String.valueOf(date);
    }

    public boolean isAbilitaCambiaMondo() {
        return abilitaCambiaMondo;
    }

    public void setAbilitaCambiaMondo(boolean abilitaCambiaMondo) {
        this.abilitaCambiaMondo = abilitaCambiaMondo;
    }

    public ArrayList<Mondo> getMondi() {
        return mondi;
    }

    public void setMondi(ArrayList<Mondo> mondi) {
        this.mondi = mondi;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mondo getM() {
        return m;
    }

    public void setM(Mondo m) {
        this.m = m;
    }

    public String getNomePartita() {
        return nomePartita;
    }

    public void setNomePartita(String nomePartita) {
        this.nomePartita = nomePartita;
    }
}
