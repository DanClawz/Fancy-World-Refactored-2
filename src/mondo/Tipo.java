package mondo;

public enum Tipo {
    LEGNO("legno", 'L'),
    FERRO("ferro", 'F'),
    BRONZO("bronzo", 'B'),
    CRISTALLO("cristallo", 'C'),
    ARGENTO("argento", 'A'),
    ORO("oro", 'O'),
    TITANITE("titanite", 'T'),
    DIAMANTE("diamante", 'D'),
    VIBRANIO("vibranio", 'V'),
    MISTERIOSA("misteriosa", 'M'),
    APERTO("", 'X');

    String tipo;
    char t;

    private Tipo(String tipo, char t) {
        this.tipo = tipo;
        this.t = t;
    }

    public String getTipo() {
        return tipo;
    }

    public char getT() {
        return t;
    }

    public static Tipo assegnaPassaggio(char c) {
        for (Tipo tipo : Tipo.values()) {
            if (c == tipo.getT()) return tipo;
        }
        return null;
    }
}
