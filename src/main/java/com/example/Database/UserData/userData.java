package com.example.Database.UserData;

public class userData {
    private String email;
    private String googleID;
    private String loggedIN;
    private String name;
    private String photo;
    private String iat;

    public userData(){
        super();
    }

    public userData(String email, String googleID,String loggedIN, String name, String photo,String iat) {
        this.email = email;
        this.googleID = googleID;
        this.name = name;
        this.photo = photo;
        this.loggedIN=loggedIN;
        this.iat = iat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoggedIN() {
        return loggedIN;
    }

    public void setLoggedIN(String loggedIN) {
        this.loggedIN = loggedIN;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }
}
/*
* {
    "googleID" : "001",
    "portfolioLink" : "abc.com",
    "courseID" : "01",
    "completionStatus" : "INCOMPLETE",
    "quizID" : "1.1",
    "score" : "20"

}
* */