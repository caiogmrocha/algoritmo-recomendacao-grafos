package com.aed2.projetos.grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
    public Graph<T> DFS(Integer id, Map<Integer, Boolean> visitedVerticesMap) {
    	
    	if (this.data.id.equals(id)) {
    		return this; 
    	}
    	
        if (visitedVerticesMap == null) {
            visitedVerticesMap = new HashMap<>();
        }
        
        visitedVerticesMap.put(this.data.id, true);
        
        for (Graph<T> adjacentVertex : this.adjacences) {
            
            Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getData().getId());


            if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                Graph<T> result = adjacentVertex.DFS(id, visitedVerticesMap);
                
                if (result != null) {
                    return result;
                }
            }
        }
    	
    	
       return null;
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
