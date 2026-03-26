package adaptadores;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArCondicionadoGellaKazaAdaptadorTest {

    private ArCondicionadoGellaKaza gellaKaza;
    private ArCondicionadoGellaKazaAdaptador adaptador;

    @BeforeEach
    void setUp() {
        gellaKaza = new ArCondicionadoGellaKaza();
        adaptador = new ArCondicionadoGellaKazaAdaptador(gellaKaza);
    }

    @Test
    void ligar_quandoDesligado_ficaLigado() {
        adaptador.ligar();
        assertTrue(gellaKaza.estaLigado());
    }

    @Test
    void ligar_quandoJaLigado_continuaLigado() {
        gellaKaza.ativar();
        adaptador.ligar();
        assertTrue(gellaKaza.estaLigado());
    }

    @Test
    void desligar_quandoLigado_ficaDesligado() {
        gellaKaza.ativar();
        adaptador.desligar();
        assertFalse(gellaKaza.estaLigado());
    }

    @Test
    void desligar_quandoJaDesligado_continuaDesligado() {
        adaptador.desligar();
        assertFalse(gellaKaza.estaLigado());
    }

    @Test
    void aumentarTemperatura_incrementaEmUm() {
        int temperaturaAntes = gellaKaza.getTemperatura();
        adaptador.aumentarTemperatura();
        assertEquals(temperaturaAntes + 1, gellaKaza.getTemperatura());
    }

    @Test
    void diminuirTemperatura_decrementaEmUm() {
        int temperaturaAntes = gellaKaza.getTemperatura();
        adaptador.diminuirTemperatura();
        assertEquals(temperaturaAntes - 1, gellaKaza.getTemperatura());
    }

    @Test
    void definirTemperatura_quandoAbaixoDaAtual_chegaNoValorAlvo() {
        adaptador.definirTemperatura(25);
        assertEquals(25, gellaKaza.getTemperatura());
    }

    @Test
    void definirTemperatura_quandoAcimaDaAtual_chegaNoValorAlvo() {
        adaptador.definirTemperatura(30);
        assertEquals(30, gellaKaza.getTemperatura());
    }

    @Test
    void definirTemperatura_quandoIgualAAtual_mantemTemperatura() {
        int temperaturaAtual = gellaKaza.getTemperatura();
        adaptador.definirTemperatura(temperaturaAtual);
        assertEquals(temperaturaAtual, gellaKaza.getTemperatura());
    }
}
