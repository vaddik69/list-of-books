package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoBooks {
    @Insert
    public void addBook(Books book);
    @Update
    public void updateBook(Books book);
    @Delete
    public void deleteBook(Books book);

    @Query("SELECT * FROM books")
    public List<Books> getAllBooks();
    @Query("SELECT count(*) FROM books")
    public int getCountBook();
    @Query("SELECT * FROM books WHERE name == :name AND author == :author")
    public Books getBook(String name, String author);
}
