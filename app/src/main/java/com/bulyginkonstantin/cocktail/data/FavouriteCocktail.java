package com.bulyginkonstantin.cocktail.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.bulyginkonstantin.cocktail.converters.ConverterIngredients;

import java.util.List;

@Entity(tableName = "favourite_cocktails")
@TypeConverters(value = ConverterIngredients.class)
public class FavouriteCocktail extends Cocktail {

    public FavouriteCocktail(int id, String strDrink, String strCategory, String strAlcoholic, String strGlass, String strInstructions, String strDrinkThumb, String dateModified, List<String> ingredients) {
        super(id, strDrink, strCategory, strAlcoholic, strGlass, strInstructions, strDrinkThumb, dateModified, ingredients);
    }

    @Ignore
    public FavouriteCocktail(Cocktail cocktail) {
        super(cocktail.getId(), cocktail.getStrDrink(), cocktail.getStrCategory(), cocktail.getStrAlcoholic(), cocktail.getStrGlass(), cocktail.getStrInstructions(), cocktail.getStrDrinkThumb(), cocktail.getDateModified(), cocktail.getIngredients());
    }
}
