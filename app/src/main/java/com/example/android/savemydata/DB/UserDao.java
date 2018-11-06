package com.example.android.savemydata.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.android.savemydata.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from user_")
    List<User> getAll();
    @Query("SELECT * FROM user_ where password = :password")
    User findByPassword(String password);
    @Query("SELECT * FROM user_ WHERE id=:id")
    User findByID(Long id);
    @Query("SELECT COUNT(*) from user_")
    int countUsers();
    @Insert
    long insert(User user);
    @Delete
    int delete(User user);
}