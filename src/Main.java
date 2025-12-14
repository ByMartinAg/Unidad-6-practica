import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Recomendaciones sistema = new Recomendaciones();
        int opcion;

        System.out.println("====================Recomendaciones musicales====================");

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("====================");
            System.out.println("Gestionar canciones");
            System.out.println("1- Agregar cancion");
            System.out.println("2- Conectar canciones");
            System.out.println("3- Eliminar cancion");
            System.out.println("4- Eliminar conexión");
            System.out.println("====================");
            System.out.println("Recomendaciones (Métodos de busqueda)");
            System.out.println("5- Recomendaciones cercanas (BFS)");
            System.out.println("6- Exploración profunda (DFS)");
            System.out.println("7- Detectar ciclos musicales");
            System.out.println("====================");
            System.out.println("Consultas");
            System.out.println("8- Mostrar todas las canciones");
            System.out.println("9- Buscar canción por ID");
            System.out.println("10- Mostrar grafo de conexiones");
            System.out.println("====================");
            System.out.println("Extras");
            System.out.println("11- Ver BFS original (imprime IDs)");
            System.out.println("12- Ver DFS original (imprime IDs)");
            System.out.println("13- Cargar canciones de ejemplo");
            System.out.println("====================");
            System.out.println("0- Salir");
            System.out.print("Ingrese una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1: // Agregar canción
                    System.out.println("\n Agregar cancion");
                    System.out.println("ID de la cancion: ");
                    int id = teclado.nextInt();
                    System.out.println("Nombre de la cancion: ");
                    String nombre = teclado.nextLine();
                    System.out.println("Artista: ");
                    String artista = teclado.nextLine();
                    System.out.println("Genero: ");
                    String genero = teclado.nextLine();
                    sistema.agregarCancion(new Cancion(id, nombre, artista, genero));
                    break;

                case 2: // Conectar canciones
                    System.out.println("\nCONECTAR CANCIONES");
                    System.out.println("ID de la primera cancion: ");
                    int id1 = teclado.nextInt();
                    System.out.println("ID de la segunda cancinn: ");
                    int id2 = teclado.nextInt();
                    teclado.nextLine();
                    System.out.println("Razón de conexion (mismo genero, mismo artista, u otro): ");
                    String razon = teclado.nextLine();
                    sistema.conectarCanciones(id1, id2, razon);
                    break;

                case 3: // Eliminar cancion
                    System.out.println("\nELIMINAR CANCION");
                    System.out.print("ID de la cancion a eliminar: ");
                    int idEliminar = teclado.nextInt();
                    sistema.eliminarVertice(idEliminar);
                    break;

                case 4: // Eliminar conexión
                    System.out.println("\nELIMINAR CONEXION");
                    System.out.print("ID de la primera cancion: ");
                    int origen = teclado.nextInt();
                    System.out.print("ID de la segunda cancion: ");
                    int destino = teclado.nextInt();
                    sistema.eliminarArista(origen, destino);
                    break;

                case 5: // Recomendaciones BFS
                    System.out.println("\nRECOMENDACIONES CERCANAS (BFS)");
                    System.out.print("ID de la cancion base: ");
                    int idBase = teclado.nextInt();
                    System.out.print("Niveles de profundidad (recomendado 1-3): ");
                    int niveles = teclado.nextInt();
                    sistema.recomendarCercanas(idBase, niveles);
                    break;

                case 6: // Exploracion DFS
                    System.out.println("\nEXPLORACION PROFUNDA (DFS)");
                    System.out.print("ID de la cancion inicial: ");
                    int idInicio = teclado.nextInt();
                    sistema.explorarGenero(idInicio);
                    break;

                case 7: // Detectar ciclos
                    sistema.detectarCiclos();
                    break;

                case 8: // Mostrar canciones
                    sistema.mostrarCanciones();
                    break;

                case 9: // Buscar cancion
                    System.out.println("\n━━━ BUSCAR CANCION ━━━");
                    System.out.print("ID de la cancion: ");
                    int idBuscar = teclado.nextInt();
                    sistema.buscarCancion(idBuscar);
                    break;

                case 10: // Mostrar grafo
                    sistema.mostrar();
                    break;

                case 11: // BFS original
                    System.out.println("\nBFS ORIGINAL (imprime IDs)");
                    System.out.print("ID de la cancion inicial: ");
                    int idBFS = teclado.nextInt();
                    sistema.bfs(idBFS);
                    break;

                case 12: // DFS original
                    System.out.println("\nDFS ORIGINAL (imprime IDs)");
                    System.out.print("ID de la cancion inicial: ");
                    int idDFS = teclado.nextInt();
                    sistema.dfs(idDFS);
                    break;

                case 13: // Cargar ejemplos
                    sistema.cargarEjemplos();
                    break;

                case 0: // Salir
                    System.out.println("baii");
                    break;

                default:
                    System.out.println("invalido");
                    break;
            }

        } while (opcion != 0);

        teclado.close();
    }
}