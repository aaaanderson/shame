package com.civitashackathon.shame;


        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.app.TimePickerDialog;
        import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.TimePicker;

        import java.io.FileOutputStream;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.GregorianCalendar;
        import java.util.LinkedList;


public class MainActivity3 extends ActionBarActivity {

    ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);

        String[] Day={"Sun", "Mon", "Tue", "Wed","Thu","Fri","Sat"};
        ArrayAdapter<String> stringArrayAdapter=
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        Day);
        Spinner spinner =
                (Spinner)  findViewById(R.id.spinner);
        spinner.setAdapter(stringArrayAdapter);

        ListView ls=(ListView)findViewById(R.id.listView);
        arrayList=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,
                R.layout.simple_list_item,
                arrayList);
        ls.setAdapter(adapter);



    }

    LinkedList<String> l1=new LinkedList<String>();

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_finish) {
            buttonOnClick3();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonOnClick3() {

        try {
            FileOutputStream fOut = openFileOutput("Schedule.txt",MODE_PRIVATE);
            String str = "";

            fOut.write(str.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MainActivity3", "File Schedule write failed because " + e.toString());

        }

        // write the logic
        String DayOfWeek[]=new String[l1.size()];
        String timeHours[]=new String[l1.size()];
        String timeMinutes[]=new String[l1.size()];
        for(int i=0; i<l1.size();i++)
        {
            String textTWL=(String)l1.get(i);
            Log.d("day hr min : ",""+textTWL);

            try {
                FileOutputStream fOut = openFileOutput("Schedule.txt",MODE_APPEND);
                String str = textTWL;

                fOut.write(str.getBytes());
                fOut.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MainActivity3", "File Schedule write failed because " + e.toString());

            }
            String splitTextTWL[]=textTWL.split(";");

                DayOfWeek[i]=splitTextTWL[0];
                timeHours[i]=splitTextTWL[1];
                timeMinutes[i]=splitTextTWL[2];

        }

        saveAlarms(DayOfWeek,timeHours, timeMinutes);

    }

    PendingIntent pIntent;
    void saveAlarms(String DayOfWeek[], String timeHours[], String timeMinutes[]){

        Intent msgIntent = new Intent(this, ShowNotification.class);
        for(int i=0; i < DayOfWeek.length; i++)
        {
            pIntent=PendingIntent.getService(this, i, msgIntent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pIntent);

            Calendar cal = new GregorianCalendar();

            cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeHours[i]));
            cal.set(Calendar.MINUTE, Integer.valueOf(timeMinutes[i]));
            cal.set(Calendar.DAY_OF_WEEK, Integer.valueOf(DayOfWeek[i]));


            if (Calendar.getInstance().getTimeInMillis() > (cal).getTimeInMillis()) {// if its in the past increment
                cal.add(Calendar.DATE, 7);
            }


            Log.d("calendar ", DayOfWeek[i]+" "+timeHours[i]+" "+timeMinutes[i]);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(),
                    1000 * 60 * 60 * 24 * 7 , pIntent);
            // 1000*60*60*24*7, pIntent);

        }
        Log.d("Alarms", "task completed");

        Intent intent1 =new Intent(this,MainActivity.class);

        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);

    }

   public void showTimeDialog(View view)
    {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                TextView timeText=(TextView)findViewById(R.id.editText2);
                timeText.setText( ""+String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                Spinner sp= (Spinner) findViewById(R.id.spinner);
                arrayList.add(sp.getSelectedItem()+" | "+String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                adapter.notifyDataSetChanged();
            l1.add((sp.getSelectedItemPosition()+1)+";"+selectedHour + ";" +  selectedMinute);
            }
        }, hour, minute,false);
        mTimePicker.setTitle("Start Time");
        mTimePicker.show();


    }

}

