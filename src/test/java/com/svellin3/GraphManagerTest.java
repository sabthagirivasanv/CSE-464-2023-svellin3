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
    private static GraphManager g;
    private static final String TEST_FILE_NAME = "testInput.dot";

    private static final String EXPECTED_OUTPUT_DOT_GRAPH_FILE = "expected.txt";

    @Before
    public void setUp() throws Exception {
        g = new GraphManagerImpl();
        g.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void testParseGraph() {
        Assert.assertEquals(4, g.nodeSize());
        Assert.assertEquals(4, g.edgeSize());

        Assert.assertTrue(g.containsEdge("c", "d"));
        Assert.assertTrue(g.containsEdge("a", "b"));
        Assert.assertTrue(g.containsEdge("d", "a"));
        Assert.assertTrue(g.containsEdge("b", "c"));

        Assert.assertFalse(g.containsNode("e"));
        Assert.assertFalse(g.containsEdge("a","d"));
    }

    @Test
    public void testToString() {
        String actual = g.toString();
        Assert.assertEquals(EXPECTED_TO_STRING, actual);
    }

    @Test
    public void testOutputGraph() throws IOException {
        g.outputGraph(TEST_OUTPUT_GRAPH_TXT_WITHOUT_EXTENSION);
        List<String> actualLines = Files.readAllLines(Paths.get(TEST_OUTPUT_GRAPH_TXT_FILE));
        List<String> expectedLines = Files.readAllLines(Paths.get(EXPECTED_OUTPUT_GRAPH_TXT_FILE));
        Assert.assertEquals(expectedLines, actualLines);
    }


    @Test
    public void testAddNode(){
        Assert.assertEquals(4, g.nodeSize());
        g.addNode("a");
        Assert.assertEquals(4, g.nodeSize());
        g.addNode(" a");
        Assert.assertEquals(4, g.nodeSize());
        g.addNode("e");
        Assert.assertEquals(5, g.nodeSize());
    }


    @Test
    public void testAddNodes(){
        Assert.assertEquals(4, g.nodeSize());
        g.addNodes(new String[]{"a","e","f"});
        Assert.assertEquals(6, g.nodeSize());
        g.addNodes(" g","h "," d ");
        Assert.assertEquals(8, g.nodeSize());
    }

    @Test
    public void testRemoveNode(){
        Assert.assertEquals(4, g.nodeSize());
        g.removeNode("f");
        Assert.assertEquals(4, g.nodeSize());
        g.removeNode(" a");
        Assert.assertEquals(3, g.nodeSize());
        Assert.assertEquals(2, g.edgeSize());
    }

    @Test
    public void testRemoveNodes(){
        Assert.assertEquals(4, g.nodeSize());
        g.removeNodes(new String[]{"a","e","f"});
        Assert.assertEquals(3, g.nodeSize());
        g.removeNodes(" g","h "," d ");
        Assert.assertEquals(2, g.nodeSize());
    }

    @Test
    public void testAddEdge(){
        Assert.assertEquals(4, g.edgeSize());
        g.addEdge("a", "d");
        Assert.assertEquals(5, g.edgeSize());
        g.addEdge(" a", "e ");
        Assert.assertTrue(g.containsNode("e"));
        Assert.assertEquals(5, g.nodeSize());
        Assert.assertEquals(6, g.edgeSize());

        g.addEdge("d","a");
        Assert.assertEquals(6, g.edgeSize());
    }

    @Test
    public void testRemoveEdge(){
        Assert.assertEquals(4, g.edgeSize());
        g.removeEdge("a", "d");
        Assert.assertEquals(4, g.edgeSize());
        g.removeEdge(" d  ", "a");
        Assert.assertEquals(3, g.edgeSize());
    }

    @Test
    public void testOutputDOTGraph() throws IOException {
        g.addEdge("e", "f");
        g.outputDOTGraph(TEST_OUTPUT_DOT_GRAPH_FILE);
        List<String> output = Files.readAllLines(Paths.get(TEST_OUTPUT_DOT_GRAPH_FILE));
        List<String> expected = Files.readAllLines(Paths.get(EXPECTED_OUTPUT_DOT_GRAPH_FILE));
        Assert.assertEquals(expected, output);
    }

    @Test
    public void testGraphSearch() {
        Path path = g.GraphSearch(new Node("a"), new Node("d"));
        Assert.assertEquals("a -> b -> c -> d", path.toString());

        path = g.GraphSearch(new Node("a"), new Node("a"));
        Assert.assertEquals("a", path.toString());


        path = g.GraphSearch(new Node("b"), new Node("a"));
        Assert.assertEquals("b -> c -> d -> a", path.toString());

        g.addNodes("e","f","g");
        g.addEdge("d","e");
        g.addEdge("e","f");
        g.addEdge("d","f");
        g.addEdge("f","g");

        path = g.GraphSearch(new Node("a"), new Node("g"));
        Assert.assertEquals("a -> b -> c -> d -> f -> g", path.toString());
    }

    @Test
    public void testGraphSearchNullCases(){

        g.addNode("f");
        Path path = g.GraphSearch(new Node("a"), new Node("f"));
        Assert.assertNull(path);

        path = g.GraphSearch(new Node("x"), new Node("d"));
        Assert.assertNull(path);

        path = g.GraphSearch(new Node("a"), new Node("g"));
        Assert.assertNull(path);

        path = g.GraphSearch(new Node("x"), new Node("z"));
        Assert.assertNull(path);
    }
}