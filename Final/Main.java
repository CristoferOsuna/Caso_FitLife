
import modelos.*;
import servicios.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Usuario u = new Usuario(1, "Cristofer", "cristo@gmail.com");
        SistemaProgreso sp = new SistemaProgreso();
        Logro l1 = new Logro(1, "Primer Logro", "Completar algo");
        Logro l2 = new Logro(2, "Segundo Logro", "Hacer otra cosa");
        u.registrarLogro(l1);
        u.registrarLogro(l2);
        u.marcarLogroCumplido(l1);
        sp.agregarPuntos(sp.calcularPuntos(l1));
        List<Insignia> todas = Arrays.asList(
            new Insignia(1, "Bronce", "Primeros pasos", 10),
            new Insignia(2, "Plata", "Buen avance", 30)
        );
        List<Insignia> nuevas = sp.verificarInsignias(u, todas);
        System.out.println("Insignias obtenidas: " + nuevas.size());
    }
}
