import java.io.*;
import java.util.ArrayList;

public class Mappa implements Serializable {
    static final int NRIGHE = 5;
    static final int NCOLONNE = 10;
    private transient BufferedReader b;
    private String mappa = "";
    private char[][] map = {};


    public Mappa(String nomeFile) {
        try {
            String t;
            b = new BufferedReader(new FileReader(new File(nomeFile)));
            while(true) {
                t = b.readLine();
                if (t==null) break;
                mappa += t + "\n";

            }
            map = grid();

        } catch (FileNotFoundException e) {
            System.err.println("File non trovato!");
        } catch (IOException e) {
            System.err.println("Errore lettura file!");
        }
    }


    public String getMappa() {
        return mappa;
    }

    public void setMappa(String mappa) {
        this.mappa = mappa;
    }

    private char[][] grid() {
        char[][] griglia = new char[NRIGHE][NCOLONNE];
        int j = 0;
        int k = 0;
        for(int i = 0; i < mappa.length(); i++) {
            if (mappa.charAt(i) == '\n') {
                j = 0;
                k++;
            }
            else
                griglia[k][j++] = mappa.charAt(i);
        }

        return griglia;
    }       // traspone la stringa mappa in una matrice delle stesse dimensioni della mappa su file

    public Coordinata posizioneIniziale() {
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (grid()[i][j] == '●') {
                    return new Coordinata(i, j);
                }
            }
        }
        return new Coordinata(-1, -1);
    }

    public ArrayList<Coordinata> posizioneOstacoli() {
        ArrayList<Coordinata> c = new ArrayList<Coordinata>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (grid()[i][j] == '█') {
                    c.add(new Coordinata(i, j));
                    //System.out.println(c.get(c.size()-1));
                }
            }
        }
        return c;
    }

    public ArrayList<Passaggio> passaggi() {
        ArrayList<Passaggio> p = new ArrayList<Passaggio>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (Character.isDigit(map[i][j])) {
                    p.add(new Passaggio(new Coordinata(i, j), Character.getNumericValue(map[i][j]), true));
                    p.get(p.size()-1).assegnaTipoPassaggio();
                    map[i][j] = '○';
                }
            }
        }

        return p;
    }

    public ArrayList<Chiave> posizioniChiavi() {
        ArrayList<Chiave> c = new ArrayList<Chiave>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (Character.isLetter(map[i][j])) {
                    Chiave t = new Chiave(new Coordinata(i, j));
                    t.setPassaggioDaAprire(Character.toLowerCase(map[i][j]));
                    c.add(t);
                    map[i][j] = '¶';
                }
            }
        }
        return c;

    }

    public Coordinata posizioneGoal() {
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (grid()[i][j] == '⌂') {
                    return new Coordinata(i, j);
                }
            }
        }
        return new Coordinata(-1, -1);
    }


    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
}
