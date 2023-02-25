package com.svellin3;

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
        return new StringBuilder()
                .append(src.getName()).append("->").append(dest.getName())
                .toString();
    }
}
