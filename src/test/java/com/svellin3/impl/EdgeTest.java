package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    private static final String SOURCE_NODE_NAME = "SOURCE";
    private static final String DESTINATION_NODE_NAME = "DESTINATION";
    private static final String KEY_SPLITTER = "->";

    private static Edge edge;
    @Before
    public void setup(){
        edge = new Edge(new Node(SOURCE_NODE_NAME), new Node(DESTINATION_NODE_NAME));
    }
    @Test
    public void testGetSource() {
        Assert.assertNotNull(edge.getSource());
        Assert.assertEquals(SOURCE_NODE_NAME,edge.getSource().getName());
    }

    @Test
    public void testGetDestination() {
        Assert.assertNotNull(edge.getDestination());
        Assert.assertEquals(DESTINATION_NODE_NAME,edge.getDestination().getName());
    }

    @Test
    public void testEdgeString() {
        Assert.assertEquals(SOURCE_NODE_NAME+KEY_SPLITTER+DESTINATION_NODE_NAME,
                Edge.edgeString(SOURCE_NODE_NAME, DESTINATION_NODE_NAME));
        Assert.assertEquals(SOURCE_NODE_NAME+KEY_SPLITTER+DESTINATION_NODE_NAME,
                Edge.edgeString(new Node(SOURCE_NODE_NAME), new Node(DESTINATION_NODE_NAME)));
    }

    @Test
    public void testToString() {
        Assert.assertEquals(SOURCE_NODE_NAME
                        + " "+ KEY_SPLITTER + " "
                        + DESTINATION_NODE_NAME,
                edge.toString());
    }
}