package com.example.alien.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alien.dao.AlienRepo;
import com.example.alien.model.Alien;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AlienController 
{	
	@Autowired
	AlienRepo repo;

	@PutMapping("/alien/{id}")
	public Alien updateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{id}")
	public String deleteAlien(@PathVariable Integer id)
	{
		Alien a = repo.getOne(id);
		repo.delete(a);
		return "deleted";
	}
	
	@PostMapping("/alien")
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@GetMapping("/aliens")
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	
	@RequestMapping("/alien/{id}")
	public Optional<Alien> oneAlien(@PathVariable("id") Integer id) {
		return repo.findById(id);
	}
	
	@GetMapping("/")
	public String home()
	{
		return "do_Ob";
	}
}
