
package modelos;
import java.util.ArrayList;
import java.util.List;
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private List<Logro> logros = new ArrayList<>();
    private List<Insignia> insignias = new ArrayList<>();
    public Usuario(int idUsuario, String nombre, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
    }
    public void registrarLogro(Logro logro) { logros.add(logro); }
    public void marcarLogroCumplido(Logro logro) { logro.marcarCumplido(); }
    public void marcarLogroIncompleto(Logro logro) { logro.marcarIncompleto(); }
    public List<Logro> obtenerLogros() { return logros; }
    public List<Insignia> obtenerInsignias() { return insignias; }
    public void agregarInsignia(Insignia i) { insignias.add(i); }
}
