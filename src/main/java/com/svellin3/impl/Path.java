package com.svellin3.impl;

import java.util.Iterator;
import java.util.LinkedList;

public class Path {
    private final LinkedList<Node> nodesInThePath;

    public Path() {
        nodesInThePath = new LinkedList<>();
    }

    public void addNodeInTheEnd(Node node){
        nodesInThePath.add(node);
    }

    public void addNodeInTheFront(Node node){
        nodesInThePath.addFirst(node);
    }

    public Node removeLastNode(){
        return nodesInThePath.removeLast();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!nodesInThePath.isEmpty()){
            Iterator<Node> iterator = nodesInThePath.iterator();
            builder.append(iterator.next().getName());
            while (iterator.hasNext()){
                builder.append(" -> ").append(iterator.next().getName());
            }
        }
        return builder.toString();
    }
}