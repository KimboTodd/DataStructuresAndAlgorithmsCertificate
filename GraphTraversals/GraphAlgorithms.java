package GraphTraversals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     * 
     * Should use three data structures, queue
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        var vertices = new ArrayList<Vertex<T>>();
        var verticesRemaining = new LinkedList<Vertex<T>>();
        var visitedSet = new HashSet<Vertex<T>>();
        var adjList = graph.getAdjList();

        vertices.add(start);
        visitedSet.add(start);
        verticesRemaining.add(start);

        while (verticesRemaining.size() > 0) {
            var current = verticesRemaining.pop();

            var values = adjList.get(current);
            if (values != null) {
                for (VertexDistance<T> vertexDistance : values) {
                    var currentVertex = vertexDistance.getVertex();

                    if (!visitedSet.contains(currentVertex)) {
                        vertices.add(currentVertex);
                        visitedSet.add(currentVertex);
                        verticesRemaining.add(currentVertex);
                    }
                }
            }
        }

        return vertices;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        var verticesTraversed = new ArrayList<Vertex<T>>();
        var visitedSet = new HashSet<Vertex<T>>();
        var verticesRemaining = new LinkedList<Vertex<T>>();
        var adjacencies = graph.getAdjList();

        verticesTraversed.add(start);
        visitedSet.add(start);

        depthFirst(start, adjacencies, verticesTraversed, verticesRemaining, visitedSet);

        return verticesTraversed;
    }

    private static <T> void depthFirst(
            Vertex<T> current,
            Map<Vertex<T>, List<VertexDistance<T>>> adjacencies,
            List<Vertex<T>> verticesTraversed,
            LinkedList<Vertex<T>> verticesRemaining,
            HashSet<Vertex<T>> visitedSet) {
        var values = adjacencies.get(current);
        if (values != null) {
            for (VertexDistance<T> vertexDistance : values) {
                var currentVertex = vertexDistance.getVertex();
                if (!visitedSet.contains(currentVertex)) {
                    verticesTraversed.add(currentVertex);
                    visitedSet.add(currentVertex);
                    depthFirst(currentVertex, adjacencies, verticesTraversed, verticesRemaining, visitedSet);
                }
            }
        }
    }
}