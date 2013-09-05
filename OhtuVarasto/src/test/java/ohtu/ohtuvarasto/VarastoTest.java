package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonPikapoistuminenToimii() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-1);

        // varastossa pitäisi olla tilaa 10 - 5 eli 5
        assertEquals(5, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonLisaysToimii() {
        varasto.lisaaVarastoon(2);

        // varaston saldon pitäisi olla 2
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonYliMenevaMaaraTilavuudeksi() {
        varasto.lisaaVarastoon(11);

        // varastossa ei pitäisi olla tilavuutta enempää tuotteita
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaKeskeyttaaVaarallaSyotteella() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-1);

        // varastossa ei piäisi olla enempää tai vähempää tuotteita
        // kuin lisaaVarastoon()-metodilla on määritelty
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiOtetaEnempaaKuinSiellaOn() {
        varasto.lisaaVarastoon(6);

        // varastosta ei pitäisi saada enempää tuotteita kuin siellä on
        assertEquals(6, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1, 1);
        varasto = new Varasto(1, 2);
        varasto = new Varasto(-1, 2);
        varasto = new Varasto(-1, -1);
        varasto.toString();
    }
}