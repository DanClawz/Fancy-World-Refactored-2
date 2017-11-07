import java.io.*;
import java.util.ArrayList;

public class LeggiProva implements Serializable{

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
        try {
            b = new BufferedReader(new FileReader(new File("./src/Prove/prova1")));
            while ((domanda = b.readLine())!=null && domanda.length()!=0) {
                String[] opzioni = new String[3];   //???????????????
                opzioni[0] = b.readLine();
                opzioni[1] = b.readLine();
                opzioni[2] = b.readLine();
                //System.out.println(opzioni[0] + opzioni[1] + opzioni[2]);
                risposta = b.readLine();
                prova1.add(new Quesito(domanda, opzioni, risposta));
            }
            b.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leggiProva2() {
        String parola;
        try {
            b = new BufferedReader(new FileReader(new File("./src/Prove/prova2")));
            while ((parola = b.readLine())!=null && parola.length()!=0) {
                Quesito q = new Quesito(parola);
                prova2.add(q);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leggiProva3() {
        String domanda;
        String risposta;
        try {
            b = new BufferedReader(new FileReader(new File("./src/Prove/prova3")));
            while ((domanda = b.readLine())!=null && domanda.length()!=0) {
                risposta = b.readLine();
                Quesito q = new Quesito(domanda, risposta);
                prova3.add(q);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean checkRispostaProva1(int index, String risposta) {
        if (prova1.get(index).getRisposta().equalsIgnoreCase(risposta)) return true;
        return false;
    }

    public boolean checkRispostaProva2(int index, String risposta) {
        if (prova2.get(index).getRisposta().equalsIgnoreCase(risposta)) return true;
        return false;
    }

    public String nascondiCaratteri(int index) {
        String risposta = prova2.get(index).getRisposta();
        String t = "";
        for (int i = 0; i < risposta.length(); i++) {
            t += '_';
        }
        return t;
    }

    public String match(int index, String rispostaUtente, String caratteriNascosti) {
        char[] risposta = prova2.get(index).getRisposta().toCharArray();
        char[] rUtente = rispostaUtente.toCharArray();
        char[] cNascosti = caratteriNascosti.toCharArray();
        for (int i = 0; i < Math.min(risposta.length, rispostaUtente.length()); i++) {
            if (rUtente[i] == risposta[i] && nUnderscore(String.valueOf(cNascosti)) > 2) cNascosti[i] = rUtente[i];
        }
        return String.valueOf(cNascosti);
    }

    private int nUnderscore(String stringa) {
        int k = 0;
        for (int i = 0; i < stringa.length(); i++) {
            if (stringa.charAt(i) == '_') k++;
        }
        return k;
    }

    public String stringaNascostaConSpazi(String nascosta) {
        String t = "";
        for (int i = 0; i < nascosta.length(); i++) {
            t += nascosta.charAt(i) + " ";
        }
        return t;
    }

    public boolean checkRispostaProva3(int index, String risposta) {
        if (risposta.toLowerCase().contains(prova3.get(index).getRisposta().toLowerCase())) return true;
        return false;
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
