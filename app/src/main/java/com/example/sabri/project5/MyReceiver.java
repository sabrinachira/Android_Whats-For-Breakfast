package com.example.sabri.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dillon Sykes and Sabrina Chira on 4/16/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context,MyService.class);
        context.startService(myIntent);
    }
}
