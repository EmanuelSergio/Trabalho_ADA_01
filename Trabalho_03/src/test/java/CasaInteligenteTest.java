import interfaces.ArCondicionado;
import interfaces.Lampada;
import interfaces.Persiana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CasaInteligenteTest {

    static class LampadaFake implements Lampada {
        int ligarChamados = 0;
        int desligarChamados = 0;

        @Override public void ligar()    { ligarChamados++; }
        @Override public void desligar() { desligarChamados++; }
    }

    static class PersianaFake implements Persiana {
        int abrirChamados = 0;
        int fecharChamados = 0;

        @Override public void abrir()  { abrirChamados++; }
        @Override public void fechar() { fecharChamados++; }
    }

    static class ArCondicionadoFake implements ArCondicionado {
        int ligarChamados = 0;
        int desligarChamados = 0;
        int temperaturaDefinida = -1;

        @Override public void ligar()    { ligarChamados++; }
        @Override public void desligar() { desligarChamados++; }
        @Override public void aumentarTemperatura() {}
        @Override public void diminuirTemperatura() {}
        @Override public void definirTemperatura(int t) { temperaturaDefinida = t; }
    }

    private LampadaFake lampada;
    private PersianaFake persiana;
    private ArCondicionadoFake ar;
    private CasaInteligente casa;

    @BeforeEach
    void setUp() {
        lampada  = new LampadaFake();
        persiana = new PersianaFake();
        ar       = new ArCondicionadoFake();
        casa = new CasaInteligente(
                new ArrayList<>(List.of(lampada)),
                new ArrayList<>(List.of(persiana)),
                new ArrayList<>(List.of(ar))
        );
    }

    @Test
    void ativarModoSono_desligaLampada() {
        casa.ativarModoSono();
        assertEquals(1, lampada.desligarChamados);
    }

    @Test
    void ativarModoSono_fechaPersiana() {
        casa.ativarModoSono();
        assertEquals(1, persiana.fecharChamados);
    }

    @Test
    void ativarModoSono_desligaAr() {
        casa.ativarModoSono();
        assertEquals(1, ar.desligarChamados);
    }

    @Test
    void ativarModoSono_naoLigaNemAbreNenhumDispositivo() {
        casa.ativarModoSono();
        assertEquals(0, lampada.ligarChamados);
        assertEquals(0, persiana.abrirChamados);
        assertEquals(0, ar.ligarChamados);
    }

    @Test
    void ativarModoSono_comMultiplosDispositivos_desligaTodos() {
        LampadaFake lampada2      = new LampadaFake();
        PersianaFake persiana2    = new PersianaFake();
        ArCondicionadoFake ar2    = new ArCondicionadoFake();
        casa.adicionarLampada(lampada2);
        casa.adicionarPersiana(persiana2);
        casa.adicionarAr(ar2);

        casa.ativarModoSono();

        assertEquals(1, lampada.desligarChamados);
        assertEquals(1, lampada2.desligarChamados);
        assertEquals(1, persiana.fecharChamados);
        assertEquals(1, persiana2.fecharChamados);
        assertEquals(1, ar.desligarChamados);
        assertEquals(1, ar2.desligarChamados);
    }

    @Test
    void ativarModoTrabalho_ligaLampada() {
        casa.ativarModoTrabalho();
        assertEquals(1, lampada.ligarChamados);
    }

    @Test
    void ativarModoTrabalho_abrePersiana() {
        casa.ativarModoTrabalho();
        assertEquals(1, persiana.abrirChamados);
    }

    @Test
    void ativarModoTrabalho_ligaArEDefineTemperatura25() {
        casa.ativarModoTrabalho();
        assertEquals(1, ar.ligarChamados);
        assertEquals(25, ar.temperaturaDefinida);
    }

    @Test
    void ativarModoTrabalho_naoDesligaNemFechaNenhumDispositivo() {
        casa.ativarModoTrabalho();
        assertEquals(0, lampada.desligarChamados);
        assertEquals(0, persiana.fecharChamados);
        assertEquals(0, ar.desligarChamados);
    }

    @Test
    void ativarModoTrabalho_comMultiplosDispositivos_ligaTodos() {
        LampadaFake lampada2      = new LampadaFake();
        PersianaFake persiana2    = new PersianaFake();
        ArCondicionadoFake ar2    = new ArCondicionadoFake();
        casa.adicionarLampada(lampada2);
        casa.adicionarPersiana(persiana2);
        casa.adicionarAr(ar2);

        casa.ativarModoTrabalho();

        assertEquals(1, lampada.ligarChamados);
        assertEquals(1, lampada2.ligarChamados);
        assertEquals(1, persiana.abrirChamados);
        assertEquals(1, persiana2.abrirChamados);
        assertEquals(1, ar.ligarChamados);
        assertEquals(25, ar.temperaturaDefinida);
        assertEquals(1, ar2.ligarChamados);
        assertEquals(25, ar2.temperaturaDefinida);
    }

    @Test
    void adicionarLampada_lampadaPassaAReceberEventos() {
        LampadaFake lampada2 = new LampadaFake();
        casa.adicionarLampada(lampada2);
        casa.ativarModoTrabalho();
        assertEquals(1, lampada2.ligarChamados);
    }

    @Test
    void adicionarPersiana_persianaPassaAReceberEventos() {
        PersianaFake persiana2 = new PersianaFake();
        casa.adicionarPersiana(persiana2);
        casa.ativarModoTrabalho();
        assertEquals(1, persiana2.abrirChamados);
    }

    @Test
    void adicionarAr_arPassaAReceberEventos() {
        ArCondicionadoFake ar2 = new ArCondicionadoFake();
        casa.adicionarAr(ar2);
        casa.ativarModoTrabalho();
        assertEquals(1, ar2.ligarChamados);
    }
}
