package com.svellin3;

import com.svellin3.impl.GraphManagerImpl;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomWalkGraphSearchTest {
    private static GraphManager graphManager;
    private static final String TEST_FILE_NAME = "random-walk-input.dot";
    @Before
    public void setUp() throws Exception {
        graphManager = new GraphManagerImpl();
        graphManager.parseGraph(TEST_FILE_NAME);
    }

    @Test
    public void testRandomWalkGraphSearch() {
        System.out.println("random testing");
        Path path = graphManager.GraphSearch(new Node("a"), new Node("c"), Algorithm.RANDOM_WALK);
        Assert.assertEquals("a -> b -> c", path.toString());
    }
}
