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

public class ResultByNameActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInfo;
    private CocktailAdapter adapter;
    private String name;
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_by_name);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        if (name != null && !name.isEmpty()) {
            recyclerViewInfo = findViewById(R.id.recyclerViewInfo);
            recyclerViewInfo.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CocktailAdapter();
            recyclerViewInfo.setAdapter(adapter);
            downloadData();

            adapter.setOnCocktailClickListener(new CocktailAdapter.OnCocktailClickListener() {
                @Override
                public void onCocktailClick(int position) {
                    Cocktail cocktail = adapter.getCocktails().get(position);
                    Intent cocktailDetails = new Intent(ResultByNameActivity.this, DetailActivity.class);
                    cocktailDetails.putExtra("id", cocktail.getId());
                    startActivity(cocktailDetails);
                }
            });

            adapter.setOnReachEndListener(new CocktailAdapter.OnReachEndListener() {
                @Override
                public void onReachEnd() {
                    Toast.makeText(ResultByNameActivity.this, "End of list", Toast.LENGTH_SHORT).show();
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

        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.SEARCH_BY_NAME, name);
        List<Cocktail> cocktails = JSONUtils.getCocktailsFromJSON(jsonObject);
        if (cocktails != null && !cocktails.isEmpty()) {
            viewModel.deleteAllCocktails();
            for (Cocktail cocktail: cocktails) {
                viewModel.insertCocktail(cocktail);
            }
        }
    }

}
