package main;

import factory.AbstractFactory;
import factory.FancyWorld;
import factory.TipoPartita;
import mondo.*;
import giocatore.*;
import utility.*;
import partita.*;

import static factory.AbstractFactory.getFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class main.Main.
 */
public class Main {

    /**
     *  main .
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        boolean menu = true;
        splashScreen();
        MyUtil.stringInputVuoto("Premi il tasto invio per continuare");

        AbstractFactory factory = AbstractFactory.getFactory(FancyWorld.MENU);

        while(menu) {
            switch(MyUtil.myMenu("\n\n\nBenvenuto!", "Nuova partita", "Carica partita", "Tutorial", "Esci")) {
                case 1: factory.createMenu(TipoPartita.NUOVAPARTITA).getPartita().gioca(); break;
                case 2: factory.createMenu(TipoPartita.CARICAPARTITA).getPartita().gioca(); break;
                case 3: factory.createMenu(TipoPartita.TUTORIAL).getPartita().gioca(); break;
                case 4: System.out.println("Esci!"); System.out.println("Un'occasione del genere non ti si ripresenterà più."); menu = false; break;
            }
        }
    }


    /**
     * Splash screen.
     */
    public static void splashScreen() {
        System.out.println("\n\n ,gggggggggggggg                                            ,ggg,      gg      ,gg                                      \n" +
                "dP\"\"\"\"\"\"88\"\"\"\"\"\"                                           dP\"\"Y8a     88     ,8P                     ,dPYb,         8I \n" +
                "Yb,_    88                                                 Yb, `88     88     d8'                     IP'`Yb         8I \n" +
                " `\"\"    88                                                  `\"  88     88     88                      I8  8I         8I \n" +
                "     ggg88gggg                                                  88     88     88                      I8  8'         8I \n" +
                "        88   8,gggg,gg   ,ggg,,ggg,     ,gggg,  gg     gg       88     88     88  ,ggggg,   ,gggggg,  I8 dP    ,gggg,8I \n" +
                "        88   dP\"  \"Y8I  ,8\" \"8P\" \"8,   dP\"  \"Yb I8     8I       88     88     88 dP\"  \"Y8gggdP\"\"\"\"8I  I8dP    dP\"  \"Y8I \n" +
                "  gg,   88  i8'    ,8I  I8   8I   8I  i8'       I8,   ,8I       Y8    ,88,    8Pi8'    ,8I ,8'    8I  I8P    i8'    ,8I \n" +
                "   \"Yb,,8P ,d8,   ,d8b,,dP   8I   Yb,,d8,_    _,d8b, ,d8I        Yb,,d8\"\"8b,,dP,d8,   ,d8',dP     Y8,,d8b,_ ,d8,   ,d8b,\n" +
                "     \"Y8P' P\"Y8888P\"`Y88P'   8I   `Y8P\"\"Y8888PPP\"\"Y88P\"888        \"88\"    \"88\" P\"Y8888P\"  8P      `Y88P'\"Y88P\"Y8888P\"`Y8\n" +
                "                                                     ,d8I'                                                              \n" +
                "                                                   ,dP'8I                                                               \n" +
                "                                                  ,8\"  8I                                                               \n" +
                "                                                  I8   8I                                                               \n" +
                "                                                  `8, ,8I                                                               \n" +
                "                                                   `Y8P\"                                                                \n\n");

    }

}


