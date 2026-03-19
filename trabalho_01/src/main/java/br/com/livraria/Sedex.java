package br.com.livraria;

public class Sedex implements TipoEntrega {
    private static final double PRECO_FAIXA_UM = 12.5;
    private static final double PRECO_FAIXA_DOIS = 20.0;
    private static final double PRECO_BASE_FAIXA_TRES = 46.5;
    private static final double PRECO_ADICIONAL_POR_100G = 1.5;
    private static final double FAIXA_PESO_UM_KG = 0.5;
    private static final double FAIXA_PESO_DOIS_KG = 1.0;

    @Override
    public double calcular(Pedido pedido) {
        double pesoTotal = pedido.getPesoTotal();

        if (pesoTotal <= FAIXA_PESO_UM_KG) {
            return PRECO_FAIXA_UM;
        }

        if (pesoTotal <= FAIXA_PESO_DOIS_KG) {
            return PRECO_FAIXA_DOIS;
        }

        int pesoTotalGramas = (int) Math.round(pesoTotal * 1000.0);
        int pesoAdicionalGramas = pesoTotalGramas - 1000;
        int blocosAdicionais = (pesoAdicionalGramas + 99) / 100;

        return PRECO_BASE_FAIXA_TRES + (blocosAdicionais * PRECO_ADICIONAL_POR_100G);
    }
}
