package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategorieRepository;
import com.example.demo.dao.DocumentRepository;
import com.example.demo.dao.PersonneRepository;
import com.example.demo.dao.QuizRepository;
import com.example.demo.dao.ScoreRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Document;
import com.example.demo.entities.Personne;
import com.example.demo.entities.QuestionCocher;
import com.example.demo.entities.Score;
import com.example.demo.entities.User;
import com.example.demo.entities.Quiz;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.management.loading.PrivateClassLoader;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class QuizRestController {
    @Autowired
    private DocumentRepository documentRepository ;
    @Autowired
    private PersonneRepository personneRepository ;
    @Autowired
    private CategorieRepository categorieRepository ;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private QuizRepository quizRepository;
   
    /***********************************************/
    static Long id ;
    static String owwnerName;
    static List<String> images = new ArrayList<>();
    static List<String> descreptions = new ArrayList<>();
    
    /***********************************************/
    
    @GetMapping(path = "/imageDoc/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @Transactional
    public byte[] image(@PathVariable(name = "id")Long id) throws Exception {
        Document d = documentRepository.findById(id).get();
        String image = d.getImages().get(0) ;
        File file = new File(System.getProperty("user.home")+"/Quiz/imagesDocs/"+image);
           Path path = Paths.get(file.toURI());
           return Files.readAllBytes(path);
    }
    
    @GetMapping(path = "/imageUser/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @Transactional
    public byte[] image2(@PathVariable(name = "id")Long id) throws Exception {
        Personne u = personneRepository.findById(id).get();
        String image = u.getImage() ;
        File file = new File(System.getProperty("user.home")+"/Quiz/imagesUsers/"+image);
           Path path = Paths.get(file.toURI());
           return Files.readAllBytes(path);
    }
    
   @PostMapping(path="/UploadUserImage/{id}")
    @Transactional
    public void uploadUserImage(MultipartFile file,@PathVariable(name = "id") Long id) throws IOException {
    	 Personne u = personneRepository.findById(id).get();
    	 System.out.println("this is the id "+id);
         u.setImage(file.getOriginalFilename());
    	 u.setImage("user"+id+".jpg");
    	 Files.write(Paths.get(System.getProperty("user.home")+"/Quiz/imagesUsers/"+u.getImage()),file.getBytes());
    	 personneRepository.save(u);
    }
   
   @PostMapping(path="/UploadAdminImage/{id}")
   @Transactional
   public void uploadAdminImage(MultipartFile file,@PathVariable(name = "id") Long id) throws IOException {
   	 Admin a = (Admin) personneRepository.findById(id).get();
   	 System.out.println("this is the id "+id);
     //a.setImage(file.getOriginalFilename());
   	 a.setImage("user"+id+".jpg");
   	 Files.write(Paths.get(System.getProperty("user.home")+"/Quiz/imagesUsers/"+a.getImage()),file.getBytes());
   	System.out.println(a.getImage());
   	 personneRepository.save(a);
   }

    
    
    @GetMapping(path = "/imageCategorie/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @Transactional
    public byte[] image3(@PathVariable(name = "id")Long id) throws Exception  {
        Categorie c = categorieRepository.findById(id).get();
        String image = c.getImage() ;
        File file = new File(System.getProperty("user.home")+"/Quiz/imagesCategories/"+image);
           Path path = Paths.get(file.toURI());
           return Files.readAllBytes(path);
    }
    
    
    
  
    @GetMapping(path = "/login/{login}/{password}")
    @Transactional
    public Integer login(@PathVariable(name = "login")String login ,@PathVariable(name = "password") String password) {
    	int e = 0;
    	ArrayList<Personne> personnes = (ArrayList<Personne>) personneRepository.findAll(); 
    	for(Personne p : personnes) {
    		if(p.getLogin().equals(login)&&p.getPassword().equals(password)) {
    			if(p instanceof Admin) {
    				e = 1;
    			}
    			else {
    				e = 2;
    			}
    		}
    	}
		return e;
    }
    
    @PostMapping(path="/InscriptionUser")
    @Transactional
    public Long InscriptionUser(@RequestBody String u) throws IOException {
    	Gson g = new Gson();  
    	Personne user = g.fromJson(u, User.class);
        personneRepository.save(user);    
    	System.out.println(user.toString());
    	Personne p = personneRepository.findByLogin(user.getLogin());
    	System.out.println(p);
    	return p.getId();
    }
    
    
    @PostMapping(path="/addScoreUser")
    @Transactional
    public void addScoreUser(@RequestBody String a) throws IOException {
        Gson g = new Gson(); 
        jsonScore o = g.fromJson(a, jsonScore.class);
        System.out.println(o.toString());
        String userLogin = o.userLogin ;
        Long quizId = o.quizId;
        Integer scoreJson = o.score;
        int  sg = 0;
      
    	User u = (User) personneRepository.findByLogin(userLogin);
    	ArrayList<Integer> scores = u.getScore().getScores();
    	ArrayList<Long> quizs = u.getScore().getQuizs();
    	scores.add(scoreJson);
    	quizs.add(quizId);
    	for(int i : scores){
    		sg = sg + i;
    	}
    	Score score = u.getScore();
    	score.setQuizs(quizs);
    	score.setScores(scores);
    	score.setScoreGlobal(sg);
    	u.setScore(score);
    	personneRepository.save(u);
    	
    	
    }
    
    
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class jsonScore   {
    	private String userLogin;
    	private Long quizId ;
    	private int score ;
    }


    @GetMapping(path = "/imageDocByName/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    @Transactional
    public byte[] image3(@PathVariable(name = "name")String name) throws Exception  {
        File file = new File(System.getProperty("user.home")+"/Quiz/imagesDocs/"+name);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }


    @PostMapping(path="/addQuizUser/{login}")
    @Transactional
    public void  addUserQuiz(@PathVariable(name = "login")String login,@RequestBody Collection<QuestionCocher> questions) throws IOException {
              
        System.out.println(questions.size());
        for(QuestionCocher q : questions) {
        	System.out.println(q.getDescription());
        	System.out.println(q.getSolutionsCocher());
        }
    }
    @PostMapping(path="/UploadDocImage/{name}")
    @Transactional
    public void uploadDocImage(MultipartFile file,@PathVariable(name = "name")String name) throws IOException {
    	 List<Quiz> quizzes = quizRepository.findAll();
    	 Long id=(long) -1 ;
    	 for(Quiz q : quizzes){
    		 id = q.getId();
    	 }
    	 ++id;
       	 name = name.replace("0", id+"");
       	 System.out.println("this is the name "+ name+" this is the id :"+id);
    	 Files.write(Paths.get(System.getProperty("user.home")+"/Quiz/imagesDocs/"+name),file.getBytes());
    }
}