package br.com.livraria;

public final class TipoEntregaFactory {
    private TipoEntregaFactory() {
    }

    public static TipoEntrega criarTipoEntrega(TipoEntregaEnum tipo) {
        if (tipo == null) {
            throw new TipoEntregaInvalidaException("Tipo de entrega não pode ser nulo");
        }

        return switch (tipo) {
            case PAC -> new EncomendaPAC();
            case SEDEX -> new Sedex();
            case RETIRADA_LOCAL -> new RetiradaLocal();
            default -> throw new TipoEntregaInvalidaException("Tipo de entrega desconhecido: " + tipo);
        };
    }
}
