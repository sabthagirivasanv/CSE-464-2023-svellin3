package com.svellin3.graphSearcher.templatePattern.impl;

import com.svellin3.graphSearcher.templatePattern.GraphSearcher;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;

import java.util.*;
import java.util.stream.Collectors;

public class DFSGraphSearcher extends GraphSearcher {

    private Stack<String> stack;

    public DFSGraphSearcher(Graph graph, Node src, Node dst) {
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
        nodeToParentNodeMap = new HashMap<>();
        nodeToParentNodeMap.put(src.getName(), null);

        edgeMap = getEdgeMap();
        visited = new HashSet<>();
        stack = new Stack<>();
        stack.push(src.getName());
    }
}
