
package modelos;
public class Insignia {
    private int idInsignia;
    private String nombre;
    private String descripcion;
    private int requisitoPuntos;
    private boolean desbloqueada;
    public Insignia(int id, String nombre, String descripcion, int requisitoPuntos) {
        this.idInsignia = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitoPuntos = requisitoPuntos;
        this.desbloqueada = false;
    }
    public void desbloquear() { this.desbloqueada = true; }
    public boolean isDesbloqueada() { return desbloqueada; }
    public int getRequisitoPuntos() { return requisitoPuntos; }
}
