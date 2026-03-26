package adaptadores;

import br.furb.analise.algoritmos.PersianaSolarius;
import interfaces.Persiana;

public class PersianaSolariusAdaptador implements Persiana {

    private final PersianaSolarius persianaSolarius;

    public PersianaSolariusAdaptador(PersianaSolarius persianaSolarius) {
        this.persianaSolarius = persianaSolarius;
    }

    @Override
    public void abrir() {
        if(!persianaSolarius.estaAberta()) {
            persianaSolarius.subirPersiana();
        }
    }

    @Override
    public void fechar() {
        if(persianaSolarius.estaAberta()) {
            persianaSolarius.descerPersiana();
        }
    }
}
