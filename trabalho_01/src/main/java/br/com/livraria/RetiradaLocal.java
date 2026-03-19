package br.com.livraria;

public class RetiradaLocal implements TipoEntrega {
    private static final double PRECO_RETIRADA_LOCAL = 0.0;

    @Override
    public double calcular(Pedido pedido) {
        return PRECO_RETIRADA_LOCAL;
    }
}
