package com.civitashackathon.shame;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */

public class LectureActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String DayOfWeek[]={"1", "1", "1","1"};
        String timeHours[]={"3","4","5","4"};
        String timeMinutes[]={"10","15","25","13"};
      /*  try {
            FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt",MODE_PRIVATE);
            String str = "1";

            fOut.write(str.getBytes());
            fOut.close();


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo","File write failed because "+e.toString());

        }*/

        String lossInString="";
        try {
            FileInputStream fin = openFileInput("MoneyLostInShame.txt");
            int c;
            while( (c = fin.read()) != -1){
                lossInString = lossInString + Character.toString((char)c);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo","File read failed for MoneyLostInShame because "+e.toString());
        }

        Log.d("loss till now",lossInString);
        //*/

        try {
            FileOutputStream fOut = openFileOutput("TuitionWeeksLectures.txt",MODE_PRIVATE);
            String str = "12000;15;6";

            fOut.write(str.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LectureActivity","File write failed because "+e.toString());

        }

        saveAlarms(DayOfWeek,timeHours,timeMinutes);


    }
    PendingIntent pIntent;
    void saveAlarms(String DayOfWeek[], String timeHours[], String timeMinutes[]){

        Intent msgIntent = new Intent(this,ShowNotification.class);
        for(int i=0; i < DayOfWeek.length; i++)
        {
            pIntent=PendingIntent.getService(this, i, msgIntent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pIntent);
            Calendar cur_cal = new GregorianCalendar();
            cur_cal.setTimeInMillis(System.currentTimeMillis());//set the current time and date for this calendar

            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
            cal.set(Calendar.SECOND, cur_cal.get(Calendar.SECOND));
            cal.set(Calendar.MILLISECOND, cur_cal.get(Calendar.MILLISECOND));
            cal.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
            cal.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeHours[i]));
            cal.set(Calendar.MINUTE, Integer.valueOf(timeMinutes[i]));
            cal.set(Calendar.DAY_OF_WEEK, Integer.valueOf(DayOfWeek[i]));

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(),
                    1000 * 20 * 60, pIntent);
                    // 1000*60*60*24*7, pIntent);

        }
        Log.d("Alarms","task completed");
    }
/*

*/

}
