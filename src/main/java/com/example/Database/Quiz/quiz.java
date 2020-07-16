package com.example.Database.Quiz;

import java.util.List;

public class quiz {
    private String courseID;
    private String topicID;
    private String quizID;
    private List<questions> questions;

    public quiz(String courseID, String topicID, String quizID) {
        this.courseID = courseID;
        this.topicID = topicID;
        this.quizID = quizID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public List<com.example.Database.Quiz.questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<com.example.Database.Quiz.questions> questions) {
        this.questions = questions;
    }
}
