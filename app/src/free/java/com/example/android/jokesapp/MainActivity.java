package com.example.android.jokesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.jokedisplayer.JokeActivity;
import com.example.android.jokesource.Joke;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private static String JOKE_KEY = "joke key";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-9165236684803479~1461397629");

        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
//        mInterstitialAd.setAdUnitId("ca-app-pub-9165236684803479/9495321381");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Request a new ad if one isn't already loaded.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public void showJoke(View view) {
        // Show the ad if it's ready. Otherwise toast and open activity.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            startJokeActivity();
        }
    }

    private void startJokeActivity() {
        Joke joke = new Joke();
        String jokeText = joke.getJoke();
        Intent jokeActivity = new Intent(this, JokeActivity.class);
        jokeActivity.putExtra(JOKE_KEY, jokeText);
        startActivity(jokeActivity);
    }
}
