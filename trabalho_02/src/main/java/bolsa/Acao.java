package bolsa;

public class Acao implements Subject {
    private Nome nome;
    private ValorAtual valorAtual;
    private OrdensCompra ordensCompra;
    private OrdensVenda ordensVenda;
    private Observadores observadores;

    public Acao(String nome, double valorInicial) {
        this.nome = new Nome(nome);
        this.valorAtual = new ValorAtual(valorInicial);
        this.ordensCompra = new OrdensCompra();
        this.ordensVenda = new OrdensVenda();
        this.observadores = new Observadores();
    }

    public void registrarOrdem(Ordem ordem) {
        if (ordem.getTipo() == TipoOrdem.COMPRA) {
            ordensCompra.adicionar(ordem);
        }
        if (ordem.getTipo() == TipoOrdem.VENDA) {
            ordensVenda.adicionar(ordem);
        }
        verificarMatch();
    }

    private void verificarMatch() {
        ordensCompra.encontrarCorrespondencia(ordensVenda, this);
    }

    public boolean nomeIgualA(String nome) {
        return this.nome.toString().equalsIgnoreCase(nome);
    }

    public String obterNome() {
        return nome.toString();
    }

    public double obterValorAtual() {
        return valorAtual.getValor();
    }

    public void executarNegociacao(Ordem compra, Ordem venda) {
        double valorNegociado = venda.getValor();
        Investidor investidorCompra = compra.getInvestidor();
        String nomeCompra = investidorCompra.getNome();
        Investidor investidorVenda = venda.getInvestidor();
        String nomeVenda = investidorVenda.getNome();
        String nomeAcao = nome.toString();
        System.out.printf("[NEGOCIAÇÃO] %s comprou ação '%s' de %s por R$%.2f%n",
                nomeCompra, nomeAcao, nomeVenda, valorNegociado);

        ordensCompra.remover(compra);
        ordensVenda.remover(venda);

        valorAtual.setValor(valorNegociado);
        observadores.notificar(this);
    }

    public boolean ordensCompraVazia() {
        return ordensCompra.estaVazia();
    }

    public boolean ordensVendaVazia() {
        return ordensVenda.estaVazia();
    }

    public int tamanhoOrdensCompra() {
        return ordensCompra.tamanho();
    }

    public int tamanhoOrdensVenda() {
        return ordensVenda.tamanho();
    }

    @Override
    public void registrarObserver(Observer observer) {
        observadores.adicionar(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observadores.remover(observer);
    }

    @Override
    public void notificarObservers() {
        observadores.notificar(this);
    }

    @Override
    public String toString() {
        return String.format("Acao[nome=%s, valorAtual=%s]", nome, valorAtual);
    }
}
