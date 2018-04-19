package com.example.sabri.project5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
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
    String notification;
    int notification_number = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("original eCount: " + eCount);
        eggRec = intent.getIntExtra("code", Constants.DEFAULT_RECEIVER);
        System.out.println("eggRec: " + eggRec);

        //editor used to change what is inside our pref storage area
        //get reference from MainActivity(not sure if this is right)
        prefFromMain = MainActivity.pref;
        //used to edit the preference
        editor = prefFromMain.edit();
        //gets data from xml storage if nothing is there defult is 0
        eCount = prefFromMain.getInt(getString(R.string.eggData), 0);
        notification = processEggData();
        System.out.println("after adding eCount: " + eCount);
        doNoti(notification);
        return super.onStartCommand(intent, flags, startId);
    }

    private String processEggData() {
        if (eggRec == 1) {
            eCount += 1;
            //updates the storage location with the new number of eggs
            editor.putInt(getString(R.string.eggData), eCount).commit();
            if (eCount == 1)
                return getString(R.string.one_egg_added) + " " + eCount + " " + getString(R.string.egg_available);
            else
                return getString(R.string.one_egg_added) + " " + eCount + " " + getString(R.string.eggs_available);
        } else if (eggRec == 2) {
            eCount += 2;
            editor.putInt(getString(R.string.eggData), eCount).commit();
            return getString(R.string.two_eggs_added) + " " + eCount + " " + getString(R.string.eggs_available);
        } else if (eggRec == -1) {
            if (eCount == 0) {
                return getString(R.string.no_eggs);
            } else {
                eCount -= 1;
                editor.putInt(getString(R.string.eggData), eCount).commit();
                if (eCount == 1)
                    return getString(R.string.subtract) + " " + eCount + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.subtract) + " " + eCount + " " + getString(R.string.eggs_available);
            }
        } else if (eggRec == Constants.MAKE_BREAKFAST) {
            if (eCount >= 6) {
                eCount -= 6;
                editor.putInt(getString(R.string.eggData), eCount).commit();
                if (eCount == 1)
                    return getString(R.string.make_breakfast) + " " + eCount + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.make_breakfast) + " " + eCount + " " + getString(R.string.eggs_available);

            } else {
                if (eCount == 1)
                    return getString(R.string.gruel) + " " + eCount + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.gruel) + " " + eCount + " " + getString(R.string.eggs_available);
            }
        }
        return "Nothing: " + eggRec;
    }


    private void doNoti(String string) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(string)
                .setSmallIcon(R.mipmap.breakfast_logo)
                .setOngoing(false)                        //true only dismissable by app
                .build();
        notificationManager.notify(notification_number, noti);
        //need to have a new notification_number, which is the id,
        //otherwise it updates the current notification and the notification bar
        //only has 1 notification at a time from the app.
        notification_number++;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
