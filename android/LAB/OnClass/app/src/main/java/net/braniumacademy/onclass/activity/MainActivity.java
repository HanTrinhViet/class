package net.braniumacademy.onclass.activity;

import static net.braniumacademy.onclass.constants.Constants.contactList;
import static net.braniumacademy.onclass.constants.Constants.contact_position;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.braniumacademy.onclass.db.ContentProvider;
import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.adapter.ContactAdapter;
import net.braniumacademy.onclass.databinding.ActivityMainBinding;
import net.braniumacademy.onclass.db.MyDB;
import net.braniumacademy.onclass.model.Contact;
import net.braniumacademy.onclass.receiver.ConnectionReceiver;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ContactAdapter contactAdapter;
    private MyDB myDB;
    private ContentProvider contentProvider;
    private ConnectionReceiver connectionReceiver;
    private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        registerForContextMenu(binding.recycleViewContact);
        showContactFromContentProvider();
        handleEventListener();

        connectionReceiver = new ConnectionReceiver();
        intentFilter = new IntentFilter("net.braniumacademy.onclass.SOME_ACTION");
        intentFilter.addAction("net.braniumacademy.onclass.CONNECTIVITY_CHANGE");
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(connectionReceiver, intentFilter);
    }

    private void handleEventListener() {
        binding.fltBtnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivityForResult(intent, 100);
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contactAdapter.getFilter().filter(s.toString());
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showContactFromContentProvider() {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 3);
        } else {
            contentProvider = new ContentProvider(this);
            contactList = contentProvider.getAllContacts();
            for (int i = 0; i < contactList.size(); i++) {
                contactList.get(i).setImage("");
            }
            contactAdapter = new ContactAdapter(this, contactList);
            binding.recycleViewContact.setLayoutManager(new LinearLayoutManager(this));
            binding.recycleViewContact.setAdapter(contactAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 100 || requestCode == 200) && resultCode == 0) {

        } else {
            Bundle bundle = data.getExtras();
            int contact_id = bundle.getInt("contact_id") != 0 ? bundle.getInt("contact_id") : 0;
            String contact_image_path = bundle.getString("contact_image_path");
            String contact_name = bundle.getString("contact_name");
            String contact_number = bundle.getString("contact_number");
            Contact contact = new Contact(contact_id, contact_name, contact_number, contact_image_path);

            if (requestCode == 100 && resultCode == 150) {
                Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
                contactList.add(contact);
                contactAdapter.notifyDataSetChanged();
                createContactToCP(contact);
            } else if (requestCode == 200 && resultCode == 150) {
                contentProvider.updateContact(contact);
            }
        }
    }

    private void createContactToCP(Contact contact) {
        contentProvider.createContact(contact);
    }

    private void updateContactToDB(Contact contact) {
        contactList.set(contact_position, contact);
        myDB.updateContact(contact.getId(), contact);
        contactAdapter.notifyDataSetChanged();
    }

    private void createContactToDB(Contact contact) {
        contactList.add(contact);
        myDB.addContact(contact);
        contactAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            Bundle bundle = new Bundle();
            Contact contact = contactList.get(item.getGroupId());
            bundle.putInt("contact_id", contact.getId());
            bundle.putString("contact_name", contact.getName());
            bundle.putString("contact_number", contact.getNumber());
            bundle.putString("contact_image_path", contact.getImage());
            intent.putExtras(bundle);
            startActivityForResult(intent, 200);
        } else if (item.getItemId() == 1) { // file am thanh, word, excel, gui email, ...
            Contact contact = contactList.get(item.getGroupId());
            contactList.remove(contact);
            contactAdapter.notifyDataSetChanged();
            contentProvider.deleteContactById(contact);
        } else if (item.getItemId() == 2) {

        } else if (item.getItemId() == 3) {
//            sendBroadcast(...);

        }
        return super.onContextItemSelected(item);
    }


}