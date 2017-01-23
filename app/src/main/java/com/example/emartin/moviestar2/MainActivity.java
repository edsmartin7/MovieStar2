package com.example.emartin.moviestar2;

//Spring for Android
//http://projects.spring.io/spring-android/
//https://github.com/spring-projects/spring-android
//Git
//http://www.goprogramming.space/connecting-an-android-studio-project-with-github/
//Threading
//don't put UI in thread?
//https://developer.android.com/guide/components/processes-and-threads.html
//Permissions
//https://developer.android.com/training/permissions/requesting.html
//https://developer.android.com/training/permissions/declaring.html
//Google Image Search
//http://stackoverflow.com/questions/8448788/google-custom-search-for-images-only
//https://developers.google.com/custom-search/
//https://developers.google.com/custom-search/docs/tutorial/customizingresults
//   ~http://stackoverflow.com/questions/533857/whats-the-best-web-image-search-api
//https://developers.google.com/custom-search/json-api/v1/overview

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText movieInput;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity save = this;
        //get EditText input
        movieInput = (EditText) findViewById(R.id.mMovieSearch);
        btn = (Button) findViewById(R.id.goSearch);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {

                        String str = movieInput.getText().toString();
                        //Spring Android API
                        //GET request
                        String url = "http://www.omdbapi.com/?t=" + str;
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                        String result = restTemplate.getForObject(url, String.class, "Android");

                        //Parse JSON to get actors
                        try {
                            JSONObject list_of_actors = new JSONObject(result);
                            String all_actors = list_of_actors.getString("Actors");
                            String[] array_of_names = all_actors.split(",");

                            for(String actor : array_of_names)
                               actor = actor.trim();

                        }catch (JSONException e){
                            System.out.println("THERE WAS AN ERROR " + e);
                        }

                        //Intent intent = new Intent(save, AppTestFragment.class);
                        //intent.putExtra("results", result);
                        //startActivity(intent);
                    }
                }).start();
            }
        });
    }
}

//spring build errors
//http://stackoverflow.com/questions/20673625/android-gradle-plugin-0-7-0-duplicate-files-during-packaging-of-apk
//json in android
//http://stackoverflow.com/questions/9605913/how-to-parse-json-in-android
