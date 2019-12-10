package com.example.assignment1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.assignment1.models.MemberList;


@Database(entities = {MemberList.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberListDAO getMemberListDAO();
}
