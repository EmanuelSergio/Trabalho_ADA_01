package br.com.livraria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoFreteTest {

    @Test
    void deveCalcularFretePacAteUmKg() {
        Pedido pedido = novoPedido(TipoEntregaEnum.PAC);
        pedido.incluirItem(new Produto("Livro A", 50.0, 1.0), 1);

        assertEquals(10.0, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveCalcularFretePacDeUmADoisKg() {
        Pedido pedido = novoPedido(TipoEntregaEnum.PAC);
        pedido.incluirItem(new Produto("Livro A", 50.0, 1.5), 1);

        assertEquals(15.0, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveLancarExcecaoQuandoPacAcimaDeDoisKg() {
        Pedido pedido = novoPedido(TipoEntregaEnum.PAC);
        pedido.incluirItem(new Produto("Livro A", 50.0, 2.1), 1);

        assertThrows(PesoPacInvalidoException.class, pedido::getPrecoTotal);
    }

    @Test
    void deveCalcularFreteSedexAteQuinhentosGramas() {
        Pedido pedido = novoPedido(TipoEntregaEnum.SEDEX);
        pedido.incluirItem(new Produto("Livro A", 50.0, 0.5), 1);

        assertEquals(12.5, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveCalcularFreteSedexDeQuinhentosAteMilGramas() {
        Pedido pedido = novoPedido(TipoEntregaEnum.SEDEX);
        pedido.incluirItem(new Produto("Livro A", 50.0, 0.9), 1);

        assertEquals(20.0, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveCalcularFreteSedexAcimaDeUmKg() {
        Pedido pedido = novoPedido(TipoEntregaEnum.SEDEX);
        pedido.incluirItem(new Produto("Livro A", 50.0, 1.3), 1);

        assertEquals(51.0, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveCalcularFreteRetiradaLocalSemCusto() {
        Pedido pedido = novoPedido(TipoEntregaEnum.RETIRADA_LOCAL);
        pedido.incluirItem(new Produto("Livro A", 100.0, 3.0), 1);

        assertEquals(0.0, pedido.getPrecoTotal() - pedido.getPrecoProdutos(), 0.0001);
    }

    @Test
    void deveCalcularPrecoTotalComProdutosEFrete() {
        Pedido pedido = novoPedido(TipoEntregaEnum.SEDEX);
        pedido.incluirItem(new Produto("Livro A", 30.0, 0.4), 2);
        pedido.incluirItem(new Produto("Livro B", 20.0, 0.2), 1);

        assertEquals(100.0, pedido.getPrecoTotal(), 0.0001);
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeDoItemForInvalida() {
        Pedido pedido = novoPedido(TipoEntregaEnum.SEDEX);

        assertThrows(QuantidadeInvalidaException.class,
                () -> pedido.incluirItem(new Produto("Livro A", 30.0, 0.4), 0));
    }

    @Test
    void deveLancarExcecaoQuandoTipoEntregaForNuloNaFactory() {
        assertThrows(TipoEntregaInvalidaException.class,
                () -> TipoEntregaFactory.criarTipoEntrega(null));
    }

    private Pedido novoPedido(TipoEntregaEnum tipoEntregaEnum) {
        FreteService freteService = new FreteService();
        return new Pedido(freteService, tipoEntregaEnum);
    }
}
