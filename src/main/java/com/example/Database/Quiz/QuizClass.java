package com.example.Database.Quiz;

public class QuizClass {
    private String courseID;
    private String topicID;
    private String quizID;

    public QuizClass(){
        super();
    }

    public QuizClass(String courseID, String topicID, String quizID) {
        this.courseID = courseID;
        this.topicID = topicID;
        this.quizID = quizID;
    }

    @Override
    public String toString() {
        return "courseID "+ courseID + " topicID "+ topicID+ " quizID "+ quizID;
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


}
