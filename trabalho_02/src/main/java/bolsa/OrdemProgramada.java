package bolsa;

public class OrdemProgramada implements Observer {
    private Ordem ordem;
    private Acao acao;
    private double precoAlvo;

    public OrdemProgramada(Ordem ordem, Acao acao, double precoAlvo) {
        this.ordem = ordem;
        this.acao = acao;
        this.precoAlvo = precoAlvo;
    }

    @Override
    public void atualizar(Acao acaoAtualizada) {
        if (acaoAtualizada.obterValorAtual() <= precoAlvo && ordem.getTipo() == TipoOrdem.COMPRA) {
            System.out.printf("[ORDEM PROGRAMADA] Preço atingiu R$%.2f. Registrando ordem de COMPRA de %s em '%s' por R$%.2f%n",
                    acaoAtualizada.obterValorAtual(), ordem.getInvestidor().getNome(), acaoAtualizada.obterNome(), ordem.getValor());
            acao.registrarOrdem(ordem);
            acao.removerObserver(this);
        }
        if (acaoAtualizada.obterValorAtual() >= precoAlvo && ordem.getTipo() == TipoOrdem.VENDA) {
            System.out.printf("[ORDEM PROGRAMADA] Preço atingiu R$%.2f. Registrando ordem de VENDA de %s em '%s' por R$%.2f%n",
                    acaoAtualizada.obterValorAtual(), ordem.getInvestidor().getNome(), acaoAtualizada.obterNome(), ordem.getValor());
            acao.registrarOrdem(ordem);
            acao.removerObserver(this);
        }
    }
}
