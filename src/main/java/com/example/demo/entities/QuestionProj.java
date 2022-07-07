package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = {com.example.demo.entities.Question.class})
public interface QuestionProj {
  public Long  getId();
  public String getDescription();
  public Proposition getProposition();
  public Collection<Integer> getSolutionsCocher();
  public Document getDocument();
  public Aide getAide();
  public String getType();
  
  
  
  //http://localhost:8089/questions/1?projection=p1
  
  
  
  
  
}
