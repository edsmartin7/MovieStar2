package com.example.emartin.moviestar2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AppTestFragment extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tester);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("results");

        //assign extra to temp_data in fragment
        TextView t = (TextView) this.findViewById(R.id.temp_data);
        t.setText(extra);

    }

}
