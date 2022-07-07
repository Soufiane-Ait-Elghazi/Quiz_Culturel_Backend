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
@DiscriminatorValue("Cocher")
public class QuestionCocher extends Question implements Serializable  {
	private ArrayList<Integer> solutionsCocher;
}