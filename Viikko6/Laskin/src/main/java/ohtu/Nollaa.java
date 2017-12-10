package ohtu;

import javax.swing.*;

public class Nollaa implements Komento{
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinen;

    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.edellinen = 0;
    }

    @Override
    public void suorita() {
        try {
            edellinen = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.nollaa();
        setFields();
    }

    @Override
    public void peru() {
        tuloskentta.setText(""+edellinen);
        edellinen = 0;
        setFields();
    }

    private void setFields(){
        syotekentta.setText("");
        tuloskentta.setText(""+0);
    }

}
