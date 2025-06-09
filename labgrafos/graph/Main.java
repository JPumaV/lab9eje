package graph;

import graph.GraphListEdge;
import graph.GraphLink;

public class Main {
    public static void main(String[] args) {
        // === PRUEBA DE GraphListEdge ===
        System.out.println("=== GRAFO CON LISTA DE ARISTAS (GraphListEdge) ===");

        GraphListEdge<String, Integer> gListEdge = new GraphListEdge<>();

        gListEdge.insertVertex("A");
        gListEdge.insertVertex("B");
        gListEdge.insertVertex("C");

        gListEdge.insertEdge("A", "B", 10);
        gListEdge.insertEdge("A", "C", 20);
        gListEdge.insertEdge("B", "C", 15);

        System.out.println(gListEdge);

        gListEdge.removeEdge("A", "C");
        gListEdge.removeVertex("B");

        System.out.println("\nDespués de eliminar arista A-C y vértice B:");
        System.out.println(gListEdge);


        // === PRUEBA DE GraphLink ===
        System.out.println("\n=== GRAFO CON LISTA DE ADYACENCIA (GraphLink) ===");

        GraphLink<String> gLink = new GraphLink<>();

        gLink.insertVertex("1");
        gLink.insertVertex("2");
        gLink.insertVertex("3");
        gLink.insertVertex("4");

        gLink.insertEdge("1", "2");
        gLink.insertEdge("1", "3");
        gLink.insertEdge("2", "4");

        System.out.println("\nDFS desde vértice 1:");
        gLink.dfs("1");

        System.out.println("\n\nBFS desde vértice 1:");
        gLink.bfs("1");

        System.out.println("\n\nEliminando arista 1-3 y vértice 2...");
        gLink.removeEdge("1", "3");
        gLink.removeVertex("2");

        System.out.println("\nDFS desde vértice 1 (actualizado):");
        gLink.dfs("1");

        System.out.println("\n\nRepresentación interna del grafo:");
        System.out.println(gLink);
    }
}