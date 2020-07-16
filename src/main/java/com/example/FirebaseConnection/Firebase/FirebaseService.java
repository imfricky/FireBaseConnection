package com.example.FirebaseConnection.Firebase;


import com.example.Database.Category.CourseCat;
import com.example.Database.Category.category;
import com.example.Database.Courses.Topic;
import com.example.Database.Courses.courses;
import com.example.Database.Person;
import com.example.Database.Student.EnrolledCourses;
import com.example.Database.Student.QuizData;
import com.example.Database.Student.Student;
import com.example.Database.UserData.userData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
    public String enrollCourse(String googleID, String courseID, String completionStatus){
        Firestore db = FirestoreClient.getFirestore();
        EnrolledCourses ec = new EnrolledCourses();
        ec.setCourseID(courseID);
        ec.setCompletionStatus(completionStatus);
        ApiFuture<WriteResult> future = db.collection("Student").document(googleID).collection("Enrolled Courses").document(courseID).set(ec);
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


