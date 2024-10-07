package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoUsers {
    @Insert
    public void addUser(Users user);
    @Update
    public void updateUser(Users user);
    @Delete
    public void deleteUser(Users user);

    @Query("SELECT * FROM users")
    public List<Users> getAllUsers();
    @Query("SELECT * FROM users WHERE login == :login")
    public Users getUser(String login);
    @Query("SELECT user_id FROM users WHERE login == :login")
    public int getUserId(String login);
}
