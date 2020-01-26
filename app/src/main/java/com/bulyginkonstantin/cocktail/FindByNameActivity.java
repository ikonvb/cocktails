package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindByNameActivity extends AppCompatActivity {

    private EditText editTextCocktailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_name);
        editTextCocktailName = findViewById(R.id.editTextCocktailName);
    }

    public void onClickSearchByName(View view) {
        Intent intent = new Intent(this, ResultByNameActivity.class);
        String name = editTextCocktailName.getText().toString();
        if (!name.isEmpty()) {
            intent.putExtra("name", editTextCocktailName.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter name of cocktail", Toast.LENGTH_SHORT).show();
        }

    }
}
