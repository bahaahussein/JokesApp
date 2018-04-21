package com.example.android.jokesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.jokedisplayer.JokeActivity;
import com.example.android.jokesource.Joke;

public class MainActivity extends AppCompatActivity {

    private static String JOKE_KEY = "joke key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showJoke(View view) {
        Joke joke = new Joke();
        String jokeText = joke.getJoke();
        Intent jokeActivity = new Intent(this, JokeActivity.class);
        jokeActivity.putExtra(JOKE_KEY, jokeText);
        startActivity(jokeActivity);
    }
}
