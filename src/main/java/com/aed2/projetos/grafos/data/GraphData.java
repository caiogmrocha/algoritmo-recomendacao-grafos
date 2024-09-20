package com.aed2.projetos.grafos.data;

public abstract class GraphData {
    protected Integer id;

    public GraphData(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
