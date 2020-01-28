package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_search);
    }

    public void onClickFindByName(View view) {
        Intent intent = new Intent(this, FindByNameActivity.class);
        startActivity(intent);
    }

    public void onClickFindByLetter(View view) {
        Intent intent = new Intent(this, FindByLetterActivity.class);
        startActivity(intent);
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
