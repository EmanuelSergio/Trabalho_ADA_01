package bolsa;

public class Investidor implements Observer {
    private String nome;

    public Investidor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void registrarOrdem(Bolsa bolsa, String nomeAcao, Ordem ordem) {
        bolsa.registrarOrdem(nomeAcao, ordem);
    }

    public void inscreverEmAcao(Bolsa bolsa, String nomeAcao) {
        bolsa.inscreverInvestidor(nomeAcao, this);
    }

    public void programarOrdem(Bolsa bolsa, String nomeAcao, Ordem ordem, double precoDisparo) {
        bolsa.programarOrdem(nomeAcao, new OrdemProgramada(ordem, bolsa.buscarAcao(nomeAcao), precoDisparo));
    }

    @Override
    public void atualizar(Acao acao) {
        System.out.printf("[NOTIFICAÇÃO] %s: ação '%s' atualizada para R$%.2f%n",
                nome, acao.obterNome(), acao.obterValorAtual());
    }

    @Override
    public String toString() {
        return "Investidor[" + nome + "]";
    }
}
