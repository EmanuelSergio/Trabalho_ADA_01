package bolsa;

public class ValorAtual {
    private double valor;

    public ValorAtual(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
        return String.format("R$%.2f", valor);
    }
}