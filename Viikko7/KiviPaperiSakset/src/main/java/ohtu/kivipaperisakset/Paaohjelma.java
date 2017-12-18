package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loop:
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            KPSTemplate peli;
            switch (scanner.nextLine()) {
                case "a":
                    peli = KPSTemplate.LuoPvsP();
                    break;
                case "b":
                    peli = KPSTemplate.luoHelppo();
                    break;
                case "c":
                    peli = KPSTemplate.luoVaikea();
                    break;
                default:
                    break loop;
            }
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            peli.pelaa();
        }

    }
}
