package com.example.sabri.project5;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int current_egg_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Whenever eggs are added, the service must post
     * a notification to the notification bar
     * describing the number of eggs available.
     * One notification will appear per button click
     */
    public void add_one_egg(View view) {
        current_egg_count += 1;
        //post notification that an egg was added
        //@string/one_eggs_added

    }

    /*
     * Whenever eggs are added, the service must post
     * a notification to the notification bar
     * describing the number of eggs available.
     * ONe notification will appear per button click
     */
    public void add_two_egg(View view) {
        current_egg_count += 2;
        //post notification that 2 eggs was added
        //@string/two_eggs_added

    }
/*
 * subtract 1 egg from the current_egg_count.
 * the number of eggs cannot go negative
 */
    public void subtract_one_egg(View view) {
        if(current_egg_count >= 0) {
            current_egg_count += -1;
            //post notification that an egg was subtracted
            //@string/subtract
        }
        else{
            //post a notification: "We have no eggs"
            // @string/no_eggs
        }
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
        if(current_egg_count >= 6){
            current_egg_count -= 6;
            //post a notification: "We are having omelets,
            //we are having x eggs available."
            //@string/make_breakfast
        }else{
            Intent myIntent = new Intent();
            sendBroadcast(myIntent.putExtra("@string/current_egg_count_string", current_egg_count));
            //post a notification: "We are having gruel,
            //we are having x eggs available."
            //@string/gruel
        }
    }
}