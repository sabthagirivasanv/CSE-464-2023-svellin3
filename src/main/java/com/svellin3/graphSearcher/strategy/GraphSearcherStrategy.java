package com.svellin3.graphSearcher.strategy;

import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

public interface GraphSearcherStrategy {
    abstract Path search(Graph graph, Node source, Node destination);
}
