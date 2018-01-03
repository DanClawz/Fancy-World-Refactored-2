package mondo;

import utility.MyUtil;

import java.util.ArrayList;

public class Portale {
    private Coordinata c;
    private int piano1, piano2;
    private Tipo tipo;

    public Portale(Coordinata c, int piano1, int piano2, Tipo tipo) {
        this.c = c;
        this.piano1 = piano1;
        this.piano2 = piano2;
        this.tipo = tipo;
    }

    public int pianoDestinazione(int pianoCorrente) {
        if (pianoCorrente == piano1) return piano2;
        if (pianoCorrente == piano2) return piano1;
        return 0;
    }


    public Coordinata getC() {
        return c;
    }

    public void setC(Coordinata c) {
        this.c = c;
    }

    public int getPiano1() {
        return piano1;
    }

    public void setPiano1(int piano1) {
        this.piano1 = piano1;
    }

    public int getPiano2() {
        return piano2;
    }

    public void setPiano2(int piano2) {
        this.piano2 = piano2;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public static void main(String args[]) {
        ArrayList<String> stringhe = MyUtil.leggiFile("./Passaggi/passaggi");
        String[] s = stringhe.get(1).split(" ");
        System.out.println(s[3]);

    }
}
