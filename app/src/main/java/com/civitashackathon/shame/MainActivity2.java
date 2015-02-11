package com.civitashackathon.shame;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;

public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    public void menuOnClickNext() {

        EditText n=  (EditText) findViewById(R.id.editText);
        EditText n1= (EditText) findViewById(R.id.editText2);
        EditText n2= (EditText) findViewById(R.id.editText1);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Float tuition = Float.parseFloat(n.getText().toString());
        int weeks = Integer.parseInt(n1.getText().toString());
        int lecPerWeek = Integer.parseInt(n2.getText().toString());

        Log.d("MainActivity2","Writing TWL");
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("Tuition",tuition);
        editor.putInt("Weeks",weeks);
        editor.putInt("LecturesPerWeek",lecPerWeek);
        // Apply the edits!
        editor.apply();

        Intent intent1 =new Intent(this,MainActivity3.class);
        startActivity(intent1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {
            menuOnClickNext();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
