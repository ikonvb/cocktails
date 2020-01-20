package com.bulyginkonstantin.cocktail.utils;

import com.bulyginkonstantin.cocktail.data.Cocktail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {

    private static final String KEY_DRINKS = "drinks";
    private static final String KEY_ID = "idDrink";
    private static final String KEY_STR_DRINK = "strDrink";
    private static final String KEY_STR_INSTRUCTIONS = "strInstructions";
    private static final String KEY_STR_DRINK_THUMB = "strDrinkThumb";
    private static final String KEY_STR_INGREDIENT1 = "strIngredient1";
    private static final String KEY_STR_INGREDIENT2 = "strIngredient2";
    private static final String KEY_STR_INGREDIENT3 = "strIngredient3";
    private static final String KEY_STR_INGREDIENT4 = "strIngredient4";
    private static final String KEY_STR_MEASURE1 = "strMeasure1";
    private static final String KEY_STR_MEASURE2 = "strMeasure2";
    private static final String KEY_STR_MEASURE3 = "strMeasure3";
    private static final String KEY_DATE_MODIFIED = "dateModified";

    public static ArrayList<Cocktail> getCocktailsFromJSON(JSONObject jsonObject) {
        ArrayList<Cocktail> result = new ArrayList<>();
        JSONObject objectCocktail;
        Cocktail cocktail;

        if (jsonObject == null) {
            return result;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_DRINKS);
            for (int i = 0; i < jsonArray.length(); i++) {
                objectCocktail = jsonArray.getJSONObject(i);
                int id = objectCocktail.getInt(KEY_ID);
                String strDrink = objectCocktail.getString(KEY_STR_DRINK);
                String strInstructions = objectCocktail.getString(KEY_STR_INSTRUCTIONS);
                String strDrinkThumb = objectCocktail.getString(KEY_STR_DRINK_THUMB);
                String strIngredient1 = objectCocktail.getString(KEY_STR_INGREDIENT1);
                String strIngredient2 = objectCocktail.getString(KEY_STR_INGREDIENT2);
                String strIngredient3 = objectCocktail.getString(KEY_STR_INGREDIENT3);
                String strIngredient4 = objectCocktail.getString(KEY_STR_INGREDIENT4);
                String strMeasure1 = objectCocktail.getString(KEY_STR_MEASURE1);
                String strMeasure2 = objectCocktail.getString(KEY_STR_MEASURE2);
                String strMeasure3 = objectCocktail.getString(KEY_STR_MEASURE3);
                String dateModified = objectCocktail.getString(KEY_DATE_MODIFIED);
                cocktail = new Cocktail(id, strDrink, strInstructions, strDrinkThumb, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strMeasure1, strMeasure2, strMeasure3, dateModified);
                result.add(cocktail);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

}
