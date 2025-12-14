import java.util.*;

public class Recomendaciones {
    private final Map<Integer, List<Integer>> grafo;
    private final Map<Integer, Cancion> canciones;

    public Recomendaciones() {
        grafo = new HashMap<>();
        canciones = new HashMap<>();
    }
    // Agregar vertice
    public void agregarVertice(Nodo nodo) {
        int vertice = nodo.getValor();
        if (!grafo.containsKey(vertice)) {
            grafo.put(vertice, new ArrayList<>());
        }
    }

    // Agregar arista no dirigida
    public void agregarArista(int origen, int destino) {
        if (grafo.containsKey(origen) && grafo.containsKey(destino)) {
            grafo.get(origen).add(destino);
            grafo.get(destino).add(origen);
        }
    }

    // Eliminar vertice
    public void eliminarVertice(int vert) {
        if (grafo.containsKey(vert)) {
            grafo.remove(vert);
            for (List<Integer> lista : grafo.values()) {
                lista.remove(Integer.valueOf(vert));
            }
            canciones.remove(vert);
            System.out.println("Vertice: " + vert + " eliminado");
        } else {
            System.out.println("No existe");
        }
    }

    // Eliminar arista
    public void eliminarArista(int origen, int destino) {
        if (grafo.containsKey(origen) && grafo.containsKey(destino)) {
            grafo.get(origen).remove(Integer.valueOf(destino));
            grafo.get(destino).remove(Integer.valueOf(origen));
            System.out.println("Arista eliminada: " + origen + " ↔ " + destino);
        } else {
            System.out.println("Arista no eliminada");
        }
    }

    // Mostrar grafo
    public void mostrar() {
        if (grafo.isEmpty()) {
            System.out.println("Está vacio");
        } else {
            System.out.println("\nConexiones:");
            for (Integer verti : grafo.keySet()) {
                System.out.println(verti + " -> " + grafo.get(verti));
            }
        }
    }

    // BFS original (solo imprime) el q el profe paso
    public void bfs(int inicio) {
        if (!grafo.containsKey(inicio)) {
            System.out.println("Vértice no existe");
        } else {
            Set<Integer> visitado = new HashSet<>();
            Queue<Integer> cola = new LinkedList<>();

            cola.add(inicio);
            visitado.add(inicio);

            System.out.print("BFS: ");

            while (!cola.isEmpty()) {
                int actual = cola.poll();
                System.out.print(actual + " ");

                for (int vecino : grafo.get(actual)) {
                    if (!visitado.contains(vecino)) {
                        visitado.add(vecino);
                        cola.add(vecino);
                    }
                }
            }
            System.out.println();
        }
    }

    // BFS que retorna lista de vértices encontrados por niveles
    public List<Integer> bfsLista(int inicio, int maxNiveles) {
        List<Integer> resultado = new ArrayList<>();

        if (!grafo.containsKey(inicio)) {
            return resultado;
        }

        Set<Integer> visitado = new HashSet<>();
        Queue<int[]> cola = new LinkedList<>();

        cola.add(new int[]{inicio, 0});
        visitado.add(inicio);

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int vertice = actual[0];
            int nivel = actual[1];

            if (nivel > maxNiveles) break;

            if (nivel > 0) {
                resultado.add(vertice);
            }

            for (int vecino : grafo.get(vertice)) {
                if (!visitado.contains(vecino)) {
                    visitado.add(vecino);
                    cola.add(new int[]{vecino, nivel + 1});
                }
            }
        }

        return resultado;
    }

    // DFS original (solo imprime) el q el profe paso
    public void dfs(int inicio) {
        if (!grafo.containsKey(inicio)) {
            System.out.println("Vértice no existe");
        } else {
            System.out.print("DFS: ");
            Set<Integer> visitado = new HashSet<>();
            dfsRec(inicio, visitado);
            System.out.println();
        }
    }

    private void dfsRec(int actual, Set<Integer> visitado) {
        visitado.add(actual);
        System.out.print(actual + " ");

        for (int vecino : grafo.get(actual)) {
            if (!visitado.contains(vecino)) {
                dfsRec(vecino, visitado);
            }
        }
    }

    // DFS que retorna lista de vertices en orden en el q explora
    public List<Integer> dfsLista(int inicio) {
        List<Integer> resultado = new ArrayList<>();

        if (!grafo.containsKey(inicio)) {
            return resultado;
        }

        Set<Integer> visitado = new HashSet<>();
        dfsRecLista(inicio, visitado, resultado);

        return resultado;
    }

    private void dfsRecLista(int actual, Set<Integer> visitado, List<Integer> resultado) {
        visitado.add(actual);
        resultado.add(actual);

        for (int vecino : grafo.get(actual)) {
            if (!visitado.contains(vecino)) {
                dfsRecLista(vecino, visitado, resultado);
            }
        }
    }

    // Busqueda de ciclos
    public boolean Ciclo() {
        Set<Integer> visitado = new HashSet<>();

        for (Integer nodo : grafo.keySet()) {
            if (!visitado.contains(nodo)) {
                if (cicloDFS(nodo, -1, visitado)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean cicloDFS(int actual, int padre, Set<Integer> visitado) {
        visitado.add(actual);

        for (int vecino : grafo.get(actual)) {
            if (!visitado.contains(vecino)) {
                if (cicloDFS(vecino, actual, visitado)) {
                    return true;
                }
            } else if (vecino != padre) {
                return true;
            }
        }
        return false;
    }

    // METODOS PERO PARA EL PROBLEMA

    // Agregar canción al sistema
    public void agregarCancion(Cancion cancion) {
        canciones.put(cancion.getId(), cancion);
        agregarVertice(new Nodo(cancion.getId()));
        System.out.println("✓ Canción agregada: " + cancion);
    }

    // Conectar canciones similares
    public void conectarCanciones(int id1, int id2, String razon) {
        if (canciones.containsKey(id1) && canciones.containsKey(id2)) {
            agregarArista(id1, id2);
            System.out.println("Conexion: " + canciones.get(id1).getNombre() +
                    " y " + canciones.get(id2).getNombre() +
                    " (" + razon + ")");
        } else {
            System.out.println("una o ambas no existen");
        }
    }

    // Recomendaciones cercanas usando BFS
    public void recomendarCercanas(int idCancion, int niveles) {
        if (!canciones.containsKey(idCancion)) {
            System.out.println("No se encontro");
            return;
        }

        Cancion origen = canciones.get(idCancion);
        System.out.println("\n RECOMENDACIONES CERCANAS (usando BFS)");
        System.out.println("Basadas en: " + origen);
        System.out.println("====================");

        List<Integer> recomendaciones = bfsLista(idCancion, niveles);

        if (recomendaciones.isEmpty()) {
            System.out.println("No hay canciones conectadas");
        } else {
            int contador = 1;
            for (int id : recomendaciones) {
                System.out.println("  " + contador + ". " + canciones.get(id));
                contador++;
                if (contador > 10) break;
            }
        }
    }

    // Exploración profunda usando DFS
    public void explorarGenero(int idCancion) {
        if (!canciones.containsKey(idCancion)) {
            System.out.println("No se encontro");
            return;
        }

        Cancion origen = canciones.get(idCancion);
        System.out.println("\nEXPLORACION PROFUNDA (usando DFS)");
        System.out.println("Desde: " + origen);

        List<Integer> exploracion = dfsLista(idCancion);

        if (exploracion.size() <= 1) {
            System.out.println("No se encontro");
        } else {
            System.out.println("  Ruta: ");
            for (int i = 0; i < exploracion.size(); i++) {
                String indent = "  ".repeat(Math.min(i, 3));
                System.out.println(indent + "---" + canciones.get(exploracion.get(i)));
                if (i > 15) {
                    System.out.println(indent + "  ... (más canciones)");
                    break;
                }
            }
        }
    }

    // Detectar circuitos musicales
    public void detectarCiclos() {
        System.out.println("Verificando si hay ciclos musicales...");

        if (Ciclo()) {
            System.out.println(" Si hay ciclos de canciones");
        } else {
            System.out.println("No ha ciclos de canciones");
        }
    }

    // Mostrar todas las canciones
    public void mostrarCanciones() {
        System.out.println("Playlistttt");
        if (canciones.isEmpty()) {
            System.out.println("no hay canciones");
        } else {
            for (Cancion c : canciones.values()) {
                System.out.println("  [" + c.getId() + "] " + c);
            }
        }
    }

    // Buscar cancion por ID
    public void buscarCancion(int id) {
        if (canciones.containsKey(id)) {
            System.out.println("\nCANCION ENCONTRADA:");
            System.out.println("  " + canciones.get(id));
        } else {
            System.out.println("no hay ");
        }
    }

    // Cargar canciones de ejemplo
    public void cargarEjemplos() {
        System.out.println("\nCargando ejemplos de canciones\n");

        // Indie i Pop
        agregarCancion(new Cancion(1, "Video Games", "Lana Del Rey", "Pop"));
        agregarCancion(new Cancion(2, "Born to Die", "Lana Del Rey", "Pop"));
        agregarCancion(new Cancion(3, "drivers license", "Olivia Rodrigo", "Pop"));

        // Reggaetón
        agregarCancion(new Cancion(4, "Piripituchy", "El Bogueto", "Reggaeton"));
        agregarCancion(new Cancion(5, "Vaquero", "El Bogueto", "Reggaeton"));
        agregarCancion(new Cancion(8, "BEIBY", "Cachirula", "Reggaeton"));
        // Corridos Tumbados
        agregarCancion(new Cancion(6, "Soy El Diablo", "Natanael Cano", "Corridos Tumbados"));
        agregarCancion(new Cancion(7, "El Drip", "Natanael Cano", "Corridos Tumbados"));


        System.out.println("\nConexiones\n");
        // Lana Del Rey
        conectarCanciones(1, 2, "mismo artista");
        // Olivia Rodrigo
        conectarCanciones(2, 3, "pop emocional");
        // El Bogueto
        conectarCanciones(4, 5, "mismo artista");
        // Natanael Cano
        conectarCanciones(6, 7, "mismo artista");
        conectarCanciones(7, 8, "mexa");
        //Cruce de estilos
        conectarCanciones(1, 3, "nostalgia");
        System.out.println("Se cargaron las canciones");
    }
}