package GraphTraversals;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

public class GraphAlgorithmsTest {
    @Test
    public void testBfs() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex<>(i));
        }
        Set<Edge<Integer>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), 1));
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(2), 1));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 1));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 1));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 1));
        Graph<Integer> graph = new Graph<>(vertices, edges);
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0), new Vertex<>(1), new Vertex<>(2), new Vertex<>(3), new Vertex<>(4),
                new Vertex<>(5)), bfsResult);
    }

    @Test
    public void testBfsEmptyGraph() {
        Graph<Integer> graph = new Graph<>(new HashSet<>(), new HashSet<>());
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0)), bfsResult);
    }

    @Test
    public void testBfsSingleVertexGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        vertices.add(new Vertex<>(0));
        Graph<Integer> graph = new Graph<>(vertices, new HashSet<>());
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0)), bfsResult);
    }

    @Test
    public void testBfsVertexConnectedToItself() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            vertices.add(new Vertex<>(i));
        }
        Set<Edge<Integer>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), 1));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 1));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(3), 1));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(4), 1));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(4), 1)); // vertex 4 is connected to itself
        Graph<Integer> graph = new Graph<>(vertices, edges);
        List<Vertex<Integer>> bfsResult = GraphAlgorithms.bfs(new Vertex<>(0), graph);
        Assert.assertEquals(
                List.of(new Vertex<>(0), new Vertex<>(1), new Vertex<>(2), new Vertex<>(3), new Vertex<>(4)),
                bfsResult);
    }

    @Test
    public void testDfs() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex<>(i));
        }
        Set<Edge<Integer>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), 1));
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(2), 1));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(3), 1));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(4), 1));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(5), 1));
        Graph<Integer> graph = new Graph<>(vertices, edges);
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0), new Vertex<>(1), new Vertex<>(3), new Vertex<>(5), new Vertex<>(2),
                new Vertex<>(4)), dfsResult);
    }

    @Test
    public void testDfsEmptyGraph() {
        Graph<Integer> graph = new Graph<>(new HashSet<>(), new HashSet<>());
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0)), dfsResult);
    }

    @Test
    public void testDfsSingleVertexGraph() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        vertices.add(new Vertex<>(0));
        Graph<Integer> graph = new Graph<>(vertices, new HashSet<>());
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0)), dfsResult);
    }

    @Test
    public void circularReferencesDFSSuccess() {
        Set<Vertex<Integer>> vertices = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            vertices.add(new Vertex<>(i));
        }
        Set<Edge<Integer>> edges = new HashSet<>();
        edges.add(new Edge<>(new Vertex<>(0), new Vertex<>(1), 1));
        edges.add(new Edge<>(new Vertex<>(1), new Vertex<>(2), 1));
        edges.add(new Edge<>(new Vertex<>(2), new Vertex<>(3), 1));
        edges.add(new Edge<>(new Vertex<>(3), new Vertex<>(4), 1));
        edges.add(new Edge<>(new Vertex<>(4), new Vertex<>(4), 1)); // vertex 4 is connected to itself
        Graph<Integer> graph = new Graph<>(vertices, edges);
        List<Vertex<Integer>> dfsResult = GraphAlgorithms.dfs(new Vertex<>(0), graph);
        Assert.assertEquals(List.of(new Vertex<>(0), new Vertex<>(1), new Vertex<>(2), new Vertex<>(3), new Vertex<>(4)),
                dfsResult);
    }
}
