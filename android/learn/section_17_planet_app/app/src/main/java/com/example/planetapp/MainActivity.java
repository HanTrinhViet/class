package com.example.planetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstVwPlanet;
    ArrayList<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1- AdapterView
        lstVwPlanet = findViewById(R.id.lst_vw_planet);


        // 2 - Data Source
        planets = new ArrayList<>();
        Planet earth = new Planet("Earth", "1 Moon", R.drawable.earth);
        Planet jupiter = new Planet("Jupiter", "8 Jupiter", R.drawable.jupiter);
        Planet mars = new Planet("Mars", "1 Moon", R.drawable.mars);
        Planet mercury = new Planet("Mercury", "1 Moon", R.drawable.mercury);
        Planet neptune = new Planet("Neptune", "1 Moon", R.drawable.neptune);
        Planet pluto = new Planet("Pluto", "1 Moon", R.drawable.pluto);
        Planet saturn = new Planet("Saturn", "1 Moon", R.drawable.saturn);
        Planet uranus = new Planet("Uranus", "1 Moon", R.drawable.uranus);
        Planet venus = new Planet("Venus", "1 Moon", R.drawable.venus);

        planets.addAll(
                Arrays.asList(earth, jupiter, mars, mercury, neptune, pluto, saturn, uranus, venus)
        );

        // 3 - Adapter
        MyCustomAdapter adapter = new MyCustomAdapter(
                planets,
                getApplicationContext()
        );

        lstVwPlanet.setAdapter(adapter);


        lstVwPlanet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Planet Name: " + adapter.getItem(position).getPlanetName(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}