package adaptadores;

import br.furb.analise.algoritmos.LampadaPhellipes;
import interfaces.Lampada;

public class LampadaInteligentePhellipesAdaptador implements Lampada {

    private final LampadaPhellipes lampadaPhellipes;

    public LampadaInteligentePhellipesAdaptador(LampadaPhellipes lampadaPhellipes) {
        this.lampadaPhellipes = lampadaPhellipes;
    }

    @Override
    public void ligar() {
        if(this.lampadaPhellipes.getIntensidade() != 100) {
            this.lampadaPhellipes.setIntensidade(100);
        }
    }

    @Override
    public void desligar() {
        if(this.lampadaPhellipes.getIntensidade() != 0) {
            this.lampadaPhellipes.setIntensidade(0);
        }
    }

}
