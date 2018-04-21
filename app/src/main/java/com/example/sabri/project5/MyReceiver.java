package com.example.sabri.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dillon Sykes (50%) and Sabrina Chira (50%) on 4/16/2018.
 */
public class MyReceiver extends BroadcastReceiver {
    int eggCountReceiver;
/*
 * onReceive creates MyService intents to be processed based on which
 * button was pressed. It also retrieves the eggCount
 */
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, MyService.class);
        eggCountReceiver = intent.getIntExtra("code", Constants.DEFAULT_RECEIVER);
        myIntent.putExtra("code", eggCountReceiver);
        context.startService(myIntent);
    }
}