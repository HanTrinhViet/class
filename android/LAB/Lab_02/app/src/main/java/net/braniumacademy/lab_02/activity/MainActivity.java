package net.braniumacademy.lab_02.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.braniumacademy.lab_02.R;
import net.braniumacademy.lab_02.adapter.ContactAdapter;
import net.braniumacademy.lab_02.databinding.ActivityMainBinding;
import net.braniumacademy.lab_02.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private List<Contact> contactList = new ArrayList<>();

    String imagePath = "";
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityMainBinding.recycleViewContact.setLayoutManager(layoutManager);

        contactAdapter = new ContactAdapter(contactList);
        activityMainBinding.recycleViewContact.setAdapter(contactAdapter);

        activityMainBinding.btnAddNewContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivityForResult(intent, 100);
        });


        activityMainBinding.btnDeleteContact.setOnClickListener(v -> {
            List<Contact> deletedList = new ArrayList<>();
            for (Contact contact : contactList) {
                if (contact.getStatus()) {
                    deletedList.add(contact);
                }
            }
            contactList.removeAll(deletedList);
            contactAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {

        } else {
            Bundle bundle = data.getExtras();
            String imageUri = bundle.getString("contact_image_path");
            String name = bundle.getString("contact_name");
            String phoneNumber = bundle.getString("contact_number");
            boolean status = true;
            if (requestCode == 100 && resultCode == 150) {
                Contact newContact = new Contact(name, phoneNumber, status, imageUri);
                contactList.add(newContact);
                contactAdapter.notifyDataSetChanged();
            }
        }
    }


}