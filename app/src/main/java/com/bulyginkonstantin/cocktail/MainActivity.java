package com.bulyginkonstantin.cocktail;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.utils.JSONUtils;
import com.bulyginkonstantin.cocktail.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.SEARCH_BY_NAME, "margarita");
        ArrayList<Cocktail> cocktails = JSONUtils.getCocktailsFromJSON(jsonObject);
        StringBuilder stringBuilder = new StringBuilder();

        for (Cocktail cocktail : cocktails) {
            stringBuilder.append(cocktail.getStrDrink()).append("\n");
        }

        Log.d("aaa" , stringBuilder.toString());
    }
}
