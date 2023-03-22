package com.svellin3.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Path {
    private final List<Node> nodesInThePath;

    public Path() {
        nodesInThePath = new LinkedList<>();
    }

    public void addNodeToThePath(Node node){
        nodesInThePath.add(node);
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
