import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Administra el funcionamiento general de la mision: la lista polimorfica de modulos
// y los recursos compartidos. Es la clase principal del modelo dentro del patron MVC.
public class Mision {

    private ArrayList<Modulo> modulos;
    private RecursosMision recursos;

    // Constructor que recibe los recursos compartidos e inicializa la lista de modulos vacia.
    public Mision(RecursosMision recursos) {
        if (recursos == null) {
            throw new IllegalArgumentException("Los recursos de la mision no pueden ser nulos");
        }
        this.recursos = recursos;
        this.modulos = new ArrayList<Modulo>();
    }

    // Agrega un nuevo modulo a la mision, validando que no exista otro con el mismo id.
    public void agregarModulo(Modulo modulo) {
        if (modulo == null) {
            throw new IllegalArgumentException("El modulo no puede ser nulo");
        }
        if (buscarPorId(modulo.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un modulo registrado con ese id");
        }
        modulos.add(modulo);
    }

    // Devuelve una copia de la lista de modulos para no exponer la lista interna.
    public ArrayList<Modulo> getModulos() {
        return new ArrayList<Modulo>(modulos);
    }

    // Devuelve los recursos compartidos de la mision.
    public RecursosMision getRecursos() {
        return recursos;
    }

    // Busca un modulo por su id y devuelve null si no se encuentra.
    public Modulo buscarPorId(String id) {
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i).getId().equals(id)) {
                return modulos.get(i);
            }
        }
        return null;
    }

    // Busca todos los modulos cuyo nombre contenga el texto indicado, sin distinguir mayusculas.
    public ArrayList<Modulo> buscarPorNombre(String nombre) {
        ArrayList<Modulo> encontrados = new ArrayList<Modulo>();
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i).getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(modulos.get(i));
            }
        }
        return encontrados;
    }

    // Devuelve una copia de los modulos ordenada por costo de construccion,
    // sin modificar el orden original de carga de la lista interna.
    public ArrayList<Modulo> obtenerModulosOrdenadosPorCosto() {
        ArrayList<Modulo> ordenados = new ArrayList<Modulo>(modulos);
        Collections.sort(ordenados, new Comparator<Modulo>() {
            @Override
            public int compare(Modulo m1, Modulo m2) {
                return Double.compare(m1.getCostoConstruccion(), m2.getCostoConstruccion());
            }
        });
        return ordenados;
    }

    // Recorre todos los modulos en orden y solicita a cada uno que procese su ciclo,
    // utilizando siempre los mismos recursos compartidos para que cada modulo use
    // el resultado dejado por el modulo anterior. Devuelve el mensaje de cada modulo.
    public ArrayList<String> avanzarCiclo() {
        ArrayList<String> resultados = new ArrayList<String>();
        for (int i = 0; i < modulos.size(); i++) {
            String resultado = modulos.get(i).procesarCiclo(recursos);
            resultados.add(resultado);
        }
        return resultados;
    }

    // Cuenta la cantidad total de modulos de tierra registrados en la mision.
    public int contarModulosTierra() {
        int contador = 0;
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra) {
                contador++;
            }
        }
        return contador;
    }

    // Cuenta la cantidad de modulos de tierra que se encuentran activos.
    public int contarModulosTierraActivos() {
        int contador = 0;
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra && modulos.get(i).isActivo()) {
                contador++;
            }
        }
        return contador;
    }

    // Suma la capacidad de descarga de todos los modulos de tierra activos.
    public double calcularCapacidadDescargaActiva() {
        double capacidad = 0.0;
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra && modulos.get(i).isActivo()) {
                capacidad += ((ModuloTierra) modulos.get(i)).getCapacidadDescarga();
            }
        }
        return capacidad;
    }

    // Suma el total historico descargado por todos los modulos de tierra, activos o no.
    public double calcularTotalHistoricoDescargado() {
        double total = 0.0;
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra) {
                total += ((ModuloTierra) modulos.get(i)).getTotalDescargado();
            }
        }
        return total;
    }

    // Devuelve todos los modulos de tierra que comparten el mayor total historico descargado.
    // Si existe un empate en el maximo, se devuelven todos los modulos empatados.
    public ArrayList<ModuloTierra> obtenerModulosTierraMayorDescarga() {
        ArrayList<ModuloTierra> mayores = new ArrayList<ModuloTierra>();
        double maximo = -1.0;
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra) {
                ModuloTierra moduloTierra = (ModuloTierra) modulos.get(i);
                if (moduloTierra.getTotalDescargado() > maximo) {
                    maximo = moduloTierra.getTotalDescargado();
                }
            }
        }
        for (int i = 0; i < modulos.size(); i++) {
            if (modulos.get(i) instanceof ModuloTierra) {
                ModuloTierra moduloTierra = (ModuloTierra) modulos.get(i);
                if (moduloTierra.getTotalDescargado() == maximo) {
                    mayores.add(moduloTierra);
                }
            }
        }
        return mayores;
    }
}
