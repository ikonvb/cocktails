package com.bulyginkonstantin.cocktail.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoctailDao {

    @Query("SELECT * FROM cocktails")
    LiveData<List<Cocktail>> getAllCocktails();

    @Query("SELECT * FROM cocktails WHERE strDrink == :name")
    Cocktail getCocktailByName(String name);

    @Query("SELECT * FROM cocktails WHERE id == :id")
    Cocktail getCocktailById(int id);

    @Query("DELETE FROM cocktails")
    void deleteAllCocktails();

    @Insert
    void insertCocktail(Cocktail cocktail);

    @Delete
    void deleteOneCocktail(Cocktail cocktail);

}
