package com.example.alien.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.example.alien.model.Alien;

public interface AlienRepo extends CrudRepository<Alien, String>
{
}
