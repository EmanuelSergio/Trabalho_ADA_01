package bolsa;

import java.util.ArrayList;
import java.util.List;

public class Bolsa {
    private List<Acao> acoes;

    public Bolsa() {
        this.acoes = new ArrayList<>();
    }

    public void adicionarAcao(Acao acao) {
        acoes.add(acao);
    }

    public Acao buscarAcao(String nome) {
        return acoes.stream()
                .filter(a -> a.nomeIgualA(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ação não encontrada: " + nome));
    }

    public void registrarOrdem(String nomeAcao, Ordem ordem) {
        buscarAcao(nomeAcao).registrarOrdem(ordem);
    }

    public void inscreverInvestidor(String nomeAcao, Investidor investidor) {
        buscarAcao(nomeAcao).registrarObserver(investidor);
    }

    public void programarOrdem(String nomeAcao, OrdemProgramada ordemProgramada) {
        buscarAcao(nomeAcao).registrarObserver(ordemProgramada);
    }

    public List<Acao> getAcoes() {
        return acoes;
    }
}
