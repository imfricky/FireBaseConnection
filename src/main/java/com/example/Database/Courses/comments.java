package com.example.Database.Courses;

public class comments {
    private String googleID;
    private String comment;
    private String commentID;
    private int upvoteCount;
    private int downvoteCount;

    public comments(){
        super();
    }
    public comments(String googleID, String comment, String commentID, int upvoteCount,int downvoteCount) {
        this.googleID = googleID;
        this.comment = comment;
        this.commentID = commentID;
        this.upvoteCount = upvoteCount;
        this.downvoteCount=downvoteCount;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public int getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(int downvoteCount) {
        this.downvoteCount = downvoteCount;
    }
}
