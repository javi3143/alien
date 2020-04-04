package com.example.alien.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alien {
@Id private String name;
	private String type;
	private String birthPlanet;
//	public int getAid() {
	//	return aid;
	//}
	//public void setAid(int aid) {
	//	this.aid = aid;
	//}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBirthPlanet() {
		return birthPlanet;
	}
	public void setBirthPlanet(String birthPlanet) {
		this.birthPlanet = birthPlanet;
	}
	@Override
	 public String toString() {
	  return "Alien [name=" + name + ", type=" + type + ", birthPlanet=" + birthPlanet + "]";
	 }

}