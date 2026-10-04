// Representa los modulos encargados de generar la energia que utilizan los demas modulos.
public class ModuloEnergia extends Modulo {

    private double energiaPorCiclo;
    private int ciclosAcumulados;
    private double totalEnergiaGenerada;

    // Constructor que recibe los datos comunes de Modulo mas los datos particulares del modulo de energia.
    // Los acumuladores (ciclosAcumulados y totalEnergiaGenerada) siempre inician en cero.
    public ModuloEnergia(String id, String nombre, double salud, boolean activo, double costoConstruccion, double energiaPorCiclo) {
        super(id, nombre, salud, activo, costoConstruccion);
        if (energiaPorCiclo <= 0) {
            throw new IllegalArgumentException("La energia generada por ciclo debe ser mayor que cero");
        }
        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclosAcumulados = 0;
        this.totalEnergiaGenerada = 0.0;
    }

    // Devuelve la cantidad de energia que el modulo genera en cada ciclo.
    public double getEnergiaPorCiclo() {
        return energiaPorCiclo;
    }

    // Devuelve la cantidad de ciclos en los que el modulo logro generar energia.
    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    // Devuelve el total historico de energia generada por el modulo.
    public double getTotalEnergiaGenerada() {
        return totalEnergiaGenerada;
    }

    // Implementa el comportamiento especifico del modulo de energia durante un ciclo:
    // si esta activo, genera energia y la agrega a los recursos compartidos de la mision.
    // Si esta inactivo, no genera energia y no incrementa sus acumuladores.
    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return "Modulo " + getId() + " (" + getNombre() + "): inactivo, no genero energia";
        }
        recursos.agregarEnergia(energiaPorCiclo);
        ciclosAcumulados++;
        totalEnergiaGenerada += energiaPorCiclo;
        return "Modulo " + getId() + " (" + getNombre() + "): genero " + energiaPorCiclo + " de energia";
    }

    // Genera una representacion en texto con la informacion comun y particular del modulo.
    @Override
    public String toString() {
        return super.toString() +
                ", Energia por ciclo: " + energiaPorCiclo +
                ", Ciclos acumulados: " + ciclosAcumulados +
                ", Total de energia generada: " + totalEnergiaGenerada;
    }
}
