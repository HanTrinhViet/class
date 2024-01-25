package net.braniumacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1 - AdapterView
        listView = findViewById(R.id.list_view_1);


        // 2 - Data Source
        String[] countries = {"USA", "Germany", "Saudi Arabia", "France"};


        // 3 - Adapter
        MyCustomAdapter adapter = new MyCustomAdapter(
                this,
                countries
        );

        listView.setAdapter(adapter);

    }
}