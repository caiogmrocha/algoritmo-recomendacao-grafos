package com.aed2.projetos.grafos.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.aed2.projetos.grafos.data.Graph;
import com.aed2.projetos.grafos.data.Profile;

public class EdgesDatabaseReader {
    public List<Graph<Profile>> readEdges(List<Graph<Profile>> graphNodes) throws Exception {
        String filePath = "src/main/resources/edges.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String row = br.readLine();

            while ((row = br.readLine()) != null) {
                String[] colunas = row.split(";");

                String firstNodeName = colunas[0];
                String secondNodeName = colunas[1];

                Optional<Graph<Profile>> firstNode = graphNodes.stream().filter(graph -> graph.getData().getName().equals(firstNodeName))
                        .findFirst();

                if (firstNode.isEmpty()) {
                    throw new Exception("First node not found");
                }

                Optional<Graph<Profile>> secondNode = graphNodes.stream().filter(graph -> (graph.getData().getName().equals(secondNodeName)))
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
                    secondNode.get().addAdjacence(firstNode.get());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graphNodes;
    }
}
