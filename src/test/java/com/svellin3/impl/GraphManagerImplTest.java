package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class GraphManagerImplTest {

    private static GraphManagerImpl graphManager;
    private static String TEST_FILE_NAME = "testInput.dot";

    @Before
    public void setUp() throws Exception {
        graphManager = new GraphManagerImpl();
        graphManager.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void nodeSizeTest(){
        Assert.assertEquals(4, graphManager.nodeSize());
        graphManager.addNode("e");
        Assert.assertEquals(5, graphManager.nodeSize());
    }

    @Test
    public void edgeSizeTest(){
        Assert.assertEquals(4, graphManager.edgeSize());
        graphManager.addEdge("a","d");
        Assert.assertEquals(5, graphManager.edgeSize());
    }

    @Test
    public void containsNodeTest(){
        Assert.assertTrue(graphManager.containsNode("a"));
        Assert.assertFalse(graphManager.containsNode("x"));
    }

    @Test
    public void containsEdgeTest(){
        Assert.assertFalse(graphManager.containsEdge("a", "d"));
        Assert.assertTrue(graphManager.containsEdge("d","a"));
    }
    @Test
    public void getExtensionTest(){
        Optional<String> ext = graphManager.getExtension("output.txt");
        Assert.assertTrue(ext.isPresent());
        Optional<String> noExt = graphManager.getExtension("output");
        Assert.assertFalse(noExt.isPresent());
    }
}