package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    public static final String TEST_NODE_NAME = "abc";

    @Test
    public void testGetName() {
        Node node = new Node(TEST_NODE_NAME);
        Assert.assertEquals(TEST_NODE_NAME, node.getName());
    }

    @Test
    public void testToString() {
        Node node = new Node(TEST_NODE_NAME);
        Assert.assertEquals(TEST_NODE_NAME, node.toString());
    }
}