package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSTemplate {

    private static final Scanner scanner = new Scanner(System.in);
    private Tuomari tuomari;

    public void pelaa() {

        tuomari = new Tuomari();
        start();

        System.out.println("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto = teeSiirto();

        gameLoop(ekanSiirto, tokanSiirto);

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    private void gameLoop(String ekanSiirto, String tokanSiirto) {
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {

            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            muistaSiirto(ekanSiirto);

            System.out.println("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            tokanSiirto = teeSiirto();

        }
    }


    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    public static KPSPelaajaVsPelaaja LuoPvsP() {
        return new KPSPelaajaVsPelaaja();
    }

    public static KPSTekoaly luoHelppo() {
        return new KPSTekoaly();
    }

    public static KPSParempiTekoaly luoVaikea() {
        return new KPSParempiTekoaly();
    }

    abstract void start();

    abstract String teeSiirto();

    abstract void muistaSiirto(String siirto);
}

