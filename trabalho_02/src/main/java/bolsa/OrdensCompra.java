package bolsa;

import java.util.ArrayList;
import java.util.List;

public class OrdensCompra {
    private List<Ordem> ordens = new ArrayList<>();

    public void adicionar(Ordem ordem) {
        ordens.add(ordem);
    }

    public void encontrarCorrespondencia(OrdensVenda ordensVenda, Acao acao) {
        for (Ordem compra : new ArrayList<>(ordens)) {
            Ordem venda = ordensVenda.encontrarVendaCorrespondente(compra);
            if (venda != null) {
                acao.executarNegociacao(compra, venda);
            }
        }
    }

    public void remover(Ordem ordem) {
        ordens.remove(ordem);
    }

    public boolean estaVazia() {
        return ordens.isEmpty();
    }

    public int tamanho() {
        return ordens.size();
    }
}