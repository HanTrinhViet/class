package net.braniumacademy.contactmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import net.braniumacademy.contactmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Data Source
    private ContactDatabase contactDatabase;

    // Adapter
    private MyAdapter myAdapter;

    // Binding Objects
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handler;
    private List<Contacts> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handler = new MainActivityClickHandler(this);

        mainBinding.setClickHandler(handler);

        // Recycle View
        RecyclerView recyclerView = mainBinding.recycleViewContact;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Database
        contactDatabase = ContactDatabase.getInstance(this);

        // View Model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Contacts c1 = new Contacts("Jack", "jack@gmail.com");
        viewModel.addNewContact(c1);


        // Loading the data
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactList.clear();
                for (Contacts contact : contacts) {
                    Log.v("TAGY", contact.getId() + " - " + contact.getName());
                    contactList.add(contact);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        // Adapter
        myAdapter = new MyAdapter(contactList);

        // Linking the ReView with adapter
        recyclerView.setAdapter(myAdapter);


    }
}