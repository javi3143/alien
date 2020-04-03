package com.example.alien.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alien {
@Id
	private String name;
	private String type;
	private String birth_planet;
	
	public String getName() {
		return name;
	}
	public void setAid(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBirth() {
		return birth_planet;
	}
	public void setBirth(String birth_planet) {
		this.birth_planet = birth_planet;
	}
	@Override
	public String toString() {
		return "Alien [name=" + name + ", type=" + type + ", birth_planet=" + birth_planet + "]";
	}
}
