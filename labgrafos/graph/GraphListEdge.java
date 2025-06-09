package graph;

import java.util.ArrayList;

public class GraphListEdge<V, E> {

    public static class VertexObj<V, E> {
        protected V info;
        protected int position;

        public VertexObj(V info, int position) {
            this.info = info;
            this.position = position;
        }

        public V getInfo() {
            return info;
        }

        public int getPosition() {
            return position;
        }

        public String toString() {
            return info.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof VertexObj) {
                VertexObj<?, ?> other = (VertexObj<?, ?>) o;
                return this.info.equals(other.info);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return info.hashCode();
        }
    }

    public static class EdgeObj<V, E> {
        protected E info;
        protected VertexObj<V, E> endVertex1;
        protected VertexObj<V, E> endVertex2;
        protected int position;

        public EdgeObj(VertexObj<V, E> vert1, VertexObj<V, E> vert2, E info, int position) {
            this.endVertex1 = vert1;
            this.endVertex2 = vert2;
            this.info = info;
            this.position = position;
        }

        public VertexObj<V, E> getEndVertex1() {
            return endVertex1;
        }

        public VertexObj<V, E> getEndVertex2() {
            return endVertex2;
        }

        public E getInfo() {
            return info;
        }

        public String toString() {
            return "(" + endVertex1 + " - " + endVertex2 + ", " + info + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof EdgeObj) {
                EdgeObj<?, ?> e = (EdgeObj<?, ?>) o;
                return (e.endVertex1.equals(this.endVertex1) && e.endVertex2.equals(this.endVertex2)) ||
                       (e.endVertex1.equals(this.endVertex2) && e.endVertex2.equals(this.endVertex1));
            }
            return false;
        }

        @Override
        public int hashCode() {
            return endVertex1.hashCode() + endVertex2.hashCode();
        }
    }

    private ArrayList<VertexObj<V, E>> secVertex;
    private ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V info) {
        if (getVertex(info) == null) {
            secVertex.add(new VertexObj<>(info, secVertex.size()));
        }
    }

    public void insertEdge(V info1, V info2, E edgeInfo) {
        VertexObj<V, E> v1 = getVertex(info1);
        VertexObj<V, E> v2 = getVertex(info2);

        if (v1 != null && v2 != null && !existsEdge(v1, v2)) {
            secEdge.add(new EdgeObj<>(v1, v2, edgeInfo, secEdge.size()));
        }
    }

    public boolean existsEdge(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        for (EdgeObj<V, E> edge : secEdge) {
            if ((edge.getEndVertex1().equals(v1) && edge.getEndVertex2().equals(v2)) ||
                (edge.getEndVertex1().equals(v2) && edge.getEndVertex2().equals(v1))) {
                return true;
            }
        }
        return false;
    }

    public VertexObj<V, E> getVertex(V info) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(info)) {
                return v;
            }
        }
        return null;
    }

    public void removeVertex(V info) {
        VertexObj<V, E> v = getVertex(info);
        if (v != null) {
            secVertex.remove(v);
            secEdge.removeIf(edge -> edge.getEndVertex1().equals(v) || edge.getEndVertex2().equals(v));
        }
    }

    public void removeEdge(V info1, V info2) {
        VertexObj<V, E> v1 = getVertex(info1);
        VertexObj<V, E> v2 = getVertex(info2);
        if (v1 != null && v2 != null) {
            secEdge.removeIf(edge ->
                (edge.getEndVertex1().equals(v1) && edge.getEndVertex2().equals(v2)) ||
                (edge.getEndVertex1().equals(v2) && edge.getEndVertex2().equals(v1))
            );
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Vértices:\n");
        for (VertexObj<V, E> v : secVertex) {
            sb.append(v).append(" ");
        }

        sb.append("\nAristas:\n");
        for (EdgeObj<V, E> e : secEdge) {
            sb.append(e).append("\n");
        }

        return sb.toString();
    }
}