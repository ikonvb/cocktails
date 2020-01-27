package com.bulyginkonstantin.cocktail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.data.FavouriteCocktail;
import com.bulyginkonstantin.cocktail.data.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavouriteCocktails;
    private CocktailAdapter adapter;
    private MainViewModel viewModel;
    private LiveData<List<FavouriteCocktail>> favouriteCocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        recyclerViewFavouriteCocktails = findViewById(R.id.recyclerViewFavouriteCocktails);
        recyclerViewFavouriteCocktails.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CocktailAdapter();
        recyclerViewFavouriteCocktails.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        favouriteCocktails = viewModel.getFavouriteCocktails();
        favouriteCocktails.observe(this, new Observer<List<FavouriteCocktail>>() {
            @Override
            public void onChanged(List<FavouriteCocktail> favouriteCocktails) {
                List<Cocktail> cocktails = new ArrayList<>();
                if (favouriteCocktails != null) {
                    cocktails.addAll(favouriteCocktails);
                    adapter.setCocktails(cocktails);
                }
            }
        });

    }
}
