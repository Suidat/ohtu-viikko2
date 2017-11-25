package ohtu;
import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import sun.security.krb5.internal.KrbApErrException;

import static org.mockito.Mockito.*;

public class kauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void before(){
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void asiakkaanTiedotOvatOikeinTilisiirrossa(){
            when(viite.uusi()).thenReturn(42);
            when(varasto.saldo(1)).thenReturn(10);
            when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

            Kauppa k = new Kauppa(varasto, pankki, viite);

            k.aloitaAsiointi();
            k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
            k.tilimaksu("pekka", "12345");

            verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));

        }

    @Test
    public void useammanTuotteenOstoOnnistuu(){
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(10));
    }

    @Test
    public void samaaTuotettaVoiOstaaUseammanKappaleen(){
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(10));
    }

    @Test
    public void loppunuttaTuotettaÉiVeloiteta(){
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));
    }

    @Test
    public void uusiAsiakasNollaaKaiken(){
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("matti", "67890");
        verify(pankki).tilisiirto(eq("matti"), eq(42), eq("67890"), anyString(),eq(5));
    }

    @Test
    public void uusiAsiakasSaaUudenViitteen(){

        when(viite.uusi()).
                thenReturn(42).
                thenReturn(43);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("matti", "67890");
        verify(pankki).tilisiirto(eq("matti"), eq(43), eq("67890"), anyString(),eq(5));
    }

    @Test
    public void koristaVoiPoistaaTuotteen(){
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));
    }

    @Test
    public void kaupanLuominenOnnistuuOikein(){
        Kauppa k = new Kauppa();
        k.aloitaAsiointi();
        k.tilimaksu("pekka","12345");

    }
}


