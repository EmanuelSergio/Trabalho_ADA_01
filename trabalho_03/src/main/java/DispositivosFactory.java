import adaptadores.*;
import br.furb.analise.algoritmos.*;
import interfaces.ArCondicionado;
import interfaces.Lampada;
import interfaces.Persiana;

public class DispositivosFactory {

    public ArCondicionado criarAdaptadorArCondicionado(Object arCondicionado) {
        return switch (arCondicionado) {
            case null -> throw new IllegalArgumentException("Objeto de ar-condicionado não pode ser null");
            case ArCondicionadoVentoBaumn arCondicionadoVentoBaumn ->
                    new ArCondicionadoVentoBaumnAdaptador(arCondicionadoVentoBaumn);
            case ArCondicionadoGellaKaza arCondicionadoGellaKaza ->
                    new ArCondicionadoGellaKazaAdaptador(arCondicionadoGellaKaza);
            default -> throw new IllegalArgumentException(
                    "Tipo de ar-condicionado não suportado: " + arCondicionado.getClass().getName()
            );
        };
    }

    public Persiana criarAdaptadorPersiana(Object persiana) {
        return switch(persiana) {
            case null -> throw new IllegalArgumentException("Objeto de persiana não pode ser null");
            case PersianaSolarius persianaSolarius ->
                new PersianaSolariusAdaptador(persianaSolarius);
            case PersianaNatLight persianaNatLight ->
                new PersianaNatLightAdaptador(persianaNatLight);
            default -> throw new IllegalArgumentException(
                    "Tipo de persiana não suportado: " + persiana.getClass().getName()
            );
        };
    }

    public Lampada criarAdaptadorLampada(Object lampada) {
        return switch(lampada) {
            case null -> throw new IllegalArgumentException("Objeto de lâmpada não pode ser null");
            case LampadaPhellipes lampadaPhellipes ->
                new LampadaInteligentePhellipesAdaptador(lampadaPhellipes);
            case LampadaShoyuMi lampadaShoyuMi ->
                new LampadaInteligenteShoyouMiAdaptador(lampadaShoyuMi);
            default -> throw new IllegalArgumentException(
                    "Tipo de lâmpada não suportado: " + lampada.getClass().getName()
            );
        };
    }

}
