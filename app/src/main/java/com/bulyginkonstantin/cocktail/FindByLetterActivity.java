package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindByLetterActivity extends AppCompatActivity {
    private EditText editTextCocktailName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_letter);
        editTextCocktailName = findViewById(R.id.editTextCocktailName);
    }

    public void onClickSearchByLetter(View view) {
        Intent intent = new Intent(this, ResultByLetterActivity.class);
        String letter = editTextCocktailName.getText().toString();
        if (!letter.isEmpty()) {
            intent.putExtra("letter", editTextCocktailName.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter at least one letter of cocktail", Toast.LENGTH_SHORT).show();
        }
    }
}
