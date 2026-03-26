package adaptadores;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArCondicionadoVentoBaumnAdaptadorTest {

    private ArCondicionadoVentoBaumn ventoBaumn;
    private ArCondicionadoVentoBaumnAdaptador adaptador;

    @BeforeEach
    void setUp() {
        ventoBaumn = new ArCondicionadoVentoBaumn();
        adaptador  = new ArCondicionadoVentoBaumnAdaptador(ventoBaumn);
    }

    @Test
    void ligar_permiteDefinirTemperatura() {
        adaptador.ligar();
        assertDoesNotThrow(() -> adaptador.definirTemperatura(20));
    }

    @Test
    void desligar_aposLigar_bloqueiaDefinirTemperatura() {
        adaptador.ligar();
        adaptador.desligar();
        assertThrows(IllegalArgumentException.class,
                () -> adaptador.definirTemperatura(20));
    }

    @Test
    void definirTemperatura_quandoLigado_alteraTemperatura() {
        adaptador.ligar();
        adaptador.definirTemperatura(20);
        assertEquals(20, ventoBaumn.getTemperatura());
    }

    @Test
    void aumentarTemperatura_quandoLigado_incrementaEmUm() {
        adaptador.ligar();
        int antes = ventoBaumn.getTemperatura();
        adaptador.aumentarTemperatura();
        assertEquals(antes + 1, ventoBaumn.getTemperatura());
    }

    @Test
    void diminuirTemperatura_quandoLigado_decrementaEmUm() {
        adaptador.ligar();
        int antes = ventoBaumn.getTemperatura();
        adaptador.diminuirTemperatura();
        assertEquals(antes - 1, ventoBaumn.getTemperatura());
    }
}
