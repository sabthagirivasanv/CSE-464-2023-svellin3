package com.svellin3.graphSearcherAlgo.impl;

import com.svellin3.graphSearcherAlgo.GraphSearcherAlgo;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;

import java.util.*;
import java.util.stream.Collectors;

public class DFSGraphSearcherAlgo extends GraphSearcherAlgo {

    private Stack<String> stack;

    public DFSGraphSearcherAlgo(Graph graph, Node src, Node dst) {
        super(graph, src, dst);
    }

    @Override
    protected void setupNextNode(String currentNode, String nextNode) {
        if(!visited.contains(nextNode)){
            stack.push(nextNode);
            nodeToParentNodeMap.put(nextNode, currentNode);
        }
    }

    @Override
    protected List<Node> getPossibleNextNodes(String currentNode) {
        List<Node> possibleDestinations = edgeMap.getOrDefault(currentNode, new LinkedList<>());
        possibleDestinations = possibleDestinations.stream()
                .sorted(Comparator.comparing(Node::getName))
                .collect(Collectors.toList());
        Collections.reverse(possibleDestinations);
        return possibleDestinations;
    }

    @Override
    protected String getNext() {
        return stack.pop();
    }

    @Override
    protected boolean isNext() {
        return !stack.isEmpty();
    }

    @Override
    protected void setupSearch() {
        super.setupSearch();
        stack = new Stack<>();
        stack.push(src.getName());
    }
}
