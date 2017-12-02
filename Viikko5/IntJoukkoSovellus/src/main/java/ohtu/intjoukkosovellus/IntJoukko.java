
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        IntJoukonLuonninAvustaja(-1);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        IntJoukonLuonninAvustaja(kapasiteetti);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        IntJoukonLuonninAvustaja(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;
    }

    private void IntJoukonLuonninAvustaja(int kapasiteetti){

        if(kapasiteetti<0)
            ljono = new int[KAPASITEETTI];
        else
            ljono = new int[kapasiteetti];

        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }


    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoOld = ljono;
                kopioiTaulukko(ljono, taulukkoOld);
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, ljono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku){
        if(etsiNumero(luku)!=-1)
            return true;
        return false;

    }

    public int etsiNumero(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                if (luku == ljono[i]) {
                    return i;
                }
            }
        }
        return -1;
    }



    public boolean poista(int luku) {
        int kohta = etsiNumero(luku);
        int apu;
        if (kohta != -1) {
            ljono[kohta] = 0;
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = ljono[j];
                ljono[j] = ljono[j + 1];
                ljono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }



    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko z = joukkoOperaatioidenAlustus(a, b, "yhdiste");

        return z;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko z = joukkoOperaatioidenAlustus(a, b, "leikkaus");

        return z;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = joukkoOperaatioidenAlustus(a, b, "erotus");

        return z;
    }

    private static IntJoukko joukkoOperaatioidenAlustus(IntJoukko a, IntJoukko b, String operaatio){
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        switch (operaatio){
            case "erotus":
                z = erota(aTaulu, bTaulu, z);
                break;
            case "leikkaus":
                z = leikkaa(aTaulu, bTaulu, z);
                break;
            case "yhdiste":
                z = yhdista(aTaulu, bTaulu, z);
                break;
        }

        return z;
    }

    private static IntJoukko erota(int[] aTaulu, int[] bTaulu, IntJoukko z){
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

    private static IntJoukko yhdista(int[] aTaulu, int[] bTaulu, IntJoukko z){
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.lisaa(bTaulu[i]);
        }
        return z;
    }

    private static IntJoukko leikkaa(int[] aTaulu, int[] bTaulu, IntJoukko z){
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    z.lisaa(bTaulu[j]);
                }
            }
        }
        return z;
    }
        
}