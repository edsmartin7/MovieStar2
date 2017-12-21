package com.example.emartin.moviestar2;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MovieQueryTask {
    
    /**
     * Returns a list (array) of actors from a movie
     * @param movie the given movie
     * @return an array of actors
     */
    public static String[] makeQuery(String movie) {
        HttpRequestTask.execute();
        String[] actors = filterActors(result);
        return actors;
    }

    /**
     * Converts json results to an array of strings
     * @param result json list of actors result from GET request to IMDB API
     * @return an array of actors (strings)
     */
    public static String[] filterActors(String result) {

        //Parse JSON to get actors
        try {
            JSONObject list_of_actors = new JSONObject(result);
            String all_actors = list_of_actors.getString("Actors");
            String[] array_of_names = all_actors.split(",");

            for (String actor : array_of_names)
                actor = actor.trim();

            for (String actor : array_of_names)
                System.out.println(actor);

            return array_of_names;

        } catch (JSONException e) {
            System.out.println("THERE WAS AN ERROR " + e);
        }

        return null;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                //Spring Android API
                //GET request
                String url = "http://www.omdbapi.com/?t=" + movie;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String result = restTemplate.getForObject(url, String.class, "Android");
            } catch (Exception e) {
                Log.e("ERRORS", e.toString());
            }

            return null;
        }
    }

}
