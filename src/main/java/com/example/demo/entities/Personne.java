package com.example.demo.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Personne implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom ;
	private String mail;
	private String image;
	@Column(unique = true)
	private String login;
	private String password;
	private int nbrQuizs;
	@OneToMany(mappedBy = "personne")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Collection<Quiz> quizs;
}
