package com.svellin3.graphSearcherAlgo.impl;

import com.svellin3.graphSearcherAlgo.GraphSearcherAlgo;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;

import java.util.*;

public class RandomWalkGraphSearcherAlgo extends GraphSearcherAlgo {

    private List<String> nextNodes;

    public RandomWalkGraphSearcherAlgo(Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        nextNodes.add(nextNode);
        if (!nodeToParentNodeMap.containsKey(nextNode)){
            nodeToParentNodeMap.put(nextNode, currentNode);
        }
    }

    @Override
    protected List<Node> getPossibleNextNodes(String currentNode) {
        return edgeMap.getOrDefault(currentNode, new LinkedList<>());
    }

    @Override
    protected String getNext() {
        Random random = new Random();
        int randomIndex = random.nextInt(nextNodes.size());
        String next = nextNodes.get(randomIndex);
        nextNodes.remove(next);
        return next;
    }

    @Override
    protected boolean isNext() {
        return !nextNodes.isEmpty();
    }

    @Override
    protected void setupSearch() {
        super.setupSearch();
        nextNodes = new LinkedList<>();
        nextNodes.add(src.getName());
    }
}
