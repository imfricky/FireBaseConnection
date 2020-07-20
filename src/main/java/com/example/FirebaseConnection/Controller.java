package com.example.FirebaseConnection;

import com.example.Database.Category.CourseCat;
import com.example.Database.Category.category;
import com.example.Database.Courses.Topic;
import com.example.Database.Courses.courses;
import com.example.Database.Person;
import com.example.Database.Quiz.QuizClass;
import com.example.Database.Quiz.questions;
import com.example.Database.UserData.userData;
import com.example.FirebaseConnection.Firebase.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
@CrossOrigin(origins = "*")
@RestController
public class Controller {
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/signin") // for signin of user and database entry
    public String createuser(@RequestBody userData userdata) throws ExecutionException, InterruptedException {
        // for signups and logins
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        String portfolio = null;
        firebaseService.enrollStudent(userdata.getGoogleID(),portfolio);
        return firebaseService.signinuser(userdata);
    }
    @PutMapping("/signout") // for signout of user
    public String logoutUser(@RequestBody userData userdata)throws ExecutionException, InterruptedException{
        // for logout for users
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.logoutUser(userdata);
    }
    @PostMapping("/enrollcourses") // to enroll course to student
    public String enrollcourses(@RequestParam String googleID, @RequestParam String courseID)throws ExecutionException, InterruptedException{
        // send Student's googleID and courseID
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");

        return firebaseService.enrollCourse(googleID,courseID);
    }
    @GetMapping("/getenrolledcourses") // To get the students enrolled courses
    public ArrayList getenrolledcourses(@RequestParam String googleID) throws  ExecutionException,InterruptedException{
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
         return firebaseService.getEnrolledCourses(googleID);
    }
    @PostMapping("/addnewcourse")
    public void addnewcourses(@RequestBody courses crs) throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        firebaseService.addNewCourse(crs);
    }
    @GetMapping("/getcoursedetails")
    public courses getcoursedetails(@RequestHeader String courseID) throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.getCourseDetails(courseID);
    }
    @GetMapping("/getallcourses") // Course Dashboard
    public ArrayList getallcourses() throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.getAllCourses();
    }
    @PostMapping("/addnewtopic") // to add new topics in course
    public void addnewtopics(@RequestParam String courseID,@RequestBody  Topic topic) throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        firebaseService.addNewTopics(courseID,topic);
    }
    @GetMapping("/getalltopics")
    public ArrayList getalltopics(@RequestParam String courseID) throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.getAllTopics(courseID);
    }
    @PostMapping("/addcategory")
    public void addcategory(@RequestBody category cat) throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        firebaseService.newCategory(cat);
    }
    @PostMapping("/addnewcoursetocat")
    public void addnewcoursetocat(@RequestHeader String catID, @RequestBody CourseCat courseCat) throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        firebaseService.addCoursesToCategory(catID,courseCat);
    }
    @GetMapping("/getallcategory")
    public ArrayList getallcategory() throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.getAllCategory();
    }
    @GetMapping("/getallcoursesincategory")
    public ArrayList getallcoursesincategory(@RequestParam String catID) throws ExecutionException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "*");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        headers.add("Access-Control-Allow-Credentials", "true");
        return firebaseService.getAllCoursesInCategory(catID);
    }

    @PostMapping("/addquiz")
    public void addquiz(@RequestBody QuizClass quizClass){
        firebaseService.addQuiz(quizClass);
    }

    @PostMapping("/addquestion")
    public void addquestion(@RequestParam String quizID,@RequestParam String quesID, @RequestBody questions question){
        firebaseService.addquestions(quizID,quesID,question);
    }

    @GetMapping("/getquestions")
    public ArrayList getquestions(@RequestParam String quizID) throws ExecutionException, InterruptedException {
        return firebaseService.getAllQuestions(quizID);
    }
    @PostMapping("/submitquiz")
    public void submitquiz(@RequestParam String googleID, @RequestParam String quizID, @RequestParam String quizScore) throws ExecutionException, InterruptedException {
        firebaseService.submitQuiz(googleID,quizID,quizScore);
    }
    @GetMapping("/completedcourses")
    public ArrayList completedCourses(@RequestParam String googleID) throws ExecutionException, InterruptedException {
        return firebaseService.completedCourses(googleID);
    }
//*********************************************************************************
    @GetMapping("/get")
    public Person getuser(@RequestHeader String name) throws ExecutionException, InterruptedException {
        return firebaseService.getUserDetails(name);

    }
    @GetMapping("/topics")
    public void topicsenrolled(@RequestParam String googleID, @RequestParam String courseID) throws ExecutionException, InterruptedException {
         firebaseService.topicsenrolled(googleID,courseID);
    }





@PostMapping("/post2")
    public String abc(@RequestHeader String googleID, @RequestHeader String courseID,@RequestHeader String quizID,@RequestHeader String score){
       return firebaseService.enrollQuiz(googleID,courseID,quizID,score);

}


}
