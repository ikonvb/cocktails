package com.bulyginkonstantin.cocktail.utils;

import com.bulyginkonstantin.cocktail.data.Cocktail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static final String KEY_DRINKS = "drinks";
    private static final String KEY_ID = "idDrink";
    private static final String KEY_STR_DRINK = "strDrink";
    private static final String KEY_STR_CATEGORY = "strCategory";
    private static final String KEY_STR_ALCOHOLIC = "strAlcoholic";
    private static final String KEY_STR_GLASS = "strGlass";
    private static final String KEY_STR_INSTRUCTIONS = "strInstructions";
    private static final String KEY_STR_DRINK_THUMB = "strDrinkThumb";
    private static final String KEY_DATE_MODIFIED = "dateModified";
    private static final String KEY_INGREDIENTS = "strIngredient";


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
                String strCategory = objectCocktail.getString(KEY_STR_CATEGORY);
                String strAlcoholic = objectCocktail.getString(KEY_STR_ALCOHOLIC);
                String strGlass = objectCocktail.getString(KEY_STR_GLASS);
                String strInstructions = objectCocktail.getString(KEY_STR_INSTRUCTIONS);
                String strDrinkThumb = objectCocktail.getString(KEY_STR_DRINK_THUMB);
                String dateModified = objectCocktail.getString(KEY_DATE_MODIFIED);
                List<String> ingredients = new ArrayList<>();

                //var k depends from cocktail database
                for (int k = 1; k <= 15; k++) {
                    String ingredient = objectCocktail.getString(KEY_INGREDIENTS + k);
                    if (!ingredient.equals("null")) {
                        ingredients.add(ingredient);
                    }
                }
                cocktail = new Cocktail(id, strDrink, strCategory, strAlcoholic, strGlass, strInstructions, strDrinkThumb, dateModified, ingredients);
                result.add(cocktail);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
