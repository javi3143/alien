package com.example.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.alien.dao.AlienRepo;
import com.example.alien.model.Alien;

@Controller
public class AlienController 
{	
	@Autowired
	AlienRepo repo;
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}
	@RequestMapping("/deleteAlien")
	public String deleteAlien(String name) {
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
