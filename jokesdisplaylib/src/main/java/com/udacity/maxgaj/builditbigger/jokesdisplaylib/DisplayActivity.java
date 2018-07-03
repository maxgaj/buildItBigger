package com.udacity.maxgaj.builditbigger.jokesdisplaylib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        jokeTextView = findViewById(R.id.tv_joke);

        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra("Joke")){
            String joke = intent.getStringExtra("Joke");
            jokeTextView.setText(joke);
        }
    }
}
