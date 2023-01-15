package com.example.aerotrackerapp;

public class Category {
   private String categoryType;
    private int id;

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(String categoryType) {
        this.categoryType = categoryType;
    }
}
