public class Main {

    public static void main(String[] args) {
        System.out.println("Fancy World");
        switch(MyUtil.myMenu("Benvenuto!", "Gioca", "Istruzioni", "Esci")) {
            case 1: CaricaPartita partita = new CaricaPartita(); break;
            case 2: System.out.println("Come si gioca???"); break;
            case 3: System.out.println("Esci!"); System.out.println("Un'occasione del genere non ti si ripresenterà più."); System.exit(1); break;
        }
    }



}


