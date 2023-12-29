
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphLink<E extends Comparable<E>> {

    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        this.listVertex = new ListLinked<>();
    }

    public String insertVertex(E data) {
        Vertex<E> v = new Vertex<>(data);
        if (listVertex.search(v)) {
            return "El vértice con el dato " + data + " ya fue insertado";
        } else {
            listVertex.insertarOrdenado(v);
            return "Vértice agregado con éxito";
        }
    }

    public void insertEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> e = new Edge<>(vDes);
        if (vOri.listAdj.search(e)) {
            throw new IllegalStateException("La arista (" + dataOri + "," + dataDes + ") ya fue insertada");
        }

        vOri.listAdj.insertFirst(e);
        vDes.listAdj.insertFirst(new Edge<>(vOri));
    }

    public void insertEdge(E dataOri, int weight, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> e = new Edge<>(vDes, weight);
        if (vOri.listAdj.search(e) || vDes.listAdj.search(e)) {
            throw new IllegalStateException("La arista (" + dataOri + "," + dataDes + ") ya fue insertada");
        }

        vOri.listAdj.insertFirst(e);
    }

    public void removeEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> e = new Edge<>(vDes);
        if (vOri.listAdj.search(e)) {
            vOri.listAdj.remove(e);
            vDes.listAdj.remove(new Edge<>(vOri));
        }
    }

    public void removeEdge(E dataOri, int weight, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> e = new Edge<>(vDes, weight);
        if (vOri.listAdj.search(e)) {
            vOri.listAdj.remove(e);
            // Comentario: No eliminamos la arista inversa para grafos no dirigidos
        }
    }

    public String removeVertex(E x) {
        Vertex<E> vertex = listVertex.searchData(new Vertex<>(x));
        if (vertex == null) {
            return "El vértice con el dato " + x + " no existe en el grafo";
        }

        Node<Vertex<E>> vAux = listVertex.getHead();
        Edge<E> e = new Edge<>(vertex);
        while (vAux != null) {
            vAux.getData().listAdj.remove(e);
            vAux = vAux.getNext();
        }

        listVertex.remove(vertex);
        return "Vértice eliminado con éxito";
    }

    public boolean searchEdge(E dataOri, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> searchEdge = new Edge<>(vDes);
        return vOri.listAdj.search(searchEdge);
    }

    public boolean searchEdge(E dataOri, int weight, E dataDes) {
        Vertex<E> vOri = listVertex.searchData(new Vertex<>(dataOri));
        Vertex<E> vDes = listVertex.searchData(new Vertex<>(dataDes));

        if (vOri == null || vDes == null) {
            throw new IllegalArgumentException("Los vértices " + dataOri + " o " + dataDes + " no existen");
        }

        Edge<E> searchEdge = new Edge<>(vDes, weight);
        return vOri.listAdj.search(searchEdge);
    }

    public boolean searchVertex(E data) {
        Vertex<E> vertex = listVertex.searchData(new Vertex<>(data));
        return vertex != null;
    }

    public Vertex<E> buscarElemento(E data) {
        return listVertex.searchData(new Vertex<>(data));
    }

    public ListLinked<Vertex<E>> getListVertex() {
        return listVertex;
    }

    public void bfs(E startData) {
        Vertex<E> startVertex = listVertex.searchData(new Vertex<>(startData));

        if (startVertex == null) {
            throw new IllegalArgumentException("El vértice con el dato " + startData + " no existe en el grafo");
        }

        SimpleQueue<Vertex<E>> queue = new SimpleQueue<>();
        queue.enqueue(startVertex);
        startVertex.visited = true;

        while (!queue.isEmpty()) {
            Vertex<E> currentVertex = queue.dequeue();
            System.out.print(currentVertex.getData() + " ");

            List<Edge<E>> adjList = new ArrayList<>(currentVertex.listAdj.length());
            for (Node<Edge<E>> edgeNode : currentVertex.listAdj.getIterable()) {
                adjList.add(edgeNode.getData());
            }

            // Recorrer la lista de adyacencia en orden inverso
            for (int i = adjList.size() - 1; i >= 0; i--) {
                Edge<E> edge = adjList.get(i);
                Vertex<E> neighbor = edge.refdest;
                if (!neighbor.visited) {
                    queue.enqueue(neighbor);
                    neighbor.visited = true;
                }
            }
        }

        resetVisited();
        System.out.println();
    }

    private void resetVisited() {
        for (Node<Vertex<E>> vertexNode : listVertex.getIterable()) {
            vertexNode.getData().visited = false;
        }
    }

    public void dfs(E startData) {
        Vertex<E> startVertex = listVertex.searchData(new Vertex<>(startData));

        if (startVertex == null) {
            throw new IllegalArgumentException("El vértice con el dato " + startData + " no existe en el grafo");
        }

        SimpleStack<Vertex<E>> stack = new SimpleStack<>();
        stack.push(startVertex);
        startVertex.visited = true;

        while (!stack.isEmpty()) {
            Vertex<E> currentVertex = stack.pop();
            System.out.print(currentVertex.getData() + " ");

            for (Node<Edge<E>> edgeNode : currentVertex.listAdj.getIterable()) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> neighbor = edge.refdest;
                if (!neighbor.visited) {
                    stack.push(neighbor);
                    neighbor.visited = true;
                }
            }
        }

        resetVisited();
        System.out.println();
    }

    public int dijkstra(E startData, E endData) {
        Vertex<E> startVertex = listVertex.searchData(new Vertex<>(startData));
        Vertex<E> endVertex = listVertex.searchData(new Vertex<>(endData));

        if (startVertex == null || endVertex == null) {
            throw new IllegalArgumentException("Los vértices " + startData + " o " + endData + " no existen");
        }

        Map<Vertex<E>, Integer> distances = new HashMap<>();

        for (Node<Vertex<E>> vertexNode : listVertex.getIterable()) {
            distances.put(vertexNode.getData(), Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        PriorityQueue<Vertex<E>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        priorityQueue.add(startVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex<E> currentVertex = priorityQueue.poll();

            if (currentVertex.equals(endVertex)) {
                break;  // Salir si ya hemos alcanzado el vértice de destino
            }

            for (Node<Edge<E>> edgeNode : currentVertex.listAdj.getIterable()) {
                Edge<E> edge = edgeNode.getData();
                Vertex<E> neighbor = edge.refdest;
                int newDistance = distances.get(currentVertex) + edge.getWeight();

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.add(neighbor);
                }
            }
        }

        return distances.get(endVertex);
    }

    public boolean isIncludedIn(GraphLink<E> otherGraph) {
        ListLinked<Vertex<E>> thisVertices = this.getListVertex();

        for (Node<Vertex<E>> thisVertexNode : thisVertices.getIterable()) {
            Vertex<E> thisVertex = thisVertexNode.getData();

            if (!otherGraph.searchVertex(thisVertex.getData())) {
                return false;
            }

            List<Edge<E>> thisEdges = thisVertex.listAdj.toList();
            List<Edge<E>> otherEdges = otherGraph.buscarElemento(thisVertex.getData()).listAdj.toList();

            for (Edge<E> thisEdge : thisEdges) {
                if (!otherEdges.contains(thisEdge)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return listVertex.toString();
    }
}
