package net.braniumacademy.onclass.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import net.braniumacademy.onclass.R;
import net.braniumacademy.onclass.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding subBinding;
    ActivityResultLauncher<Intent> resultLauncher;
    private static String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        registerResult();
        subBinding = DataBindingUtil.setContentView(this, R.layout.activity_sub);

        Intent data = getIntent();
        if (data.getExtras() != null) {
            Bundle dataBundle = data.getExtras();
            subBinding.ivAddContactImage.setImageURI(Uri.parse(dataBundle.getString("contact_image_path")));
            subBinding.etAddContactName.setText(dataBundle.getString("contact_name"));
            subBinding.etAddContactNumber.setText(dataBundle.getString("contact_number"));
        }

        subBinding.ivAddContactImage.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            resultLauncher.launch(intent);
        });
        subBinding.btnCancelAddContact.setOnClickListener(v -> finish());
        subBinding.btnAddContact.setOnClickListener(v ->  {
            Intent intent = new Intent(SubActivity.this, MainActivity.class);

            String contactImagePath = imagePath;
            String name = subBinding.etAddContactName.getText().toString();
            String number = subBinding.etAddContactNumber.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("contact_image_path", contactImagePath);
            bundle.putString("contact_name", name);
            bundle.putString("contact_number", number);

            intent.putExtras(bundle);
            setResult(150, intent);
            finish();
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
                            subBinding.ivAddContactImage.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(SubActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}