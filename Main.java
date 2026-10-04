// Clase principal encargada de iniciar el programa: crea los recursos, la mision,
// carga los modulos iniciales, crea la vista y el controlador, y pone en marcha el menu.
public class Main {

    public static void main(String[] args) {
        // Se crean los recursos compartidos de la mision con valores iniciales.
        RecursosMision recursos = new RecursosMision(100.0, 0.0, 0.0);
        Mision mision = new Mision(recursos);

        // Se cargan al menos 10 modulos iniciales, combinando los tres tipos existentes
        // y variando sus datos para poder observar los distintos casos de operacion.
        mision.agregarModulo(new ModuloVuelo("MV01", "Explorador Norte", 100.0, true, 5000.0, "Espectrometro", 20.0, 15.0));
        mision.agregarModulo(new ModuloVuelo("MV02", "Explorador Sur", 100.0, true, 4800.0, "Camara multiespectral", 25.0, 18.0));
        mision.agregarModulo(new ModuloVuelo("MV03", "Explorador Este", 95.0, true, 5200.0, "Magnetometro", 15.0, 10.0));
        mision.agregarModulo(new ModuloVuelo("MV04", "Explorador Oeste", 90.0, false, 4700.0, "Espectrometro", 18.0, 12.0));

        mision.agregarModulo(new ModuloTierra("MT01", "Antena Quetzaltenango", 100.0, true, 6000.0, "Estacion Quetzaltenango", 30.0, 10.0));
        mision.agregarModulo(new ModuloTierra("MT02", "Antena Peten", 100.0, true, 6200.0, "Estacion Peten", 25.0, 8.0));
        mision.agregarModulo(new ModuloTierra("MT03", "Antena Izabal", 98.0, true, 5800.0, "Estacion Izabal", 35.0, 12.0));

        mision.agregarModulo(new ModuloEnergia("ME01", "Panel Solar Alfa", 100.0, true, 3000.0, 20.0));
        mision.agregarModulo(new ModuloEnergia("ME02", "Panel Solar Beta", 100.0, true, 3200.0, 25.0));
        mision.agregarModulo(new ModuloEnergia("ME03", "Panel Solar Gamma", 85.0, false, 2900.0, 18.0));

        // Se crea la vista y el controlador, y se inicia el menu principal del programa.
        VistaConsola vista = new VistaConsola();
        ControladorMision controlador = new ControladorMision(mision, vista);
        controlador.iniciar();
    }
}
