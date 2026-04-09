package adaptadores;

import br.furb.analise.algoritmos.ArCondicionadoGellaKaza;
import interfaces.ArCondicionado;

public class ArCondicionadoGellaKazaAdaptador implements ArCondicionado {

    private final ArCondicionadoGellaKaza arCondicionadoGellaKaza;

    public ArCondicionadoGellaKazaAdaptador(ArCondicionadoGellaKaza arCondicionadoGellaKaza) {
        this.arCondicionadoGellaKaza = arCondicionadoGellaKaza;
    }

    @Override
    public void ligar() {
        if(!arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.ativar();
        }
    }

    @Override
    public void desligar() {
        if(arCondicionadoGellaKaza.estaLigado()) {
            arCondicionadoGellaKaza.desativar();
        }
    }

    @Override
    public void aumentarTemperatura() {
        arCondicionadoGellaKaza.aumentarTemperatura();
    }

    @Override
    public void diminuirTemperatura() {
        arCondicionadoGellaKaza.diminuirTemperatura();
    }

    @Override
    public void definirTemperatura(int temperatura) {
         while(arCondicionadoGellaKaza.getTemperatura() != temperatura ) {
             int temperaturaAtual = arCondicionadoGellaKaza.getTemperatura();

             if(temperaturaAtual  > temperatura) {
                 this.diminuirTemperatura();
             }
             if(temperaturaAtual  < temperatura) {
                 this.aumentarTemperatura();
             }
         }
    }

}
