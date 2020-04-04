package com.example.alien.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alien.model.Alien;

public interface AlienRepo extends JpaRepository<Alien, String>
{
}
