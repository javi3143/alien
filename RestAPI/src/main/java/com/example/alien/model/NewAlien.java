package com.example.alien.model;

import java.util.Optional;

public class NewAlien {
    private Long id;
    private String name;
    private Long clave;
    private Alien parent;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getClave() {
        return clave;
    }
    public Alien getParent(Alien parent) {
        return parent;
    }

	public void setParent(Alien parent) {
		this.parent = parent;
		
	}
}
