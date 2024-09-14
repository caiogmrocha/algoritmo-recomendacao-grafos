package com.aed2.projetos.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<Graph<Profile>> graphNodes = new ArrayList<Graph<Profile>>();

            Integer vertices = scanner.nextInt();
            Integer edges = scanner.nextInt();

            for (Integer i = 0; i < vertices; i++) {
                String graphId = scanner.next();

                Graph<Profile> graph = new Graph<Profile>(new Profile(graphId, graphId, new String[1]));

                graphNodes.add(graph);
            }

            for (Integer i = 0; i < edges; i++) {
                String edgeInput = scanner.next();

                String firstNodeId = String.valueOf(edgeInput.charAt(0));
                String secondNodeId = String.valueOf(edgeInput.charAt(1));

                Optional<Graph<Profile>> firstNode = graphNodes.stream().filter(graph -> graph.getData().getId().equals(firstNodeId))
                        .findFirst();

                if (firstNode.isEmpty()) {
                    throw new Exception("First node not found");
                }

                Optional<Graph<Profile>> secondNode = graphNodes.stream().filter(graph -> graph.getData().getId().equals(secondNodeId))
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

            Graph<Profile> graph = graphNodes.get(0);

            Graph<Profile> foundedVertex = graph.DFS("C", null, null);

            System.out.println(String.format("Vertex A: %s", (foundedVertex != null ? foundedVertex.getData().getId() : "NOT FOUND")));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            scanner.close();
        }
    }
}