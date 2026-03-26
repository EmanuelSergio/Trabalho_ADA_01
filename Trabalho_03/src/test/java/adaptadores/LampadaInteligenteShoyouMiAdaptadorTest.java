package adaptadores;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampadaInteligenteShoyouMiAdaptadorTest {

    private LampadaShoyuMi lampadaShoyuMi;
    private LampadaInteligenteShoyouMiAdaptador adaptador;

    @BeforeEach
    void setUp() {
        lampadaShoyuMi = new LampadaShoyuMi();
        adaptador = new LampadaInteligenteShoyouMiAdaptador(lampadaShoyuMi);
    }

    @Test
    void ligar_quandoDesligada_ficaLigada() {
        adaptador.ligar();
        assertTrue(lampadaShoyuMi.estaLigada());
    }

    @Test
    void ligar_quandoJaLigada_continuaLigada() {
        lampadaShoyuMi.ligar();
        adaptador.ligar();
        assertTrue(lampadaShoyuMi.estaLigada());
    }

    @Test
    void desligar_quandoLigada_ficaDesligada() {
        lampadaShoyuMi.ligar();
        adaptador.desligar();
        assertFalse(lampadaShoyuMi.estaLigada());
    }

    @Test
    void desligar_quandoJaDesligada_continuaDesligada() {
        adaptador.desligar();
        assertFalse(lampadaShoyuMi.estaLigada());
    }
}
