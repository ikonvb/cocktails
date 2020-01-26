package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}
