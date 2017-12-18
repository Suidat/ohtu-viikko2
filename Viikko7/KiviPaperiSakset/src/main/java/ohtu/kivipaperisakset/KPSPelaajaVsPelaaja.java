package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPSTemplate {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    void start() {
        //Tyhjä
    }

    @Override
    String teeSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        String siirto = scanner.nextLine();
        return siirto;
    }

    @Override
    void muistaSiirto(String siirto) {
        //Tyhjä
    }
}