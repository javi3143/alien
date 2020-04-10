package com.example.alien.model;

public class NewAlien {
    private Long id;
    private String name;
    private String type;
    private String planet;
    private Alien parent;
    private Long clave;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getClave() {
        return clave;
    }
    
    public String getType() {
        return type;
    }

    public String getPlanet() {
        return planet;
    }
    public Alien getParent(Alien parent) {
        return parent;
    }

}
