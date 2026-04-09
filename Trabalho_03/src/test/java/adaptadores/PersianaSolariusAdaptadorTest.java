package adaptadores;

import br.furb.analise.algoritmos.PersianaSolarius;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersianaSolariusAdaptadorTest {

    private PersianaSolarius persianaSolarius;
    private PersianaSolariusAdaptador adaptador;

    @BeforeEach
    void setUp() {
        persianaSolarius = new PersianaSolarius();
        adaptador = new PersianaSolariusAdaptador(persianaSolarius);
    }

    @Test
    void fechar_quandoAberta_ficaFechada() {
        adaptador.fechar();
        assertFalse(persianaSolarius.estaAberta());
    }

    @Test
    void fechar_quandoJaFechada_continuaFechada() {
        persianaSolarius.descerPersiana(); // fecha diretamente na lib
        adaptador.fechar();
        assertFalse(persianaSolarius.estaAberta());
    }

    @Test
    void abrir_quandoFechada_ficaAberta() {
        persianaSolarius.descerPersiana();
        adaptador.abrir();
        assertTrue(persianaSolarius.estaAberta());
    }

    @Test
    void abrir_quandoJaAberta_continuaAberta() {
        adaptador.abrir();
        assertTrue(persianaSolarius.estaAberta());
    }
}
