package com.example.assignment.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.assignment.models.MemberList;


@Database(entities = {MemberList.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberListDAO getMemberListDAO();
}
