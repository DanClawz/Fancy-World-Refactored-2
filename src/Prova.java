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
        leggi.leggiProva1();
        //System.out.println(leggi.getProva1().get(0).toStringProva1());
        //leggi.aCaso();

        int r = MyUtil.randomInt(0, leggi.getProva1().size()-1);
        //System.out.println(leggi.getProva1().get(1).getOpzioni()[0]);

        //System.out.println(leggi.getProva1().get(r).toStringProva1());

        //MyUtil.controlledStringInput(leggi.getProva3().get(r).getDomanda(), leggi.getProva1().get(r).getOpzioni());

        int scelta = MyUtil.myMenu(leggi.getProva1().get(r).getDomanda(), leggi.getProva1().get(r).getOpzioni());
        if (leggi.checkRispostaProva1(r, leggi.getProva1().get(r).getOpzioni()[scelta-1])) {
            System.out.println("Risposta esatta!");
        }
        else System.out.println("Risposta non esatta!");


    }

}
