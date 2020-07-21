package com.example.Database.Student;

import java.util.ArrayList;
import java.util.List;

public class EnrolledCourses {
    private String completionStatus;
    private  String courseID;



    private double percentage;
    //protected ArrayList<QuizData> qz = new ArrayList<QuizData>();


    public EnrolledCourses(){
        super();
    }

    public EnrolledCourses(String completionStatus, String courseID, double percentage) {
        this.completionStatus = completionStatus;
        this.courseID = courseID;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return completionStatus+ " "+courseID;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }




}