import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase de la vista dentro del patron MVC. Se encarga de toda la interaccion con el usuario:
// mostrar el menu, leer sus opciones y presentar los resultados del programa.
public class VistaConsola {

    private Scanner scanner;

    // Constructor que inicializa el Scanner utilizado para leer la entrada del usuario.
    public VistaConsola() {
        scanner = new Scanner(System.in);
    }

    // Imprime en consola las opciones disponibles del menu principal.
    public void mostrarMenu() {
        System.out.println();
        System.out.println("===== MISION QUETZAL-2 =====");
        System.out.println("1. Listar modulos");
        System.out.println("2. Buscar modulo");
        System.out.println("3. Mostrar catalogo ordenado por costo");
        System.out.println("4. Avanzar ciclo");
        System.out.println("5. Mostrar resumen de comunicaciones");
        System.out.println("6. Salir");
    }

    // Lee la opcion numerica elegida por el usuario, manejando entradas con formato incorrecto
    // sin detener la ejecucion del programa.
    public int leerOpcion() {
        int opcion;
        while (true) {
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                return opcion;
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida, debe ingresar un numero entero");
                scanner.nextLine();
            }
        }
    }

    // Muestra un mensaje al usuario y lee una linea de texto como respuesta.
    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    // Muestra un mensaje simple al usuario.
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra la informacion de un unico modulo.
    public void mostrarModulo(Modulo modulo) {
        System.out.println(modulo.toString());
    }

    // Muestra la informacion de una lista de modulos, o un mensaje si la lista esta vacia.
    public void mostrarModulos(ArrayList<Modulo> modulos) {
        if (modulos.isEmpty()) {
            System.out.println("No hay modulos para mostrar");
            return;
        }
        for (int i = 0; i < modulos.size(); i++) {
            System.out.println(modulos.get(i).toString());
        }
    }

    // Muestra los mensajes resultantes de procesar un ciclo completo de la mision.
    public void mostrarResultadosCiclo(ArrayList<String> resultados) {
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println(resultados.get(i));
        }
    }

    // Muestra el estado actual de los recursos compartidos de la mision.
    public void mostrarRecursos(RecursosMision recursos) {
        System.out.println(recursos.toString());
    }
}
