public class Main {

    public static void main(String[] args) {
        System.out.println("Fancy World");
        String nomeGiocatore = MyUtil.stringInput("Inserisci il tuo nome");
        switch(MyUtil.myMenu("Benvenuto, " + nomeGiocatore + "!", "Sì, giochiamo!", "Quali sono le regole?", "No, non sono interessato")) {
            case 1: Partita partita = new Partita(); break;
            case 2: System.out.println("Come si gioca???"); break;
            case 3: System.out.println("Esci!"); System.out.println("Un'occasione del genere non ti si ripresenterà più."); System.exit(1); break;
        }
    }
}


