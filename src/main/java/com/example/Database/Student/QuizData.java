package com.example.Database.Student;

public class QuizData {
    private String quizID;
    private String score;

    public QuizData(){
        super();
    }

    public QuizData(String quizID, String score) {
        this.quizID = quizID;
        this.score = score;
    }

    @Override
    public String toString() {
        return quizID+" "+ score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }
}
