package com.bulyginkonstantin.cocktail.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cocktail.class}, version = 1, exportSchema = false)
public abstract class CocktailDatabase extends RoomDatabase {

    private static final String DB_NAME = "cocktails.db";
    private static final Object LOCK = new Object();
    private static CocktailDatabase database;

    public static CocktailDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, CocktailDatabase.class, DB_NAME).build();
            }
            return database;
        }
    }

    public abstract CoctailDao getCocktailDao();
}
