package graph;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

public class GraphTests {

  private Graph<Integer> graphDirected, graphUndirected;
  private int v1, v2, v3, v4;

  @Before
  public void setUp() {
    v1 = 1;
    v2 = 2;
    v3 = 3;
    v4 = 4;

    graphDirected = new DirectedGraph<>();
    graphUndirected = new UndirectedGraph<>();
  }

  @Test
  public void testGraph_NumVertices() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    assertEquals(2, graphDirected.getNumVertices());
  }

  @Test
  public void testGraph_NumEdgesDirected() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, 3.0);
    assertEquals(1, graphDirected.getNumEdges());
  }

  @Test
  public void testGraph_NumEdgesUndirected() throws Exception {
    graphUndirected.addVertex(v1);
    graphUndirected.addVertex(v2);
    graphUndirected.addEdge(v1, v2, 3.0);
    assertEquals(1, graphUndirected.getNumEdges());
  }

  @Test(expected = GraphException.class)
  public void testGraph_AddVertexDuplicateSameObject() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v1);
  }

  @Test
  public void testGraph_GetEdgeFalse() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    assertNull(graphDirected.getEdge(v1, v2));
  }

  @Test(expected = GraphException.class)
  public void testGraph_AddEdgeNoVerticesDirected() throws Exception {
    graphDirected.addEdge(v1, v2, 3.0);
  }

  @Test
  public void testGraph_AddEdgeDirected() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, 3.0);
    assertTrue(graphDirected.edgeExists(v1, v2));
  }

  @Test(expected = GraphException.class)
  public void testGraph_AddEdgeAlreadyExistsDirected() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, 3.0);
    graphDirected.addEdge(v1, v2, 5.0);
  }

  @Test(expected = GraphException.class)
  public void testGraph_AddEdgeNoVerticesUndirected() throws Exception {
    graphUndirected.addEdge(v1, v2, 3.0);
  }

  @Test
  public void testGraph_AddEdgeUndirected() throws Exception {
    graphUndirected.addVertex(v1);
    graphUndirected.addVertex(v2);
    graphUndirected.addEdge(v1, v2, 3.0);
    assertTrue(graphUndirected.edgeExists(v1, v2));
    assertTrue(graphUndirected.edgeExists(v2, v1));
  }

  @Test(expected = GraphException.class)
  public void testGraph_AddEdgeAlreadyExistsUndirected() throws Exception {
    graphUndirected.addVertex(v1);
    graphUndirected.addVertex(v2);
    graphUndirected.addEdge(v1, v2, 3.0);
    graphUndirected.addEdge(v1, v2, 5.0);
  }

  @Test
  public void testGraph_GetWeightDirected() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addVertex(v3);
    graphDirected.addEdge(v1, v2, 5.0);
    graphDirected.addEdge(v2, v1, 5.0);
    graphDirected.addEdge(v1, v3, 10.0);
    assertEquals(20.0, graphDirected.getWeight(), 0.001);
  }

  @Test
  public void testGraph_GetWeightUndirected() throws Exception {
    graphUndirected.addVertex(v1);
    graphUndirected.addVertex(v2);
    graphUndirected.addVertex(v3);
    graphUndirected.addEdge(v1, v2, 5.0);
    graphUndirected.addEdge(v1, v3, 20.0);
    assertEquals(25.0, graphUndirected.getWeight(), 0.001);
  }

  @Test
  public void testGraph_getEdgeWeight() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, 3.0);
    assertEquals(3.0, graphDirected.getEdgeWeight(v1, v2), 0.001);
  }

  @Test
  public void testGraph_NotWeighedEdge() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, null);
    assertNull(graphDirected.getEdgeWeight(v1, v2));
  }

  @Test(expected = GraphException.class)
  public void testGraph_NotWeighedGraph() throws Exception {
    graphDirected.addVertex(v1);
    graphDirected.addVertex(v2);
    graphDirected.addEdge(v1, v2, null);
    assertNull(graphDirected.getWeight());
  }

}
