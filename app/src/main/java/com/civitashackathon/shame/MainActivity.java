package com.civitashackathon.shame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "-----------------------------------------------------------");
        String lossInString="";
        try    {
            FileInputStream fin = openFileInput("MoneyLostInShame.txt");
            int c;
            while ((c = fin.read()) != -1) {
                lossInString = lossInString + Character.toString((char) c);
            }
            fin.close();
        }

        catch( Exception e)

        {
            try {
                FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt", MODE_PRIVATE);
                lossInString = "1";

                fOut.write(lossInString.getBytes());
                fOut.close();


            } catch (Exception se) {
                se.printStackTrace();
                Log.e("SelectionNo", "File write failed because " + se.toString());

            }
        }

        TextView lossText=(TextView)findViewById(R.id.mytextview);
        lossText.setText(lossInString);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = new Intent(getApplicationContext(),LectureActivity.class);
        startActivity(intent);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}