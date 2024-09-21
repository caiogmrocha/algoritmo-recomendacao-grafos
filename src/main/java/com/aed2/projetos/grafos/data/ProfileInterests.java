package com.aed2.projetos.grafos.data;

public enum ProfileInterests {
    TECNOLOGIA("tecnologia"),
    ECONOMIA("economia"),
    SAUDE("saude"),
    BELEZA("beleza");

    private String interest;

    ProfileInterests(String interest) {
        this.interest = interest;
    }

    public String getInterest() {
        return interest;
    }    
}
