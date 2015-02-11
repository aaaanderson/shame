package com.civitashackathon.shame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
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
       /* try {
            FileInputStream fin = openFileInput("MoneyLostInShame.txt");
            int c;
            while( (c = fin.read()) != -1){
                lossInString = lossInString + Character.toString((char)c);
            }
            fin.close();

            SpannableString ss1=  new SpannableString("$"+lossInString);
            ss1.setSpan(new RelativeSizeSpan(2f), 0, lossInString.indexOf('.')+1, 0); // set size
           // ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, 0);// set color
            TextView myTextView=(TextView)findViewById(R.id.mytextview);
            myTextView.setText(ss1);

        } catch (Exception e) {
            try {
                FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt",MODE_PRIVATE);
                String str = "1";

                fOut.write(str.getBytes());
                fOut.close();


            } catch (Exception se) {
                se.printStackTrace();
                Log.e("SelectionNo","File write failed because "+se.toString());

            }



            e.printStackTrace();
            Log.e("SelectionNo","File read failed for MoneyLostInShame because "+e.toString());
        }*/

       /* SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("MoneyLostInShame","2");

// Apply the edits!
        editor.apply();*/

// Get from the SharedPreferences

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Float lostInShame = settings.getFloat("MoneyLostInShame",0.00F);
        Log.d("reading preferences",lostInShame+"");
        SpannableString ss1=  new SpannableString(("$"+lostInShame));
        ss1.setSpan(new RelativeSizeSpan(2f), 0, lostInShame.toString().indexOf('.')+1, 0); // set size
        TextView myTextView=(TextView)findViewById(R.id.mytextview);
        myTextView.setText(ss1);


       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onClickButton1();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
public void onClickButton1()
{
    Intent intent = new Intent(this, MainActivity2.class);
     startActivity(intent);

}
    @Override
    protected void onResume() {
        super.onResume();


        }
}
