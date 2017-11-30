import java.io.Serializable;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Mondo implements Serializable {

    final static int NLUOGHI = 8;
    private ArrayList<Luogo> mondo;
    private int pianoCorrente;
    private String nomeMondo;
    private int id;

    public Mondo(String nome, int id) {
        ArrayList<String> parametri = MyUtil.leggiFile("./src/Mappe/" + nome + "/" + "nomi_luoghi");
        this.nomeMondo = parametri.get(0);
        this.id = id;
        mondo = new ArrayList<Luogo>();
        for (int i = 1; i <= NLUOGHI; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i, parametri.get(i)));
        }

        pianoCorrente = 1;  // ATTENZIONE AGLI INDICI!!!!!
    }

    public void cambioLuogo(char input, ArrayList<Chiave> chiavi) {
        int indice = pianoCorrente - 1;
        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(indice).getLista_passaggi(), mondo.get(indice).getPosCorrente());
        int pianoPartenza;
        Coordinata coordinataPassaggio = mondo.get(indice).getPosCorrente();

        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente))
                && (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio))
                && (((mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto())) ||
                Passaggio.matchChiavi(chiavi, mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente())))) {

            this.pianoCorrente = nuovoPiano;
            mondo.get(pianoCorrente-1).apriPassaggio(mondo.get(pianoCorrente-1).getPosCorrente(), true);        // apre il passaggio da a (partenza) verso b (destinazione)
            mondo.get(pianoCorrente-1).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-1).resetPassaggi();
            mondo.get(pianoCorrente-1).setChiaviDepositate();
            mondo.get(pianoCorrente-1).setProvaSostenuta(false);
        }

        else if ((Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) && (input == 'u' && nuovoPiano <= pianoCorrente) || (input == 'd' && nuovoPiano >= pianoCorrente))) {
            char c = input == 'u' ? 'd' : 'u';
            System.out.println("Direzione errata! Prova con: " + c);
        }

        else if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) &&  (!mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto()))
            System.out.println("Passaggio non possibile! Chiave richiesta: " + mondo.get(pianoCorrente-1).passaggioSuCoordinata(mondo.get(pianoCorrente-1).getPosCorrente()).getTipoPassaggio());

        mondo.get(pianoCorrente-1).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente-1).muovi(coordinataPassaggio);

        if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio)) {
            pianoPartenza = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-1).getLista_passaggi(), mondo.get(pianoCorrente-1).getPosCorrente());
            mondo.get(pianoCorrente-1).apriPassaggio(mondo.get(pianoPartenza-1).getPosCorrente(), true);        // apre il passaggio da b (destinazione) verso a (partenza)
        }


    }


    public boolean isProvaPresente() {
        for (Luogo l : mondo) {
            if (!l.getProve().isEmpty()) return true;
        }
        return false;
    }


    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente-1).isGoalRaggiunto();
    }

    public String luogoGoal() {
        for (Luogo l : mondo) {
            if (l.isGoalPresente()) return l.getNomeLuogo();
        }
        return null;
    }

    public String stampaMappa() {
        return nomeMondo.toUpperCase() + "\n" +
                mondo.get(pianoCorrente-1).getNomeLuogo() + "\n" +
                "Il goal si trova in: " + luogoGoal() + "\n" +
                mondo.get(pianoCorrente-1).stampaMappa();
    }

    public ArrayList<Luogo> getMondo() {
        return mondo;
    }

    public void setMondo(ArrayList<Luogo> mondo) {
        this.mondo = mondo;
    }

    public int getPianoCorrente() {
        return pianoCorrente;
    }

    public void depositaChiave(Chiave chiave) {
        chiave.setPosChiave(mondo.get(pianoCorrente-1).getPosCorrente());
        mondo.get(pianoCorrente-1).aggiungiChiave(chiave);
        chiave.setDepositata(true);
    }

    public Chiave raccogliChiave() {
        Chiave c = mondo.get(pianoCorrente-1).getChiave(mondo.get(pianoCorrente-1).getPosCorrente());
        mondo.get(pianoCorrente-1).rimuoviChiave(c);
        return c;
    }

    public String getNomeMondo() {
        return nomeMondo;
    }

    public void setNomeMondo(String nomeMondo) {
        this.nomeMondo = nomeMondo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "nome mondo:" + this.nomeMondo + "\n" +
                "luoghi:" + mondo;
    }
}
