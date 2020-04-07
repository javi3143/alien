package com.example.alien.model;

import com.example.alien.model.Alien;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class AlienDto {
    private Long id;
    private String name;
    private Alien parent;
    private Set<Alien> children;
    
}
