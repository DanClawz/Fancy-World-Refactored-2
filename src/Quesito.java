import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * La classe Quesito.
 */
public class Quesito implements Serializable{
    
    /** Variabile stringa della domanda. */
    private String domanda;
    
    /** Variabile stringa della  risposta. */
    private String risposta;
    
    /** Le opzioni. */
    private String[] opzioni;

    /**
     * Costuttore della classe.
     *
     * @param domanda la domanda
     * @param opzioni le opzioni
     * @param risposta le risposta
     */
    public Quesito(String domanda, String[] opzioni, String risposta) {
        this.domanda = domanda;
        this.opzioni = opzioni;
        this.risposta = risposta;
    }

    /**
     * Costuttore della classe.
     *
     * @param domanda le domanda
     * @param risposta le risposta
     */
    public Quesito(String domanda, String risposta) {
        this.domanda = domanda;
        this.risposta = risposta;
    }

    /**
     * Costuttore della classe.
     *
     * @param risposta the risposta
     */
    public Quesito(String risposta) {
        this.risposta = risposta;
    }

    /**
     *  string prova 1.
     *
     * @return  string
     */
    public String toStringProva1() {
        return domanda + "\n" + opzioni[0] + "\n" + opzioni[1] + "\n" + opzioni[2] + "\n" + risposta + "\n";
    }

    /**
     *  string prova 2.
     *
     * @return  string
     */
    public String toStringProva2() {
        return risposta + "\n";
    }

    /**
     *  string prova 3.
     *
     * @return  string
     */
    public String toStringProva3() {
        return domanda + "\n" + risposta + "\n";
    }

    /**
     * Restituisce  la domanda.
     *
     * @return la domanda
     */
    public String getDomanda() {
        return domanda;
    }

    /**
     * Assegna la domanda.
     *
     * @param domanda la nuova domanda
     */
    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    /**
     * Restituisce la risposta.
     *
     * @return la risposta
     */
    public String getRisposta() {
        return risposta;
    }

    /**
     * Assegna la risposta.
     *
     * @param risposta la nuova risposta
     */
    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    /**
     * Restituisce le opzioni.
     *
     * @return le opzioni
     */
    public String[] getOpzioni() {
        return opzioni;
    }

    /**
     * Assegna le opzioni.
     *
     * @param opzioni le nuove opzioni
     */
    public void setOpzioni(String[] opzioni) {
        this.opzioni = opzioni;
    }
}
