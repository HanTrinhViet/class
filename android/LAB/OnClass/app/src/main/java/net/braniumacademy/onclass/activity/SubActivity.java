package net.braniumacademy.onclass.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.constants.Constants;
import net.braniumacademy.onclass.databinding.ActivitySubBinding;
import net.braniumacademy.onclass.model.Contact;

public class SubActivity extends AppCompatActivity {
    private Contact contact = new Contact();
    private ActivitySubBinding binding;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);
        binding.setContact(contact);
        setUpUpdatedData();
        handleEventListener();
    }

    private void setUpUpdatedData() {
        Intent data = getIntent();
        if (data.getExtras() != null) {
            Bundle dataBundle = data.getExtras();
            contact.setId(dataBundle.getInt("contact_id"));
            contact.setName(dataBundle.getString("contact_name"));
            contact.setNumber(dataBundle.getString("contact_number"));
            binding.ivAddContactImage.setImageURI(Uri.parse(dataBundle.getString("contact_image_path")));
        }
    }
    private void handleEventListener() {
        binding.ivAddContactImage.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            resultLauncher.launch(intent);
        });

        binding.btnCancelAddContact.setOnClickListener(v -> finish());

        binding.btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(SubActivity.this, MainActivity.class);
            int id = contact.getId();
            String name = contact.getName();
            String number = contact.getNumber();
            String contactImagePath = Constants.imagePath;
            Bundle bundle = new Bundle();
            bundle.putInt("contact_id", id);
            bundle.putString("contact_name", name);
            bundle.putString("contact_number", number);
            bundle.putString("contact_image_path", contactImagePath);
            intent.putExtras(bundle);
            setResult(150, intent);
            finish();
        });

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            Constants.imagePath = imageUri.toString();
                            binding.ivAddContactImage.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(SubActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}