package com.svellin3.impl;

public class Edge {
    private Node source;
    private Node destination;


    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public static String edgeString(Node src, Node dest){
        return edgeString(src.getName(), dest.getName());
    }

    public static String edgeString(String src, String dest){
        return new StringBuilder()
                .append(src).append("->").append(dest)
                .toString();
    }

    public String toString(){
        return source.toString()+" -> "+destination.toString();
    }
}
