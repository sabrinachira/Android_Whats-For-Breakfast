package com.example.sabri.project5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

/**
 * Created by Dillon Sykes (50%) and Sabrina Chira (50%) on 4/16/2018.
 */
public class MyService extends Service {
    SharedPreferences prefFromMain;
    SharedPreferences.Editor editor;
    int egg_from_receiver;
    int egg_count;
    String notification;
    int notification_number;
/*
 * onStartCommand retrieves the egg_count from the intent, copies the SharedPreference
 * from MainActivity, and gets the saved egg_count. It also calls processEggData
 * where notification is set to the correct string based on which button was pressed.
 * egg_count is also decremented or incremented based on which button was pressed.
 * doNoti is also called with the string that was set in processEggData.
 * MyService is also stopped at the end of every notification.
 */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        egg_from_receiver = intent.getIntExtra("code", Constants.DEFAULT_RECEIVER);

        //editor used to change what is inside our pref storage area
        //get reference from MainActivity(not sure if this is right)
        prefFromMain = MainActivity.pref;
        //used to edit the preference
        editor = prefFromMain.edit();
        //gets data from xml storage if nothing is there defult is 0
        egg_count = prefFromMain.getInt(getString(R.string.eggData), 0);
        notification_number = prefFromMain.getInt("notification_number", 0);
        notification = processEggData();
        //send notification into doNoti for the proper notificaiton phrase
        doNoti(notification);
        //stops service
        stopSelf();
        return START_NOT_STICKY;
    }

/*
 * processEggData detects what number egg_from_receiver is, increments or decrements
 * egg_count, then returns the string that needs to be printed in the
 * notification.
 * If "add 1 egg" button is pressed, then processEggData returns that 1
 * egg was added, as well as the current egg count.
 * If "add 2 eggs" button is pressed, then processEggData returns that 2
 * eggs were added, as well as the current egg count.
 * If "subtract 1 egg" button is pressed, then processEggData returns that 1
 * egg was subtracted, as well as the current egg count.
 * If "Make Breakfast" button is pressed, then processEggData returns either
 * "We are having omelets," if the egg count is 6 or over, as well as the current egg count.
 * If the egg count is less than 6, then processEggData returns
 * "We are having gruel," as well as the current egg count.
 */
    private String processEggData() {
        if (egg_from_receiver == 1) {
            egg_count += 1;
            //updates the storage location with the new number of eggs
            editor.putInt(getString(R.string.eggData), egg_count).commit();
            if (egg_count == 1)
                return getString(R.string.one_egg_added) + " " + egg_count + " " + getString(R.string.egg_available);
            else
                return getString(R.string.one_egg_added) + " " + egg_count + " " + getString(R.string.eggs_available);
        } else if (egg_from_receiver == 2) {
            egg_count += 2;
            editor.putInt(getString(R.string.eggData), egg_count).commit();
            return getString(R.string.two_eggs_added) + " " + egg_count + " " + getString(R.string.eggs_available);
        } else if (egg_from_receiver == -1) {
            if (egg_count == 0) {
                return getString(R.string.no_eggs);
            } else {
                egg_count -= 1;
                editor.putInt(getString(R.string.eggData), egg_count).commit();
                if (egg_count == 1)
                    return getString(R.string.subtract) + " " + egg_count + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.subtract) + " " + egg_count + " " + getString(R.string.eggs_available);
            }
        } else if (egg_from_receiver == Constants.MAKE_BREAKFAST) {
            if (egg_count >= 6) {
                egg_count -= 6;
                editor.putInt(getString(R.string.eggData), egg_count).commit();
                if (egg_count == 1)
                    return getString(R.string.make_breakfast) + " " + egg_count + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.make_breakfast) + " " + egg_count + " " + getString(R.string.eggs_available);

            } else {
                if (egg_count == 1)
                    return getString(R.string.gruel) + " " + egg_count + " " + getString(R.string.egg_available);
                else
                    return getString(R.string.gruel) + " " + egg_count + " " + getString(R.string.eggs_available);
            }
        }
        return "Nothing: " + egg_from_receiver;
    }

/*
 * doNoti takes in a string and creates a notification in the notification bar or a phone.
 * It must create a notification for every time a button is pressed. That is what the
 * notification_number does; it is the id for each notificaiton, which is also saved in the
 * SavedPreferences.
 */
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
        editor.putInt("notification_number", notification_number).commit();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
