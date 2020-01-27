package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.data.FavouriteCocktail;
import com.bulyginkonstantin.cocktail.data.MainViewModel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView cocktailImageView;
    private TextView cocktailNameTextView;
    private TextView glassTextView;
    private TextView alcoholicTextView;
    private TextView styleTextView;
    private TextView allIngredientsTextView;
    private TextView instructionsTextView;
    private MainViewModel viewModel;
    private Button buttonChangeFavourites;

    private int id;
    private Cocktail cocktail;
    private FavouriteCocktail favouriteCocktail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        buttonChangeFavourites = findViewById(R.id.buttonChangeFavourites);
        cocktailImageView = findViewById(R.id.cocktailImageView);
        cocktailNameTextView = findViewById(R.id.cocktailNameTextView);
        glassTextView = findViewById(R.id.glassTextView);
        alcoholicTextView = findViewById(R.id.alcoholicTextView);
        styleTextView = findViewById(R.id.styleTextView);
        allIngredientsTextView = findViewById(R.id.allIngredientsTextView);
        instructionsTextView = findViewById(R.id.instructionsTextView);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
        } else {
            finish();
        }
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        cocktail = viewModel.getCocktailById(id);
        Picasso.get().load(cocktail.getStrDrinkThumb()).into(cocktailImageView);

        cocktailNameTextView.setText(cocktail.getStrDrink());
        glassTextView.setText(cocktail.getStrGlass());
        alcoholicTextView.setText(cocktail.getStrAlcoholic());
        styleTextView.setText(cocktail.getStrCategory());
        instructionsTextView.setText(cocktail.getStrInstructions());
        showIngredients(cocktail);
        setFavourite();
    }

    private void showIngredients(Cocktail cocktail) {
        int i = 1;
        for (String s : cocktail.getIngredients()) {
            allIngredientsTextView.append(i + "." + s + "\n");
            i++;

        }
    }

    public void onClickChangeFavorites(View view) {


        if (favouriteCocktail == null) {
            viewModel.insertFavouriteCocktail(new FavouriteCocktail(cocktail));
            Toast.makeText(this, R.string.add_to_favourites, Toast.LENGTH_SHORT).show();
        } else {
            viewModel.deleteFavouriteCocktail(favouriteCocktail);
            Toast.makeText(this, R.string.remove_from_favourites, Toast.LENGTH_SHORT).show();
        }

        setFavourite();

    }

    private void setFavourite() {
        favouriteCocktail = viewModel.getFavouriteCocktailById(id);
        if (favouriteCocktail != null) {
            buttonChangeFavourites.setText(R.string.remove_from_favourites);
        } else {
            buttonChangeFavourites.setText(R.string.add_to_favourites);
        }
    }
}
