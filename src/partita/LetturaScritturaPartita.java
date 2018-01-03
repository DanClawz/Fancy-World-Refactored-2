package partita;

import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * La classe partita.LetturaScritturaPartita.
 */
public class LetturaScritturaPartita implements Serializable {

    /**
     * Scrivi partite.
     *
     * @param p la lista delle partite
     */
    public static void scrivi(ListaPartite p) {
        try {
            FileOutputStream file=new FileOutputStream("./saves.txt");
            ObjectOutputStream fOut= new ObjectOutputStream(file);
            fOut.writeObject(p);
            fOut.close();
        }catch(IOException e) {
            System.out.println("\nNon scrive !!!\n" + e);
            e.printStackTrace();
        }
    }

    /**
     * Leggi partite.
     *
     * @return la lista delle partite
     */
    public static ListaPartite leggi() {
        ListaPartite partite = new ListaPartite();
        try {
            FileInputStream file=new FileInputStream("./saves.txt");
            ObjectInputStream fIn= new ObjectInputStream(file);
            boolean endFile=false;
            while(!endFile) {
                try {
                    ListaPartite lp=(ListaPartite)fIn.readObject();
                    partite = lp;
                }catch(IOException e) {
                    endFile=true;
                }
            }
            fIn.close();
        }catch(ClassNotFoundException e) {
            //...
        }catch(IOException e) {
            System.out.println("\nNessun salvataggio presente!\n");
        }
        return partite;
    }

}
