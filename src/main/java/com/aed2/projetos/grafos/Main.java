package com.aed2.projetos.grafos;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import com.aed2.projetos.grafos.data.Graph;
import com.aed2.projetos.grafos.data.Profile;
import com.aed2.projetos.grafos.data.ProfileInterests;
import com.aed2.projetos.grafos.readers.EdgesDatabaseReader;
import com.aed2.projetos.grafos.readers.VerticesDatabaseReader;

public class Main {
    public static void main(String[] args) {
        VerticesDatabaseReader verticesDatabaseReader = new VerticesDatabaseReader();

        List<Graph<Profile>> graphVerticesList = verticesDatabaseReader.readVertices();

        EdgesDatabaseReader edgesDatabaseReader = new EdgesDatabaseReader();

        try {
            graphVerticesList = edgesDatabaseReader.readEdges(graphVerticesList); //dados 
            
            Graph<Profile> Grafo = graphVerticesList.get(0); //iniciar busca a partir do primeiro elemento
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o interesse de busca: ");
            String interesse = scanner.nextLine().toUpperCase();

            try {
                ProfileInterests profileInterest = ProfileInterests.valueOf(interesse);
    
                Predicate<Graph<Profile>> CondicaoBusca = vertice -> Arrays.asList(vertice.getData().getInterests())
                    .stream()
                    .anyMatch(interest -> interest.equals(profileInterest));
                
                List<Graph<Profile>> ResultadoBusca = Grafo.DFS(CondicaoBusca, null, null);
                
                System.out.println("Resultados encontrados:");
                ResultadoBusca.forEach(result -> 
                    System.out.println(String.format(
                        "ID: %d, Nome: %s, Interesses: %s", 
                        result.getData().getId(), 
                        result.getData().getName(), 
                        String.join(", ", 
                            Arrays.stream(result.getData().getInterests())
                                .map(ProfileInterests::getInterest)
                                .toArray(String[]::new)
                        )
                    ))
                );
            
                
            } catch (IllegalArgumentException e) {
                System.out.println("Nenhum resultado encontrado para o interesse: " + interesse);
            }

            scanner.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}