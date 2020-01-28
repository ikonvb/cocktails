package com.bulyginkonstantin.cocktail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bulyginkonstantin.cocktail.data.FavouriteCocktail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouriteCocktailAdapter extends RecyclerView.Adapter<FavouriteCocktailAdapter.FavouriteCocktailViewHolder>{

    private List<FavouriteCocktail> cocktails;
    private OnCocktailClickListener onCocktailClickListener;
    private OnReachEndListener onReachEndListener;

    interface OnCocktailClickListener {
        void onCocktailClick(int position);
    }

    interface OnReachEndListener {
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnCocktailClickListener(OnCocktailClickListener onCocktailClickListener) {
        this.onCocktailClickListener = onCocktailClickListener;
    }

    public FavouriteCocktailAdapter() {
        cocktails = new ArrayList<>();
    }

    public void addCocktails(List<FavouriteCocktail> cocktail) {
        this.cocktails.addAll(cocktail);
        notifyDataSetChanged();
    }

    public List<FavouriteCocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<FavouriteCocktail> cocktails) {
        this.cocktails = cocktails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavouriteCocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktail_item, parent, false);
        return new FavouriteCocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteCocktailViewHolder holder, int position) {
        if (position > cocktails.size() - 4 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
        FavouriteCocktail cocktail = cocktails.get(position);
        holder.textViewCocktailName.setText(cocktail.getStrDrink());
        Picasso.get().load(cocktail.getStrDrinkThumb()).into(holder.imageViewSmallPoster);
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    class FavouriteCocktailViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSmallPoster;
        private TextView textViewCocktailName;


        public FavouriteCocktailViewHolder (@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            textViewCocktailName = itemView.findViewById(R.id.textViewCocktailName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCocktailClickListener != null) {
                        onCocktailClickListener.onCocktailClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
