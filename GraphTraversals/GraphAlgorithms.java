package GraphTraversals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;
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

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        var mSTedgeSet = new HashSet<Edge<T>>();
        var visitedSet = new HashSet<Vertex<T>>();
        var priorityQueue = new PriorityQueue<Edge<T>>();

        var validNumberEdges = (graph.getVertices().size() - 1) * 2;
        Set<Edge<T>> edges = graph.getEdges();

        for (Edge<T> edge : edges) {
            // starting with starting vertex
            if (edge.getU().equals(start)) {
                priorityQueue.add(edge);
            }
        }
        visitedSet.add(start);

        while (!priorityQueue.isEmpty() && visitedSet.size() != validNumberEdges) {
            var poppedEdge = priorityQueue.poll();
            Vertex<T> destinationVertex = poppedEdge.getV();
            if (visitedSet.contains(destinationVertex) == false) {
                visitedSet.add(destinationVertex);
                mSTedgeSet.add(poppedEdge);
                // add inverse per java docs
                mSTedgeSet.add(new Edge<T>(poppedEdge.getV(), poppedEdge.getU(), poppedEdge.getWeight()));

                // loop through edges
                for (Edge<T> childEdge : edges) {
                    // loop through the edges looking for a starting edge that matches currentEdge
                    if (childEdge.getU().equals(destinationVertex)) {
                        // if we find one, enqueue it only if the destination vertex hasn't been visited
                        if (!visitedSet.contains(childEdge.getV())) {
                            priorityQueue.add(childEdge);
                        }
                    }
                }
            }
        }

        if (mSTedgeSet.size() != validNumberEdges) {
            return null;
        }

        return mSTedgeSet;
    }
}