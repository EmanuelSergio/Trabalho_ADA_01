package adaptadores;

import br.furb.analise.algoritmos.ArCondicionadoVentoBaumn;
import interfaces.ArCondicionado;

public class ArCondicionadoVentoBaumnAdaptador implements ArCondicionado {

    private final ArCondicionadoVentoBaumn arCondicionadoVentoBaumn;

    public ArCondicionadoVentoBaumnAdaptador(ArCondicionadoVentoBaumn arCondicionadoVentoBaumn) {
        this.arCondicionadoVentoBaumn = arCondicionadoVentoBaumn;
    }

    @Override
    public void ligar() {
        arCondicionadoVentoBaumn.ligar();
    }

    @Override
    public void desligar() {
        arCondicionadoVentoBaumn.desligar();
    }

    @Override
    public void aumentarTemperatura() {
        arCondicionadoVentoBaumn.definirTemperatura(this.getTemperatura() + 1);
    }

    @Override
    public void diminuirTemperatura() {
        arCondicionadoVentoBaumn.definirTemperatura(this.getTemperatura() - 1);
    }

    @Override
    public void definirTemperatura(int temperatura) {
        arCondicionadoVentoBaumn.definirTemperatura(temperatura);
    }

    private int getTemperatura() {
        return this.arCondicionadoVentoBaumn.getTemperatura();
    }
}
