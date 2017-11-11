package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

/**
 * Created by frestmau on 11.11.2017.
 */
@Component
public interface Pankkipinta {
    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}
