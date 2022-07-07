package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = {com.example.demo.entities.User.class})
public interface UserProj {
  public Long getId();
  public String getNom();
  public String getPrenom();
  public String getLogin();
  public String getMail();
  public String getImage();
  public String getPassword();
  public Long getNbrQuizs();
  public Score getScore();
 // public Collection<Quiz> getQuizs();
  
  

}
  //http://localhost:8089/users/1?projection=p1