package com.example.chaitanya.cachestorage;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    Button buttonLoadICache, buttonLoadECache, buttonLoadPrivate, buttonLoadPublic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);

        textView = (TextView) findViewById(R.id.text_load);
        buttonLoadICache = (Button) findViewById(R.id.btn_load_internalcache);
        buttonLoadECache = (Button) findViewById(R.id.btn_load_externalcache);
        buttonLoadPrivate = (Button) findViewById(R.id.btn_load_externalprivate);
        buttonLoadPublic = (Button) findViewById(R.id.btn_load_externalpublic);

        buttonLoadICache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = getCacheDir();
                File myFile = new File(folder, "myData1.txt");
                String data = readData(myFile);
                if (data != null) {
                    textView.setText(data);
                } else {
                    textView.setText("Data was not returned.");
                }
            }
        });

        buttonLoadECache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = getExternalCacheDir();
                File myFile = new File(folder, "myData2.txt");
                String data = readData(myFile);
                if (data != null) {
                    textView.setText(data);
                } else {
                    textView.setText("Data was not returned.");
                }
            }
        });

        buttonLoadPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = getExternalFilesDir("myPrivateData");
                File myFile = new File(folder, "myData3.txt");
                String data = readData(myFile);
                if (data != null) {
                    textView.setText(data);
                } else {
                    textView.setText("Data was not returned.");
                }
            }
        });

        buttonLoadPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File myFile = new File(folder, "myData4.txt");
                String data = readData(myFile);
                if (data != null) {
                    textView.setText(data);
                } else {
                    textView.setText("Data was not returned.");
                }
            }
        });
    }

    private String readData(File myFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1){
                stringBuffer.append((char) read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
