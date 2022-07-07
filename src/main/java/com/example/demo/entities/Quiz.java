package com.example.demo.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Quiz implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String owner;
	private String description ;
	private Boolean accepte ;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Categorie categorie;
	@OneToMany(mappedBy = "quiz")
	private Collection<Question> questions;
	@ManyToOne
	private Personne personne ;
	private int nbrQuestions;
	private boolean docAuto ;

}
