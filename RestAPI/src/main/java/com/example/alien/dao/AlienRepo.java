package com.example.alien.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.alien.model.Alien;

@Repository
public interface AlienRepo extends JpaRepository<Alien, Long>
{
}
