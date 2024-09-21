package com.aed2.projetos.grafos.data;

public class Profile extends GraphData {
    private String name;
    private ProfileInterests[] interests;
    
    public Profile(Integer id, String name, ProfileInterests[] interests) {
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

    public ProfileInterests[] getInterests() {
        return interests;
    }

    public void setInterests(ProfileInterests[] interests) {
        this.interests = interests;
    }
}
