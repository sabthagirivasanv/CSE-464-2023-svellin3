package com.svellin3.graphSearcher.strategy;

import com.svellin3.Algorithm;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

public abstract class GraphSearcherStrategy {

    protected Algorithm algorithmName;

    public abstract Path search(Graph graph, Node source, Node destination);

    public Algorithm getAlgorithmName() {
        return algorithmName;
    }
}
