
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListLinked<E extends Comparable<E>> {

    private Node<E> head;
    private int count;

    public ListLinked() {
        this.head = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int length() {
        return this.count;
    }

    public boolean search(E x) {
        Node<E> aux = this.head;
        while (aux != null) {
            if (aux.getData().equals(x)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public Node<E> getNodeAtIndex(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        Node<E> aux = this.head;
        int currentIndex = 0;

        while (currentIndex < index && aux != null) {
            aux = aux.getNext();
            currentIndex++;
        }

        return aux;
    }

    public E searchData(E x) {
        Node<E> aux = this.head;
        while (aux != null) {
            if (aux.getData().compareTo(x) == 0) {
                return aux.getData();
            }
            aux = aux.getNext();
        }
        return null;
    }

    public void insertFirst(E x) {
        this.head = new Node<>(x, this.head);
        this.count++;
    }

    public void insertLast(E x) {
        if (this.isEmpty()) {
            this.insertFirst(x);
        } else {
            Node<E> aux = this.head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(new Node<>(x));
            this.count++;
        }
    }

    public void insertarOrdenado(E x) {
        if (this.isEmpty() || x.compareTo(this.head.getData()) < 0) {
            this.insertFirst(x);
        } else {
            Node<E> current = this.head;
            Node<E> prev = null;

            while (current != null && x.compareTo(current.getData()) >= 0) {
                prev = current;
                current = current.getNext();
            }

            prev.setNext(new Node<>(x, current));
            this.count++;
        }
    }

    public void remove(E x) {
        if (!isEmpty()) {
            if (this.head.getData().equals(x)) {
                this.head = this.head.getNext();
                this.count--;
            } else {
                Node<E> aux = this.head;
                Node<E> prev = null;
                while (aux != null && !aux.getData().equals(x)) {
                    prev = aux;
                    aux = aux.getNext();
                }
                if (aux != null) {
                    prev.setNext(aux.getNext());
                    this.count--;
                }
            }
        }
    }

    public Node<E> getHead() {
        return this.head;
    }

    public Iterable<Node<E>> getIterable() {
        return () -> new Iterator<Node<E>>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node<E> next() {
                Node<E> temp = current;
                current = current.getNext();
                return temp;
            }
        };
    }

    public List<E> toList() {
        List<E> list = new ArrayList<>();
        Node<E> current = head;
        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<E> aux = this.head;
        while (aux != null) {
            str.append(aux.toString());
            aux = aux.getNext();
        }
        return str.toString();
    }
}
