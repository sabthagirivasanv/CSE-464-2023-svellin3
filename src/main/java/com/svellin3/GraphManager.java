package com.svellin3;

import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class GraphManager {

    Graph graph;
    public void parseGraph(String fileName) throws IOException {
        File initialFile = new File(fileName);
        InputStream dot = new DataInputStream(Files.newInputStream(initialFile.toPath()));
        MutableGraph mutableGraph = new Parser().read(dot);
        graph = new Graph(mutableGraph);
        System.out.println("Number of Nodes: "+nodeSize());
        System.out.println("Label of Nodes:"+ graph.getAllNodes());
        System.out.println("Number of Edges: "+edgeSize());
        System.out.println("The Graph: ");
        System.out.println(mutableGraph);
    }

    public int nodeSize(){
        return graph.nodeSize();
    }

    public int edgeSize(){
        return graph.edgeSize();
    }

    public boolean containsNode(String nodeName) {
        return graph.containsNode(nodeName);
    }

    public String toString(){
        return graph.toString();
    }
}