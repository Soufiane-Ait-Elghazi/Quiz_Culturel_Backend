package com.example.demo.entities;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@DiscriminatorValue("VraieFaux")
public class QuestionVraieFaux extends Question implements Serializable  {
	private Boolean boolSol;
}
