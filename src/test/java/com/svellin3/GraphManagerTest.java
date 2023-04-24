package com.svellin3;


import com.svellin3.impl.GraphManagerImpl;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphManagerTest {

    public static final String EXPECTED_TO_STRING = "digraph {\n" +
            "\"a\" -> \"b\"\n" +
            "\"b\" -> \"c\"\n" +
            "\"c\" -> \"d\"\n" +
            "\"d\" -> \"a\"\n" +
            "}";
    public static final String TEST_OUTPUT_GRAPH_TXT_WITHOUT_EXTENSION = "testOutputGraph";

    public static final String TEST_OUTPUT_GRAPH_TXT_FILE = TEST_OUTPUT_GRAPH_TXT_WITHOUT_EXTENSION+".txt";
    public static final String EXPECTED_OUTPUT_GRAPH_TXT_FILE = "ExpectedOutputGraph.txt";
    public static final String TEST_OUTPUT_DOT_GRAPH_FILE = "testOutputDOTGraph.dot";
    private static GraphManager graphManager;
    private static final String TEST_FILE_NAME = "testInput.dot";

    private static final String EXPECTED_OUTPUT_DOT_GRAPH_FILE = "expected.txt";

    @Before
    public void setUp() throws Exception {
        graphManager = new GraphManagerImpl();
        graphManager.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, graphManager.nodeSize());
        Assert.assertEquals(4, graphManager.edgeSize());

        Assert.assertTrue(graphManager.containsEdge("c", "d"));
        Assert.assertTrue(graphManager.containsEdge("a", "b"));
        Assert.assertTrue(graphManager.containsEdge("d", "a"));
        Assert.assertTrue(graphManager.containsEdge("b", "c"));

        Assert.assertFalse(graphManager.containsNode("e"));
        Assert.assertFalse(graphManager.containsEdge("a","d"));
    }

    @Test
    public void testToString() {
        String actual = graphManager.toString();
        Assert.assertEquals(EXPECTED_TO_STRING, actual);
    }

    @Test
    public void testOutputGraph() throws IOException {
        graphManager.outputGraph(TEST_OUTPUT_GRAPH_TXT_WITHOUT_EXTENSION);
        List<String> actualLines = Files.readAllLines(Paths.get(TEST_OUTPUT_GRAPH_TXT_FILE));
        List<String> expectedLines = Files.readAllLines(Paths.get(EXPECTED_OUTPUT_GRAPH_TXT_FILE));
        Assert.assertEquals(expectedLines, actualLines);
    }


    @Test
    public void testAddNode(){
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNode("a");
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNode(" a");
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNode("e");
        Assert.assertEquals(5, graphManager.nodeSize());
    }


    @Test
    public void testAddNodes(){
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNodes(new String[]{"a","e","f"});
        Assert.assertEquals(6, graphManager.nodeSize());
        graphManager.addNodes(" g","h "," d ");
        Assert.assertEquals(8, graphManager.nodeSize());
    }

    @Test
    public void testRemoveNode(){
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.removeNode("f");
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.removeNode(" a");
        Assert.assertEquals(3, graphManager.nodeSize());
        Assert.assertEquals(2, graphManager.edgeSize());
    }

    @Test
    public void testRemoveNodes(){
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.removeNodes(new String[]{"a","e","f"});
        Assert.assertEquals(3, graphManager.nodeSize());
        graphManager.removeNodes(" g","h "," d ");
        Assert.assertEquals(2, graphManager.nodeSize());
    }

    @Test
    public void testAddEdge(){
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.addEdge("a", "d");
        Assert.assertEquals(5, graphManager.edgeSize());
        graphManager.addEdge(" a", "e ");
        Assert.assertTrue(graphManager.containsNode("e"));
        Assert.assertEquals(5, graphManager.nodeSize());
        Assert.assertEquals(6, graphManager.edgeSize());

        graphManager.addEdge("d","a");
        Assert.assertEquals(6, graphManager.edgeSize());
    }

    @Test
    public void testRemoveEdge(){
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.removeEdge("a", "d");
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.removeEdge(" d  ", "a");
        Assert.assertEquals(3, graphManager.edgeSize());
    }

    @Test
    public void testOutputDOTGraph() throws IOException {
        graphManager.addEdge("e", "f");
        graphManager.outputDOTGraph(TEST_OUTPUT_DOT_GRAPH_FILE);
        List<String> output = Files.readAllLines(Paths.get(TEST_OUTPUT_DOT_GRAPH_FILE));
        List<String> expected = Files.readAllLines(Paths.get(EXPECTED_OUTPUT_DOT_GRAPH_FILE));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void testDFSGraphSearch() {
        Path path = graphManager.GraphSearch(new Node("a"), new Node("d"), Algorithm.DFS);
        Assert.assertEquals("a -> b -> c -> d", path.toString());

        path = graphManager.GraphSearch(new Node("a"), new Node("a"), Algorithm.DFS);
        Assert.assertEquals("a", path.toString());


        path = graphManager.GraphSearch(new Node("b"), new Node("a"), Algorithm.DFS);
        Assert.assertEquals("b -> c -> d -> a", path.toString());

        graphManager.addNodes("e","f","g", "h", "i");
        graphManager.addEdge("d","e");
        graphManager.addEdge("d","f");
        graphManager.addEdge("d","g");
        graphManager.addEdge("d","h");
        graphManager.addEdge("g","h");
        graphManager.addEdge("h","i");

        path = graphManager.GraphSearch(new Node("a"), new Node("i"), Algorithm.DFS);
        Assert.assertEquals("a -> b -> c -> d -> g -> h -> i", path.toString());
    }

    @Test
    public void testDFSGraphSearchNullCases(){

        graphManager.addNode("f");
        Path path = graphManager.GraphSearch(new Node("a"), new Node("f"), Algorithm.DFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("x"), new Node("d"), Algorithm.DFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("a"), new Node("g"), Algorithm.DFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("x"), new Node("z"), Algorithm.DFS);
        Assert.assertNull(path);
    }

    @Test
    public void testBFSGraphSearch() {
        Path path = graphManager.GraphSearch(new Node("a"), new Node("d"), Algorithm.BFS);
        Assert.assertEquals("a -> b -> c -> d", path.toString());

        path = graphManager.GraphSearch(new Node("a"), new Node("a"), Algorithm.BFS);
        Assert.assertEquals("a", path.toString());


        path = graphManager.GraphSearch(new Node("b"), new Node("a"), Algorithm.BFS);
        Assert.assertEquals("b -> c -> d -> a", path.toString());

        graphManager.addNodes("e","f","g");
        graphManager.addEdge("d","e");
        graphManager.addEdge("e","f");
        graphManager.addEdge("d","f");
        graphManager.addEdge("f","g");

        path = graphManager.GraphSearch(new Node("a"), new Node("g"), Algorithm.BFS);
        Assert.assertEquals("a -> b -> c -> d -> f -> g", path.toString());
    }

    @Test
    public void testBFSGraphSearchNullCases(){

        graphManager.addNode("f");
        Path path = graphManager.GraphSearch(new Node("a"), new Node("f"), Algorithm.BFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("x"), new Node("d"), Algorithm.BFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("a"), new Node("g"), Algorithm.BFS);
        Assert.assertNull(path);

        path = graphManager.GraphSearch(new Node("x"), new Node("z"), Algorithm.BFS);
        Assert.assertNull(path);
    }
}