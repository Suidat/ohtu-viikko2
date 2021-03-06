package ohtu.verkkokauppa;

public class Kauppa {

    private Varasto varasto;
    private Pankkipinta pankki;
    private Ostoskori ostoskori;
    private Generaattori viitegeneraattori;
    private String kaupanTili;

    public Kauppa() {
        varasto = new Varasto();
        pankki = new Pankki();
        viitegeneraattori = new Viitegeneraattori();
        kaupanTili = "33333-44455";
    }

    public Kauppa(Varasto v, Pankkipinta p, Generaattori g){
        this.varasto = v;
        this.pankki = p;
        this.viitegeneraattori = g;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
        ostoskori.poista(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id)>0) {
            Tuote t = varasto.haeTuote(id);             
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();
        
        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
