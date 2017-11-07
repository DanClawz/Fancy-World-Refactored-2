public class Prova {
    private Coordinata c;
    private int tipo;
    private LeggiProva leggi;
    private int punteggio;
    // costruttore
    public Prova(Coordinata c) {
        this.c = c;
        leggi = new LeggiProva();
        //this.tipo = MyUtil.randomInt(1, 3);
        this.tipo = 3;
        this.punteggio = this.tipo*10;
        leggiProva();
    }

    public void leggiProva() {
        switch(this.tipo) {
            case 1: leggi.leggiProva1(); break;
            case 2: leggi.leggiProva2(); break;
            case 3: leggi.leggiProva3(); break;
        }
    }

    public int prova1() {
        int tentativi = 2;
        int indiceDomanda = MyUtil.randomInt(0, leggi.getProva1().size()-1);

        while(tentativi > 0) {
            int scelta = MyUtil.myMenu(leggi.getProva1().get(indiceDomanda).getDomanda(), leggi.getProva1().get(indiceDomanda).getOpzioni());
            if (leggi.checkRispostaProva1(indiceDomanda, leggi.getProva1().get(indiceDomanda).getOpzioni()[scelta-1])) {
                System.out.println("Risposta corretta!");
                return punteggio;
            }
            else {
                System.out.println("Risposta non corretta! Hai ancora " + --tentativi + " tentativi");
            }
        }

        System.out.println("Tentativi esauriti! Risposta esatta: " + leggi.getProva1().get(indiceDomanda).getRisposta());
        return -punteggio;
    }

    public int prova2() {
        int tentativi = 15;
        int indiceParola = MyUtil.randomInt(0, leggi.getProva2().size()-1);


        String risposta = leggi.nascondiCaratteri(indiceParola);
        System.out.println("Inserisci risposta: ");

        while(tentativi > 0) {
            String inputUtente = MyUtil.stringInput(leggi.stringaNascostaConSpazi(risposta));
            if (leggi.checkRispostaProva2(indiceParola, inputUtente))
                break;
            else
                risposta = leggi.match(indiceParola, inputUtente, risposta);
            System.out.println("Hai ancora " + --tentativi);
        }
        if (tentativi == 0) {
            System.out.println("Prova non superata! Risposta: " + leggi.getProva2().get(indiceParola).getRisposta());
            return -punteggio;
        }
        else {
            System.out.println("Risposta corretta!");
            return punteggio;
        }
    }

    public int prova3() {
        int tentativi = 3;
        int indiceRisposta = MyUtil.randomInt(0, leggi.getProva3().size()-1);

        while(!leggi.checkRispostaProva3(indiceRisposta, MyUtil.stringInput(leggi.getProva3().get(indiceRisposta).getDomanda())) && --tentativi > 0) {
            System.out.println("Risposta non corretta! Hai ancora " + tentativi + " tentativi a disposizione.");
        }
        if (tentativi == 0) {
            System.out.println("Tentativi esauriti! La risposta corretta e' " + leggi.getProva3().get(indiceRisposta).getRisposta());
            return -punteggio;
        }
        else {
            System.out.println("Risposta corretta!");
            return punteggio;
        }

    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Coordinata getC() {
        return c;
    }

    public void setC(Coordinata c) {
        this.c = c;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public LeggiProva getLeggi() {
        return leggi;
    }

    public void setLeggi(LeggiProva leggi) {
        this.leggi = leggi;
    }

    public static void main (String args[]) {
        Prova p = new Prova(new Coordinata(0, 0));
        /*int tentativi = 3;
        LeggiProva leggi = new LeggiProva();
        leggi.leggiProva3();
        int r = MyUtil.randomInt(0, leggi.getProva3().size()-1);

        while(!leggi.checkRispostaProva3(r, MyUtil.stringInput(leggi.getProva3().get(r).getDomanda())) && tentativi-- > 0) {
            System.out.println("Risposta non corretta! Hai ancora " + tentativi + " tentativi a disposizione.");
        }
        System.out.println("Risposta corretta!");*/

        System.out.println("Hai vinto " + p.prova3() + " punti");
    }

}
