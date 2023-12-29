
public class SimpleStack<T> {

    private Node<T> top;

    public void push(T data) {
        top = new Node<>(data, top);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
