package com.aed2.projetos.grafos;

import java.util.List;
import java.util.function.Predicate;

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
            graphVerticesList = edgesDatabaseReader.readEdges(graphVerticesList); //dados 
            
            Graph<Profile> Grafo = graphVerticesList.get(0); //iniciar busca a partir do primeiro elemento
            
            //Tornar condição dinâmica ( criar input de interesse, onde usuário escreva qual o assunto de interesse) 
           
            Predicate<Graph<Profile>> CondicaoBusca = vertice -> vertice.getData().getId() > 8; //criando uma condição de busca
            
            List<Graph<Profile>> ResultadoBusca = Grafo.DFS(CondicaoBusca, null, null);
            
            //Mostrando resultados de busca: 
            System.out.println("\nVertices encontrados : \n");
            
            // adicionar verificação para quando a lista vier vazia, caso não venha apresentar registros
            // caso venha vazio: retornar mensagem "Nenhum vertice encontrado"
            
            //ADAPTAR PARA RETORNAR OBJETO COMPLETO A CADA ITERACAO
            for (Graph<Profile> vertice : ResultadoBusca) {
                System.out.println(vertice.getData().getName());
            }
    
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}