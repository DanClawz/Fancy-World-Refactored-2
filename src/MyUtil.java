import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * La classe MyUtil.
 */
public class MyUtil {

    /** L' input. */
    private static Scanner input = new Scanner(System.in);
    
    /** Il reader. */
    private static BufferedReader reader;

    static {
        input.useDelimiter(System.getProperty("line.separator"));
    }

    /**
     *  input.
     *
     * @param MESSAGGIO il messaggio
     * @return  int
     */
    public static int intInput(String MESSAGGIO) {
        String s = stringInput(MESSAGGIO);
        int n;
        try {
            n = Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            System.out.print("-- Numero non valido / Stringa immessa! --");
            return intInput(MESSAGGIO);
        }
        return n;
    }

    /**
     * Long input.
     *
     * @param MESSAGGIO il messaggio
     * @return  long
     */
    public static long longInput(String MESSAGGIO) {
        String n = stringInput(MESSAGGIO + ":");
        long l;
        try {
            l = Long.parseLong(n);
        } catch (NumberFormatException e) {
            System.out.println("-- Numero non valido / Stringa immessa! --");
            return longInput(MESSAGGIO);
        }
        return l;
    }

    /**
     * Char input.
     *
     * @param MESSAGGIO il messaggio
     * @return il char
     */
    public static char charInput(String MESSAGGIO) {
        System.out.print(MESSAGGIO + " (se una stringa, viene letto solo il 1^ carattere): ");
        String s = input.next();
        if (!s.equals("")) return s.charAt(0);
        else return charInput("Devi inserire un carattere!");
    }

    /**
     * Controlla char input.
     *
     * @param MESSAGGIO il messaggio
     * @param caratteri i caratteri
     * @return i char
     */
    public static char controlledCharInput(String MESSAGGIO, char... caratteri) {
        char c = charInput(MESSAGGIO + ": ");
        for (int i = 0; i < caratteri.length; i++) {
            if (c == caratteri[i]) return c;
        }
        System.out.println("-- Devi inserire uno dei seguenti caratteri: " + Arrays.toString(caratteri) + " --");
        return controlledCharInput(MESSAGGIO, caratteri);
    }

    /**
     * String input.
     *
     * @param MESSAGGIO il messaggio
     * @return la string
     */
    public static String stringInput(String MESSAGGIO) {
        System.out.print(MESSAGGIO + ": ");
        String s = input.next();
        //if (s.equals("")) return stringInput("Stringa vuota! Inserisci una stringa valida");
        return s;
    }

    /**
     * String input non vuoto.
     *
     * @param MESSAGGIO il messaggio
     * @return la string
     */
    public static String stringInputNonVuoto(String MESSAGGIO) {
        System.out.print(MESSAGGIO + ": ");
        String s = input.next();
        if (s.equals("")) return stringInput("Stringa vuota! Inserisci una stringa valida");
        return s;
    }

    /**
     * String input vuoto.
     *
     * @param MESSAGGIO il messaggio
     * @return il string
     */
    public static String stringInputVuoto(String MESSAGGIO) {
        System.out.print(MESSAGGIO + "\n");
        String s = input.nextLine();
        return s;
    }


    /**
     * menu.
     *
     * @param MESSAGGIO il messaggio
     * @param opzioni le opzioni
     * @return l'int
     */
    public static int myMenu (String MESSAGGIO, String... opzioni) {
        System.out.println(MESSAGGIO);
        for (int i=0;i<opzioni.length;i++) {
            System.out.println((i+1) + ": " + opzioni[i]);
        }
        return MyUtil.controlledIntInput("Scelta ", 1, opzioni.length);
    }

    /**
     * Random int.
     *
     * @param min  min
     * @param max  max
     * @return  int
     */
    public static int randomInt(int min, int max) {
        Random r = new Random();
        int n= r.nextInt(max) + min;
        return n ;
    }

    /**
     * Controllo int input.
     *
     * @param MESSAGGIO il messaggio
     * @param min il min
     * @param max il max
     * @return l'int
     */
    public static int controlledIntInput(String MESSAGGIO,int min, int max) {
        int n = intInput(MESSAGGIO + "[" + min + "-" + max + "]");
        if (n < min || n > max) {
            System.out.println("-- Devi inserire un numero compreso tra " + min + " e " + max + "! --");
            return controlledIntInput(MESSAGGIO, min, max);
        }

        return n;
    }

    /**
     * Controlla la  stringa input.
     *
     * @param MESSAGGIO il messaggio
     * @param opzioni le opzioni
     * @return la stringa
     */
    public static String controlledStringInput(String MESSAGGIO, String... opzioni) {
        String s = stringInput(MESSAGGIO + Arrays.toString(opzioni) + ": ");
        for (int i = 0; i < opzioni.length; i++) {
            if (s.equalsIgnoreCase(opzioni[i])) return s;
        }
        System.out.println("-- Devi inserire una delle seguenti stringhe: " + Arrays.toString(opzioni) + " --");
        return controlledStringInput(MESSAGGIO, opzioni);
    }

    /**
     * Controlla la  stringa input.
     *
     * @param MESSAGGIO il messaggio
     * @param opzioni le opzioni
     * @return la stringa
     */
    public static String controlledStringInput(String MESSAGGIO, ArrayList<String> opzioni) {
        String s = stringInput(MESSAGGIO);
        String[] opzioniArray = (String[]) opzioni.toArray();
        for (int i = 0; i < opzioniArray.length; i++) {
            if (s.equalsIgnoreCase(opzioniArray[i])) return s;
        }
        System.out.println("-- Devi inserire una delle seguenti stringhe: " + Arrays.toString(opzioniArray) + " --");
        return controlledStringInput(MESSAGGIO, opzioni);
    }

    /**
     * Leggi file.
     *
     * @param nomeFile il nome file
     * @return  array list
     */
    public static ArrayList<String> leggiFile(String nomeFile) {
        ArrayList<String> stringhe = new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader(new File(nomeFile)));
            String stringa;
            while((stringa = reader.readLine()) != null) {
                stringhe.add(stringa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringhe;
    }

    /**
     * Leggi file stringa.
     *
     * @param nomeFile il nome file
     * @return la stringa
     */
    public static String leggiFileStringa(String nomeFile) {
        String s = "";
        try {
            reader = new BufferedReader(new FileReader(new File(nomeFile)));
            String stringa;
            while((stringa = reader.readLine()) != null) {
                s += (stringa) + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
