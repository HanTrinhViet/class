package com.example.ngay1_15_2024;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lstVwContact;
    ArrayList<Contact> contactList;
    MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.button);
        lstVwContact = findViewById(R.id.listView);

        Contact c1 = new Contact(R.drawable.anh_1, "Rose 1", "0123456789");
        Contact c2 = new Contact(R.drawable.anh_2, "Rose 2", "0123456789");
        Contact c3 = new Contact(R.drawable.anh_3, "Rose 3", "0123456789");

        contactList = new ArrayList<>();

        contactList.add(c1);
        contactList.add(c2);
        contactList.add(c3);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);

        adapter = new MyCustomAdapter(contactList, getApplicationContext());
        lstVwContact.setAdapter(adapter);

        lstVwContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                                position,
                                Toast.LENGTH_LONG)
                        .show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        lstVwContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String name = b.getString("Name");
        String phone = b.getString("Phone");
        Contact contact = new Contact(R.drawable.anh_1, name, phone);
        if (requestCode == 100 && resultCode == 150) {
            contactList.add(contact);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == 200 && resultCode == 150) {

//            arrayList.set()
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.actionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_sort_by_name) {
            Toast.makeText(this, "Sort By Name", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.mnu_sort_by_phone) {
            Toast.makeText(this, "Sort by phone", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.mnu_broadcast) {
            Toast.makeText(this, "Broadcast", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}