package com.svellin3.graphSearcher.strategy.impl;

import com.svellin3.Algorithm;
import com.svellin3.graphSearcher.strategy.GraphSearcherStrategy;
import com.svellin3.graphSearcherAlgo.GraphSearcherAlgo;
import com.svellin3.graphSearcherAlgo.impl.DFSGraphSearcherAlgo;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

public class DFSGraphSearcherStrategy extends GraphSearcherStrategy {

    public DFSGraphSearcherStrategy() {
        super(Algorithm.DFS);
    }
    @Override
    public Path search(Graph graph, Node source, Node destination) {
        GraphSearcherAlgo algo = new DFSGraphSearcherAlgo(graph, source, destination);
        return algo.search();
    }
}
