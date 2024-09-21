package com.aed2.projetos.grafos.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aed2.projetos.grafos.data.Graph;
import com.aed2.projetos.grafos.data.Profile;
import com.aed2.projetos.grafos.data.ProfileInterests;

public class VerticesDatabaseReader {
    public List<Graph<Profile>> readVertices() {
        String filePath = "src/main/resources/vertices.csv";
        
        List<Graph<Profile>> graphNodes = new ArrayList<Graph<Profile>>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String row = br.readLine();
            
            while ((row = br.readLine()) != null) {
                String[] colunas = row.split(";");

                Integer graphId = Integer.parseInt(colunas[0]);
                String profileName = colunas[1];
                String[] rawProfileInterests = colunas[2].split(",");

                ProfileInterests[] profileInterests = new ProfileInterests[rawProfileInterests.length];

                for (Integer i = 0; i < rawProfileInterests.length; i++) {
                    profileInterests[i] = ProfileInterests.valueOf(rawProfileInterests[i].toUpperCase());
                }

                Graph<Profile> graph = new Graph<Profile>(new Profile(graphId, profileName, profileInterests));

                graphNodes.add(graph);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graphNodes;
    }
}
