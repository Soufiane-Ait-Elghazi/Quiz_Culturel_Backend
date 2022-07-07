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
@DiscriminatorValue("Remplire")
public class QuestionRemplire extends Question implements Serializable  {
	private ArrayList<String> solutionsRemplire;
}