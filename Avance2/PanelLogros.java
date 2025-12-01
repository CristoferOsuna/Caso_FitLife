
package ui;
import modelos.Logro;
import java.util.List;
public class PanelLogros {
    private List<Logro> listaLogros;
    public PanelLogros(List<Logro> listaLogros) { this.listaLogros = listaLogros; }
    public void visualizarPanel() {
        System.out.println("===== Panel de Logros =====");
        for (Logro l : listaLogros) System.out.println("• " + l);
    }
}
