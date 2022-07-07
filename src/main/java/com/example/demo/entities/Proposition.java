package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Proposition implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ArrayList<String> proposCocher ;
	private ArrayList<String> proposRemplire;
	private ArrayList<Boolean> propoVraieFauxs ;
	 @OneToOne(mappedBy = "proposition", cascade = CascadeType.ALL)
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 private Question question;

}
