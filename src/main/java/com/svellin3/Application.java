package com.svellin3;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        GraphManager graphManager = new GraphManager();
        //parse graph:
        graphManager.parseGraph("testInput.dot");
    }
}