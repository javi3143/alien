package com.example.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.alien.dao.AlienRepo;
import com.example.alien.model.Alien;
import com.example.alien.model.AlienDto;
import com.example.alien.model.NewAlien;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/alien")
public class AlienController 
{	
	@Autowired
	AlienRepo repo;
	
	@PostMapping("/post")
	public Alien addAlien(@RequestBody NewAlien alien) {
		Alien a = new Alien();
		a.setName(alien.getName());
		a.setId(alien.getId());
		a.setPlanet(alien.getPlanet());
		a.setType(alien.getType());
		if(alien.getClave() == null) {
			a.setParent(null);
		} else {
			a.setParent(alien.getParent(repo.getOne(alien.getClave())));
		}
		repo.save(a);
		return a;
	}

	@PutMapping("{id}")
	public Alien updateAlien(@RequestBody Alien alien) {
		Alien a = repo.getOne(alien.getId());
		a.setName(alien.getName());
		a.setPlanet(alien.getPlanet());
		repo.save(a);
		return a;
	}
	
	@DeleteMapping("{id}")
	public String deleteAlien(@PathVariable("id") Long id)
	{
		Alien a = repo.getOne(id);
		repo.delete(a);
		return "deleted";
	}

    @GetMapping("/{id}/siblings")
    public ResponseEntity<Set<AlienDto>> getAllSiblings(@PathVariable("id") Long id) {
        return repo.findById(id).map(findSiblings).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
	@GetMapping("/aliens")
	public List<Alien> getAllAliens() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<AlienDto> getAllDetails(@PathVariable("id") Long id) {
        return repo.findById(id).map(mapToAlienDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
	@GetMapping("/all")
	public Stream<AlienDto> getAll() {
		return repo.findAll().stream().map(mapToAlienDto);
	}
	
    private Function<Alien, Set<AlienDto>> findSiblings = alien -> alien.getParent().getChildren().stream()
            .map(p -> AlienDto.builder().id(p.getId()).name(p.getName()).build()).collect(Collectors.toSet());

    private Function<Alien, AlienDto> mapToAlienDto = p -> AlienDto.builder()
    															   .id(p.getId())
    															   .name(p.getName())
    															   .planet(p.getPlanet())
    															   .type(p.getType())
    															   .parent(p.getParent())
    															   .children(p.getChildren())
    															   .build();
}
