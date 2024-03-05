package net.braniumacademy.onclass.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.adapter.ContactAdapter;
import net.braniumacademy.onclass.databinding.ActivityMainBinding;
import net.braniumacademy.onclass.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList = new ArrayList<>();
    private static int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        contactAdapter = new ContactAdapter(this, contactList);
        mainBinding.recycleViewContact.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recycleViewContact.setAdapter(contactAdapter);
        registerForContextMenu(mainBinding.recycleViewContact);
        mainBinding.fltBtnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivityForResult(intent, 100);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {

        } else {
            Bundle bundle = data.getExtras();
            String contact_image_path = bundle.getString("contact_image_path");
            String contact_name = bundle.getString("contact_name");
            String contact_number = bundle.getString("contact_number");

            if (requestCode == 100 && resultCode == 150) {
                Contact newContact = new Contact(null, contact_name, contact_number, contact_image_path);
                contactList.add(newContact);
                contactAdapter.notifyDataSetChanged();
            } else if (requestCode == 200 && resultCode == 150) {
                Contact newContact = new Contact(null, contact_name, contact_number, contact_image_path);
                contactList.set(ContactAdapter.index, newContact);
                contactAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.actionmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_edit) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            Bundle bundle = new Bundle();
            Contact contact = contactList.get(ContactAdapter.index);
            bundle.putString("contact_image_path", contact.getImagePath());
            bundle.putString("contact_name", contact.getName());
            bundle.putString("contact_number", contact.getNumber());
            intent.putExtras(bundle);
            startActivityForResult(intent, 200);
        } else if (item.getItemId() == R.id.mnu_delete) { // file am thanh, word, excel, gui email, ...

        } else if (item.getItemId() == R.id.mnu_call) {

        } else if (item.getItemId() == R.id.mnu_broadcast) {


        }
        return super.onContextItemSelected(item);
    }




}