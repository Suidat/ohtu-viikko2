package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by frestmau on 11.11.2017.
 */
@Component
public interface Kirjanpitopinta {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
