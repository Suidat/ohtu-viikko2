package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSTemplate {

    private static final Scanner scanner = new Scanner(System.in);
    private TekoalyParannettu tekoaly;

    @Override
    void start() {
        tekoaly = new TekoalyParannettu(20);
    }

    @Override
    String teeSiirto() {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }

    @Override
    void muistaSiirto(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }
}
