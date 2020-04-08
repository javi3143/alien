package com.example.alien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
		if(alien.getClave() == null) {
			a.setParent(null);
		} else {
			a.setParent(alien.getParent(repo.getOne(alien.getClave())));
		}
		repo.save(a);
		return a;
	}
//	if(dto.getParentId() == 0) {
//        alien.setParent(null);
//    }else {
//        Alien parent = this.repo.findById(dto.getParentId());
//        alien.setParent(parent);
//    }
	@PutMapping("{id}")
	public Alien updateAlien(@RequestBody Alien alien) {
		Alien a = repo.getOne(alien.getId());
		a.setName(alien.getName());
		repo.save(a);
		return a;
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<AlienDto> getAllDetails(@PathVariable("id") Long id) {
        return repo.findById(id).map(mapToAlienDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
	
	@DeleteMapping("{id}")
	public String deleteAlien(@PathVariable("id") Long id)
	{
		Alien a = repo.getOne(id);
		repo.delete(a);
		return "deleted";
	}
	
	@GetMapping("/all")
	public Stream<AlienDto> getAll() {
		return repo.findAll().stream().map(mapToAlienDto);
	}
	
    private Function<Alien, Set<AlienDto>> findSiblings = alien -> alien.getParent().getChildren().stream()
            .map(p -> AlienDto.builder().id(p.getId()).name(p.getName()).build()).collect(Collectors.toSet());

    private Function<Alien, AlienDto> mapToAlienDto = p -> AlienDto.builder().id(p.getId()).name(p.getName()).children(p.getChildren()).build();
//	@PutMapping("/alien/{id}")
//	public Alien updateAlien(@RequestBody Alien alien) {
//		repo.save(alien);
//		return alien;
//	}
//	
//	@DeleteMapping("/alien/{id}")
//	public String deleteAlien(@PathVariable Integer id)
//	{
//		Alien a = repo.getOne(id);
//		repo.delete(a);
//		return "deleted";
//	}
//	
//	 @PostMapping("/alien")
//     public Alien addAlien(@RequestBody AlienDto dto) {
//         Alien alien = new Alien();
//         alien.setName(dto.getName());
//         alien.setType(dto.getType());
//         alien.setPlanet(dto.getPlanet());
//         
//         Optional<Alien> parent = repo.findById(1);
//         alien.setParent(parent);
//         repo.save(alien);
//         return alien;
//        } 
//	
//	@GetMapping("/aliens")
//	public List<Alien> getAliens() {
//		return repo.findAll();
//	}
//	
//	@RequestMapping("/alien/{id}")
//	public Optional<Alien> oneAlien(@PathVariable("id") Integer id) {
//		return repo.findById(id);
//	}
//	
//	@GetMapping("/")
//	public String home()
//	{
//		return "do_Ob";
//	}
}
