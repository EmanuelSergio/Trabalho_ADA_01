package adaptadores;

import br.furb.analise.algoritmos.PersianaNatLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersianaNatLightAdaptadorTest {

    private PersianaNatLight persianaNatLight;
    private PersianaNatLightAdaptador adaptador;

    @BeforeEach
    void setUp() {
        persianaNatLight = new PersianaNatLight();
        adaptador = new PersianaNatLightAdaptador(persianaNatLight);
    }

    @Test
    void fechar_quandoAbertaEErguida_descePalhetaEFecha() {
        adaptador.fechar();
        assertFalse(persianaNatLight.estaPalhetaErguida());
        assertFalse(persianaNatLight.estaPalhetaAberta());
    }

    @Test
    void fechar_quandoJaFechada_continuaFechada() {
        adaptador.fechar();
        adaptador.fechar();
        assertFalse(persianaNatLight.estaPalhetaErguida());
        assertFalse(persianaNatLight.estaPalhetaAberta());
    }

    @Test
    void fechar_quandoErguida_desceMesmoSePalhetaJaFechada() {
        persianaNatLight.descerPalheta();
        adaptador.fechar();
        assertFalse(persianaNatLight.estaPalhetaAberta());
    }

    @Test
    void abrir_quandoJaAbertaEErguida_continuaAbertaEErguida() {
        adaptador.abrir();
        assertTrue(persianaNatLight.estaPalhetaAberta());
        assertTrue(persianaNatLight.estaPalhetaErguida());
    }

    @Test
    void abrir_aposFechar_reabreEErguePalheta() {
        adaptador.fechar();
        adaptador.abrir();
        assertTrue(persianaNatLight.estaPalhetaAberta());
        assertTrue(persianaNatLight.estaPalhetaErguida());
    }

    @Test
    void abrir_quandoApenasDescida_abreEErguePalheta() {
        persianaNatLight.descerPalheta();
        adaptador.abrir();
        assertTrue(persianaNatLight.estaPalhetaErguida());
    }
}
