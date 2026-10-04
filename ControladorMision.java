import java.util.ArrayList;

// Clase del controlador dentro del patron MVC. Comunica las acciones solicitadas
// por el usuario a traves de la vista con las operaciones del modelo (la Mision).
public class ControladorMision {

    private Mision mision;
    private VistaConsola vista;

    // Constructor que recibe la mision a administrar y la vista a utilizar.
    public ControladorMision(Mision mision, VistaConsola vista) {
        this.mision = mision;
        this.vista = vista;
    }

    // Pone en marcha el menu principal del programa hasta que el usuario decide salir.
    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            switch (opcion) {
                case 1:
                    listarModulos();
                    break;
                case 2:
                    buscarModulo();
                    break;
                case 3:
                    mostrarCatalogoPorCosto();
                    break;
                case 4:
                    avanzarCiclo();
                    break;
                case 5:
                    mostrarResumenComunicaciones();
                    break;
                case 6:
                    salir = true;
                    vista.mostrarMensaje("Saliendo del sistema");
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida, intente nuevamente");
            }
        }
    }

    // Solicita a la mision la lista completa de modulos y la muestra a traves de la vista.
    public void listarModulos() {
        vista.mostrarModulos(mision.getModulos());
    }

    // Pregunta al usuario si desea buscar por id o por nombre y muestra el resultado de la busqueda.
    public void buscarModulo() {
        vista.mostrarMensaje("Buscar por:");
        vista.mostrarMensaje("1. Id");
        vista.mostrarMensaje("2. Nombre");
        int opcion = vista.leerOpcion();
        if (opcion == 1) {
            String id = vista.leerTexto("Ingrese el id del modulo: ");
            Modulo modulo = mision.buscarPorId(id);
            if (modulo == null) {
                vista.mostrarMensaje("No se encontro un modulo con ese id");
            } else {
                vista.mostrarModulo(modulo);
            }
        } else if (opcion == 2) {
            String nombre = vista.leerTexto("Ingrese el nombre del modulo: ");
            ArrayList<Modulo> encontrados = mision.buscarPorNombre(nombre);
            vista.mostrarModulos(encontrados);
        } else {
            vista.mostrarMensaje("Opcion invalida");
        }
    }

    // Solicita a la mision el catalogo de modulos ordenado por costo y lo muestra a traves de la vista.
    public void mostrarCatalogoPorCosto() {
        vista.mostrarModulos(mision.obtenerModulosOrdenadosPorCosto());
    }

    // Solicita a la mision avanzar un ciclo completo y muestra los resultados y los recursos actualizados.
    public void avanzarCiclo() {
        ArrayList<String> resultados = mision.avanzarCiclo();
        vista.mostrarResultadosCiclo(resultados);
        vista.mostrarRecursos(mision.getRecursos());
    }

    // Reune la informacion relacionada con los modulos de tierra y la presenta como resumen de comunicaciones.
    public void mostrarResumenComunicaciones() {
        vista.mostrarMensaje("===== RESUMEN DE COMUNICACIONES =====");
        vista.mostrarMensaje("Modulos de tierra registrados: " + mision.contarModulosTierra());
        vista.mostrarMensaje("Modulos de tierra activos: " + mision.contarModulosTierraActivos());
        vista.mostrarMensaje("Capacidad de descarga activa: " + mision.calcularCapacidadDescargaActiva());
        vista.mostrarMensaje("Total historico descargado: " + mision.calcularTotalHistoricoDescargado());
        ArrayList<ModuloTierra> mayores = mision.obtenerModulosTierraMayorDescarga();
        vista.mostrarMensaje("Modulos de tierra con mayor descarga historica:");
        vista.mostrarModulos(new ArrayList<Modulo>(mayores));
    }
}
