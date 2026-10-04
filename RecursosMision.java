// Representa los recursos compartidos de la mision: energia, datos en orbita y datos descargados.
// Todos los modulos leen y modifican esta misma instancia durante la simulacion.
public class RecursosMision {

    private double energiaDisponible;
    private double datosEnOrbita;
    private double datosDescargados;

    // Constructor que recibe y valida los valores iniciales de los recursos de la mision.
    public RecursosMision(double energiaDisponible, double datosEnOrbita, double datosDescargados) {
        if (energiaDisponible < 0) {
            throw new IllegalArgumentException("La energia disponible no puede ser negativa");
        }
        if (datosEnOrbita < 0) {
            throw new IllegalArgumentException("Los datos en orbita no pueden ser negativos");
        }
        if (datosDescargados < 0) {
            throw new IllegalArgumentException("Los datos descargados no pueden ser negativos");
        }
        this.energiaDisponible = energiaDisponible;
        this.datosEnOrbita = datosEnOrbita;
        this.datosDescargados = datosDescargados;
    }

    // Devuelve la energia actualmente disponible para los modulos.
    public double getEnergiaDisponible() {
        return energiaDisponible;
    }

    // Devuelve la cantidad de datos que aun estan pendientes de descargar.
    public double getDatosEnOrbita() {
        return datosEnOrbita;
    }

    // Devuelve el total historico de datos que ya fueron descargados.
    public double getDatosDescargados() {
        return datosDescargados;
    }

    // Aumenta la energia disponible, usado cuando un modulo de energia genera energia.
    public void agregarEnergia(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a agregar no puede ser negativa");
        }
        energiaDisponible += cantidad;
    }

    // Reduce la energia disponible, usado cuando un modulo consume energia para operar.
    public void consumirEnergia(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a consumir no puede ser negativa");
        }
        if (cantidad > energiaDisponible) {
            throw new IllegalStateException("No hay suficiente energia disponible");
        }
        energiaDisponible -= cantidad;
    }

    // Aumenta los datos pendientes en orbita, usado cuando un modulo de vuelo recolecta datos.
    public void agregarDatosOrbita(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a agregar no puede ser negativa");
        }
        datosEnOrbita += cantidad;
    }

    // Mueve datos de orbita hacia datos descargados, usado cuando un modulo de tierra realiza una descarga.
    public void descargarDatos(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a descargar no puede ser negativa");
        }
        if (cantidad > datosEnOrbita) {
            throw new IllegalStateException("No hay suficientes datos en orbita");
        }
        datosEnOrbita -= cantidad;
        datosDescargados += cantidad;
    }

    // Genera una representacion en texto con el estado actual de los recursos.
    @Override
    public String toString() {
        return "Energia disponible: " + energiaDisponible +
                ", Datos en orbita: " + datosEnOrbita +
                ", Datos descargados: " + datosDescargados;
    }
}
