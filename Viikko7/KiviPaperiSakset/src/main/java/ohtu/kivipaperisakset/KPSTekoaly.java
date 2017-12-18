package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPSTemplate {

    private static final Scanner scanner = new Scanner(System.in);
    private Tekoaly tekoaly;

    @Override
    void start() {
        tekoaly = new Tekoaly();
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