package com.civitashackathon.shame;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */
import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;



public class ShowNotification extends IntentService {


    public PendingIntent pendingIntent, yesIntent, noIntent;

    public ShowNotification() {
        super("ShowNotification");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("show notifications", "start");

        Intent msgYesIntent = new Intent(this, SelectionYes.class);
        yesIntent=PendingIntent.getService(this, 10, msgYesIntent, 0);

        Intent msgNoIntent = new Intent(this, SelectionNo.class);
        noIntent=PendingIntent.getService(this, 11, msgNoIntent, 0);


        String temp="";
        Intent qintent=new Intent(this, MainActivity.class);
        pendingIntent=PendingIntent.getActivity(this, 0, qintent, 0);


        Notification n = new Notification.Builder(this)
                .setContentTitle("Did you make it to the class?")
                .setContentText("hoot")
                .setContentIntent(pendingIntent).setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .addAction(R.drawable.ic_stat_yes, "Yes", yesIntent)
                .addAction(R.drawable.ic_stat_no, "No", noIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Hide the notification after its selected

        notificationManager.notify(0, n);
        Log.d("show notifications", "done");


    }

}