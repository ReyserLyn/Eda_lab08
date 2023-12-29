
public class Main {

    public static void main(String[] args) {
        // Creamos un Grafo NO Dirigido
        GraphLink<String> undirectedGraph = createUndirectedGraph();

        System.out.println("Grafo No Dirigido:");
        System.out.println(undirectedGraph);

        System.out.println("Recorrido BFS desde el vértice A:"); // BFS
        undirectedGraph.bfs("A");

        System.out.println("Recorrido DFS desde el vértice A:"); // DFS
        undirectedGraph.dfs("A");

        System.out.println();

        // Creamos un Grago Dirigido con Peso
        GraphLink<String> weightedDirectedGraph = createWeightedDirectedGraph();

        System.out.println("Grafo Dirigido con Peso:");
        System.out.println(weightedDirectedGraph);

        System.out.println("Recorrido BFS desde el vértice A:"); // BFS
        weightedDirectedGraph.bfs("A");

        System.out.println("Recorrido DFS desde el vértice A:"); // DFS
        weightedDirectedGraph.dfs("A");

        // DIJKSTRA
        System.out.println("\nLa distancia mínima entre A y F es: " + weightedDirectedGraph.dijkstra("A", "F"));
        System.out.println();

        System.out.println("Agregamos un peso de 2 de B a F");
        weightedDirectedGraph.insertEdge("B", 2, "F");
        System.out.println("La nueva distancia mínima entre A y F es: " + weightedDirectedGraph.dijkstra("A", "F"));
        System.out.println();

        // Ejercicio 4, grafo con palabras - a) Creamos el grafo
        GraphLink<String> wordGraph = createWordGraph();

        // Ejercicio 4 - b) Mostramos la lista de adyacencia
        System.out.println("Grafo de Palabras:");
        System.out.println(wordGraph);

        // Ejercicio 5 - Probamos la inclusion de un grafo dentro de otro
        GraphLink<String> graph1 = createGraph1();
        GraphLink<String> graph2 = createGraph2();

        // Mostramos los grafos
        System.out.println("Grafo 1:");
        System.out.println(graph1);

        System.out.println("Grafo 2:");
        System.out.println(graph2);

        // Verificar si graph1 está incluido en graph2
        boolean isIncluded = graph1.isIncludedIn(graph2);

        // Mostrar el resultado de la inclusión
        System.out.println("¿Grafo 1 está incluido en Grafo 2? " + isIncluded);

        isIncluded = graph2.isIncludedIn(graph1);
        System.out.println("¿Grafo 2 está incluido en Grafo 1? " + isIncluded);
    }

    // Crear grafo no dirigido
    private static GraphLink<String> createUndirectedGraph() {
        GraphLink<String> undirectedGraph = new GraphLink<>();

        undirectedGraph.insertVertex("A");
        undirectedGraph.insertVertex("B");
        undirectedGraph.insertVertex("C");
        undirectedGraph.insertVertex("D");
        undirectedGraph.insertVertex("E");
        undirectedGraph.insertVertex("F");
        undirectedGraph.insertVertex("G");

        undirectedGraph.insertEdge("A", "B");
        undirectedGraph.insertEdge("A", "C");
        undirectedGraph.insertEdge("B", "D");
        undirectedGraph.insertEdge("B", "F");
        undirectedGraph.insertEdge("C", "F");
        undirectedGraph.insertEdge("C", "G");

        return undirectedGraph;
    }

    // Crear grafo dirigido con peso
    private static GraphLink<String> createWeightedDirectedGraph() {
        GraphLink<String> weightedDirectedGraph = new GraphLink<>();

        weightedDirectedGraph.insertVertex("A");
        weightedDirectedGraph.insertVertex("B");
        weightedDirectedGraph.insertVertex("C");
        weightedDirectedGraph.insertVertex("D");
        weightedDirectedGraph.insertVertex("E");
        weightedDirectedGraph.insertVertex("F");
        weightedDirectedGraph.insertVertex("G");

        weightedDirectedGraph.insertEdge("A", 5, "B");
        weightedDirectedGraph.insertEdge("A", 6, "C");
        weightedDirectedGraph.insertEdge("B", 7, "D");
        weightedDirectedGraph.insertEdge("B", 15, "E");
        weightedDirectedGraph.insertEdge("C", 25, "F");
        weightedDirectedGraph.insertEdge("C", 234, "G");

        return weightedDirectedGraph;
    }

    // Método para crear el grafo con las palabras dadas
    private static GraphLink<String> createWordGraph() {
        GraphLink<String> wordGraph = new GraphLink<>();

        String[] palabras = {"words", "cords", "corps", "coops", "crops", "drops", "drips", "grips", "gripe", "grape", "graph"};

        for (String palabra : palabras) {
            wordGraph.insertVertex(palabra);
        }

        for (int i = 0; i < palabras.length; i++) {
            for (int j = i + 1; j < palabras.length; j++) {
                if (diferirEnUnaPosicion(palabras[i], palabras[j])) {
                    wordGraph.insertEdge(palabras[i], palabras[j]);
                }
            }
        }

        return wordGraph;
    }

    // Método para verificar si dos palabras difieren exactamente en una posición
    private static boolean diferirEnUnaPosicion(String palabra1, String palabra2) {
        if (palabra1.length() != palabra2.length()) {
            return false;
        }

        int diferencia = 0;
        for (int i = 0; i < palabra1.length(); i++) {
            if (palabra1.charAt(i) != palabra2.charAt(i)) {
                diferencia++;
                if (diferencia > 1) {
                    return false;
                }
            }
        }

        return diferencia == 1;
    }

    private static GraphLink<String> createGraph1() {
        GraphLink<String> graph = new GraphLink<>();
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertEdge("A", "B");
        return graph;
    }

    private static GraphLink<String> createGraph2() {
        GraphLink<String> graph = new GraphLink<>();
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertEdge("A", "B");
        graph.insertEdge("B", "C");
        return graph;
    }
}
