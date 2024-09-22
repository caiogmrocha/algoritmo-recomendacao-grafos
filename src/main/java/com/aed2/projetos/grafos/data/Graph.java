package com.aed2.projetos.grafos.data;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;
import java.util.HashMap;

public class Graph<T extends GraphData> {
    private T data;
    private List<Graph<T>> adjacences;
   

    public Graph(T data) {
        this.data = data;
        this.adjacences = new ArrayList<Graph<T>>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void addAdjacence(Graph<T> adjacence) {
        if (!this.adjacences.contains(adjacence)) {
            this.adjacences.add(adjacence);
        }
    }

    public void removeAdjacence(Graph<T> adjacence) {
        if (!this.adjacences.contains(adjacence)) {
            this.adjacences.remove(adjacence);
        }
    }

    public Boolean hasAdjacence(Graph<T> adjacence) {
        return this.adjacences.contains(adjacence);
    }
    
    //implementação do busca em profundidade recursivo (pilha na propria recursão)
    public List<Graph<T>> DFS(Predicate <Graph<T>> interesse, Map<T, Boolean> visitedVerticesMap, List<Graph<T>> ListaResposta) {
    
        if (visitedVerticesMap == null) {
            visitedVerticesMap = new HashMap<>();
        }
        
        if (ListaResposta == null) {
        	ListaResposta = new ArrayList<>();
        }
        
        visitedVerticesMap.put(this.data, true);
        
        if (interesse.test(this)) {
        	ListaResposta.add(this);
        }
       
        for (Graph<T> adjacentVertex : this.adjacences) {
            Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getData());

            if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                adjacentVertex.DFS(interesse, visitedVerticesMap, ListaResposta);
            }
        }
    	
       return ListaResposta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getData().getId() == null) ? 0 : this.getData().getId().hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Graph<T> other = (Graph<T>) obj;
        if (this.getData().getId() == null) {
            if (other.getData().getId() != null)
                return false;
        } else if (!this.getData().getId().equals(other.getData().getId()))
            return false;
        return true;
    }
}
