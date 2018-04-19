package com.example.sabri.project5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates xml file where data will be saved(I think)
        //eggData is the key we can use to call later
        pref = getSharedPreferences(getString(R.string.eggData), Context.MODE_PRIVATE);
    }

    /*
     * Whenever eggs are added, the service must post
     * a notification to the notification bar
     * describing the number of eggs available.
     * One notification will appear per button click
     */
    public void add_one_egg(View view) {
        Intent myIntent = new Intent(getApplicationContext(),MyReceiver.class);
        sendBroadcast(myIntent.putExtra(getString(R.string.code), 1));
    }

    /*
     * Whenever eggs are added, the service must post
     * a notification to the notification bar
     * describing the number of eggs available.
     * ONe notification will appear per button click
     */
    public void add_two_egg(View view) {
        Intent myIntent = new Intent(getApplicationContext(),MyReceiver.class);
        sendBroadcast(myIntent.putExtra(getString(R.string.code), 2));
    }
/*
 * subtract 1 egg from the current_egg_count.
 * the number of eggs cannot go negative
 */
    public void subtract_one_egg(View view) {
            Intent myIntent = new Intent(getApplicationContext(),MyReceiver.class);
            sendBroadcast(myIntent.putExtra(getString(R.string.code), -1));
    }
/*
 * If 6 or more eggs available, it will subtract 6 from the total,
 * store the new total and post a notification with
 * verbiage similar to "We are having omelets, we have x eggs available."
 * Where x is the number of eggs available after 6 are subtracted.
 * If less than 6, it will post a notification with verbiage similar to
 * "We are having gruel, we have x eggs available."
 */
    public void make_breakfast(View view) {
            Intent myIntent = new Intent(getApplicationContext(),MyReceiver.class);
            sendBroadcast(myIntent.putExtra(getString(R.string.code), Constants.MAKE_BREAKFAST));
    }
}