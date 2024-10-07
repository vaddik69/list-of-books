package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Users {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public int id;

    @ColumnInfo(name = "login")
    public String login;
    @ColumnInfo(name = "password")
    public String password;

    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public Users(String login, String password) {
        this.id = 0;
        this.login = login;
        this.password = password;
    }
}
