import java.io.*;
import java.util.ArrayList;

public class LeggiProva {

    private ArrayList<Quesito> prova1, prova2, prova3;
    private transient BufferedReader b;

    public LeggiProva() {
        prova1 = new ArrayList<Quesito>();
        prova2 = new ArrayList<Quesito>();
        prova3 = new ArrayList<Quesito>();

    }

    public void leggiProva1() {
        String domanda;
        String risposta;
        String[] opzioni = new String[3];
        try {
            b = new BufferedReader(new FileReader(new File("./src/Prove/prova1")));
            while ((domanda = b.readLine())!=null && domanda.length()!=0) {
                opzioni[0] = b.readLine();
                opzioni[1] = b.readLine();
                opzioni[2] = b.readLine();
                risposta = b.readLine();
                Quesito q = new Quesito(domanda, opzioni, risposta);
                prova1.add(q);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public ArrayList<Quesito> getProva1() {
        return prova1;
    }

    public ArrayList<Quesito> getProva2() {
        return prova2;
    }

    public ArrayList<Quesito> getProva3() {
        return prova3;
    }
}
