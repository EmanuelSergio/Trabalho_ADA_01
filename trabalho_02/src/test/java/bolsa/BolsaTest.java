package bolsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList; 
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BolsaTest {

    private Bolsa bolsa;
    private Investidor mariana;
    private Investidor joaquim;
    private Investidor carlos;

    @BeforeEach
    void setUp() {
        bolsa = new Bolsa();
        mariana = new Investidor("Mariana");
        joaquim = new Investidor("Joaquim");
        carlos = new Investidor("Carlos");

        Acao bb = new Acao("BBAS3", 23.50);
        bolsa.adicionarAcao(bb);
    }

    @Test
    void deveRealizarMatchEntreOrdemDeCompraEVenda() {
        Ordem venda = new Ordem(mariana, TipoOrdem.VENDA, 24.00);
        Ordem compra = new Ordem(joaquim, TipoOrdem.COMPRA, 24.00);

        bolsa.registrarOrdem("BBAS3", venda);
        bolsa.registrarOrdem("BBAS3", compra);

        Acao acao = bolsa.buscarAcao("BBAS3");
        assertEquals(24.00, acao.obterValorAtual(), 0.001);
        assertTrue(acao.ordensCompraVazia());
        assertTrue(acao.ordensVendaVazia());
    }

    @Test
    void naoDeveRealizarMatchComValoresDiferentes() {
        Ordem venda = new Ordem(mariana, TipoOrdem.VENDA, 25.00);
        Ordem compra = new Ordem(joaquim, TipoOrdem.COMPRA, 24.00);

        bolsa.registrarOrdem("BBAS3", venda);
        bolsa.registrarOrdem("BBAS3", compra);

        Acao acao = bolsa.buscarAcao("BBAS3");
        assertEquals(23.50, acao.obterValorAtual(), 0.001);
        assertFalse(acao.ordensCompraVazia());
        assertFalse(acao.ordensVendaVazia());
    }

    @Test
    void deveNotificarInvestidorAposNegociacao() {
        List<String> notificacoes = new ArrayList<>();

        Investidor observador = new Investidor("Observador") {
            @Override
            public void atualizar(Acao acao) {
                notificacoes.add("atualizado para " + acao.obterValorAtual());
            }
        };

        bolsa.adicionarAcao(new Acao("PETR4", 30.00));
        bolsa.inscreverInvestidor("PETR4", observador);

        Ordem venda = new Ordem(mariana, TipoOrdem.VENDA, 32.00);
        Ordem compra = new Ordem(joaquim, TipoOrdem.COMPRA, 32.00);

        bolsa.registrarOrdem("PETR4", venda);
        bolsa.registrarOrdem("PETR4", compra);

        assertEquals(1, notificacoes.size());
        assertEquals("atualizado para 32.0", notificacoes.get(0));
    }

    @Test
    void deveExecutarOrdemProgramadaQuandoPrecoAtingirAlvo() {
        bolsa.adicionarAcao(new Acao("VALE3", 50.00));

        Ordem ordemProgramada = new Ordem(carlos, TipoOrdem.COMPRA, 45.00);
        carlos.programarOrdem(bolsa, "VALE3", ordemProgramada, 45.00);

        Ordem vendaMariana = new Ordem(mariana, TipoOrdem.VENDA, 45.00);
        Ordem compraJoaquim = new Ordem(joaquim, TipoOrdem.COMPRA, 45.00);

        bolsa.registrarOrdem("VALE3", vendaMariana);
        bolsa.registrarOrdem("VALE3", compraJoaquim);

        Ordem vendaExtra = new Ordem(mariana, TipoOrdem.VENDA, 45.00);
        bolsa.registrarOrdem("VALE3", vendaExtra);

        Acao acao = bolsa.buscarAcao("VALE3");
        assertEquals(45.00, acao.obterValorAtual(), 0.001);
    }

    @Test
    void deveBuscarAcaoPorNome() {
        Acao acao = bolsa.buscarAcao("BBAS3");
        assertNotNull(acao);
        assertEquals("BBAS3", acao.obterNome());
    }

    @Test
    void deveLancarExcecaoQuandoAcaoNaoEncontrada() {
        assertThrows(IllegalArgumentException.class, () -> bolsa.buscarAcao("XXXX9"));
    }

    @Test
    void deveAdicionarOrdemNaListaCorreta() {
        Ordem compra = new Ordem(joaquim, TipoOrdem.COMPRA, 20.00);
        Ordem venda = new Ordem(mariana, TipoOrdem.VENDA, 22.00);

        bolsa.registrarOrdem("BBAS3", compra);
        bolsa.registrarOrdem("BBAS3", venda);

        Acao acao = bolsa.buscarAcao("BBAS3");
        assertEquals(1, acao.tamanhoOrdensCompra());
        assertEquals(1, acao.tamanhoOrdensVenda());
    }

    @Test
    void deveRemoverObserverCorretamente() {
        bolsa.adicionarAcao(new Acao("ITUB4", 20.00));

        List<String> notificacoes = new ArrayList<>();
        Investidor obs = new Investidor("Obs") {
            @Override
            public void atualizar(Acao acao) {
                notificacoes.add("notificado");
            }
        };

        bolsa.inscreverInvestidor("ITUB4", obs);
        bolsa.buscarAcao("ITUB4").removerObserver(obs);

        Ordem v = new Ordem(mariana, TipoOrdem.VENDA, 21.00);
        Ordem c = new Ordem(joaquim, TipoOrdem.COMPRA, 21.00);
        bolsa.registrarOrdem("ITUB4", v);
        bolsa.registrarOrdem("ITUB4", c);

        assertTrue(notificacoes.isEmpty());
    }

    @Test
    void deveAtualizarValorDaAcaoAposMatch() {
        Acao acao = bolsa.buscarAcao("BBAS3");
        double valorAnterior = acao.obterValorAtual();

        Ordem v = new Ordem(mariana, TipoOrdem.VENDA, 26.00);
        Ordem c = new Ordem(joaquim, TipoOrdem.COMPRA, 26.00);
        bolsa.registrarOrdem("BBAS3", v);
        bolsa.registrarOrdem("BBAS3", c);

        assertNotEquals(valorAnterior, acao.obterValorAtual());
        assertEquals(26.00, acao.obterValorAtual(), 0.001);
    }
}
