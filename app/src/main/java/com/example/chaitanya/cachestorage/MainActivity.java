package com.example.chaitanya.cachestorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editTextData;
    Button buttonICache, buttonECache, buttonPrivate, buttonPublic, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextData = (EditText) findViewById(R.id.et_data);
        buttonICache = (Button) findViewById(R.id.btn_internalcache);
        buttonECache = (Button) findViewById(R.id.btn_externalcache);
        buttonPrivate = (Button) findViewById(R.id.btn_externalprivate);
        buttonPublic = (Button) findViewById(R.id.btn_externalpublic);
        buttonNext = (Button) findViewById(R.id.btn_next);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        buttonICache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theData = editTextData.getText().toString();
                File folder = getCacheDir();
                File myFile = new File(folder, "myData1.txt");
                writeData(myFile, theData);
            }
        });

        buttonECache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theData = editTextData.getText().toString();
                File folder = getExternalCacheDir();
                File myFile = new File(folder, "myData2.txt");
                writeData(myFile, theData);
            }
        });


        buttonPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theData = editTextData.getText().toString();
                if (getExternalFilesDir("myPrivateData") != null) {
                    File folder = getExternalFilesDir("myPrivateData");
                    File myFile = new File(folder, "myData3.txt");
                    writeData(myFile, theData);
                }
            }
        });

        buttonPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theData = editTextData.getText().toString();
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile = new File(folder, "myData4.txt");
                writeData(myFile, theData);
            }
        });

    }

    private void writeData(File myFile, String theData) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(theData.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
