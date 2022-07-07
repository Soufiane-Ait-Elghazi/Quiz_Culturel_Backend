package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Aide;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Document;
import com.example.demo.entities.Personne;
import com.example.demo.entities.Proposition;
import com.example.demo.entities.Question;
import com.example.demo.entities.QuestionCocher;
import com.example.demo.entities.QuestionRemplire;
import com.example.demo.entities.QuestionVraieFaux;
import com.example.demo.entities.Quiz;
import com.example.demo.entities.User;
import com.example.demo.service.IQuizInitService;
import com.sun.xml.bind.v2.schemagen.xmlschema.Documentation;

@SpringBootApplication
public class QuizBackEndApplication implements CommandLineRunner {
	@Autowired
	private IQuizInitService quizInitService ;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(QuizBackEndApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Admin.class,User.class,Personne.class,Categorie.class,Quiz.class,Question.class,QuestionRemplire.class,QuestionVraieFaux.class,QuestionCocher.class,Proposition.class,Document.class,Aide.class);
		quizInitService.initAdmins();
		quizInitService.initUsers();
		quizInitService.initCategories();
		quizInitService.initQuizs();
		quizInitService.initQuestions();
		quizInitService.initDocuments();
		quizInitService.initPropositions();
		quizInitService.initAides();
		quizInitService.initScores();
	}
}
