package com.svellin3.graphSearcher.templatePattern.impl;

import com.svellin3.graphSearcher.templatePattern.GraphSearcher;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

import java.util.*;

public class BFSGraphSearcher extends GraphSearcher {

    private Queue<String> queue;

    public BFSGraphSearcher(Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        queue.add(nextNode);
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
        return queue.poll();
    }

    @Override
    protected boolean isNext() {
        return !queue.isEmpty();
    }

    @Override
    protected void setupSearch() {
        nodeToParentNodeMap = new HashMap<>();
        nodeToParentNodeMap.put(src.getName(), null);
        edgeMap = getEdgeMap();
        visited = new HashSet<>();
        queue = new LinkedList<>();
        queue.add(src.getName());
    }


}
