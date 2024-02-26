package net.braniumacademy.lab_02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import net.braniumacademy.lab_02.adapter.ContactAdapter;
import net.braniumacademy.lab_02.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivMainContactAvatar;
    private EditText etMainContactName;
    private EditText etMainContactPhoneNumber;
    private CheckBox checkBoxMainContactStatus;
    private Button btnAddContact;
    private Button btnDeleteContact;
    private RecyclerView recyclerViewContactList;
    private List<Contact> contactList = new ArrayList<>();
    ActivityResultLauncher<Intent> resultLauncher;
    String imagePath = "";
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMainContactAvatar = findViewById(R.id.iv_main_contact_avatar);
        etMainContactName = findViewById(R.id.et__main_contact_name);
        etMainContactPhoneNumber = findViewById(R.id.et_main_contact_phone_number);
        checkBoxMainContactStatus = findViewById(R.id.check_box_main_contact_status);
        btnAddContact = findViewById(R.id.btn_add_contact);
        btnDeleteContact = findViewById(R.id.btn_delete_contact);
        recyclerViewContactList = findViewById(R.id.recycle_view_contact);

        ivMainContactAvatar.setImageResource(R.drawable.ic_launcher_foreground);
        registerResult();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContactList.setLayoutManager(layoutManager);

        contactAdapter = new ContactAdapter(contactList);
        recyclerViewContactList.setAdapter(contactAdapter);

        ivMainContactAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            resultLauncher.launch(intent);
        });

        btnAddContact.setOnClickListener(v -> {
            String imageUri = imagePath.toString();
            String name = etMainContactName.getText().toString();
            String phoneNumber = etMainContactPhoneNumber.getText().toString();
            boolean status = checkBoxMainContactStatus.isChecked();
            Contact newContact = new Contact(name, phoneNumber, status, imageUri);
            contactList.add(newContact);
            contactAdapter.notifyDataSetChanged();
        });


        btnDeleteContact.setOnClickListener(v -> {
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

    public void registerResult() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            imagePath = imageUri.toString();
                            ivMainContactAvatar.setImageURI(imageUri);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}