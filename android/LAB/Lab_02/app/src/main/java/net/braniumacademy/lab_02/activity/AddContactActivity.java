package net.braniumacademy.lab_02.activity;

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

import net.braniumacademy.lab_02.R;
import net.braniumacademy.lab_02.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding activityAddContactBinding;
    ActivityResultLauncher<Intent> resultLauncher;
    private static String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        activityAddContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);

        registerResult();

        activityAddContactBinding.ivAddContactPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            resultLauncher.launch(intent);
        });

        activityAddContactBinding.btnCancel.setOnClickListener(v -> finish());

        activityAddContactBinding.btnAddNewContact.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            String contactName = activityAddContactBinding.etAddContactName.getText().toString();
            String contactNumber = activityAddContactBinding.etAddContactName.getText().toString();
            String contactImagePath = imagePath;
            boolean contactIsEnabled = activityAddContactBinding.checkBoxAddContact.isChecked();

            if (contactName.isEmpty() || contactNumber.isEmpty() || contactImagePath.isEmpty()) {
                Toast.makeText(this, "Các trường không được để trống", Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("contact_image_path", contactImagePath);
                bundle.putString("contact_name", contactName);
                bundle.putString("contact_number", contactNumber);
                bundle.putBoolean("contact_is_enabled", contactIsEnabled);

                intent.putExtras(bundle);
                setResult(150, intent);
                finish();
            }
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
                            activityAddContactBinding.ivAddContactPhoto.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(AddContactActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}