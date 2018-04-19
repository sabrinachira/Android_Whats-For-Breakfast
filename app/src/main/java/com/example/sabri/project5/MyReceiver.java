package com.example.sabri.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

/**
 * Created by Dillon Sykes and Sabrina Chira on 4/16/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    int eggCountReceiver;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Intent to go to MyService Class
        Intent myIntent = new Intent(context,MyService.class);
        //Get data from previous intent from MainActivity
        intent.getIntExtra(Resources.getSystem().getString(R.string.code), eggCountReceiver);
        //Put data we got from MainActivity intent and place it into our myIntent
        //that is going to our service
        myIntent.putExtra(Resources.getSystem().getString(R.string.code),eggCountReceiver);
        //go to MyService class
        context.startService(myIntent);
    }
}
