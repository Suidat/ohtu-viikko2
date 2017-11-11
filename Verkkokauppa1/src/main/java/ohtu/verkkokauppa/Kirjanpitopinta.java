package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 * Created by frestmau on 11.11.2017.
 */
public interface Kirjanpitopinta {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
