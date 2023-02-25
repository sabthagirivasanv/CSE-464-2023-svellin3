package com.svellin3;

import com.svellin3.impl.GraphManagerImpl;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GraphManager graphManager = new GraphManagerImpl();
        //parse graph:
        graphManager.parseGraph("testInput.dot");

        graphManager.addNode("f");
        graphManager.toString();

        graphManager.addNodes("g", "a", "h");
        graphManager.toString();

        graphManager.removeNode("b");
        graphManager.toString();

        graphManager.removeNodes("a","g","h","k");

        graphManager.toString();
        //graphManager.removeNode("a");
    }
}