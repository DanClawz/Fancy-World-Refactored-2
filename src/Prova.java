public class Prova {
    // costruttore


    public static void main (String args[]) {
        /*int tentativi = 3;
        LeggiProva leggi = new LeggiProva();
        leggi.leggiProva3();
        int r = MyUtil.randomInt(0, leggi.getProva3().size()-1);

        while(!leggi.checkRispostaProva3(r, MyUtil.stringInput(leggi.getProva3().get(r).getDomanda())) && tentativi-- > 0) {
            System.out.println("Risposta non corretta! Hai ancora " + tentativi + " tentativi a disposizione.");
        }
        System.out.println("Risposta corretta!");*/

        int tentativi = 2;
        LeggiProva leggi = new LeggiProva();
        leggi.leggiProva2();

        int r = MyUtil.randomInt(0, leggi.getProva2().size()-1);

        String risposta = leggi.nascondiCaratteri(r);
        System.out.println("Inserisci risposta: ");

        while(true) {
            String inputUtente = MyUtil.stringInput(leggi.stringaNascostaConSpazi(risposta));
            String t;
            if (leggi.checkRispostaProva2(r, inputUtente))
                break;
            else
                risposta = leggi.match(r, inputUtente, risposta);

        }
        System.out.println("Risposta corretta");




    }

}
