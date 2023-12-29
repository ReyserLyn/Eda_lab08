
public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {

    protected E data;
    protected ListLinked<Edge<E>> listAdj;
    boolean visited;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<>();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        String adjListString = this.listAdj.toString();
        if (adjListString.length() > 4) {
            adjListString = adjListString.substring(0, adjListString.length() - 4);
        }
        return this.data + " -->\t " + adjListString + "\n";
    }

    @Override
    public int compareTo(Vertex<E> x) {
        return this.data.compareTo(x.data);
    }

    public ListLinked<Edge<E>> getListAdj() {
        return listAdj;
    }
}
