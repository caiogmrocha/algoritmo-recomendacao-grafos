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
                Integer graphId = scanner.nextInt();

                Graph<Profile> graph = new Graph<Profile>(new Profile(graphId, "graphId", new String[1]));

                graphNodes.add(graph);
            }

            for (Integer i = 0; i < edges; i++) {

                Integer firstNodeId = scanner.nextInt();
                Integer secondNodeId = scanner.nextInt();

                Optional<Graph<Profile>> firstNode = graphNodes.stream().filter(graph -> graph.getData().getId().equals(firstNodeId))
                        .findFirst();

                if (firstNode.isEmpty()) {
                    throw new Exception("First node not found");
                }

                Optional<Graph<Profile>> secondNode = graphNodes.stream().filter(graph -> (graph.getData().getId() == secondNodeId))
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

            Integer vertexToBeFoundedId = scanner.nextInt();

            Graph<Profile> foundedVertex = graph.DFS(vertexToBeFoundedId, null, null);

            System.out.println(String.format("Vertex search status: %s", (foundedVertex != null ? "FOUNDED" : "NOT FOUND")));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        } finally {
            scanner.close();
        }
    }
}