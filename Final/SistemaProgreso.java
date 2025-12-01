
package servicios;
import modelos.*;
import java.util.ArrayList;
import java.util.List;
public class SistemaProgreso {
    private int puntosAcumulados = 0;
    public int calcularPuntos(Logro logro) {
        return logro.getEstado() == EstadoLogro.CUMPLIDO ? 10 : 0;
    }
    public void agregarPuntos(int puntos) { puntosAcumulados += puntos; }
    public List<Insignia> verificarInsignias(Usuario usuario, List<Insignia> todasInsignias) {
        List<Insignia> nuevas = new ArrayList<>();
        for (Insignia i : todasInsignias) {
            if (!i.isDesbloqueada() && puntosAcumulados >= i.getRequisitoPuntos()) {
                i.desbloquear();
                usuario.agregarInsignia(i);
                nuevas.add(i);
            }
        }
        return nuevas;
    }
}
