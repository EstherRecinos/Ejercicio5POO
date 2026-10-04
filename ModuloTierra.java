// Representa las estaciones o antenas encargadas de descargar los datos pendientes en orbita.
public class ModuloTierra extends Modulo {

    private String estacion;
    private double capacidadDescarga;
    private double energiaPorDescarga;
    private double totalDescargado;

    // Constructor que recibe los datos comunes de Modulo mas los datos particulares del modulo de tierra.
    // El acumulador totalDescargado siempre inicia en cero.
    public ModuloTierra(String id, String nombre, double salud, boolean activo, double costoConstruccion, String estacion, double capacidadDescarga, double energiaPorDescarga) {
        super(id, nombre, salud, activo, costoConstruccion);
        if (capacidadDescarga <= 0) {
            throw new IllegalArgumentException("La capacidad de descarga debe ser mayor que cero");
        }
        if (energiaPorDescarga <= 0) {
            throw new IllegalArgumentException("La energia por descarga debe ser mayor que cero");
        }
        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = 0.0;
    }

    // Devuelve el nombre de la estacion asociada al modulo.
    public String getEstacion() {
        return estacion;
    }

    // Devuelve la cantidad maxima de datos que el modulo puede descargar en un ciclo.
    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }

    // Devuelve la energia que necesita el modulo para realizar una descarga.
    public double getEnergiaPorDescarga() {
        return energiaPorDescarga;
    }

    // Devuelve el total historico de datos descargados por el modulo.
    public double getTotalDescargado() {
        return totalDescargado;
    }

    // Implementa el comportamiento especifico del modulo de tierra durante un ciclo:
    // intenta descargar datos si esta activo, tiene energia suficiente y existen datos pendientes en orbita.
    // La cantidad descargada nunca supera los datos disponibles ni la capacidad del modulo,
    // y siempre consume toda la energia requerida aunque la descarga sea menor que la capacidad maxima.
    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return "Modulo " + getId() + " (" + getNombre() + "): inactivo, no realizo ninguna operacion";
        }
        if (recursos.getEnergiaDisponible() < energiaPorDescarga) {
            return "Modulo " + getId() + " (" + getNombre() + "): no pudo descargar datos, energia insuficiente";
        }
        if (recursos.getDatosEnOrbita() <= 0) {
            return "Modulo " + getId() + " (" + getNombre() + "): no pudo descargar datos, no hay datos pendientes en orbita";
        }
        double cantidad = Math.min(capacidadDescarga, recursos.getDatosEnOrbita());
        recursos.consumirEnergia(energiaPorDescarga);
        recursos.descargarDatos(cantidad);
        totalDescargado += cantidad;
        return "Modulo " + getId() + " (" + getNombre() + "): descargo " + cantidad + " datos";
    }

    // Genera una representacion en texto con la informacion comun y particular del modulo.
    @Override
    public String toString() {
        return super.toString() +
                ", Estacion: " + estacion +
                ", Capacidad de descarga: " + capacidadDescarga +
                ", Energia por descarga: " + energiaPorDescarga +
                ", Total descargado: " + totalDescargado;
    }
}
