package com.svellin3;

import com.svellin3.impl.GraphManagerImpl;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GraphManager graphManager = new GraphManagerImpl();
        //parse graph:
        graphManager.parseGraph("testInput.dot");

//        graphManager.addNode("f");
//        graphManager.addNodes("g", "a", "h");
//        graphManager.removeNode("b");
//        graphManager.removeNodes("a","g","h","k");
//        graphManager.removeEdge("b", "c");
//        graphManager.addEdge("b", "a");
//        graphManager.addEdge("a", "c");
//        graphManager.removeEdge("a","d");
//        graphManager.removeEdge("d","a");

    }
}