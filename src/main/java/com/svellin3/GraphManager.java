package com.svellin3;

import java.io.IOException;

public interface GraphManager {
    void parseGraph(String fileName) throws IOException;
    boolean containsNode(String nodeName);
    void addNode(String label);
    void addNodes(String... label);
    void removeNode(String label);
    void removeNodes(String... label);
    int nodeSize();
}
