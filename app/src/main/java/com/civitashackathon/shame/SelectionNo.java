package com.civitashackathon.shame;

/**
 * Created by Jay Tarun Chheda on 2/7/2015.
 */
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
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

        try {
            FileInputStream fin = openFileInput("MoneyLostInShame.txt");
            int c;
            while( (c = fin.read()) != -1){
                lossInString = lossInString + Character.toString((char)c);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo","File read failed because "+e.toString());
        }
        Float loss=Float.valueOf(lossInString);
        /*

        Some math for calculating new loss

        */

        try {
            FileOutputStream fOut = openFileOutput("MoneyLostInShame.txt",MODE_PRIVATE);
            String str = loss.toString();

            fOut.write(str.getBytes());
            fOut.close();


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo","File write failed because "+e.toString());

        }
        try {
            FileOutputStream fOut = openFileOutput("helloShame.txt",MODE_APPEND);
            String str = "\nDidn't attend the lecture at "+System.currentTimeMillis();

            fOut.write(str.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SelectionNo", "Could no write to file because "+e.toString());
        }

        Log.d("SelectionNo", "done");

    }

}