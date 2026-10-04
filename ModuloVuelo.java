// Representa los modulos en orbita que recolectan datos cientificos durante cada ciclo.
public class ModuloVuelo extends Modulo {

    private String tipoInstrumento;
    private double datosPorCiclo;
    private double consumoEnergia;
    private int ciclosAcumulados;
    private double totalRecolectado;

    // Constructor que recibe los datos comunes de Modulo mas los datos particulares del modulo de vuelo.
    // Los acumuladores (ciclosAcumulados y totalRecolectado) siempre inician en cero.
    public ModuloVuelo(String id, String nombre, double salud, boolean activo, double costoConstruccion, String tipoInstrumento, double datosPorCiclo, double consumoEnergia) {
        super(id, nombre, salud, activo, costoConstruccion);
        if (datosPorCiclo <= 0) {
            throw new IllegalArgumentException("Los datos por ciclo deben ser mayores que cero");
        }
        if (consumoEnergia <= 0) {
            throw new IllegalArgumentException("El consumo de energia debe ser mayor que cero");
        }
        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoEnergia = consumoEnergia;
        this.ciclosAcumulados = 0;
        this.totalRecolectado = 0.0;
    }

    // Devuelve el tipo de instrumento cientifico del modulo.
    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    // Devuelve la cantidad de datos que recolecta el modulo en cada ciclo exitoso.
    public double getDatosPorCiclo() {
        return datosPorCiclo;
    }

    // Devuelve la energia que necesita el modulo para recolectar datos.
    public double getConsumoEnergia() {
        return consumoEnergia;
    }

    // Devuelve la cantidad de ciclos en los que el modulo logro operar.
    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    // Devuelve el total historico de datos recolectados por el modulo.
    public double getTotalRecolectado() {
        return totalRecolectado;
    }

    // Implementa el comportamiento especifico del modulo de vuelo durante un ciclo:
    // intenta recolectar datos si esta activo y existe suficiente energia disponible.
    // Si no puede operar, informa la causa y no modifica sus acumuladores.
    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return "Modulo " + getId() + " (" + getNombre() + "): inactivo, no realizo ninguna operacion";
        }
        if (recursos.getEnergiaDisponible() < consumoEnergia) {
            return "Modulo " + getId() + " (" + getNombre() + "): no pudo recolectar datos, energia insuficiente";
        }
        recursos.consumirEnergia(consumoEnergia);
        recursos.agregarDatosOrbita(datosPorCiclo);
        ciclosAcumulados++;
        totalRecolectado += datosPorCiclo;
        return "Modulo " + getId() + " (" + getNombre() + "): recolecto " + datosPorCiclo + " datos cientificos";
    }

    // Genera una representacion en texto con la informacion comun y particular del modulo.
    @Override
    public String toString() {
        return super.toString() +
                ", Tipo de instrumento: " + tipoInstrumento +
                ", Datos por ciclo: " + datosPorCiclo +
                ", Consumo de energia: " + consumoEnergia +
                ", Ciclos acumulados: " + ciclosAcumulados +
                ", Total recolectado: " + totalRecolectado;
    }
}
