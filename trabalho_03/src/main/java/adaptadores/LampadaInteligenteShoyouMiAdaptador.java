package adaptadores;

import br.furb.analise.algoritmos.LampadaShoyuMi;
import interfaces.Lampada;

public class LampadaInteligenteShoyouMiAdaptador implements Lampada {

    private final LampadaShoyuMi lampadaShoyuMi;

    public LampadaInteligenteShoyouMiAdaptador(LampadaShoyuMi lampadaShoyuMi) {
        this.lampadaShoyuMi = lampadaShoyuMi;
    }

    @Override
    public void ligar() {
        if(!this.lampadaShoyuMi.estaLigada()) {
            this.lampadaShoyuMi.ligar();
        }
    }

    @Override
    public void desligar() {
        if(this.lampadaShoyuMi.estaLigada()) {
            this.lampadaShoyuMi.desligar();
        }
    }
}
