package mvc;

import mondo.Luogo;

public class ControllerLuogo {
    private Luogo luogo; // model
    private Mappa mappa; // view

    public ControllerLuogo(Luogo luogo, Mappa mappa) {
        this.luogo = luogo;
        this.mappa = mappa;
    }


    public void mossa(char input) {
        if(input == 'n' || input == 's' || input == 'e' || input == 'w') muovi(input);
        else if (input == 'u' || input == 'd') cambiaLuogo(input);
        else if (input == 'x') deposita();
    }


    public void muovi(char mossa) {

    }

    public void cambiaLuogo(char mossa) {

    }

    public void deposita() {

    }




    public Luogo getLuogo() {
        return luogo;
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

    public Mappa getMappa() {
        return mappa;
    }

    public void setMappa(Mappa mappa) {
        this.mappa = mappa;
    }
}
