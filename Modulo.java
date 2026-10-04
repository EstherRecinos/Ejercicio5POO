// Clase padre que representa las caracteristicas comunes de todos los modulos de la mision.
// Sirve como base para ModuloVuelo, ModuloTierra y ModuloEnergia.
public abstract class Modulo {

    private String id;
    private String nombre;
    private double salud;
    private boolean activo;
    private double costoConstruccion;

    // Constructor que recibe y valida la informacion comun de todo modulo.
    public Modulo(String id, String nombre, double salud, boolean activo, double costoConstruccion) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El id del modulo no puede estar vacio");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del modulo no puede estar vacio");
        }
        if (costoConstruccion <= 0) {
            throw new IllegalArgumentException("El costo de construccion debe ser mayor que cero");
        }
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.activo = activo;
        this.costoConstruccion = costoConstruccion;
    }

    // Devuelve el id unico del modulo.
    public String getId() {
        return id;
    }

    // Devuelve el nombre del modulo.
    public String getNombre() {
        return nombre;
    }

    // Devuelve el estado de salud del modulo.
    public double getSalud() {
        return salud;
    }

    // Indica si el modulo se encuentra activo.
    public boolean isActivo() {
        return activo;
    }

    // Devuelve el costo de construccion del modulo.
    public double getCostoConstruccion() {
        return costoConstruccion;
    }

    // Actualiza el estado de salud del modulo.
    public void setSalud(double salud) {
        this.salud = salud;
    }

    // Actualiza el estado activo o inactivo del modulo.
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Operacion polimorfica que cada tipo de modulo debe implementar de forma distinta.
    // Recibe los recursos compartidos de la mision y devuelve un mensaje describiendo el resultado.
    public abstract String procesarCiclo(RecursosMision recursos);

    // Genera una representacion en texto con la informacion comun del modulo.
    @Override
    public String toString() {
        return "Id: " + id +
                ", Nombre: " + nombre +
                ", Salud: " + salud +
                ", Activo: " + (activo ? "Si" : "No") +
                ", Costo de construccion: Q" + String.format("%.2f", costoConstruccion);
    }
}
