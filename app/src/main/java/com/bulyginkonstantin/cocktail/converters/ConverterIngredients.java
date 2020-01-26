package com.bulyginkonstantin.cocktail.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ConverterIngredients {

    @TypeConverter
    public String listIngredientsToString(List<String> ingredients) {

        return new Gson().toJson(ingredients);
    }

    @TypeConverter
    public List<String> stringToListIngredients(String ingredientsAsString) {
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(ingredientsAsString, ArrayList.class);
        ArrayList<String> ingredients = new ArrayList<>();
        for (Object o: objects) {
            ingredients.add(o.toString());
        }

        return ingredients;
    }
}
