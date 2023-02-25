package com.svellin3;

import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private String name;
    private Map<String, Node> nodes;
    private Map<String, Edge> edges;

    public Graph(MutableGraph mutableGraph) {
        nodes = new HashMap<>();
        edges = new HashMap<>();
        this.name = mutableGraph.name().toString();
        for (MutableNode each : mutableGraph.nodes()) {
            String nodeName = each.name().toString();
            nodes.putIfAbsent(nodeName, new Node(nodeName));
        }

        for (Link edge : mutableGraph.edges()) {
            Node src = nodes.get(edge.from().name().toString());
            Node dst = nodes.get(edge.to().name().toString());
            edges.putIfAbsent(Edge.edgeString(src, dst), new Edge(src, dst));
        }
    }

    public int nodeSize(){
        return nodes.keySet().size();
    }

    public boolean containsNode(String nodeName) {
        return nodes.containsKey(nodeName);
    }

    public List<String> getAllNodes() {
        return nodes.values().stream().map(Node::getName).collect(Collectors.toList());
    }

    public int edgeSize() {
        return edges.keySet().size();
    }
}