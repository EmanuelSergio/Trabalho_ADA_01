package adaptadores;

import br.furb.analise.algoritmos.PersianaNatLight;
import interfaces.Persiana;

public class PersianaNatLightAdaptador implements Persiana {

    private final PersianaNatLight persianaNatLight;

    public PersianaNatLightAdaptador(PersianaNatLight persianaNatLight) {
        this.persianaNatLight = persianaNatLight;
    }

    @Override
    public void abrir() {
        try {
            if (!persianaNatLight.estaPalhetaAberta()) {
                persianaNatLight.abrirPalheta();
            }

            if (!persianaNatLight.estaPalhetaErguida()) {
                persianaNatLight.subirPalheta();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao abrir persiana", e);
        }
    }

    @Override
    public void fechar() {
        try {
            if (persianaNatLight.estaPalhetaErguida()) {
                persianaNatLight.descerPalheta();
            }

            if (persianaNatLight.estaPalhetaAberta()) {
                persianaNatLight.fecharPalheta();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao fechar persiana", e);
        }
    }
}
