package com.example.alien.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.alien.model.Alien;

public interface AlienRepo extends CrudRepository<Alien, Integer>
{
	
}
