package com.svellin3.graphSearcher;

import com.svellin3.Algorithm;
import com.svellin3.graphSearcher.strategy.GraphSearcherStrategy;
import com.svellin3.graphSearcher.strategy.impl.BFSGraphSearcherStrategy;
import com.svellin3.graphSearcher.strategy.impl.DFSGraphSearcherStrategy;
import com.svellin3.graphSearcherAlgo.impl.DFSGraphSearcherAlgo;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphSearcher {

    private final GraphSearcherStrategy strategy;

    public GraphSearcher(GraphSearcherStrategy strategy) {
        this.strategy = strategy;
    }

    public Path search(Graph graph, Node source, Node destination){
        return strategy.search(graph, source, destination);
    }
}
