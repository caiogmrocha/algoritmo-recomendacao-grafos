package com.aed2.projetos.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.aed2.projetos.grafos.data.Graph;
import com.aed2.projetos.grafos.data.Profile;
import com.aed2.projetos.grafos.readers.EdgesDatabaseReader;
import com.aed2.projetos.grafos.readers.VerticesDatabaseReader;

public class Main {
    public static void main(String[] args) {
        VerticesDatabaseReader verticesDatabaseReader = new VerticesDatabaseReader();

        List<Graph<Profile>> graphVerticesList = verticesDatabaseReader.readVertices();

        EdgesDatabaseReader edgesDatabaseReader = new EdgesDatabaseReader();

        try {
            graphVerticesList = edgesDatabaseReader.readEdges(graphVerticesList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void runGraphOnCommandLine() {
        Scanner scanner = new Scanner(System.in);

        try {
            List<Graph<Profile>> graphNodes = new ArrayList<Graph<Profile>>();
            
            System.out.println("Quantidade de vertices: ");
            Integer vertices = scanner.nextInt();
            
            System.out.println("\nQuantidade de arestas: ");
            Integer edges = scanner.nextInt();
            
            System.out.println("\nInforme o valor dos vertices:");
            for (Integer i = 0; i < vertices; i++) {
                Integer graphId = scanner.nextInt();

                Graph<Profile> graph = new Graph<Profile>(new Profile(graphId, "graphId", new String[1]));

                graphNodes.add(graph);
            }
            
            // informar dois valores que vão ser conectados multiplicados pela quantidade de conexões 
            for (Integer i = 0; i < edges; i++) {
            	System.out.println("\nInformar conexão entre dois grafos:");
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
                    throw new Exception("[Error]: Os vértices não podem ser iguais");
                }

                if (firstNode.get().hasAdjacence(secondNode.get())) {
                    throw new Exception("[Error]: Os vértices já estão conectados");
                } else {
                    firstNode.get().addAdjacence(secondNode.get());
                }

                if (secondNode.get().hasAdjacence(firstNode.get())) {
                    throw new Exception("[Error]: Os vértices já estão conectados");
                } else {
                    secondNode.get().addAdjacence(firstNode.get());
                }
            }

            Graph<Profile> graph = graphNodes.get(0);
            
            // encontrar vertice no grafo  
            System.out.println("\nInformar valor a ser procurado no grafo: ");
            Integer vertexToBeFoundedId = scanner.nextInt();

            Graph<Profile> foundedVertex = graph.DFS(vertexToBeFoundedId, null);

            System.out.println(String.format("\nResultado: %s", (foundedVertex != null ? "Vertice encontrado !" : "Vertice nao encontrado !")));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
    }
}