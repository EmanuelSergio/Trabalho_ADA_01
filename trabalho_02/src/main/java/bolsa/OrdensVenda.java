package bolsa;

import java.util.ArrayList;
import java.util.List;

public class OrdensVenda {
    private List<Ordem> ordens = new ArrayList<>();

    public void adicionar(Ordem ordem) {
        ordens.add(ordem);
    }

    public Ordem encontrarVendaCorrespondente(Ordem compra) {
        for (Ordem venda : new ArrayList<>(ordens)) {
            if (venda.getValor() == compra.getValor()) {
                return venda;
            }
        }
        return null;
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