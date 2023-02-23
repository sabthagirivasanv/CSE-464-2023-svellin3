package com.svellin3;

import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.*;
import java.nio.file.Files;

public class GraphManager {

    MutableGraph graph;
    public void parseGraph(String fileName) throws IOException {
        File initialFile = new File(fileName);
        try {
            InputStream dot = new DataInputStream(Files.newInputStream(initialFile.toPath()));
            graph = new Parser().read(dot);
            System.out.println(graph);
        } catch (Exception e) {
            throw e;
        }
    }
}