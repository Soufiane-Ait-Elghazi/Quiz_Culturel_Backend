package com.example.demo.service;



import java.util.ArrayList;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.*;
import com.example.demo.entities.*;


@Service
@Transactional
public class QuizInitServiceImpl implements  IQuizInitService{
	@Autowired
	private QuizRepository quizRepository ;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private PropositionRepository propositionRepository;
	@Autowired
	private AideRepository aideRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Override
	
	public void initAdmins() {
		Personne a = new Admin();
		a.setNom("Ait Elghazi");
		a.setPrenom("Soufiane");
		a.setMail("soufianeaitelghazi@gmail.com");
		a.setNbrQuizs(0);
		a.setLogin("user1");
		a.setPassword("user1");
		a.setImage("admin.jpg");
		((Admin) a).setSecretCode("48624");
		personneRepository.save(a);
	}
	@Override
	public void initUsers() {
		Personne u1 = new User();
		u1.setNom("Ait Elghazi");
		u1.setPrenom("Soufiane");
		u1.setMail("soufianeaitelghazi@gmail.com");
		u1.setNbrQuizs(0);
		u1.setLogin("user2");
		u1.setPassword("user2");
		u1.setImage("user2.jpg");
		personneRepository.save(u1);
		Personne u2 = new User();
		u2.setNom("Bendir");
		u2.setPrenom("Mohammed");
		u2.setMail("medbendir@gmail.com");
		u2.setNbrQuizs(0);
		u2.setLogin("user3");
		u2.setPassword("user3");
		u2.setImage(u2.getLogin()+".jpg");
		personneRepository.save(u2);
	}


	@Override
	public void initCategories() {
		Categorie c = new Categorie();
      	c.setNom("Mixte");
      	c.setImage("cat1.jpg");
      	c.setDescription("Le modèle Catégorie mixte permet d'indiquer que la catégorie regroupe à la fois des questions culturels au bien sprtif .");
      	categorieRepository.save(c);
		Categorie c2 = new Categorie();
      	c2.setNom("Sportif");
      	c2.setImage("cat2.jpg");
      	c2.setDescription("Le modèle Catégorie sportif permet d'indiquer que la catégorie regroupe just les questions concernant le sport ");

      	categorieRepository.save(c2);
    	categorieRepository.save(c);
		Categorie c3 = new Categorie();
      	c3.setNom("Culturel");
      	c3.setImage("cat2.jpg");
      	c3.setDescription("Catégorie culturel permet d'indiquer que la catégorie regroupe just les questions concernant la culture");

      	categorieRepository.save(c3);
	}
	@Override
	public void initQuizs() {
	categorieRepository.findAll().forEach(c->{
		personneRepository.findAll().forEach(p->{
			Quiz quiz = new Quiz();
			quiz.setAccepte(true);
			quiz.setDocAuto(false);
			quiz.setDescription("descri ......");
			quiz.setOwner(p.getNom()+" "+p.getPrenom());
			quiz.setPersonne(p);
			quiz.setNbrQuestions(15);
			quiz.setCategorie(c);
			quizRepository.save(quiz);
		  });
		});
	}
	@Override
	public void initQuestions() {
		
		quizRepository.findAll().forEach(quiz->{
			ArrayList<Integer> Sols = new ArrayList();
			for (int i=0;i<quiz.getNbrQuestions();i++) {
				QuestionCocher question = new QuestionCocher();
				question.setType("questionCocher");
				question.setDescription("De quel mouvement ou école de peinture\r\n"
						+ "l'artiste Franz Pforr faisait-il partie");
				question.setNbrProposition(4);
				question.setNbrSolutions(1);
				Sols.clear();
				question.setQuiz(quiz);
				Sols.add(1);
				question.setSolutionsCocher(Sols);
				questionRepository.save(question);
			}
			
		});
		
		
	}
	@Override
	public void initDocuments() {
	 questionRepository.findAll().forEach(question->{
		 Document document = new Document();
		 ArrayList<String> descriptions = new ArrayList<>();
		 ArrayList<String> images = new ArrayList<>();
		 for(int i = 1 ;i<4 ;i++) {
				 descriptions.add("The raising of Jairus' daughter is a reported miracle of Jesus that occurs in the synoptic Gospels, where it is interwoven with the account of the healing of a bleeding woman. The narratives can be found in Mark 5:21–43, Matthew 9:18–26 and Luke 8:40–56."
				 		+ "Jesus took the girl by the hand. He told her to get up. She stood up and walked. Her parents were amazed. Jesus told them not to tell anyone what had happened. Then He told her parents to give the girl something to eat.");
				// images.add("q"+question.getQuiz().getId()+"_q"+question.getId()+"_I"+i+".jpg");
				 images.add("q1_q1_I2.jpg");
		  }
		 document.setQuestion(question);
		 document.setDescriptions(descriptions);
		 document.setImages(images);
		 question.setDocument(document);
		 documentRepository.save(document);
	   });	
	}
	@Override
	public void initPropositions() {
		ArrayList<Boolean> bools = new ArrayList<>();
		bools.add(true);
		bools.add(false);
		ArrayList<String> list = new ArrayList<>();
		list.add("Futuristes");
		list.add("Nazaréens");
		list.add("Anciens");
		list.add("Cubistes");

		questionRepository.findAll().forEach(question->{
			for(int i =0 ;i<question.getNbrProposition();i++){
				Proposition p = new Proposition();
				p.setProposCocher(list);
				p.setProposRemplire(new ArrayList<>());
				p.setPropoVraieFauxs(bools);
				p.setQuestion(question);
				question.setProposition(p);
				propositionRepository.save(p);
			}
			

		});
	}
	
	@Override
	public void initAides() {
		questionRepository.findAll().forEach(question->{
			Aide aide = new Aide();
			
			question.setAide(aide);
			aide.setQuestion(question);
			aide.setAide("The Savior had everyone leave the house except His disciples, Jairus, and Jairus’s wife. They went to the room where the little girl was lying.");
			aideRepository.save(aide);
		});
		
	}
	@Override
	public void initScores() {
       userRepository.findAll().forEach(u->{
			   Score score = new Score();
			   score.setQuizs(new ArrayList<Long>());
			   score.setScores(new ArrayList<Integer>());
			   score.setUser(u);
			   scoreRepository.save(score);
	   });	
	}
}
