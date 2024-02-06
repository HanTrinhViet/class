package com.example.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Adapter View: ListView
        listView = findViewById(R.id.lst_vw_country);

        // Data Source
        String[] countries = {"USA", "Germany", "Saudi Arabia", "France"};


        // Adapter
        MyCustomAdapter adapter = new MyCustomAdapter(this, countries);

        listView.setAdapter(adapter);
    }
}