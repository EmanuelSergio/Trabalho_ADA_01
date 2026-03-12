package br.com.livraria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class Pedido {
    private static final String MENSAGEM_QUANTIDADE_INVALIDA = "Quantidade deve ser maior que zero";

    private final List<ItemPedido> itens = new ArrayList<>();
    private final FreteService freteService;
    private TipoEntrega tipoEntrega;

    public Pedido(FreteService freteService, TipoEntregaEnum tipoEntregaEnum) {
        this.freteService = freteService;
        this.tipoEntrega = criarTipoEntrega(tipoEntregaEnum);
    }

    public void incluirItem(Produto produto, int quantidade) {
        validarQuantidade(quantidade);
        adicionarItem(produto, quantidade);
    }

    public void setTipoEntrega(TipoEntregaEnum tipo) {
        this.tipoEntrega = criarTipoEntrega(tipo);
    }

    public double calcularFrete() {
        return tipoEntrega.calcular(this);
    }

    public double getPesoTotal() {
        return somarItens(ItemPedido::getPesoTotal);
    }

    public double getPrecoProdutos() {
        return somarItens(ItemPedido::getPrecoTotal);
    }

    public double getPrecoTotal() {
        double precoProdutos = getPrecoProdutos();
        double frete = freteService.calcularFrete(this);

        return precoProdutos + frete;
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException(MENSAGEM_QUANTIDADE_INVALIDA);
        }
    }

    private void adicionarItem(Produto produto, int quantidade) {
        itens.add(new ItemPedido(produto, quantidade));
    }

    private TipoEntrega criarTipoEntrega(TipoEntregaEnum tipoEntregaEnum) {
        return TipoEntregaFactory.criarTipoEntrega(tipoEntregaEnum);
    }

    private double somarItens(ToDoubleFunction<ItemPedido> extrator) {
        return itens.stream()
                .mapToDouble(extrator)
                .sum();
    }
}
