package mondo;

public class Passaggio2 {
    private Portale p;
    private boolean aperto;

    public Passaggio2(Portale p, boolean aperto) {
        this.p = p;
        this.aperto = aperto;
    }






    public Portale getP() {
        return p;
    }

    public void setP(Portale p) {
        this.p = p;
    }

    public boolean isAperto() {
        return aperto;
    }

    public void setAperto(boolean aperto) {
        this.aperto = aperto;
    }
}
