package ohtu.verkkokauppa;

/**
 * Created by frestmau on 11.11.2017.
 */
public interface Pankkipinta {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
