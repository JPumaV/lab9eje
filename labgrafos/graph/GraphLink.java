package graph;

import listas.*;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    private Vertex<E> getVertex(E data) {
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            if (current.getData().getData().equals(data))
                return current.getData();
            current = current.getNext();
        }
        return null;
    }

    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    public void insertEdge(E ori, E des) {
        Vertex<E> v1 = getVertex(ori);
        Vertex<E> v2 = getVertex(des);

        if (v1 != null && v2 != null && !v1.listAdj.contains(new Edge<>(v2))) {
            v1.listAdj.add(new Edge<>(v2));
            v2.listAdj.add(new Edge<>(v1)); // No dirigido
        }
    }

    public boolean searchEdge(E ori, E des) {
        Vertex<E> v1 = getVertex(ori);
        Vertex<E> v2 = getVertex(des);

        if (v1 != null && v2 != null) {
            return v1.listAdj.contains(new Edge<>(v2));
        }
        return false;
    }

    public void removeEdge(E ori, E des) {
        Vertex<E> v1 = getVertex(ori);
        Vertex<E> v2 = getVertex(des);

        if (v1 != null && v2 != null) {
            v1.listAdj.remove(new Edge<>(v2));
            v2.listAdj.remove(new Edge<>(v1));
        }
    }

    public void removeVertex(E data) {
        Vertex<E> v = getVertex(data);
        if (v != null) {
            Node<Vertex<E>> current = listVertex.getHead();
            while (current != null) {
                current.getData().listAdj.remove(new Edge<>(v));
                current = current.getNext();
            }
            listVertex.remove(v);
        }
    }

    public void dfs(E startData) {
        Vertex<E> start = getVertex(startData);
        if (start == null)
            return;

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(Vertex<E> v, ListLinked<Vertex<E>> visited) {
        System.out.print(v.getData() + " ");
        visited.add(v);

        Node<Edge<E>> current = v.listAdj.getHead();
        while (current != null) {
            Vertex<E> neighbor = current.getData().getRefDest();
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
            current = current.getNext();
        }
    }

    public void bfs(E startData) {
        Vertex<E> start = getVertex(startData);
        if (start == null)
            return;

        ListLinked<Vertex<E>> visited = new ListLinked<>();
        java.util.Queue<Vertex<E>> queue = new java.util.LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> v = queue.poll();
            System.out.print(v.getData() + " ");

            Node<Edge<E>> current = v.listAdj.getHead();
            while (current != null) {
                Vertex<E> neighbor = current.getData().getRefDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
                current = current.getNext();
            }
        }
    }

    public String toString() {
        return listVertex.toString();
    }
}