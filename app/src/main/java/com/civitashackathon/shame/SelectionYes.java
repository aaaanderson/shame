package com.civitashackathon.shame;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;
import java.io.FileOutputStream;


public class SelectionYes extends IntentService {



    public SelectionYes() {
        super("SelectionYes");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SelectionYes", "start");


        try {
            FileOutputStream fOut = openFileOutput("helloShame.txt",MODE_APPEND);
            String str = "\nAttended the lecture at "+System.currentTimeMillis();

            fOut.write(str.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionYes", "Could no write to file because of following error: "+e.toString());
        }
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Dismiss Notification
        notificationmanager.cancel(0);

        Log.d("SelectionYes", "done");

    }

}