import interfaces.ArCondicionado;
import interfaces.Lampada;
import interfaces.Persiana;

import java.util.ArrayList;
import java.util.List;

public class CasaInteligente {

    private final List<Lampada> lampadas;
    private final List<ArCondicionado> aresCondicionados;
    private final List<Persiana> persianas;

    public CasaInteligente(
            List<Lampada> lampadas,
            List<Persiana> persianas,
            List<ArCondicionado> ares
    ) {
        this.lampadas = lampadas;
        this.persianas = persianas;
        this.aresCondicionados = ares;
    }

    public void adicionarLampada(Lampada lampada) {
        lampadas.add(lampada);
    }

    public void adicionarPersiana(Persiana persiana) {
        persianas.add(persiana);
    }

    public void adicionarAr(ArCondicionado ar) {
        aresCondicionados.add(ar);
    }

    public void ativarModoSono() {
        this.aresCondicionados.forEach(ArCondicionado::desligar);
        this.lampadas.forEach(Lampada::desligar);
        this.persianas.forEach(Persiana::fechar);
    }

    public void ativarModoTrabalho() {
        this.lampadas.forEach(Lampada::ligar);
        this.aresCondicionados.forEach(arCondicionado -> {
            arCondicionado.ligar();
            arCondicionado.definirTemperatura(25);
        });
        this.persianas.forEach(Persiana::abrir);
    }


}
