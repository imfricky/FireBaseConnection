package com.example.Database.Student;

public class Topics {
    private String topicID;
    private String completionStatus;
    private String quizScore="0";

    public Topics(){
        super();
    }

    public Topics(String topicID, String completionStatus, String quizScore) {
        this.topicID = topicID;
        this.completionStatus = completionStatus;
        this.quizScore = quizScore;
    }

    @Override
    public String toString() {
        return "quiz score : " + quizScore + "\n";
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }


    public String getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(String quizScore) {
        this.quizScore = quizScore;
    }
}
