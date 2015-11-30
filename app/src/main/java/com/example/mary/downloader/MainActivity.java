package com.example.mary.downloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // you don't want to try to access the internet on the main thread because it will cause
        // the UI to lock up
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    downloadHTML("http://news.bbc.co.uk");
                } catch (Exception e) {
                    Log.d("DJG", e.toString());
                }
                return null;
            }
        }.execute();

    }

    private void downloadHTML(String target) throws Exception {
        //you'll need to add INTERNET permission to do any of this
        URL url = new URL(target);
        //get input stream
        InputStream is = url.openStream();
        //put it into an input stream reader to make it easier to read
        InputStreamReader isr = new InputStreamReader(is);
        //put it into buffered reader so you can read it line by line
        BufferedReader br = new BufferedReader(isr);

        String line = null;

        while ((line = br.readLine()) != null) {
            Log.d("DJG", line);
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
