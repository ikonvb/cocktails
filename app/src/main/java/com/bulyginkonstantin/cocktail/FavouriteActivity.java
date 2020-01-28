package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.FavouriteCocktail;
import com.bulyginkonstantin.cocktail.data.MainViewModel;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavouriteCocktails;
    private FavouriteCocktailAdapter adapter;
    private MainViewModel viewModel;
    private LiveData<List<FavouriteCocktail>> favouriteCocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        recyclerViewFavouriteCocktails = findViewById(R.id.recyclerViewFavouriteCocktails);
        recyclerViewFavouriteCocktails.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavouriteCocktailAdapter();
        recyclerViewFavouriteCocktails.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        favouriteCocktails = viewModel.getFavouriteCocktails();

        favouriteCocktails.observe(this, new Observer<List<FavouriteCocktail>>() {
            @Override
            public void onChanged(List<FavouriteCocktail> favouriteCocktails) {
                if (favouriteCocktails != null) {
                    adapter.setCocktails(favouriteCocktails);
                }
            }
        });

        adapter.setOnCocktailClickListener(new FavouriteCocktailAdapter.OnCocktailClickListener() {
            @Override
            public void onCocktailClick(int position) {
                FavouriteCocktail cocktail = adapter.getCocktails().get(position);
                Intent cocktailDetails = new Intent(FavouriteActivity.this, DetailActivity.class);
                cocktailDetails.putExtra("id", cocktail.getId());
                startActivity(cocktailDetails);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mainPage:
                Intent intentMainPage = new Intent(this, ChooseSearchActivity.class);
                startActivity(intentMainPage);
                break;
            case R.id.favouritePage:
                Intent intentFavourite = new Intent(this, FavouriteActivity.class);
                startActivity(intentFavourite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
