import java.io.Serializable;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Mondo implements Serializable {

    private int NLUOGHI;
    private ArrayList<Luogo> mondo;
    private int pianoCorrente, pianoPartenza;
    private int pianoArrayList;
    private String nomeMondo;
    private int id;

    public Mondo(String nome, int id) {
        NLUOGHI = Integer.parseInt(MyUtil.leggiFile("./src/Mappe/" + nome + "/" + "num_luoghi").get(0));
        ArrayList<String> parametri = MyUtil.leggiFile("./src/Mappe/" + nome + "/" + "nomi_luoghi");
        this.nomeMondo = parametri.get(0);
        this.id = id;
        mondo = new ArrayList<Luogo>();
        pianoPartenza = Integer.parseInt(MyUtil.leggiFile("./src/Mappe/" + nome + "/" + "piano_partenza").get(0));  // ATTENZIONE AGLI INDICI!!!!!

        int j = pianoPartenza;
        for (int i = 1; i <= NLUOGHI; i++) {
            String nomeFile = "";
            nomeFile += "./src/Mappe/" + nome + "/" + nome + "_luogo" + i;
            mondo.add(new Luogo(nomeFile, i, parametri.get(i)));
        }
        pianoCorrente = pianoPartenza;
    }

    public void cambioLuogo(char input, ArrayList<Chiave> chiavi) {
        /*System.out.println(pianoPartenza);
        System.out.println(pianoCorrente);*/
        int indice = pianoCorrente - pianoPartenza;

        int nuovoPiano = Passaggio.pianoDestPassaggio(mondo.get(indice).getLista_passaggi(), mondo.get(indice).getPosCorrente());
        //System.out.println(nuovoPiano);
        int pianoUpDown;
        Coordinata coordinataPassaggio = mondo.get(indice).getPosCorrente();

        if (((input == 'u' && nuovoPiano > pianoCorrente) || (input == 'd' && nuovoPiano < pianoCorrente))
                && (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio))
                && (((mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto())) ||
                Passaggio.matchChiavi(chiavi, mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente())))) {

            this.pianoCorrente = nuovoPiano;
            mondo.get(pianoCorrente-pianoPartenza).apriPassaggio(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente(), true);        // apre il passaggio da a (partenza) verso b (destinazione)
            mondo.get(pianoCorrente-pianoPartenza).setPassaggioRaggiunto(true);
            mondo.get(pianoCorrente-pianoPartenza).resetPassaggi();
            mondo.get(pianoCorrente-pianoPartenza).setChiaviDepositate();
            mondo.get(pianoCorrente-pianoPartenza).setProvaSostenuta(false);
        }

        else if ((Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) && (input == 'u' && nuovoPiano <= pianoCorrente) || (input == 'd' && nuovoPiano >= pianoCorrente))) {
            char c = input == 'u' ? 'd' : 'u';
            System.out.println("Direzione errata! Prova con: " + c);
        }

        else if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio) &&  (!mondo.get(indice).passaggioSuCoordinata(mondo.get(indice).getPosCorrente()).isAperto()))
            System.out.println("Passaggio non possibile! Chiave richiesta: " + mondo.get(pianoCorrente-pianoPartenza).passaggioSuCoordinata(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente()).getTipoPassaggio());

        mondo.get(pianoCorrente-pianoPartenza).setPosCorrente(coordinataPassaggio);
        mondo.get(pianoCorrente-pianoPartenza).muovi(coordinataPassaggio);

        if (Passaggio.compareListaPassaggi(mondo.get(indice).getLista_passaggi(), coordinataPassaggio)) {
            pianoUpDown = Passaggio.pianoDestPassaggio(mondo.get(pianoCorrente-pianoPartenza).getLista_passaggi(), mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
            System.out.println(pianoUpDown);
            mondo.get(pianoCorrente-pianoPartenza).apriPassaggio(mondo.get(pianoUpDown-pianoPartenza).getPosCorrente(), true);        // apre il passaggio da b (destinazione) verso a (partenza)
        }


    }


    public boolean isProvaPresente() {
        for (Luogo l : mondo) {
            if (!l.getProve().isEmpty()) return true;
        }
        return false;
    }


    public boolean obbiettivoRaggiunto() {
        return mondo.get(pianoCorrente-pianoPartenza).isGoalRaggiunto();
    }

    public String luogoGoal() {
        for (Luogo l : mondo) {
            if (l.isGoalPresente()) return l.getNomeLuogo();
        }
        return null;
    }

    public String stampaMappa() {
        return nomeMondo.toUpperCase() + "\n" +
                mondo.get(pianoCorrente-pianoPartenza).getNomeLuogo() + "\n" +
                "Il goal si trova in: " + luogoGoal() + "\n" +
                mondo.get(pianoCorrente-pianoPartenza).stampaMappa();
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
        chiave.setPosChiave(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
        mondo.get(pianoCorrente-pianoPartenza).aggiungiChiave(chiave);
        chiave.setDepositata(true);
    }

    public Chiave raccogliChiave() {
        Chiave c = mondo.get(pianoCorrente-pianoPartenza).getChiave(mondo.get(pianoCorrente-pianoPartenza).getPosCorrente());
        mondo.get(pianoCorrente-pianoPartenza).rimuoviChiave(c);
        return c;
    }

    public int getPianoPartenza() {
        return pianoPartenza;
    }

    public void setPianoPartenza(int pianoPartenza) {
        this.pianoPartenza = pianoPartenza;
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
