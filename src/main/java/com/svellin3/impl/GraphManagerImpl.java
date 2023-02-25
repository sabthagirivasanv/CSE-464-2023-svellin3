package com.svellin3.impl;

import com.svellin3.GraphManager;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.nio.file.Files;

public class GraphManagerImpl implements GraphManager{

    Graph graph;
    public void parseGraph(String fileName) throws IOException {
        File initialFile = new File(fileName);
        InputStream dot = new DataInputStream(Files.newInputStream(initialFile.toPath()));
        MutableGraph mutableGraph = new Parser().read(dot);
        graph = new Graph(mutableGraph);
        toString();
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
        String output;
        System.out.println("Number of Nodes: "+nodeSize());
        System.out.println("Label of Nodes:"+ graph.getAllNodes());
        System.out.println("Number of Edges: "+edgeSize());
        System.out.println("Edges: ");
        output = graph.toString();
        System.out.println(output);
        return output;
    }

    public void addNode(String label){
        graph.addNode(label);
    }

    public void addNodes(String... label){
        graph.addNodes(label);
    }

    public void removeNode(String label){
        graph.removeNode(label);
    }

    public void removeNodes(String... label){
        graph.removeNodes(label);
    }
}