package com.civitashackathon.shame;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SelectionNo extends IntentService {

    public SelectionNo() {
        super("SelectionNo");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String lossInString="";
        Log.d("SelectionNo", "start");
        Log.d("SelectionNo", "Reading Tuition, No_of_Weeks, No_of_Lectures_per_Week");
        String splitTuitionWeeksLecturesString[];
        int tuitionWeeksLectures[]=new int[3];

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Float tuition = settings.getFloat("Tuition",0.00F);
        int weeks = settings.getInt("Weeks",1);
        int lecPerWeek = settings.getInt("LecturesPerWeek",1);

        Log.d("SelectionNo", "Reading Money lost in shame");


        Float lostInShame = settings.getFloat("MoneyLostInShame",0.00F);

        Log.d("SelectionNo", "Calculating Money lost in shame");

        lostInShame = lostInShame +(tuition/(weeks*lecPerWeek));

        Log.d("SelectionNo", "Writing MoneyLostInShame"+lostInShame.toString());
        lostInShame=Float.parseFloat(String.format("%.2f", lostInShame));
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("MoneyLostInShame",lostInShame);

        // Apply the edits!
        editor.apply();


        try {
            FileOutputStream fOut = openFileOutput("helloShame.txt",MODE_APPEND);
            String str = "\nDidn't attend the lecture at "+System.currentTimeMillis();

            fOut.write(str.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo", "Could no write to file because "+e.toString());
        }
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        Log.d("SelectionNo", "done");

    }

}