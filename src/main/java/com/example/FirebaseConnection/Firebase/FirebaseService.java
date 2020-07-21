package com.example.FirebaseConnection.Firebase;


import com.example.Database.Category.CourseCat;
import com.example.Database.Category.category;
import com.example.Database.Courses.Topic;
import com.example.Database.Courses.courses;
import com.example.Database.Person;
import com.example.Database.Quiz.QuizClass;
import com.example.Database.Quiz.questions;
import com.example.Database.Student.EnrolledCourses;
import com.example.Database.Student.QuizData;
import com.example.Database.Student.Student;
import com.example.Database.Student.Topics;
import com.example.Database.UserData.userData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import com.google.protobuf.Api;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    public String signinuser(userData userdata) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("User").document(userdata.getGoogleID()).set(userdata);
        return "SignUp Successful for : "+ userdata.getGoogleID();
    }

    public String logoutUser(userData userdata)throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("User").document(userdata.getGoogleID()).set(userdata);
        return "Logout Successful for : "+ userdata.getGoogleID();
    }
    public void enrollStudent(String googleID, String portfolioLink){
        Firestore db = FirestoreClient.getFirestore();
        Student student = new Student();
        student.setGoogleID(googleID);
        student.setPortfolioLink(portfolioLink);
        ApiFuture<WriteResult> future = db.collection("Student").document(googleID).set(student);
        return;
    }
    public String enrollCourse(String googleID, String courseID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        EnrolledCourses ec = new EnrolledCourses("Incomplete", courseID,0);
        ApiFuture<WriteResult> future = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).set(ec);

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = db.collection("Courses").document(courseID).collection("Topics").get();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshotApiFuture.get().getDocuments();
        ArrayList<Topic> topics = new ArrayList<>();
        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
            topics.add(queryDocumentSnapshot.toObject(Topic.class));
        }
        for(int i =0; i< topics.size();i++){
            Topic topic = topics.get(i);
            Topics topics1 = new Topics(topic.getTopicID(),"Incomplete","0");
            ApiFuture<WriteResult> future1 = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).collection("Topics").document(topics1.getTopicID()).set(topics1);

        }

        // ApiFuture<QuerySnapshot> querySnapshot = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).collection("Topics").get();
        // List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshot.get().getDocuments();


        return "Successfully Registered";
    }

    public ArrayList getEnrolledCourses(String googleID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Student").document(googleID).collection("Enrolled Courses").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        ArrayList<EnrolledCourses> ec = new ArrayList<>();
        for (QueryDocumentSnapshot document : documentSnapshots){
            ec.add(document.toObject(EnrolledCourses.class));
        }
        return ec;

    }

    public void addNewCourse(courses crs) throws Exception {
        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> future = db.collection("Courses").document(crs.getCourseID()).set(crs);
        CourseCat courseCat = new CourseCat(crs.getCatID(),crs.getCourseCategory(),crs.getCourseID(),crs.getCourseName(), crs.getphoto());

        DocumentReference documentReference = db.collection("Category").document(crs.getCatID());
        ApiFuture<DocumentSnapshot> future1 = documentReference.get();
        DocumentSnapshot documentSnapshot = future1.get();

        if (documentSnapshot.exists())
        {
            ApiFuture<WriteResult> future2 = db.collection("Category").document(courseCat.getCatID()).collection("Courses").document(courseCat.getCourseID()).set(courseCat);

        }
        else
        {
            System.out.println("No such Category Exists with catID "+ crs.getCatID());
        }


    }

    public courses getCourseDetails(String courseID) throws ExecutionException, InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection("Courses").document(courseID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        courses crs = new courses();
        if (documentSnapshot.exists())
        {
            crs = documentSnapshot.toObject(courses.class);
            return crs;
        }
        else{
            return null;
        }

    }
    public ArrayList getAllCourses() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Courses").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        ArrayList<courses> crs = new ArrayList<>();
        for (QueryDocumentSnapshot document : documentSnapshots){
            crs.add(document.toObject(courses.class));
        }
        return crs;
    }
    public void addNewTopics(String courseID, Topic topic) throws Exception{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("Courses").document(courseID).collection("Topics").document(topic.getTopicID()).set(topic);

    }

    public ArrayList getAllTopics(String courseID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Courses").document(courseID).collection("Topics").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        ArrayList<Topic> topics = new ArrayList<>();
        for (QueryDocumentSnapshot document : documentSnapshots){
            topics.add(document.toObject(Topic.class));
        }
        return topics;
    }
    public void newCategory(category cat) throws ExecutionException,InterruptedException{
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("Category").document(cat.getCategoryID()).set(cat);
    }

    public void addCoursesToCategory(String catID, CourseCat courseCat) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection("Category").document(catID);
        ApiFuture<DocumentSnapshot> future1 = documentReference.get();
        DocumentSnapshot documentSnapshot = future1.get();
        category cat = new category();
        if (documentSnapshot.exists())
        {
            cat = documentSnapshot.toObject(category.class);

        }
        else
        {
            System.out.println("No such Category Exists");
        }




        ApiFuture<WriteResult> future = db.collection("Category").document(catID).collection("Courses").document(courseCat.getCourseID()).set(courseCat);
    }
    public ArrayList getAllCategory() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Category").get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        ArrayList<category> categories = new ArrayList<>();
        for(QueryDocumentSnapshot documents : documentSnapshots){
            categories.add(documents.toObject(category.class));
        }
        return categories;
    }
    public ArrayList getAllCoursesInCategory(String catID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Category").document(catID).collection("Courses").get();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = future.get().getDocuments();
        ArrayList<CourseCat> courseCats = new ArrayList<>();
        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
            courseCats.add(documentSnapshot.toObject(CourseCat.class));
        }
        return courseCats;
    }
    public void addQuiz(QuizClass quizClass){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future1 = db.collection("Quiz").document(quizClass.getQuizID()).set(quizClass);
        //ApiFuture<WriteResult> future2 = db.collection("Courses").document(quizClass.getCourseID()).collection("Topics").document(quizClass.getTopicID()).collection("Quiz").document(quizClass.getQuizID()).set(quizClass);

    }
    public void addquestions(String quizID, String quesID, questions question){
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("Quiz").document(quizID).collection("Questions").document(quesID).set(question);
    }
    public ArrayList getAllQuestions(String quizID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Quiz").document(quizID).collection("Questions").get();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = future.get().getDocuments();
        ArrayList<questions> questionsArrayList = new ArrayList<>();
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
            questionsArrayList.add(documentSnapshot.toObject(questions.class));

        }
        return questionsArrayList;
    }
    public void submitQuiz(String googleID,String quizID,String quizScore) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection("Quiz").document(quizID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        QuizClass quizClass = new QuizClass();
        if (documentSnapshot.exists()){
            quizClass = documentSnapshot.toObject(QuizClass.class);
            //System.out.println(quizClass);
        }
        else{
            System.out.println("Error in finding document of quizID "+ quizID);

        }

        Topics topics = new Topics(quizClass.getTopicID(),"Complete",quizScore);
        //topics.setCompletionStatus("Complete");
        //topics.setTopicID(quizClass.getTopicID());
        //topics.setQuizScore(quizScore);

        DocumentReference documentReference1 = db.collection("Student").document(googleID).collection("Enrolled Courses").document(quizClass.getCourseID());
        ApiFuture<DocumentSnapshot> future1 = documentReference1.get();
        DocumentSnapshot documentSnapshot1 = future1.get();

        //ApiFuture<WriteResult> future2 = db.collection("Student").document(googleID).collection("Enrolled Courses").document(quizClass.getCourseID()).collection("Topics").document(quizClass.getTopicID()).set(topics);
        if(documentSnapshot1.exists()) {
            ApiFuture<WriteResult> future2 = db.collection("Student").document(googleID).collection("Enrolled Courses").document(quizClass.getCourseID()).collection("Topics").document(quizClass.getTopicID()).set(topics);
        }
        else {
            System.out.println("Course "+ quizClass.getCourseID()+ "not enrolled into student " + googleID);
        }
        String courseID = quizClass.getCourseID();
        topicsenrolled(googleID,courseID);

    }
    public void topicsenrolled(String googleID, String courseID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).collection("Topics").get();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshot.get().getDocuments();
        ArrayList<Topics> topics = new ArrayList<>();
        int marks=0;
        int totalQuiz =0;

        for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
            topics.add(queryDocumentSnapshot.toObject(Topics.class));

        }
        for(int i =0 ; i< topics.size();i++){
            Topics topics1 = new Topics();
            totalQuiz++;
            topics1 = topics.get(i);
            //System.out.println(topics1);
            marks +=Integer.parseInt(topics1.getQuizScore())  ;
        }

        int flag=0;
        for(int i =0 ; i< topics.size();i++){
            Topics topics1 = new Topics();

            topics1 = topics.get(i);
            //System.out.println(topics1);
            if (topics1.getCompletionStatus().equals("Complete"))  {
                flag++;
            }


        }
        if(flag==(topics.size())){

            DocumentReference documentReference = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            EnrolledCourses ec = new EnrolledCourses();
            if(documentSnapshot.exists()){
                ec = documentSnapshot.toObject(EnrolledCourses.class);
            }
            else{
                System.out.println("No Enrolled course found for student id "+ googleID +"and courseID "+ courseID );

            }
            double percentage=0;
            percentage = ((double) marks/((double) totalQuiz*5))*100;

            double percent = Math.round(percentage * 100.0) / 100.0;
            System.out.println(percent);
            ec.setCompletionStatus("Complete");
            ec.setPercentage(percent);
            ApiFuture<WriteResult> future1 = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).set(ec);

            System.out.println("Course Completed");

        }
        //System.out.println(marks);
        //System.out.println(topics);

    }

    public ArrayList completedCourses(String googleID) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Student").document(googleID).collection("Enrolled Courses").get();
        List<QueryDocumentSnapshot> queryDocumentSnapshots = future.get().getDocuments();
        ArrayList<EnrolledCourses> ec = new ArrayList<>();
        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots){
            EnrolledCourses ec1 = new EnrolledCourses();
            ec1 = queryDocumentSnapshot.toObject(EnrolledCourses.class);
            if(ec1.getCompletionStatus().equals("Complete")) {
                ec.add(queryDocumentSnapshot.toObject(EnrolledCourses.class));
            }
        }

        return ec;

    }
    //***************************************************************************************



    public Person getUserDetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();


        Person person = null;

        if (documentSnapshot.exists())
        {
            person = documentSnapshot.toObject(Person.class);
            return person;
        }
        else{
            return null;
        }
    }

    public Student savedetails(Student student){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try{
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("Student").document(student.getGoogleID()).set(student);

        }
        catch(Exception e){
            System.out.println("Exception caught "+ e );
        }
        return student;
    }





    public String enrollQuiz(String googleID, String courseID, String quizID, String score){
        Firestore db = FirestoreClient.getFirestore();
        QuizData qz = new QuizData();
        qz.setQuizID(quizID);
        qz.setScore(score);
        ApiFuture<WriteResult> apiFuture = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).collection("Quiz").document(quizID).set(qz);
        return "success";
    }





}


