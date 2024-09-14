package com.aed2.projetos.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<Graph> graphNodes = new ArrayList<Graph>();

            Integer vertices = scanner.nextInt();
            Integer edges = scanner.nextInt();

            for (Integer i = 0; i < vertices; i++) {
                String graphId = scanner.next();

                Graph graph = new Graph(graphId);

                graphNodes.add(graph);
            }

            for (Integer i = 0; i < edges; i++) {
                String edgeInput = scanner.next();

                String firstNodeId = String.valueOf(edgeInput.charAt(0));
                String secondNodeId = String.valueOf(edgeInput.charAt(1));

                Optional<Graph> firstNode = graphNodes.stream().filter(graph -> graph.getId().equals(firstNodeId))
                        .findFirst();

                if (firstNode.isEmpty()) {
                    throw new Exception("First node not found");
                }

                Optional<Graph> secondNode = graphNodes.stream().filter(graph -> graph.getId().equals(secondNodeId))
                        .findFirst();

                if (secondNode.isEmpty()) {
                    throw new Exception("Second node not found");
                }

                if (firstNode.get().equals(secondNode.get())) {
                    throw new Exception("First and second nodes are the same");
                }

                if (firstNode.get().hasAdjacence(secondNode.get())) {
                    throw new Exception("First and second nodes are already connected");
                } else {
                    firstNode.get().addAdjacence(secondNode.get());
                }

                if (secondNode.get().hasAdjacence(firstNode.get())) {
                    throw new Exception("First and second nodes are already connected");
                } else {
                    secondNode.get().addAdjacence(firstNode.get());
                }
            }

            Graph graph = graphNodes.get(0);

            Graph foundedVertex = graph.DFS("C", null, null);

            System.out.println(String.format("Vertex A: %s", (foundedVertex != null ? foundedVertex.getId() : "NOT FOUND")));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            scanner.close();
        }
    }
}