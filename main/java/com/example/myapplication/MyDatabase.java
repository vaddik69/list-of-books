package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class, Books.class, FavouriteBook.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DaoUsers getDaoUsers();
    public abstract DaoBooks getDaoBooks();
    public abstract DaoFavouriteBook getDaoFavouriteBook();
}
