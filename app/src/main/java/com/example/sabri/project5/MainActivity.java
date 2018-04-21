package com.example.sabri.project5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Dillon Sykes (50%) and Sabrina Chira (50%) on 4/16/2018.
 */
/*
 * MainActivity creates MyReceiver intents based on which button was pressed.
 * When a button is pressed, it sends a broadcast to the MyReceiver, which
 * creates a MyService intent, which creates a notification based on which
 * button was pressed and the current number of eggs.
 */
public class MainActivity extends AppCompatActivity {
    public static SharedPreferences pref;

    /*
     * onCreate creates the layout of the app, as well as creating the SharedPreferences
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates xml file where data will be saved(I think)
        //eggData is the key we can use to call later
        pref = getSharedPreferences(getString(R.string.eggData), Context.MODE_PRIVATE);
    }

    /*
     * When the "add 1 egg" button is pressed, add_one_egg method is called.
     * This creates a MyReceiver intent with a code of 1. MyService.java
     * deciphers the codes.
     */
    public void add_one_egg(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MyReceiver.class);
        sendBroadcast(myIntent.putExtra("code", 1));
    }

    /*
   * When the "add 2 egg" button is pressed, add_two_egg method is called.
   * This creates a MyReceiver intent with a code of 2. MyService.java
   * deciphers the codes.
   */
    public void add_two_egg(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MyReceiver.class);
        sendBroadcast(myIntent.putExtra("code", 2));
    }

    /*
     * When the "subtract 1 egg" button is pressed, subtract_one_egg method is called.
     * This creates a MyReceiver intent with a code of -1. MyService.java
     * deciphers the codes.
     */
    public void subtract_one_egg(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MyReceiver.class);
        sendBroadcast(myIntent.putExtra("code", -1));
    }

    /*
     * When the "Make Breakfast" button is pressed, make_breakfast method is called.
     * This creates a MyReceiver intent with a code of 9999. MyService.java
     * deciphers the codes.
     */
    public void make_breakfast(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MyReceiver.class);
        sendBroadcast(myIntent.putExtra("code", Constants.MAKE_BREAKFAST));
    }
}