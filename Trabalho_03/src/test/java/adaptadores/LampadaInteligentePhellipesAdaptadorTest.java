package adaptadores;

import br.furb.analise.algoritmos.LampadaPhellipes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LampadaInteligentePhellipesAdaptadorTest {

    private LampadaPhellipes lampadaPhellipes;
    private LampadaInteligentePhellipesAdaptador adaptador;

    @BeforeEach
    void setUp() {
        lampadaPhellipes = new LampadaPhellipes();
        adaptador = new LampadaInteligentePhellipesAdaptador(lampadaPhellipes);
    }

    @Test
    void ligar_quandoDesligada_defineIntensidade100() {
        adaptador.ligar();
        assertEquals(100, lampadaPhellipes.getIntensidade());
    }

    @Test
    void ligar_quandoJaLigada_mantemIntensidade100() {
        lampadaPhellipes.setIntensidade(100);
        adaptador.ligar();
        assertEquals(100, lampadaPhellipes.getIntensidade());
    }

    @Test
    void desligar_quandoLigada_defineIntensidade0() {
        lampadaPhellipes.setIntensidade(100);
        adaptador.desligar();
        assertEquals(0, lampadaPhellipes.getIntensidade());
    }

    @Test
    void desligar_quandoJaDesligada_mantemIntensidade0() {
        adaptador.desligar(); // parte de intensidade=0
        assertEquals(0, lampadaPhellipes.getIntensidade());
    }

    @Test
    void ligar_comIntensidadeParcial_defineIntensidade100() {
        lampadaPhellipes.setIntensidade(50);
        adaptador.ligar();
        assertEquals(100, lampadaPhellipes.getIntensidade());
    }
}
