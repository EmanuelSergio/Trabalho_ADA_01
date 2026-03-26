import adaptadores.*;
import br.furb.analise.algoritmos.*;
import interfaces.ArCondicionado;
import interfaces.Lampada;
import interfaces.Persiana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispositivosFactoryTest {

    private DispositivosFactory factory;

    @BeforeEach
    void setUp() {
        factory = new DispositivosFactory();
    }

    @Test
    void criarAdaptadorArCondicionado_comVentoBaumn_retornaAdaptadorVentoBaumn() {
        ArCondicionado resultado = factory.criarAdaptadorArCondicionado(new ArCondicionadoVentoBaumn());
        assertInstanceOf(ArCondicionadoVentoBaumnAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorArCondicionado_comGellaKaza_retornaAdaptadorGellaKaza() {
        ArCondicionado resultado = factory.criarAdaptadorArCondicionado(new ArCondicionadoGellaKaza());
        assertInstanceOf(ArCondicionadoGellaKazaAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorArCondicionado_comNull_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorArCondicionado(null));
    }

    @Test
    void criarAdaptadorArCondicionado_comTipoDesconhecido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorArCondicionado("tipoInvalido"));
    }

    @Test
    void criarAdaptadorPersiana_comSolarius_retornaAdaptadorSolarius() {
        Persiana resultado = factory.criarAdaptadorPersiana(new PersianaSolarius());
        assertInstanceOf(PersianaSolariusAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorPersiana_comNatLight_retornaAdaptadorNatLight() {
        Persiana resultado = factory.criarAdaptadorPersiana(new PersianaNatLight());
        assertInstanceOf(PersianaNatLightAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorPersiana_comNull_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorPersiana(null));
    }

    @Test
    void criarAdaptadorPersiana_comTipoDesconhecido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorPersiana(42));
    }

    @Test
    void criarAdaptadorLampada_comPhellipes_retornaAdaptadorPhellipes() {
        Lampada resultado = factory.criarAdaptadorLampada(new LampadaPhellipes());
        assertInstanceOf(LampadaInteligentePhellipesAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorLampada_comShoyuMi_retornaAdaptadorShoyouMi() {
        Lampada resultado = factory.criarAdaptadorLampada(new LampadaShoyuMi());
        assertInstanceOf(LampadaInteligenteShoyouMiAdaptador.class, resultado);
    }

    @Test
    void criarAdaptadorLampada_comNull_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorLampada(null));
    }

    @Test
    void criarAdaptadorLampada_comTipoDesconhecido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.criarAdaptadorLampada(new Object()));
    }
}
