package com.svellin3;

import com.svellin3.impl.GraphManagerImpl;
import com.svellin3.impl.Node;
import com.svellin3.impl.Path;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        GraphManager graphManager = new GraphManagerImpl();

        System.out.println("please enter the dot file to parse the graph:");
        String dotFileName = new Scanner(System.in).nextLine();
        graphManager.parseGraph(dotFileName);

        int option = 0;
        while(option != 11){
            printAllOptions();
            option = new Scanner(System.in).nextInt();

            switch (option){
                case 1:
                    graphManager.toString();
                    break;
                case 2:
                    System.out.println("Please enter the output filename:");
                    String outputFileName = new Scanner(System.in).nextLine();
                    graphManager.outputGraph(outputFileName);
                    break;
                case 3:
                    System.out.println("Please enter the node name:");
                    String nodeName = new Scanner(System.in).nextLine();
                    graphManager.addNode(nodeName);
                    break;
                case 4:
                    System.out.println("Please enter the list of node names as comma separated values:");
                    String nodeNameList = new Scanner(System.in).nextLine();
                    graphManager.addNodes(nodeNameList.split(","));
                    break;
                case 5:
                    System.out.println("Please enter the node name to be removed:");
                    String nodeNameToBeRemoved = new Scanner(System.in).nextLine();
                    graphManager.removeNode(nodeNameToBeRemoved);
                    break;
                case 6:
                    System.out.println("Please enter the list of node names to be removed as comma separated values:");
                    String toBeRemovedList = new Scanner(System.in).nextLine();
                    graphManager.removeNodes(toBeRemovedList.split(","));
                    break;
                case 7:
                    System.out.println("Please enter the source node:");
                    String sourceNode = new Scanner(System.in).nextLine();
                    System.out.println("Please enter the destination node:");
                    String destNode = new Scanner(System.in).nextLine();
                    graphManager.addEdge(sourceNode, destNode);
                    break;
                case 8:
                    System.out.println("Please enter the source node:");
                    String sourceNodeToBeRemoved = new Scanner(System.in).nextLine();
                    System.out.println("Please enter the destination node:");
                    String destNodeToBeRemoved = new Scanner(System.in).nextLine();
                    graphManager.removeEdge(sourceNodeToBeRemoved, destNodeToBeRemoved);
                    break;
                case 9:
                    System.out.println("Please enter the dot filename:");
                    String outputDotFileName = new Scanner(System.in).nextLine();
                    graphManager.outputDOTGraph(outputDotFileName);
                    break;
                case 10:
                    System.out.println("Please enter the graphics filename:");
                    String graphicsFileName = new Scanner(System.in).nextLine();
                    System.out.println("Please enter the format:");
                    String format = new Scanner(System.in).nextLine();
                    graphManager.outputGraphics(graphicsFileName, format);
                    break;
                case 11:
                    graphSearch(graphManager, Algorithm.BFS);
                    break;
                case 12:
                    graphSearch(graphManager, Algorithm.DFS);
                    break;
                case 13:
                    System.out.println("Exiting the applications...");
                    return;
            }

            System.out.println("\n\nSelect an option to proceed"+
                    "\n0. Show actions"
                    +"\n13. Exit"
            );
            option = new Scanner(System.in).nextInt();
        }
    }

    private static void printAllOptions() {
        System.out.println("\n\npress the below options to perform actions:\n"
        +"1. print the graph\n"
        +"2. output to file\n"
        +"3. add a new node\n"
        +"4. add a list of new nodes\n"
        +"5. remove a node\n"
        +"6. remove a list of nodes\n"
        +"7. add an edge\n"
        +"8. remove an edge\n"
        +"9. output as DOT graph\n"
        +"10. output into graphics\n"
        +"11. search nodes by BFS\n"
        +"12. search nodes by DFS\n"
        +"13. exit\n"
        );
    }

    private static void graphSearch(GraphManager graphManager, Algorithm algo) {
        System.out.println("Please enter the source node:");
        String src = new Scanner(System.in).nextLine();
        System.out.println("Please enter the destination node:");
        String dst = new Scanner(System.in).nextLine();
        Path path = graphManager.GraphSearch(new Node(src), new Node(dst), algo);
        System.out.println("The path is :\n"+path.toString());
    }
}