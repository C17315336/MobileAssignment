package com.example.assignment.db;

// Imports Section
import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.assignment.models.MemberList;

/*************************************
 *
 * Assignment        : Mobile Software Development CA
 * Package           : com.example.assignment
 * File              : db/AppDatabase.java
 * Author            : Eoghan Byrne
 * Student number    : C17315336
 * Last modified     : 10th December 2019
 *
 *************************************/

@Database(entities = {MemberList.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberListDAO getMemberListDAO();
}
