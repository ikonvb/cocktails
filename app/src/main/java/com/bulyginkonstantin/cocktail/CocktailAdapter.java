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
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private List<Cocktail> cocktails;
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

    public CocktailAdapter() {
        cocktails = new ArrayList<>();
    }

    public void addCocktails(ArrayList<Cocktail> cocktail) {
        this.cocktails.addAll(cocktail);
        notifyDataSetChanged();
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<Cocktail> cocktails) {
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
        if (position > cocktails.size() - 4 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
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
