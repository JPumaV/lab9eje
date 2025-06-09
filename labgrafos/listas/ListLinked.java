package listas;

public class ListLinked<E> {
    private Node<E> head;

    public ListLinked() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
    }

    public boolean remove(E data) {
        Node<E> current = head;
        Node<E> prev = null;

        while (current != null) {
            if (current.getData().equals(data)) {
                if (prev == null)
                    head = current.getNext();
                else
                    prev.setNext(current.getNext());
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }

    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(data))
                return true;
            current = current.getNext();
        }
        return false;
    }

    public Node<E> getHead() {
        return head;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getData().toString()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }
}