package com.svellin3.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathTest {

    private static Path path;
    @Before
    public void setUp() throws Exception {
        path = new Path();
    }

    @Test
    public void addNodeToThePath() {
        Assert.assertEquals("", path.toString());
        path.addNodeToThePath(new Node("a"));
        path.addNodeToThePath(new Node("b"));
        path.addNodeToThePath(new Node("c"));
        Assert.assertEquals("a -> b -> c", path.toString());
    }
}