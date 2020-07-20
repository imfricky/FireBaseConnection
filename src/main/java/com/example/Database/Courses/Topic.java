package com.example.Database.Courses;

public class Topic {
    private String topicID;
    private String topicName;
    private String quizID;
    private String videoID;
    private String url;
    private String description;


    public Topic(){
        super();
    }

    public Topic(String topicID, String topicName, String quizID, String videoID, String url, String description) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.quizID = quizID;

        this.videoID = videoID;
        this.url = url;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Topic ID : "+ topicID +"\n";
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }


    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

/*
* {
        "topicID" : "101.1",
        "topicName" "Installation",
        "quizID" "101.1.2",
        "videoID" : "101.1.1",
        "url" "abc.com",
        "description"  : "This is the installation video"
    }
* */