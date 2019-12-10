package com.example.assignment.models;

// Imports Section
import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

/*************************************
 *
 * Assignment        : Mobile Software Development CA
 * Package           : com.example.assignment
 * File              : models/MemberList.java
 * Author            : Eoghan Byrne
 * Student number    : C17315336
 * Last modified     : 10th December 2019
 *
 *************************************/

@Entity(tableName = "member")
public class MemberList {

    // Define table columns and constraints
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    private String category;
    private String name;
    private String detail;


    // Setters and Getters for the above
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }
}
