package com.aed2.projetos.grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
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

    public Graph<T> BFS(Integer id, Stack<Graph<T>> pathStack, Map<Integer, Boolean> visitedVerticesMap) {
        if (this.data.id.equals(id)) {
            return this;
        } else {
            if (visitedVerticesMap == null) {
                visitedVerticesMap = new HashMap<Integer, Boolean>();
            }

            visitedVerticesMap.put(this.data.id, true);

            if (pathStack == null) {
                pathStack = new Stack<Graph<T>>();
            }

            for (int i = this.adjacences.size() - 1; i >= 0; i--) {
                Graph<T> adjacentVertex = this.adjacences.get(i);

                Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getData().getId());

                if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                    pathStack.push(adjacentVertex);
                }
            }

            Graph<T> vertex = null;

            while (pathStack.size() > 0 && vertex == null) {
                Graph<T> child = pathStack.pop();

                vertex = child.BFS(id, pathStack, visitedVerticesMap);
            }

            return vertex;
        }
    }

    public Graph<T> DFS(Integer id, Queue<Graph<T>> pathQueue, Map<Integer, Boolean> visitedVerticesMap) {
        if (this.data.id.equals(id)) {
            return this;
        } else {
            if (visitedVerticesMap == null) {
                visitedVerticesMap = new HashMap<Integer, Boolean>();
            }

            visitedVerticesMap.put(this.data.id, true);

            if (pathQueue == null) {
                pathQueue = new LinkedList<Graph<T>>();
            }

            for (int i = this.adjacences.size() - 1; i >= 0; i--) {
                Graph<T> adjacentVertex = this.adjacences.get(i);

                Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getData().getId());

                if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                    pathQueue.add(adjacentVertex);
                }
            }

            Graph<T> vertex = null;

            while (pathQueue.size() > 0 && vertex == null) {
                Graph<T> child = pathQueue.poll();

                vertex = child.DFS(id, pathQueue, visitedVerticesMap);
            }

            return vertex;
        }
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
