
package modelos;
import java.util.Date;
// idLogro sirve para identificar cada logro
public class Logro {
    private int idLogro;
    private String nombre;
    private String descripcion;
    private Date fechaRegistro;
    private EstadoLogro estado;
    public Logro(int id, String nombre, String descripcion) {
        this.idLogro = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaRegistro = new Date();
        this.estado = EstadoLogro.INCOMPLETO;
    }
    // Devuelvo el estado actual del logro para que otras clases puedan revisarlo.
    public void marcarCumplido() { this.estado = EstadoLogro.CUMPLIDO; }
    public void marcarIncompleto() { this.estado = EstadoLogro.INCOMPLETO; }
    public EstadoLogro getEstado() { return estado; }
}
