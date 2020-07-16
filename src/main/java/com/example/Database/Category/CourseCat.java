package com.example.Database.Category;

public class CourseCat {
    private String catID;
    private String courseCategory;
    private String courseID;
    private String courseName;
	private String photo;

    public CourseCat(String catID, String courseCategory, String courseID, String courseName, String photo) {
        this.catID = catID;
        this.courseCategory = courseCategory;
        this.courseID = courseID;
        this.courseName = courseName;
		this.photo = photo;
    }

    public CourseCat(){
        super();
    }


    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
	
	public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }
}
