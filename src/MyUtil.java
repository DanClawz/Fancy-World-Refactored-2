import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MyUtil {

    private static Scanner input = new Scanner(System.in);

    static {
        input.useDelimiter(System.getProperty("line.separator"));
    }

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

    public static char charInput(String MESSAGGIO) {
        System.out.print(MESSAGGIO + " (se una stringa, viene letto solo il 1^ carattere): ");
        String s = input.next();
        if (!s.equals("")) return s.charAt(0);
        else return charInput("Devi inserire un carattere!");
    }

    public static char controlledCharInput(String MESSAGGIO, char... caratteri) {
        char c = charInput(MESSAGGIO + ": ");
        for (int i = 0; i < caratteri.length; i++) {
            if (c == caratteri[i]) return c;
        }
        System.out.println("-- Devi inserire uno dei seguenti caratteri: " + Arrays.toString(caratteri) + " --");
        return controlledCharInput(MESSAGGIO, caratteri);
    }

    public static String stringInput(String MESSAGGIO) {
        System.out.print(MESSAGGIO + ": ");
        String s = input.next();
        return s;
    }

    public static int myMenu (String MESSAGGIO, String... opzioni) {
        System.out.println(MESSAGGIO);
        for (int i=0;i<opzioni.length;i++) {
            System.out.println((i+1) + ": " + opzioni[i]);
        }
        return MyUtil.controlledIntInput("Scelta ", 1, opzioni.length);
    }

    public static int randomInt(int min, int max) {
        Random r = new Random();
        int n= r.nextInt(max) + min;
        return n ;
    }

    public static int controlledIntInput(String MESSAGGIO,int min, int max) {
        int n = intInput(MESSAGGIO + "[" + min + "-" + max + "]");
        if (n < min || n > max) {
            System.out.println("-- Devi inserire un numero compreso tra " + min + " e " + max + "! --");
            return controlledIntInput(MESSAGGIO, min, max);
        }

        return n;
    }

    public static String controlledStringInput(String MESSAGGIO, String... opzioni) {
        String s = stringInput(MESSAGGIO + Arrays.toString(opzioni) + ": ");
        for (int i = 0; i < opzioni.length; i++) {
            if (s.equalsIgnoreCase(opzioni[i])) return s;
        }
        System.out.println("-- Devi inserire una delle seguenti stringhe: " + Arrays.toString(opzioni) + " --");
        return controlledStringInput(MESSAGGIO, opzioni);
    }

    public static String controlledStringInput(String MESSAGGIO, ArrayList<String> opzioni) {
        String s = stringInput(MESSAGGIO);
        String[] opzioniArray = (String[]) opzioni.toArray();
        for (int i = 0; i < opzioniArray.length; i++) {
            if (s.equalsIgnoreCase(opzioniArray[i])) return s;
        }
        System.out.println("-- Devi inserire una delle seguenti stringhe: " + Arrays.toString(opzioniArray) + " --");
        return controlledStringInput(MESSAGGIO, opzioni);
    }

}
