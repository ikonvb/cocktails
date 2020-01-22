package com.bulyginkonstantin.cocktail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.Cocktail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private ArrayList<Cocktail> cocktails;

    public CocktailAdapter() {
        cocktails = new ArrayList<>();
    }

    public void addCocktails(ArrayList<Cocktail> cocktail) {
        this.cocktails.addAll(cocktail);
        notifyDataSetChanged();
    }

    public ArrayList<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(ArrayList<Cocktail> cocktails) {
        this.cocktails = cocktails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktail_item, parent, false);

        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        Cocktail cocktail = cocktails.get(position);
        holder.textViewCocktailName.setText(cocktail.getStrDrink());
        Picasso.get().load(cocktail.getStrDrinkThumb()).into(holder.imageViewSmallPoster);
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    class CocktailViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSmallPoster;
        private TextView textViewCocktailName;

        public CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            textViewCocktailName = itemView.findViewById(R.id.textViewCocktailName);
        }
    }
}
