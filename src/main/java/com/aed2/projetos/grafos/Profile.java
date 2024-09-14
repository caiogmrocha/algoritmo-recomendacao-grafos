package com.aed2.projetos.grafos;

public class Profile extends GraphData {
    private String name;
    private String[] interests;
    
    public Profile(String id, String name, String[] interests) {
        super(id);
        this.name = name;
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }
}
