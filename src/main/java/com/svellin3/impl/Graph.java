package com.svellin3.impl;

import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.*;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

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
            createEdge(edge.from().name().toString(), edge.to().name().toString());
        }
    }

    private void createEdge(String srcLabel, String dstLabel) {
        Node src = nodes.getOrDefault(srcLabel, nodes.put(srcLabel, new Node(srcLabel)));
        Node dst = nodes.getOrDefault(dstLabel, nodes.put(dstLabel, new Node(dstLabel)));
        edges.putIfAbsent(Edge.edgeString(src, dst), new Edge(src, dst));
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

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Edge each : edges.values()){
            builder.append(each.toString()).append("\n");
        }
        return builder.toString();
    }

    public void addNode(String nodeName) {
        nodeName = nodeName.trim();
        if(nodes.containsKey(nodeName)){
            System.out.println("Node: "+nodeName+" already exists!!!");
        }else{
            nodes.put(nodeName, new Node(nodeName));
        }
    }

    public void addNodes(String... nodeNames) {
        for (String node : nodeNames) {
            addNode(node);
        }
    }

    public void removeNode(String nodeName){
        nodeName = nodeName.trim();
        if(nodes.remove(nodeName) != null){
            String finalNodeName = nodeName;
            List<String> edgesToBeRemoved = edges.values().stream()
                    .filter(edge -> edge.getSource().getName().equals(finalNodeName)
                            || edge.getDestination().getName().equals(finalNodeName))
                    .map(edge -> Edge.edgeString(edge.getSource(), edge.getDestination()))
                    .collect(Collectors.toList());
            edgesToBeRemoved.forEach(each -> edges.remove(each));
        }
    }

    public void removeNodes(String... nodeNames){
        for (String each : nodeNames) {
            removeNode(each);
        }
    }

    public void addEdge(String srcLabel, String dstLabel) {
        srcLabel = srcLabel.trim();
        dstLabel = dstLabel.trim();
        String edgeKey = Edge.edgeString(srcLabel, dstLabel);
        if(!edges.containsKey(edgeKey)){
            createEdge(srcLabel, dstLabel);
        }else{
            System.out.println("Edge already exist from "+srcLabel+" to "+dstLabel);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        srcLabel = srcLabel.trim();
        dstLabel = dstLabel.trim();
        String edgeKey = Edge.edgeString(srcLabel, dstLabel);
        if (edges.containsKey(edgeKey)) {
            edges.remove(edgeKey);
        } else {
            System.out.println("No such edge exist from " + srcLabel + " to " + dstLabel);

        }
    }

    public MutableGraph convertToGraphViz(){
        MutableGraph g = mutGraph(name).setDirected(true);
        nodes.values().forEach(each -> g.add(mutNode(each.getName())));
        edges.values().forEach(each -> g.add(mutNode(each.getSource().getName()).addLink(mutNode(each.getDestination().getName()))));
        return g;
    }

    public boolean containsEdge(String src, String dst) {
        return edges.containsKey(Edge.edgeString(src, dst));
    }

    public Path findPathUsingBFS(Node src, Node dst) {

        if (!validateNodes(src, dst)){
            return null;
        }

        Map<String, String> parentMap = new HashMap<>();
        Map<String, List<Node>> edgeMap = getEdgeMap();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        String currentParent = null;
        queue.add(src.getName());
        while (!queue.isEmpty()){
            String currentNode = queue.poll();
            if (!visited.contains(currentNode)){
                visited.add(currentNode);
                parentMap.put(currentNode, currentParent);
                currentParent = currentNode;

                if (currentNode.equals(dst.getName())){
                    break;
                }

                List<Node> possibleDestinations = edgeMap.getOrDefault(currentNode, new LinkedList<>());
                for (Node eachDst : possibleDestinations) {
                    queue.add(eachDst.getName());
                }
            }
        }

        return generatePath(parentMap, dst);
    }

    private boolean validateNodes(Node... nodes) {
        for (Node each : nodes) {
            if (!this.nodes.containsKey(each.getName())){
                return false;
            }
        }
        return true;
    }

    private Path generatePath(Map<String, String> childToParentMap, Node dst) {
        Path path = null;
        if (childToParentMap.containsKey(dst.getName())){
            path = new Path();
            path.addNodeInTheFront(dst);
            String parent = childToParentMap.get(dst.getName());
            while (parent != null){
                path.addNodeInTheFront(new Node(parent));
                parent = childToParentMap.get(parent);
            }
        }
        return path;
    }

    private Map<String, List<Node>> getEdgeMap() {
        Map<String, List<Node>> edgeMap = new HashMap<>();
        if (!edges.values().isEmpty()){
            for (Edge eachEdge : edges.values()) {
                List<Node> list = edgeMap.getOrDefault(eachEdge.getSource().getName(), new LinkedList<>());
                list.add(eachEdge.getDestination());
                edgeMap.put(eachEdge.getSource().getName(), list);
            }
        }
        return edgeMap;
    }
}