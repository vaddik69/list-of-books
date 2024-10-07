package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Books {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    public int id;

    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "genre")
    public String genre;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "rating")
    public String rating;

    public Books(String author, String genre, String name, String date, String rating) {
        this.id = 0;
        this.author = author;
        this.genre = genre;
        this.name = name;
        this.date = date;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }

    public String getRating() {
        return rating;
    }
}
