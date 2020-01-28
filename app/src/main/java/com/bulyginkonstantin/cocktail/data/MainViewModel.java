package com.bulyginkonstantin.cocktail.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static CocktailDatabase database;
    private LiveData<List<Cocktail>> cocktails;
    private LiveData<List<FavouriteCocktail>> favouriteCocktails;

    public LiveData<List<Cocktail>> getCocktails() {
        return cocktails;
    }

    public LiveData<List<FavouriteCocktail>> getFavouriteCocktails() {
        return favouriteCocktails;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = CocktailDatabase.getInstance(getApplication());
        cocktails = database.getCocktailDao().getAllCocktails();
        favouriteCocktails = database.getCocktailDao().getAllFavouriteCocktails();
    }

    public Cocktail getCocktailById(int id) {
        try {
            return new GetCocktailByIdTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetCocktailByIdTask extends AsyncTask<Integer, Void, Cocktail> {

        @Override
        protected Cocktail doInBackground(Integer... integers) {

            if (integers != null && integers.length > 0) {
                return database.getCocktailDao().getCocktailById(integers[0]);
            }
            return null;
        }
    }

    public Cocktail getCocktailByName(String name) {
        try {
            return new GetCocktailTask().execute(name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetCocktailTask extends AsyncTask<String, Void, Cocktail> {

        @Override
        protected Cocktail doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                return database.getCocktailDao().getCocktailByName(strings[0]);
            }

            return null;
        }
    }

    public void deleteAllCocktails() {
        try {
            new DeleteAllCocktailsTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DeleteAllCocktailsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            database.getCocktailDao().deleteAllCocktails();
            return null;
        }
    }

    public void insertCocktail(Cocktail cocktail) {
        try {
            new InsertCocktailTask().execute(cocktail).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertCocktailTask extends AsyncTask<Cocktail, Void, Void> {

        @Override
        protected Void doInBackground(Cocktail... cocktails) {
            if (cocktails != null && cocktails.length > 0) {
                database.getCocktailDao().insertCocktail(cocktails[0]);
            }
            return null;
        }
    }

    public void deleteCocktail(Cocktail cocktail) {
        try {
            new DeleteOneCocktailTask().execute(cocktail).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DeleteOneCocktailTask extends AsyncTask<Cocktail, Void, Void> {

        @Override
        protected Void doInBackground(Cocktail... cocktails) {
            if (cocktails != null && cocktails.length > 0) {
                database.getCocktailDao().deleteOneCocktail(cocktails[0]);
            }
            return null;
        }
    }


    public void insertFavouriteCocktail(FavouriteCocktail cocktail) {
        try {
            new InsertFavouriteCocktailTask().execute(cocktail).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InsertFavouriteCocktailTask extends AsyncTask<FavouriteCocktail, Void, Void> {

        @Override
        protected Void doInBackground(FavouriteCocktail... cocktails) {
            if (cocktails != null && cocktails.length > 0) {
                database.getCocktailDao().insertFavouriteCocktail(cocktails[0]);
            }
            return null;
        }
    }

    public void deleteFavouriteCocktail(FavouriteCocktail cocktail) {
        try {
            new DeleteFavouriteCocktailTask().execute(cocktail).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DeleteFavouriteCocktailTask extends AsyncTask<FavouriteCocktail, Void, Void> {

        @Override
        protected Void doInBackground(FavouriteCocktail... cocktails) {
            if (cocktails != null && cocktails.length > 0) {
                database.getCocktailDao().deleteFavouriteCocktail(cocktails[0]);
            }
            return null;
        }
    }

    public FavouriteCocktail getFavouriteCocktailById(int id) {
        try {
            return new GetFavouriteCocktailByIdTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetFavouriteCocktailByIdTask extends AsyncTask<Integer, Void, FavouriteCocktail> {

        @Override
        protected FavouriteCocktail doInBackground(Integer... integers) {

            if (integers != null && integers.length > 0) {
                return database.getCocktailDao().getFavouriteCocktailById(integers[0]);
            } else {
                return null;
            }
        }
    }
}
