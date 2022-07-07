package com.example.demo.entities;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@DiscriminatorValue("User")
public class User extends Personne  implements Serializable  {
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Score score;
	private int nbrTentativesAide ;
}