package com.example.emartin.moviestar2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText movieInput;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get EditText input
        movieInput = (EditText) findViewById(R.id.mMovieSearch);
        btn = (Button) findViewById(R.id.goSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = movieInput.getText().toString();
                MovieQueryTask.makeQuery(str);
            }
        });
    }

}
