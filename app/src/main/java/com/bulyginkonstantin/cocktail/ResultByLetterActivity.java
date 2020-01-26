package com.bulyginkonstantin.cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.bulyginkonstantin.cocktail.data.MainViewModel;
import com.bulyginkonstantin.cocktail.utils.JSONUtils;
import com.bulyginkonstantin.cocktail.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.List;

public class ResultByLetterActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private CocktailAdapter adapter;
    private String letter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_by_letter);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        final Intent intent = getIntent();
        letter = intent.getStringExtra("letter");

        if (letter != null && !letter.isEmpty()) {
            recyclerViewInfo = findViewById(R.id.recyclerViewInfo);
            recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CocktailAdapter();
            recyclerViewInfo.setAdapter(adapter);
            downloadData();


            adapter.setOnCocktailClickListener(new CocktailAdapter.OnCocktailClickListener() {
                @Override
                public void onCocktailClick(int position) {
                    Cocktail cocktail = adapter.getCocktails().get(position);
                    Intent cocktailDetails = new Intent(ResultByLetterActivity.this, DetailActivity.class);
                    cocktailDetails.putExtra("id", cocktail.getId());
                    startActivity(cocktailDetails);
                }
            });

            adapter.setOnReachEndListener(new CocktailAdapter.OnReachEndListener() {
                @Override
                public void onReachEnd() {
                    Toast.makeText(ResultByLetterActivity.this, "End of list", Toast.LENGTH_SHORT).show();
                }
            });


            LiveData<List<Cocktail>> cocktailsFromLivedata = viewModel.getCocktails();
            cocktailsFromLivedata.observe(this, new Observer<List<Cocktail>>() {
                @Override
                public void onChanged(List<Cocktail> cocktails) {
                    adapter.setCocktails(cocktails);
                }
            });

            //RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            //recyclerViewInfo.addItemDecoration(divider);
        }
    }

    private void downloadData() {

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.SEARCH_BY_LETTER, letter);
        List<Cocktail> cocktails = JSONUtils.getCocktailsFromJSON(jsonObject);
        if (cocktails != null && !cocktails.isEmpty()) {
            viewModel.deleteAllCocktails();
            for (Cocktail cocktail: cocktails) {
                viewModel.insertCocktail(cocktail);
            }
        }
    }
}
