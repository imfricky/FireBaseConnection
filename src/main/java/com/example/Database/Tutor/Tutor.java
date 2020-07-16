package com.example.Database.Tutor;

public class Tutor {

    private String googleID;
    private String courseID;
    private String profileLink;

    public Tutor(){
        super();
    }

    public Tutor(String googleID, String courseID, String profileLink) {
        super();
        this.googleID = googleID;
        this.courseID = courseID;
        this.profileLink = profileLink;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }
}