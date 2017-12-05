import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean menu = true;
        splashScreen();
        MyUtil.stringInputVuoto("Premi un tasto per continuare");

        /*while(menu) {
            switch(MyUtil.myMenu("Benvenuto!", "Gioca", "Istruzioni", "Esci")) {
                case 1: new CaricaPartita(); break;
                case 2: System.out.println("Come si gioca???"); break;
                case 3: System.out.println("Esci!"); System.out.println("Un'occasione del genere non ti si ripresenterà più."); menu = false; break;
            }
        }*/

        while(menu) {
            switch(MyUtil.myMenu("\n\n\nBenvenuto!", "Nuova partita", "Carica partita", "Tutorial", "Esci")) {
                case 1: new CaricaPartita(true); break;
                case 2: new CaricaPartita(false); break;
                case 3: new Partita(new Giocatore(0, 10), new Mondo("tutorial", 99)); break;
                case 4: System.out.println("Esci!"); System.out.println("Un'occasione del genere non ti si ripresenterà più."); menu = false; break;
            }
        }

    }


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


