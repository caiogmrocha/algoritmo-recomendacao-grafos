package com.aed2.projetos.grafos;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Graph {
    private String id;
    private List<Graph> adjacences;

    public Graph(String id) {
        this.id = id;
        this.adjacences = new ArrayList<Graph>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addAdjacence(Graph adjacence) {
        if (!this.adjacences.contains(adjacence)) {
            this.adjacences.add(adjacence);
        }
    }

    public void removeAdjacence(Graph adjacence) {
        if (!this.adjacences.contains(adjacence)) {
            this.adjacences.remove(adjacence);
        }
    }

    public Boolean hasAdjacence(Graph adjacence) {
        return this.adjacences.contains(adjacence);
    }

    public Graph BFS(String id, Stack<Graph> pathStack, Map<String, Boolean> visitedVerticesMap) {
        if (this.id.equals(id)) {
            return this;
        } else {
            if (visitedVerticesMap == null) {
                visitedVerticesMap = new HashMap<String, Boolean>();
            }

            visitedVerticesMap.put(this.id, true);

            if (pathStack == null) {
                pathStack = new Stack<Graph>();
            }

            for (int i = this.adjacences.size() - 1; i >= 0; i--) {
                Graph adjacentVertex = this.adjacences.get(i);

                Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getId());

                if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                    pathStack.push(adjacentVertex);
                }
            }

            Graph vertex = null;

            while (pathStack.size() > 0 && vertex == null) {
                Graph child = pathStack.pop();

                vertex = child.BFS(id, pathStack, visitedVerticesMap);
            }

            return vertex;
        }
    }

    public Graph DFS(String id, Queue<Graph> pathQueue, Map<String, Boolean> visitedVerticesMap) {
        if (this.id.equals(id)) {
            return this;
        } else {
            if (visitedVerticesMap == null) {
                visitedVerticesMap = new HashMap<String, Boolean>();
            }

            visitedVerticesMap.put(this.id, true);

            if (pathQueue == null) {
                pathQueue = new LinkedList<Graph>();
            }

            for (int i = this.adjacences.size() - 1; i >= 0; i--) {
                Graph adjacentVertex = this.adjacences.get(i);

                Boolean vertexIsAlreadyVisited = visitedVerticesMap.get(adjacentVertex.getId());

                if (vertexIsAlreadyVisited == null || !vertexIsAlreadyVisited) {
                    pathQueue.add(adjacentVertex);
                }
            }

            Graph vertex = null;

            while (pathQueue.size() > 0 && vertex == null) {
                Graph child = pathQueue.poll();

                vertex = child.DFS(id, pathQueue, visitedVerticesMap);
            }

            return vertex;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Graph other = (Graph) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
