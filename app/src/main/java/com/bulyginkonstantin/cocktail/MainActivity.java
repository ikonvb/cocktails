package com.bulyginkonstantin.cocktail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.utils.JSONUtils;
import com.bulyginkonstantin.cocktail.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private CocktailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewInfo = findViewById(R.id.recyclerViewInfo);
        recyclerViewInfo.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CocktailAdapter();
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.SEARCH_BY_LETTER, "c");
        ArrayList<Cocktail> cocktails = JSONUtils.getCocktailsFromJSON(jsonObject);
        adapter.setCocktails(cocktails);
        recyclerViewInfo.setAdapter(adapter);

    }
}
