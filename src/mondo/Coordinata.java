package mondo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * La classe mondo.Coordinata.
 */
public class Coordinata implements Serializable {
    
    /** I parametri della classe. */
    private int x, y;

    /**
     * Il costruttore della classe.
     *
     * @param x  x
     * @param y  y
     */
    public Coordinata(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Resituisce la x.
     *
     * @return la x
     */
    public int getX() {
        return x;
    }

    /**
     * Assegna la x.
     *
     * @param x la  x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Resituisce la y.
     *
     * @return la y
     */
    public int getY() {
        return y;
    }

    /**
     * Assegna la y.
     *
     * @param y la y
     */
    public void setY(int y) {
        this.y = y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {           //override di equals per gli oggetti mondo.Coordinata
        if (o instanceof Coordinata) {
            Coordinata c = (Coordinata)o;
            if (this.x == c.getX() && this.y == c.getY()) return true;
        }
        return false;
    }
}
