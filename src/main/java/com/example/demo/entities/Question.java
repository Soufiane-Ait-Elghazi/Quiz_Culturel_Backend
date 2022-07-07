package com.example.demo.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Question implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private int nbrSolutions;
	private int nbrProposition;
	@ManyToOne
	private Quiz quiz;
	@OneToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Document document;
	@OneToOne
	private Proposition proposition ;
	@OneToOne
	private Aide aide;
	private String type;
}

