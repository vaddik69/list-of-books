package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_books", foreignKeys = {
        @ForeignKey(entity = Users.class,
        parentColumns = "user_id",
        childColumns = "id_user",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),

        @ForeignKey(entity = Books.class,
        parentColumns = "book_id",
        childColumns = "id_book",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)})

public class FavouriteBook {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favourite_book_id")
    int favourite_book_id;

    @ColumnInfo(name = "id_user")
    int id_user;

    @ColumnInfo(name = "id_book")
    int id_book;

    public FavouriteBook(int id_user, int id_book) {
        this.favourite_book_id = 0;
        this.id_user = id_user;
        this.id_book = id_book;
    }
}
