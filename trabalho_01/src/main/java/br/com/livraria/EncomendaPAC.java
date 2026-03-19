package br.com.livraria;

public class EncomendaPAC implements TipoEntrega {
    private static final double PRECO_FAIXA_UM = 10.0;
    private static final double PRECO_FAIXA_DOIS = 15.0;
    private static final double FAIXA_PESO_UM_KG = 1.0;
    private static final double FAIXA_PESO_DOIS_KG = 2.0;

    @Override
    public double calcular(Pedido pedido) {
        double pesoTotal = pedido.getPesoTotal();

        if (pesoTotal <= FAIXA_PESO_UM_KG) {
            return PRECO_FAIXA_UM;
        }

        if (pesoTotal <= FAIXA_PESO_DOIS_KG) {
            return PRECO_FAIXA_DOIS;
        }

        throw new PesoPacInvalidoException("PAC não aceita pedidos acima de 2 kg");
    }
}
