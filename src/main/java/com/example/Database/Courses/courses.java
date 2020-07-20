package com.example.Database.Courses;

public class courses {
    private String catID;
    private String courseCategory;
    private String courseID;
    private String courseName;
    private String description;
    private String duration;
    private int currentlyEnrolled;
	private String photo;



    public courses(){
        super();
    }


    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCurrentlyEnrolled() {
        return currentlyEnrolled;
    }

    public void setCurrentlyEnrolled(int currentlyEnrolled) {
        this.currentlyEnrolled = currentlyEnrolled;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }
	
	public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }
}
/**
 *
 {
 "catID" : "60",
 "courseCategory" : "AI",
 "courseID" : "102",
 "courseName" : "Artificial Intelligence 1",
 "description" : "The following course is under AI",
 "duration" : "10:00:00",
 "currentlyEnrolled" : "1",
 "photo" : "1.jpg"
 }
 */
