package com.example.alien.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.alien.dao.AlienRepo;
import com.example.alien.model.Alien;

@RestController
public class AlienController 
{	
	@Autowired
	AlienRepo repo;
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@PutMapping("/alien")
	public Alien updateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{name}")
	public String deleteAlien(@PathVariable String name)
	{
		Alien a = repo.getOne(name);
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
	
	@RequestMapping("/alien/{name}")
	public Optional<Alien> oneAlien(@PathVariable("name") String name) {
		return repo.findById(name);
	}
	
	// old
	@RequestMapping("/deleteAlien")
	public String deleteOneAlien(String name) {
		repo.deleteById(name);
		return "home.jsp";
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam String name) {
		ModelAndView mv= new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(name).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	
}
