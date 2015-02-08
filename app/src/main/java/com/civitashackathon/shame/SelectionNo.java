package com.civitashackathon.shame;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;


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

        try {
            FileInputStream fin = openFileInput("TuitionWeeksLectures.txt");
            int c;

            String tuitionWeeksLecturesString="";
            while( (c = fin.read()) != -1){
                tuitionWeeksLecturesString = tuitionWeeksLecturesString + Character.toString((char)c);
            }
            Log.d("values",tuitionWeeksLecturesString);
            splitTuitionWeeksLecturesString = tuitionWeeksLecturesString.split(";");
            for(int i=0; i < 3; i++) {
                Log.d("values", splitTuitionWeeksLecturesString[i]);
            }
                for(int i=0; i < 3; i++)
            {

                tuitionWeeksLectures[i]=Integer.valueOf(splitTuitionWeeksLecturesString[i]);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo","File read failed for TuitionWeeksLectures because "+e.toString());
        }
        Log.d("SelectionNo", "Reading Money lost in shame");

        try {
            FileInputStream fin = openFileInput("MoneyLostInShame.txt");
            int c;
            while( (c = fin.read()) != -1){
                lossInString = lossInString + Character.toString((char)c);
            }
            fin.close();
        } catch (Exception e) {
            try {
                FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt",MODE_PRIVATE);
                lossInString = "1";

                fOut.write(lossInString.getBytes());
                fOut.close();


            } catch (Exception se) {
                se.printStackTrace();
                Log.e("SelectionNo","File write failed because "+se.toString());

            }
            e.printStackTrace();
            Log.e("SelectionNo","File read failed for MoneyLostInShame because "+e.toString());
        }

        Float loss=Float.valueOf(lossInString);
        loss = loss +(tuitionWeeksLectures[0]/(tuitionWeeksLectures[1]*tuitionWeeksLectures[2]));



        try {
            FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt",MODE_PRIVATE);
            String str = loss.toString();
            str = String.format("%.2f", Float.valueOf(str));

            fOut.write(str.getBytes());
            fOut.close();


        } catch (Exception e) {

            e.printStackTrace();
            Log.e("SelectionNo","File write failed because "+e.toString());

        }

        Log.d("SelectionNo", "Writing MoneyLostInShame"+loss.toString());

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