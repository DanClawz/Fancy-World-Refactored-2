package mondo;

import utility.MyUtil;

import java.io.Serializable;
import java.util.ArrayList;

public class Portale implements Serializable{
    private Coordinata c;
    private int piano1, piano2;
    private Tipo tipo;

    public Portale(Coordinata c, int piano1, int piano2, Tipo tipo) {
        this.c = c;
        this.piano1 = piano1;
        this.piano2 = piano2;
        this.tipo = tipo;
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

}
