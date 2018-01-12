package system_msg;

import utility.MyUtil;

import java.util.ArrayList;

public class Msg {
    private static ArrayList<String> msg = MyUtil.leggiFile("./messages");

    public static String splash() {
        return msg.get(0);
    }

    public static String msgMenuPrincipale() {
        return msg.get(1);
    }

    public static String[] menuPrincipale() {
        ArrayList<String> opzioni = new ArrayList<String>();
        opzioni.add(msg.get(2));
        opzioni.add(msg.get(3));
        opzioni.add(msg.get(4));
        opzioni.add(msg.get(5));
        return opzioni.toArray(new String[4]);
    }

    public static String msgEsci() {
        return msg.get(6);
    }

    public static String msgNomeSalvataggio() {
        return msg.get(7);
    }

    public static String msgAbilitaAutosave() {
        return msg.get(8);
    }

    public static char[] opzioniSN() {
        return new char[]{'s', 'y', 'n'};
    }

    public static String msgConfigMondi() {
        return msg.get(9);
    }

    public static String msgConfigSceltaMondo() {
        return msg.get(10);
    }

    public static String msgGiocaSceltaMondo() {
        return msg.get(11);
    }

    public static String msgAbilitaCambiaMondo() {
        return msg.get(12);
    }

    public static String msgConfigMondiTitolo() {
        return msg.get(13);
    }

    public static String[] opzioniConfigMondi() {
        return new String[]{
                msg.get(14),
                msg.get(15),
                msg.get(16),
                msg.get(17),
                msg.get(18),
                msg.get(19),
                msg.get(20),
                msg.get(21),
                msg.get(22),
                msg.get(23),
                msg.get(24),
        };
    }

    public static String msgPunteggioIniziale() {
        return msg.get(25);
    }

    public static String msgPunteggioInizialeModificato() {
        return msg.get(26);
    }

    public static String msgPunteggioFinale() {
        return msg.get(27);
    }

    public static String msgPunteggioFinaleModificato() {
        return msg.get(28);
    }

    public static String msgNessunaProvaPresente() {
        return msg.get(29);
    }

    public static String msgNTipiProve() {
        return msg.get(30);
    }

    public static String msgNTipiProveModificato() {
        return msg.get(31);
    }

    public static String msgValoreMaxProva() {
        return msg.get(32);
    }

    public static String msgValoreMaxProvaModificato() {
        return msg.get(33);
    }

    public static String msgValoreMaxProvaMinore() {
        return msg.get(34);
    }

    public static String msgValoreProvaNuovo() {
        return msg.get(35);
    }

    public static String msgValoreProvaNuovoModificato() {
        return msg.get(36);
    }

    public static String msgPesoMaxChiave() {
        return msg.get(37);
    }

    public static String msgPesoMaxChiaveModificato() {
        return msg.get(38);
    }

    public static String msgPesoMaxChiaveMinore() {
        return msg.get(39);
    }

    public static String msgSelezionaChiave() {
        return msg.get(40);
    }

    public static String msgInserisciPeso() {
        return msg.get(41);
    }

    public static String msgPesoModificato() {
        return msg.get(42);
    }

    public static String msgNMaxChiavi() {
        return msg.get(43);
    }

    public static String msgNMaxChiaviModificato() {
        return msg.get(44);
    }

    public static String msgMaxPesoChiavi() {
        return msg.get(45);
    }

    public static String msgMaxPesoChiaviModificato() {
        return msg.get(46);
    }

    public static String msgNTipiChiavi() {
        return msg.get(47);
    }

    public static String msgNTipiChiaviModificato() {
        return msg.get(48);
    }

    public static String msgCaricaPartita() {
        return msg.get(49);
    }

    public static String msgSostieniProva() { return msg.get(50); }

    public static String msgPunteggio() { return msg.get(51); }

    public static String msgNessunaChiave() { return msg.get(52); }

    public static String msgChiaviInPossesso() { return msg.get(53); }

    public static String msgRaccogliChiave() { return msg.get(54); }

    public static String msgChiaveRaccolta() { return msg.get(55); }

    public static String msgChiaveNonRaccolta() { return msg.get(56); }

    public static String msgMossa() { return msg.get(57); }

    public static String msgChiaveDaDepositare() { return msg.get(58); }

    public static String msgChiaveDepositata() { return msg.get(59); }

    public static String msgChiaveNonDepositata() { return msg.get(60); }

    public static String msgNessunaChiaveDepositata() { return msg.get(61); }

    public static String msgNessunaChiavePresente() { return msg.get(62); }

    public static String msgPassaggioVerso() { return msg.get(63); }

    public static String msgObiettivoRaggiunto() { return msg.get(64); }

    public static String msgPunteggioNonRaggiunto() { return msg.get(65); }

    public static String msgMondoCompletato() { return msg.get(66); }

    public static String msgPartitaSalvata() { return msg.get(67); }

    public static String msgMossaNonPossibile() { return msg.get(68); }

    public static String msgMossaPossibile() { return msg.get(69); }

    public static String msgTipoPassaggio() { return msg.get(70); }

    public static String msgPassaggioAperto() { return msg.get(71); }

    public static String msgDirezioneErrata() { return msg.get(72); }

    public static String msgChiaveRichiesta() { return msg.get(73); }

    public static String msgLuogoGoal() { return msg.get(74); }
}
