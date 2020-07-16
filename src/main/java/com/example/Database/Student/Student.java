package com.example.Database.Student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String googleID;
    private String portfolioLink;
  //  protected ArrayList<EnrolledCourses> ec = new ArrayList<EnrolledCourses>();

    public Student(){
        super();
    }

    public Student(String googleID, String portfolioLink) {
        this.googleID = googleID;
        this.portfolioLink = portfolioLink;
        //this.ec = ec;
    }
    @Override
    public String toString(){
        return googleID+" "+portfolioLink;

    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getPortfolioLink() {
        return portfolioLink;
    }

    public void setPortfolioLink(String portfolioLink) {
        this.portfolioLink = portfolioLink;
    }


}