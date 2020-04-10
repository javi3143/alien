package com.example.alien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @EqualsAndHashCode.Include
	private Long id;
    @Getter
    @Setter
	private String name;
    @Getter
    @Setter
	private String type;
    @Getter
    @Setter
	private String planet;
	@ManyToOne(fetch=FetchType.LAZY)
    @Getter
    @Setter
	private Alien parent;
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
	private Set<Alien> children;
	@JsonIgnore
	public Set<Alien> getChildren() {
		return children;
	}
	
//	@JsonIgnore
//	public Alien getParent() {
//		return parent;
//	}
	
}