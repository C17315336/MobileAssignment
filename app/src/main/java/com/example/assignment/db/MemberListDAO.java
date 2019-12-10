package com.example.assignment.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.models.MemberList;

import java.util.List;


@Dao
public interface MemberListDAO {
    @Insert
    public void insert(MemberList... memberLists);

    @Update
    public void update(MemberList... memberLists);

    @Delete
    public void delete(MemberList... memberList);

    @Query("SELECT * FROM member")
    public List<MemberList> getMemberLists();

    @Query("SELECT * FROM member WHERE id = :number")
    public MemberList getMemberListWithId(int number);
}
