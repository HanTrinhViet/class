package net.braniumacademy.section_20_card_view_the_sports_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_sports;
    List<Sport> sportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_sports = findViewById(R.id.rv_sports);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_sports.setLayoutManager(linearLayoutManager);

        sportList.addAll(
                Arrays.asList(
                        new Sport("Basketball", R.drawable.basketball),
                        new Sport("Football", R.drawable.football),
                        new Sport("Ping", R.drawable.ping),
                        new Sport("Tennis", R.drawable.tennis),
                        new Sport("Volley", R.drawable.volley)
                )
        );

        SportAdapter sportAdapter = new SportAdapter(sportList);
        rv_sports.setAdapter(sportAdapter);
    }
}