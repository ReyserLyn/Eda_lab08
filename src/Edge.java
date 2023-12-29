
public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> {

    protected Vertex<E> refdest;
    protected int weight;

    public Edge(Vertex<E> refdest, int weight) {
        this.refdest = refdest;
        this.weight = weight;
    }

    public Edge(Vertex<E> refdest) {
        this(refdest, -1);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge<?> edge = (Edge<?>) o;
        return weight == edge.weight && refdest.equals(edge.refdest);
    }

    @Override
    public String toString() {
        if (this.weight != -1) {
            return this.refdest.data + "[" + this.weight + "]" + " -> ";
        } else {
            return this.refdest.data + " -> ";
        }
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.getWeight()) {
            return 1;
        } else if (this.weight == o.getWeight()) {
            return 0;
        }
        return -1;
    }
}
