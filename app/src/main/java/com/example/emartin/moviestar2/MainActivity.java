package com.example.emartin.moviestar2;

//Spring for Android
//http://projects.spring.io/spring-android/
//https://github.com/spring-projects/spring-android
//Git
//http://www.goprogramming.space/connecting-an-android-studio-project-with-github/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
                String str = movieInput.getText().toString();
                //Spring Android API
                //GET request
                String url = "http://www.omdbapi.com/?t=" + str;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String result = restTemplate.getForObject(url, String.class, "Android");

                Intent intent = new Intent(save, AppTestFragment.class);
                intent.putExtra("results", result);
                startActivity(intent);
            }
        });
    }
}

//spring build errors
//http://stackoverflow.com/questions/20673625/android-gradle-plugin-0-7-0-duplicate-files-during-packaging-of-apk
