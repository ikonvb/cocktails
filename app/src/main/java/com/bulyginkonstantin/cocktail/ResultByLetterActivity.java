package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.utils.JSONUtils;
import com.bulyginkonstantin.cocktail.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class ResultByLetterActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private CocktailAdapter adapter;
    private String letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_by_letter);


        Intent intent = getIntent();
        letter = intent.getStringExtra("letter");

        if (letter != null && !letter.isEmpty()) {
            recyclerViewInfo = findViewById(R.id.recyclerViewInfo);
            recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CocktailAdapter();

            JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.SEARCH_BY_LETTER, letter);
            ArrayList<Cocktail> cocktails = JSONUtils.getCocktailsFromJSON(jsonObject);
            adapter.setCocktails(cocktails);
            recyclerViewInfo.setAdapter(adapter);

            adapter.setOnCocktailClickListener(new CocktailAdapter.OnCocktailClickListener() {
                @Override
                public void onCocktailClick(int position) {
                    Toast.makeText(ResultByLetterActivity.this, "Clicked: " + position, Toast.LENGTH_SHORT).show();
                }
            });

            adapter.setOnReachEndListener(new CocktailAdapter.OnReachEndListener() {
                @Override
                public void onReachEnd() {
                    Toast.makeText(ResultByLetterActivity.this, "End of list", Toast.LENGTH_SHORT).show();
                }
            });

            //RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            //recyclerViewInfo.addItemDecoration(divider);
        }
    }
}
