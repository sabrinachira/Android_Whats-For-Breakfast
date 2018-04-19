package com.example.sabri.project5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dillon Sykes and Sabrina Chira on 4/16/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    int eggCountReceiver;
    int current_egg_count;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context,MyService.class);
        intent.getIntExtra("@string/code", eggCountReceiver);

        if(eggCountReceiver == 1){
            current_egg_count +=1;
            //notification with @string/one_egg_added
        }else if(eggCountReceiver == 2){
            current_egg_count +=2;
            //notification with @string/two_eggs_added
        }else if(eggCountReceiver == -1) {
            if(current_egg_count == 0){
                //notification with @string/no_eggs
            }else{
                current_egg_count -=1;
                //notification with @string/subtract
            }
        }else if(eggCountReceiver == 6){
            if(current_egg_count >= 6){
                current_egg_count -=6;
                //notification with @string/make_breakfast
            }else{
                //notification with @string/gruel
            }
        }

        context.startService(myIntent);
    }
}
