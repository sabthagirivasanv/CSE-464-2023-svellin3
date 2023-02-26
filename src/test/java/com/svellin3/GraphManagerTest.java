package com.svellin3;


import com.svellin3.impl.GraphManagerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphManagerTest {

    private static GraphManager g;
    private static String TEST_FILE_NAME = "testInput.dot";

    @Before
    public void setUp() throws Exception {
        g = new GraphManagerImpl();
        g.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void nodeSizeTest(){
        Assert.assertEquals(4, g.nodeSize());
    }

    @Test
    public void containsNodeTest(){
        Assert.assertTrue(g.containsNode("a"));
        Assert.assertFalse(g.containsNode("x"));
    }
    @Test
    public void parseGraphTest() {
        Assert.assertEquals(4, g.nodeSize());
    }

}