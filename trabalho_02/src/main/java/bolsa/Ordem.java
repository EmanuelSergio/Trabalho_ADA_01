package bolsa;

public class Ordem {
    private Investidor investidor;
    private TipoOrdem tipo;
    private double valor;

    public Ordem(Investidor investidor, TipoOrdem tipo, double valor) {
        this.investidor = investidor;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public TipoOrdem getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
    
    @Override
    public String toString() {
        return String.format("Ordem[%s, %s, R$%.2f]", investidor.getNome(), tipo, valor);
    }
}
