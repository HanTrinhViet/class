package com.example.section_19_market_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView rccVwItems;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rccVwItems = findViewById(R.id.rcc_vw_items);

        itemList = new ArrayList<>(
                List.of(
                        new Item(R.drawable.fruit, "Fruits", "Fresh Fruits from the Garden"),
                        new Item(R.drawable.vegitables, "Vegetables", "Delicious Vegetables "),
                        new Item(R.drawable.bread, "Bakery", "Bread, Wheat and Beans"),
                        new Item(R.drawable.beverage, "Beverage", "Juice, Tea, Coffee and Soda"),
                        new Item(R.drawable.milk, "Milk", "Milk, Shakes and Yogurt"),
                        new Item(R.drawable.popcorn, "Snacks", "Pop Corn, Donut and Drinks")
                )
        );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rccVwItems.setLayoutManager(linearLayoutManager);

        MyCustomAdapter adapter = new MyCustomAdapter(itemList);
        rccVwItems.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v, int pos) {
        Toast.makeText(this, "Item title: " + itemList.get(pos).getItemTitle(), Toast.LENGTH_SHORT).show();
    }
}