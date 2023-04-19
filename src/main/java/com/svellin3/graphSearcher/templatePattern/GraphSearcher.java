package com.svellin3.graphSearcher.templatePattern;

import com.svellin3.impl.Edge;
import com.svellin3.impl.Graph;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

import java.util.*;

public abstract class GraphSearcher {

    protected final Graph graph;

    protected final Node src;

    protected final Node dst;

    protected Map<String, List<Node>> edgeMap;

    protected Map<String, String> nodeToParentNodeMap;

    protected Set<String> visited;

    public GraphSearcher(Graph graph, Node src, Node dst) {
        this.graph = graph;
        this.src = src;
        this.dst = dst;
    }

    public final Path search(){
        Path path = null;
        if(validateInput(src, dst)){
            setupSearch();
            boolean destinationFound = false;
            while(!destinationFound && isNext()){
                String currentNodeName = getNext();
                destinationFound = processCurrentNode(currentNodeName);
            }
            path = generatePath();
        }
        return path;
    }

    private boolean processCurrentNode(String currentNode){
        if (!visited.contains(currentNode)){
            visited.add(currentNode);
            if (currentNode.equals(dst.getName())){
                return true;
            }else{
                List<Node> possibleDestinations = getPossibleNextNodes(currentNode);
                for (Node eachDst : possibleDestinations) {
                    setupNextNode(currentNode, eachDst.getName());
                }
            }
        }
        return false;
    }

    protected abstract void setupNextNode(String currentNode, String nextNode);

    protected abstract List<Node> getPossibleNextNodes(String currentNode);

    protected abstract String getNext();

    protected abstract boolean isNext();

    protected abstract void setupSearch();

    private boolean validateInput(Node... nodes) {
        for (Node each : nodes) {
            Map<String, Node> nodesInGraph = graph.getNodes();
            if (!nodesInGraph.containsKey(each.getName())){
                return false;
            }
        }
        return true;
    }

    protected Map<String, List<Node>> getEdgeMap() {
        Map<String, List<Node>> edgeMap = new HashMap<>();
        if (!graph.getEdges().values().isEmpty()){
            for (Edge eachEdge : graph.getEdges().values()) {
                List<Node> list = edgeMap.getOrDefault(eachEdge.getSource().getName(), new LinkedList<>());
                list.add(eachEdge.getDestination());
                edgeMap.put(eachEdge.getSource().getName(), list);
            }
        }
        return edgeMap;
    }


    protected Path generatePath() {
        Path path = null;
        if (nodeToParentNodeMap.containsKey(dst.getName())){
            path = new Path();
            path.addNodeInTheFront(dst);
            String parent = nodeToParentNodeMap.get(dst.getName());
            while (parent != null){
                path.addNodeInTheFront(new Node(parent));
                parent = nodeToParentNodeMap.get(parent);
            }
        }
        return path;

    }

}
