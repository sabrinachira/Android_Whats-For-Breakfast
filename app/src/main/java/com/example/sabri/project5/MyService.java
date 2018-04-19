package com.example.sabri.project5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

/**
 * Created by Dillon Sykes and Sabrina Chira on 4/16/2018.
 */

public class MyService extends Service {
    SharedPreferences prefFromMain;
    SharedPreferences.Editor editor;
    int eggRec;
    int eCount;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        intent.getIntExtra(getString(R.string.code),eggRec);
        //editor used to change what is inside our pref storage area
        //get reference from MainActivity(not sure if this is right)
        prefFromMain = MainActivity.pref;
        //used to edit the preference
        editor = prefFromMain.edit();
        //gets data from xml storage if nothing is there defult is 0
        eCount = prefFromMain.getInt(getString(R.string.eggData),0);
        processEggData();
        //not yet implemented
        doNoti();
        return super.onStartCommand(intent, flags, startId);
    }

    private void processEggData() {
        if(eggRec == 1){
            eCount +=1;
            //updates the storage location with the new number of eggs
            editor.putInt(getString(R.string.eggData),eCount).commit();
            //notification with @string/one_egg_added
        }else if(eggRec == 2){
            eCount +=2;
            editor.putInt(getString(R.string.eggData),eCount).commit();
            //notification with @string/two_eggs_added
        }else if(eggRec == -1) {
            if(eCount == 0){
                //notification with @string/no_eggs
            }else{
                eCount -=1;
                editor.putInt(getString(R.string.eggData),eCount).commit();
                //notification with @string/subtract
            }
        }else if(eggRec == Constants.MAKE_BREAKFAST) {
            if (eCount >= 6) {
                eCount -= 6;
                //notification with @string/make_breakfast
            } else {
                //notification with @string/gruel
            }
        }
    }


    private void doNoti() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Just a Notice")
                .setSmallIcon(R.mipmap.breakfast_logo)
                .setOngoing(false)						//true only dismissable by app
                .setProgress(100,10,true )				//show a progress bar
                .build();

        // Hide the notification after its selected
        //noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(1, noti);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
