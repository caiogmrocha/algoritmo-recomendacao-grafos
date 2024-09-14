package com.aed2.projetos.grafos;

public abstract class GraphData {
    protected String id;

    public GraphData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
