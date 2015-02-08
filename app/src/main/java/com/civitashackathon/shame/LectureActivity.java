package com.civitashackathon.shame;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import java.util.Calendar;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */

public class LectureActivity extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


     /*
          //buttonClick

      Intent msgIntent = new Intent(this,ShowNotification.class);
        pIntent=PendingIntent.getService(this, 0, msgIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 48);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.PM);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pIntent);
        alarmManager.setRepeating(ELAPSED_REALTIME_WAKEUP,
                calendar.getTimeInMillis(),
                (INTERVAL_DAY)*7, pIntent);
     */
        return true;
    }

/*

*/

}
