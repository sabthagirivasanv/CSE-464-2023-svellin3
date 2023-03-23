package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathTest {

    private static Path path;
    @Before
    public void setUp() throws Exception {
        path = new Path();
    }

    @Test
    public void addNodeToThePath() {
        Assert.assertEquals("", path.toString());
        path.addNodeInTheEnd(new Node("a"));
        path.addNodeInTheEnd(new Node("c"));
        Assert.assertEquals("a -> c", path.toString());
        path.addNodeInTheFront(new Node("f"));
        Assert.assertEquals("f -> a -> c", path.toString());
        path.addNodeInTheEnd(new Node("b"));
        Assert.assertEquals("f -> a -> c -> b", path.toString());
    }
}