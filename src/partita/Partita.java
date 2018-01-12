package partita;

import mondo.*;
import giocatore.*;
import system_msg.Msg;
import utility.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * La Classe partita.Partita.
 */
public class Partita implements Serializable{
    
    /** Il nome partita. */
    private String nomePartita;
    
    /** Il giocatore. */
    private Giocatore giocatore;
    
    /** Il mondo. */
    private Mondo m;
    
    /** L' input. */
    private char input;
    
    /** L' id. */
    private int id;
    
    /** Le date. */
    private Date date;
    
    /** Le partite. */
    private ListaPartite partite;
    
    /**  auto save. */
    private boolean autoSave;
    
    /** I mondi. */
    private ArrayList<Mondo> mondi;
    
    /** L' abilita cambia mondo. */
    private boolean abilitaCambiaMondo;
    
    /** La scelta. */
    private int scelta;

    /**
     * Costuttore della classe
     *
     * @param id L' id
     * @param nomePartita Il  nome partita
     * @param giocatore Il giocatore
     * @param mondi I mondi
     * @param scelta La scelta
     * @param abilitaCambiaMondo L' abilita cambia mondo
     */
    public Partita(int id, String nomePartita, Giocatore giocatore, boolean autoSave, ArrayList<Mondo> mondi, boolean abilitaCambiaMondo, int scelta) {
        this.abilitaCambiaMondo = abilitaCambiaMondo;
        this.nomePartita = nomePartita;
        this.mondi = mondi;
        this.scelta = scelta;
        this.m = mondi.get(scegliMondo(scelta));
        this.id = id;
        this.giocatore = giocatore;
        partite = LetturaScritturaPartita.leggi();
        date = new Date();
        this.autoSave = autoSave;
        stampaMessaggio(this.m.getPathMondo());
    }

    /**
     * Costuttore della classe.
     *
     * @param giocatore Il giocatore
     * @param tutorial Il tutorial
     */
    public Partita(Giocatore giocatore, Mondo tutorial) {
        this.giocatore = giocatore;
        this.m = tutorial;
        this.autoSave = false;
        this.abilitaCambiaMondo = false;
        gioca();
    }


    /**
     * Scegli mondo.
     *
     * @param scelta  scelta
     * @return  int
     */
    private int scegliMondo(int scelta) {
        switch (scelta) {
            case 0: return 0;
            default: return scelta-1;
        }
    }

    /**
     * Gioca.
     */
    public void gioca() {
        String passaggioVerso = "";
        int nMosse = 0;


        while(true) {
            System.out.println("\n\n\n\n");

            System.out.println(m.stampaMappa());
            System.out.println(passaggioVerso);


            if (m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).isProvaRaggiunta() && !m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).isProvaSostenuta()) {
                if (MyUtil.controlledBoolInput(Msg.msgSostieniProva(), Msg.opzioniSN())) {
                    int punti = m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).getProva().prova();
                    if (punti > 0) m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).setProvaSostenuta(true);
                    giocatore.modificaPunteggio(punti);
                }
            }

            System.out.println(String.format(Msg.msgPunteggio(), giocatore.getPunteggio()));

            System.out.println(giocatore.getChiavi().isEmpty() ? (Msg.msgNessunaChiave()) : (String.format(Msg.msgChiaviInPossesso(), giocatore.getChiavi())));

            Chiave chiavePosCorrente = m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).getChiave(m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).getPosCorrente());

            if (m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).isChiavePresente() && !chiavePosCorrente.isDepositata()) {    // controllo: se la chiave e' presente e se e' depositata
                System.out.println(chiavePosCorrente);
                if (MyUtil.controlledBoolInput(Msg.msgRaccogliChiave(), Msg.opzioniSN()) && giocatore.checkChiaveRaccoglibile(chiavePosCorrente)) {
                    Chiave c = m.raccogliChiave();
                    giocatore.aggiungiChiave(c);
                    System.out.println(String.format(Msg.msgChiaveRaccolta(), c));
                }

                else System.out.println(String.format(Msg.msgChiaveNonRaccolta(), giocatore.getChiavi().size(), giocatore.pesoTotaleChiavi()));
                m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).setChiavePresente(false);
            }

            String in = MyUtil.stringInput(Msg.msgMossa());
            checkInput(in);




            if(input == 'n' || input == 's' || input == 'e' || input == 'w')
                m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).aggiornaMappa(input);
            else if (input == 'u' || input == 'd')
                m.cambioLuogo(input, giocatore.getChiavi());
            else if (input == 'x') {
                if (!giocatore.getChiavi().isEmpty()) {

                    int i = MyUtil.myMenu(Msg.msgChiaveDaDepositare(), opzioniDeposita(giocatore.getChiavi()));
                    if (i != opzioniDeposita(giocatore.getChiavi()).length) {
                        Chiave chiaveNuova = giocatore.getChiavi().get(i-1);
                        if(m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).posLibera(chiaveNuova)) {
                            m.depositaChiave(chiaveNuova); // aggiunge la chiave dalla mappa
                            giocatore.rimuoviChiave(chiaveNuova);    // rimuove la chiave dalla lista chiavi del giocatore
                            System.out.println(Msg.msgChiaveDepositata());
                        }
                        else System.out.println(Msg.msgChiaveNonDepositata());
                    }
                    else System.out.println(Msg.msgNessunaChiaveDepositata());
                }
                else System.out.println(Msg.msgNessunaChiavePresente());
            }
            else if (input == 'q') {
                if (!this.m.isTutorial()) {
                    salvaPartita();
                    System.exit(1);
                }
                else return;

            }

            if (m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).isPassaggioRaggiunto())
                passaggioVerso = String.format(Msg.msgPassaggioVerso(), nomeDestinazione(Passaggio.pianoDestPassaggio(m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).getLista_passaggi(), m.getMondo().get(m.getPianoCorrente()-m.getPianoPartenza()).getPosCorrente())));
                //System.out.println();

            else passaggioVerso = "";

            if (m.obbiettivoRaggiunto()) {
                System.out.println(m.stampaMappa());

                if (m.isProvaPresente()) {
                    if (giocatore.getPunteggio() >= giocatore.getPunteggioVittoria()) {
                        System.out.println(Msg.msgObiettivoRaggiunto());

                        if (abilitaCambiaMondo) cambiaMondo();
                        else break;
                    }
                    else System.out.println(String.format(Msg.msgPunteggioNonRaggiunto(), giocatore.getPunteggioVittoria(), giocatore.getPunteggio()));

                }

                else {
                    System.out.println(Msg.msgObiettivoRaggiunto());

                    if (abilitaCambiaMondo) cambiaMondo();
                    else break;
                }
            }



            nMosse++;
            if (autoSave && nMosse == 20) {
                salvaPartita();
                nMosse = 0;
            }
        }

    }

    public String nomeDestinazione(int pianoDest) {
        return m.getMondo().get(pianoDest - Integer.parseInt(MyUtil.leggiFile(m.getPathMondo() + "piano_partenza").get(0))).getNomeLuogo();
    }

    /**
     * Cambia mondo.
     */
    public void cambiaMondo() {
        if (m.getId() < mondi.size()) {
            if (MyUtil.controlledBoolInput(Msg.msgMondoCompletato(), Msg.opzioniSN()))
                this.m = mondi.get(m.getId());
            stampaMessaggio(this.m.getPathMondo());
        }
        else {
            System.exit(1);
        }
    }

    /**
     * Auto salvataggio.
     *
     * @param autoSave  auto save
     */
    public void autoSalvataggio(boolean autoSave) {
        this.autoSave = autoSave;
    }

    /**
     * Salva partita.
     */
    public void salvaPartita() {
        if (!this.m.isTutorial()) {
            isPartitaPresente();
            LetturaScritturaPartita.scrivi(partite);
            System.out.println(Msg.msgPartitaSalvata());
        }

    }


    /**
     * Controlla se la partita è presente.
     */
    public void isPartitaPresente() {   //aggiungo/aggiorno la partita alla lista

        for(int i=0; i<partite.getPartite().size(); i++) {
            if (partite.getPartite().get(i).getId() == this.id) {
                partite.getPartite().set(i, this);
                return;
            }
        }
        partite.aggiungiPartita(this);

    }

    /**
     * Stampa messaggio.
     *
     * @param nome  nome
     */
    private void stampaMessaggio(String nome) {
        String s = MyUtil.leggiFileStringa(nome + "messaggio");
        System.out.println("\n\n\n" + s);
        MyUtil.stringInputVuoto("");
        MyUtil.stringInputVuoto(Msg.splash());
    }

    /**
     * Opzioni deposita.
     *
     * @param chiavi  chiavi
     * @return  string
     */
    private String[] opzioniDeposita(ArrayList<Chiave> chiavi) {
        String[] opzioni = new String[chiavi.size()+1];
        for (int i = 0; i < chiavi.size(); i++) {
            opzioni[i] = chiavi.get(i).getTipoChiave();
        }
        opzioni[opzioni.length-1] = "Non depositare";
        return opzioni;
    }

    /**
     * Controlla input.
     *
     * @param input  input
     */
    private void checkInput(String input) {
        if (input.length() != 0)
            this.input = input.charAt(0);
    }

    /**
     * Restituisce la scelta.
     *
     * @return la scelta
     */
    public int getScelta() {
        return scelta;
    }

    /**
     * Assegna la scelta.
     *
     * @param scelta la nuova scelta
     */
    public void setScelta(int scelta) {
        this.scelta = scelta;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm");
        return nomePartita + ", " + m.getNomeMondo() + ", " + String.valueOf(sdf.format(date));
    }

    /**
     * Controlla se l'abilita è cambia mondo.
     *
     * @return true, se l'abilita è cambia mondo
     */
    public boolean isAbilitaCambiaMondo() {
        return abilitaCambiaMondo;
    }

    /**
     * Assegna l' abilita cambia mondo.
     *
     * @param abilitaCambiaMondo la nuova abilita cambia mondo
     */
    public void setAbilitaCambiaMondo(boolean abilitaCambiaMondo) {
        this.abilitaCambiaMondo = abilitaCambiaMondo;
    }

    /**
     * Restituisce i mondi.
     *
     * @return i mondi
     */
    public ArrayList<Mondo> getMondi() {
        return mondi;
    }

    /**
     * Assegna i mondi.
     *
     * @param mondi i nuovi mondi
     */
    public void setMondi(ArrayList<Mondo> mondi) {
        this.mondi = mondi;
    }

    /**
     * Restituisce il giocatore.
     *
     * @return il giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * Assegna il giocatore.
     *
     * @param giocatore il nuovo giocatore
     */
    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    /**
     * Restituisce l'id.
     *
     * @return l'id
     */
    public int getId() {
        return id;
    }

    /**
     * Assegna l'id.
     *
     * @param id il nuovo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il mondo.
     *
     * @return il mondo
     */
    public Mondo getM() {
        return m;
    }

    /**
     * Assegna il mondo.
     *
     * @param m il nuovo mondo
     */
    public void setM(Mondo m) {
        this.m = m;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

    /**
     * Restituisce il nome partita.
     *
     * @return il nome partita
     */






    public String getNomePartita() {
        return nomePartita;
    }

    /**
     * Assegna il nome partita.
     *
     * @param nomePartita il nuovo nome partita
     */
    public void setNomePartita(String nomePartita) {
        this.nomePartita = nomePartita;
    }
}
