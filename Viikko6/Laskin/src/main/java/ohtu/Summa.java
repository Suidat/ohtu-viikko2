package ohtu;

import javax.swing.*;

public class Summa implements Komento {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinen;

    public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
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
        sovellus.plus(edellinen);
        setFields();
    }

    @Override
    public void peru() {
        sovellus.miinus(edellinen);
        edellinen = 0;
        setFields();
    }

    private void setFields(){
        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
    }
}
