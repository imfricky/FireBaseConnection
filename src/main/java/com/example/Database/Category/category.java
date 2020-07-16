package com.example.Database.Category;

import java.util.Vector;

public class category {
    private String categoryID;
    private String catName;



    public category(String categoryID, String catName) {
        this.categoryID = categoryID;
        this.catName = catName;
    }

    public category(){
        super();
    }



    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }


}
