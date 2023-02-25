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
//        graphManager.removeNodes("a","g","h","k");
//        graphManager.removeEdge("b", "c");
        graphManager.addEdge("a", "f");
        graphManager.addEdge("f", "c");
        graphManager.addEdge("a", "d");
        graphManager.addEdge("b", "a");
        graphManager.addNode("e");
//        graphManager.removeEdge("a","d");
        graphManager.outputGraphics("outputGraphics.png", "png");

    }
}