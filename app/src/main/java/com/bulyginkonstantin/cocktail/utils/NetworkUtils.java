package com.bulyginkonstantin.cocktail.utils;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    //Base URL to search by name
    private static final String BASE_URL_SEARCH_BY_NAME = "https://www.thecocktaildb.com/api/json/v1/1/search.php";
    private static final String SEARCH_BY_NAME_VALUE = "s";   //0
    private static final String SEARCH_BY_FIRST_LETTER = "f"; //1

    public static final int SEARCH_BY_NAME = 0;
    public static final int SEARCH_BY_LETTER = 1;


    //search by cocktail name
    private static URL searchUrl(int searchBy, String str) {
        URL result = null;
        String search = null;

        if (searchBy == SEARCH_BY_NAME) {
            search = SEARCH_BY_NAME_VALUE;
        } else if (searchBy == SEARCH_BY_LETTER){
            search = SEARCH_BY_FIRST_LETTER;
        }
        Uri uri = Uri.parse(BASE_URL_SEARCH_BY_NAME).buildUpon().appendQueryParameter(search, str).build();
        try {

            if (uri != null) {
                result = new URL(uri.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static JSONObject getJSONFromNetwork(int searchBy, String str) {
        URL url = searchUrl(searchBy, str);
        JSONObject result = null;
        try {
            result = new JSONLoadTask().execute(url).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;

    }

    private static class JSONLoadTask extends AsyncTask<URL, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... urls) {
            JSONObject result = null;
            if (urls == null || urls.length == 0) {
                return  result;
            } else {
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) urls[0].openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line = reader.readLine();

                    while (line != null) {
                        builder.append(line);
                        line = reader.readLine();
                    }

                    result = new JSONObject(builder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
            return result;
        }
    }






}
