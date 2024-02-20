package com.example.ngay1_15_2024;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ListView listView;

    ArrayList<Contact> arrayList;

    int process = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        Contact c1 = new Contact(1, R.drawable.anh_1, "Rose 1", "0123456789");
        Contact c2 = new Contact(2, R.drawable.anh_2, "Rose 2", "0123456789");
        Contact c3 = new Contact(3, R.drawable.anh_3, "Rose 3", "0123456789");

        arrayList = new ArrayList<>();

        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(c3);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);

        MyCustomAdapter adapter = new MyCustomAdapter(arrayList, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                                position,
                                Toast.LENGTH_LONG)
                        .show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String id = null;
        String name = b.getString("name");
        String phone = b.getString("phone");
        Contact contact = new Contact(3, name, phone);
        if (requestCode == 100 && resultCode == 150) {
            // add
            // adapter.notifyDataSetChanged();
        }

        if (requestCode == 200 && resultCode == 150) {
             // update
        }

    }
}