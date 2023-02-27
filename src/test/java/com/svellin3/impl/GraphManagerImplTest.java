package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class GraphManagerImplTest {

    private static GraphManagerImpl g;
    private static String TEST_FILE_NAME = "testInput.dot";

    @Before
    public void setUp() throws Exception {
        g = new GraphManagerImpl();
        g.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void nodeSizeTest(){
        Assert.assertEquals(4, g.nodeSize());
        g.addNode("e");
        Assert.assertEquals(5, g.nodeSize());
    }

    @Test
    public void edgeSizeTest(){
        Assert.assertEquals(4, g.edgeSize());
        g.addEdge("a","d");
        Assert.assertEquals(5,g.edgeSize());
    }

    @Test
    public void containsNodeTest(){
        Assert.assertTrue(g.containsNode("a"));
        Assert.assertFalse(g.containsNode("x"));
    }

    @Test
    public void containsEdgeTest(){
        Assert.assertFalse(g.containsEdge("a", "d"));
        Assert.assertTrue(g.containsEdge("d","a"));
    }
    @Test
    public void getExtensionTest(){
        Optional<String> ext = g.getExtension("output.txt");
        Assert.assertTrue(ext.isPresent());
        Optional<String> noExt = g.getExtension("output");
        Assert.assertFalse(noExt.isPresent());
    }
}