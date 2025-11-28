import javax.swing.*;
import java.util.ArrayList;

class Usuario {
    private String nombre;
    private int puntos;

    public Usuario() {
        nombre = "";
        puntos = 0;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPuntos(int p) {
        puntos = p;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int cant) {
        puntos = puntos + cant;
    }
}

class Recordatorio {
    private String mensaje;
    private String hora;

    public Recordatorio() {
        mensaje = "";
        hora = "";
    }

    public void setMensaje(String m) {
        mensaje = m;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setHora(String h) {
        hora = h;
    }

    public String getHora() {
        return hora;
    }
}

public class FitLife {

    public static void main(String[] args) {

        Usuario usuario = new Usuario();
        usuario.setNombre(JOptionPane.showInputDialog("Ingresa tu nombre:"));

        ArrayList<Recordatorio> recordatorios = new ArrayList<>();

        int opcion = 0;

        while (opcion != 5) {

            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Hola " + usuario.getNombre() + "\n\n"
                    + "1. Agregar puntos\n"
                    + "2. Ver puntos\n"
                    + "3. Crear recordatorio\n"
                    + "4. Ver recordatorios\n"
                    + "5. Salir\n\n"
                    + "Selecciona una opción:"
            ));

            if (opcion == 1) {
                int pts = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos puntos deseas agregar?"));
                usuario.sumarPuntos(pts);
                JOptionPane.showMessageDialog(null, "Puntos agregados correctamente.");
            }

            else if (opcion == 2) {
                JOptionPane.showMessageDialog(null,
                        "Tienes: " + usuario.getPuntos() + " puntos.");
            }

            else if (opcion == 3) {
                Recordatorio rec = new Recordatorio();
                rec.setMensaje(JOptionPane.showInputDialog("Escribe el mensaje del recordatorio:"));
                rec.setHora(JOptionPane.showInputDialog("¿A qué hora lo deseas recibir?"));

                recordatorios.add(rec);
                JOptionPane.showMessageDialog(null, "Recordatorio creado correctamente.");
            }

            else if (opcion == 4) {
                if (recordatorios.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No tienes recordatorios registrados.");
                } else {
                    String lista = "Tus recordatorios:\n\n";
                    for (Recordatorio r : recordatorios) {
                        lista += "- " + r.getMensaje() + " a las " + r.getHora() + "\n";
                    }
                    JOptionPane.showMessageDialog(null, lista);
                }
            }

            else if (opcion == 5) {
                JOptionPane.showMessageDialog(null, "Saliendo de la aplicación...");
            }

            else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}