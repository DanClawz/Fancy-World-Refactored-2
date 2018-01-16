package mondo;

public enum Tipo {
    LEGNO("legno", 'L', 2),
    FERRO("ferro", 'F', 4),
    BRONZO("bronzo", 'B', 6),
    CRISTALLO("cristallo", 'C', 8),
    ARGENTO("argento", 'A', 10),
    ORO("oro", 'O', 13),
    TITANITE("titanite", 'T', 16),
    DIAMANTE("diamante", 'D', 19),
    VIBRANIO("vibranio", 'V', 22),
    MISTERIOSA("misteriosa", 'M', 25),
    APERTO("aperto", 'X', 0);

    String tipo;
    char t;
    int peso;


    private Tipo(String tipo, char t, int peso) {
        this.tipo = tipo;
        this.t = t;
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public char getT() {
        return t;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }


    public static Tipo assegnaPassaggio(char c) {
        for (Tipo tipo : Tipo.values()) {
            if (c == tipo.getT()) return tipo;
        }
        return null;
    }


}
