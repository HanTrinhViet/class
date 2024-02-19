package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwAppName;
    Button btnStart;
    Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwAppName = findViewById(R.id.txt_vw_app_name);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(
                        getApplicationContext(),
                        MyCustomService.class
                );
                startService(intentService);
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(
                        getApplicationContext(),
                        MyCustomService.class
                );
                stopService(intentService);
            }
        });
    }
}