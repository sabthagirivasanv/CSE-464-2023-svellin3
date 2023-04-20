package com.svellin3.graphSearcher.strategy.factory;

import com.svellin3.Algorithm;
import com.svellin3.graphSearcher.strategy.GraphSearcherStrategy;
import com.svellin3.graphSearcher.strategy.impl.BFSGraphSearcherStrategy;
import com.svellin3.graphSearcher.strategy.impl.DFSGraphSearcherStrategy;

import java.util.HashMap;
import java.util.Map;

public class GraphSearcherStrategyFactory {
    private static final Map<Algorithm, GraphSearcherStrategy> strategyBag = new HashMap<>();

    static {
        strategyBag.put(Algorithm.DFS, new DFSGraphSearcherStrategy());
        strategyBag.put(Algorithm.BFS, new BFSGraphSearcherStrategy());
    }
    public static GraphSearcherStrategy getStrategy(Algorithm algorithm){
        return strategyBag.get(algorithm);
    }
}
