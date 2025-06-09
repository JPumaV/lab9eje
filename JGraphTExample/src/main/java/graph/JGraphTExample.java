package graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class JGraphTExample {
    public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.setEdgeWeight(graph.addEdge("A", "B"), 4);
        graph.setEdgeWeight(graph.addEdge("B", "C"), 3);

        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        var path = dijkstra.getPath("A", "C");

        if (path != null) {
            System.out.println("Camino más corto de A a C: " + path.getVertexList());
            System.out.println("Distancia total: " + path.getWeight());
        } else {
            System.out.println("No se encontró camino.");
        }
    }
}
