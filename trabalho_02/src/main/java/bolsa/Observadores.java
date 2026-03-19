package bolsa;

import java.util.ArrayList;
import java.util.List;

public class Observadores {
    private List<Observer> observadores = new ArrayList<>();

    public void adicionar(Observer observer) {
        if (!observadores.contains(observer)) {
            observadores.add(observer);
        }
    }

    public void remover(Observer observer) {
        observadores.remove(observer);
    }

    public void notificar(Acao acao) {
        for (Observer observer : new ArrayList<>(observadores)) {
            observer.atualizar(acao);
        }
    }
}