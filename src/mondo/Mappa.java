package mondo;

import prova.Prova;
import utility.MyUtil;

import java.io.*;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * La classe mondo.Mappa.
 */
public class Mappa implements Serializable {
    
    /** il  numero di righe. */
    public int NRIGHE;
    
    /** il numeri di colonne. */
    public int NCOLONNE;
    
    /** il buffer reader. */
    private transient BufferedReader b;
    
    /** la mappa. */
    private String mappa = "";
    
    /** la mappa. */
    private char[][] map = {};

    private String pathLuogo;


    /**
     * Il costruttore della classe.
     *
     * @param nomeFile il nome del file
     */
    public Mappa(String nomeFile, String pathLuogo) {
        NRIGHE = 15;
        NCOLONNE = 50;

        this.pathLuogo = pathLuogo;

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

    /**
     * Il costruttore della classe.
     *
     * @param nomeFile il nome del file
     * @param tutorial il tutorial
     */
    public Mappa(String nomeFile, String pathLuogo, boolean tutorial) {

        this.pathLuogo = pathLuogo;

        if (tutorial) {
            NRIGHE = 5;
            NCOLONNE = 10;
        }
        else {
            NRIGHE = 15;
            NCOLONNE = 50;
        }
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

    /**
     * Dimensioni mappa.
     */
    private void dimensioniMappa() {

    }

    /**
     * Restituisce la mappa.
     *
     * @return la mappa
     */
    public String getMappa() {
        return mappa;
    }

    /**
     * Assegna la mappa.
     *
     * @param mappa the new mappa
     */
    public void setMappa(String mappa) {
        this.mappa = mappa;
    }

    /**
     * Grid.
     *
     * @return il carattere
     */
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

    /**
     * Posizione iniziale.
     *
     * @return la coordinata
     */
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

    /**
     * Posizione ostacoli.
     *
     * @return la lista
     */
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

    /**
     * Passaggi.
     *
     * @return la lista
     */
    /*public ArrayList<Passaggio> passaggi() {
        ArrayList<Passaggio> p = new ArrayList<Passaggio>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (Character.isDigit(map[i][j])) {
                    p.add(new Passaggio(new Coordinata(i, j), Character.getNumericValue(map[i][j]), true));
                    p.get(p.size()-1).assegnaTipoPassaggio();
                    map[i][j] = '○';
                }
                else if (Character.isLetter(map[i][j])) {
                    switch(map[i][j]) {
                        case 'Q': p.add(new Passaggio(new Coordinata(i, j), 10, true));
                                p.get(p.size()-1).assegnaTipoPassaggio();
                                map[i][j] = '○';break;
                        case 'W': p.add(new Passaggio(new Coordinata(i, j), 11, true));
                                p.get(p.size()-1).assegnaTipoPassaggio();
                                map[i][j] = '○';break;
                        case 'E': p.add(new Passaggio(new Coordinata(i, j), 12, true));
                                p.get(p.size()-1).assegnaTipoPassaggio();
                                map[i][j] = '○';break;
                    }

                }
            }
        }

        return p;
    }*/



    public ArrayList<Passaggio2> passaggi() {
        ArrayList<String> stringhe = MyUtil.leggiFile(this.pathLuogo + "passaggi");
        ArrayList<Portale> portali = new ArrayList<Portale>();
        ArrayList<Passaggio2> passaggi = new ArrayList<Passaggio2>();
        for (String s : stringhe) {
            String[] valori = s.split(" ");
            int x = Integer.parseInt(valori[0]);
            int y = Integer.parseInt(valori[1]);
            int piano1 = Integer.parseInt(valori[2]);
            int piano2 = Integer.parseInt(valori[3]);
            Tipo tipo = Tipo.assegnaPassaggio(valori[4].charAt(0));
            portali.add(new Portale(new Coordinata(x, y), piano1, piano2, tipo));
        }

        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                for (Portale p : portali) {
                    if (i == p.getC().getX() && j == p.getC().getY()) {
                        boolean aperto;
                        if (p.getTipo().getT() == 'X') passaggi.add(new Passaggio2(p, true));
                        else passaggi.add(new Passaggio2(p, false));
                        map[i][j] = '○';
                    }
                }
            }
        }

        for (Portale p : portali) {
            // ??
        }
        return passaggi;
    }

    public void stampaPassaggi(Portale p) {

    }


    /**
     * La posizioni chiavi.
     *
     * @return la lista con la posizione delle chiavi
     */
    public ArrayList<Chiave> posizioniChiavi() {
        ArrayList<Chiave> c = new ArrayList<Chiave>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (Character.isLetter(map[i][j]) && (map[i][j]!='Q' && map[i][j]!='W' && map[i][j]!='E')) {
                    Chiave t = new Chiave(new Coordinata(i, j));
                    t.setPassaggioDaAprire(Character.toLowerCase(map[i][j]));
                    c.add(t);
                    map[i][j] = '¶';
                }
            }
        }
        return c;

    }

    /**
     * La posizioni prove.
     *
     * @return La lista con la posizione delle prove.
     */
    public ArrayList<Prova> posizioniProve() {
        ArrayList<Prova> prove = new ArrayList<Prova>();
        for (int i = 0; i < NRIGHE; i++) {
            for (int j = 0; j < NCOLONNE; j++) {
                if (map[i][j] == '$') {
                    Prova p = new Prova(new Coordinata(i, j));
                    prove.add(p);
                    map[i][j] = '.';
                }
            }
        }
        return prove;
    }

    /**
     * Posizione del goal.
     *
     * @return la coordinata
     */
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


    /**
     * Restituisce il numero di righe.
     *
     * @return il numero di righe
     */
    public int getNRIGHE() {
        return NRIGHE;
    }

    /**
     * Assegna il numero di righe.
     *
     * @param NRIGHE il numero di righe
     */
    public void setNRIGHE(int NRIGHE) {
        this.NRIGHE = NRIGHE;
    }

    /**
     * Restituisce il numero di colonne.
     *
     * @return il numero di colonne
     */
    public int getNCOLONNE() {
        return NCOLONNE;
    }

    /**
     * Assegna il numero di colonne.
     *
     * @param NCOLONNE il numero di colonne
     */
    public void setNCOLONNE(int NCOLONNE) {
        this.NCOLONNE = NCOLONNE;
    }

    /**
     * Restituisce la mappa the map.
     *
     * @return la mappa
     */
    public char[][] getMap() {
        return map;
    }

    /**
     * Assegna la mappa.
     *
     * @param map la nuova mappa
     */
    public void setMap(char[][] map) {
        this.map = map;
    }


}
