package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "q1",types = {com.example.demo.entities.Quiz.class})
public interface QuizProj {
  public Long  getId();
  public boolean getDocAuto();
  public boolean getAccepte();
  public Collection<QuestionCocher> getQuestions();
  
  

  //http://localhost:8089/questions/1?projection=q1
  
  
  
  
  
}