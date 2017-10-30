import java.io.Serializable;

public class Coordinata implements Serializable {
    private int x, y;

    public Coordinata(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object o) {           //override di equals per gli oggetti Coordinata
        if (o instanceof Coordinata) {
            Coordinata c = (Coordinata)o;
            if (this.x == c.getX() && this.y == c.getY()) return true;
        }
        return false;
    }
}
