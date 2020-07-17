package com.example.Database.Student;

public class Topics {
    private String topicID;
    private String completionStatus;

    public Topics(){
        super();
    }
    public Topics(String topicID, String completionStatus) {
        this.topicID = topicID;
        this.completionStatus = completionStatus;
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


}
