package net.braniumacademy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.braniumacademy.adapter.MyCustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Trịnh Viết Hân
 * @apiNote lab 1
 * @since 19/02/2024
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTxtFullName;
    EditText edTxtPhoneNumber;
    RadioGroup rdoGrpGender;
    CheckBox checkBoxHobbySoccer;
    CheckBox checkBoxHobbyMovie;
    CheckBox checkBoxHobbyBadminton;
    Spinner spnHometown;
    ImageView imgVwPersonAvatarShow;
    Button btnAddInfo;
    FloatingActionButton btnAddImage;
    ListView lstVwInfo;
    List<Person> personList = new ArrayList<>();
    ActivityResultLauncher<Intent> resultLauncher;
    String imagePath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTxtFullName = findViewById(R.id.ed_txt_full_name);
        edTxtPhoneNumber = findViewById(R.id.ed_txt_phone_number);
        rdoGrpGender = findViewById(R.id.rdo_grp_gender);
        checkBoxHobbySoccer = findViewById(R.id.check_box_hobby_soccer);
        checkBoxHobbyMovie = findViewById(R.id.check_box_hobby_movie);
        checkBoxHobbyBadminton = findViewById(R.id.check_box_hobby_badminton);
        spnHometown = findViewById(R.id.spn_hometown);
        imgVwPersonAvatarShow = findViewById(R.id.img_vw_person_avatar_show);
        btnAddInfo = findViewById(R.id.btn_add_info);
        btnAddImage = findViewById(R.id.btn_add_image);
        lstVwInfo = findViewById(R.id.lst_vw_info);

        imgVwPersonAvatarShow.setImageResource(R.mipmap.ic_avatar_foreground);
        registerResult();
//        if (personList.isEmpty()) {
//            personList.addAll(
//                    List.of(
//                            new Person("Tru"),
//                            new Person(),
//                            new Person()
//                    )
//            );
//        }

        // Add item to spinner
        configSpinner(spnHometown);

        btnAddInfo.setOnClickListener(this);
        btnAddImage.setOnClickListener(this);
    }

    public void registerResult() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            imagePath = imageUri.toString();
                            imgVwPersonAvatarShow.setImageURI(imageUri);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     * Method to setup the spinner data
     *
     * @param spnHometown Spinner object
     */
    private void configSpinner(Spinner spnHometown) {
        var homeTown = Province.values();
        String[] homeTownValue = new String[homeTown.length];
        int count = 0;
        for (Province town : homeTown) {
            homeTownValue[count++] = town.getValue();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                homeTownValue
        );

        spnHometown.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_info) {
            String fullName = edTxtFullName.getText().toString();
            String phoneNumber = edTxtPhoneNumber.getText().toString();
            String gender = getGender();
            String hobbies = getHobbies();
            String hometown = spnHometown.getSelectedItem().toString();
            if (fullName.isBlank() || phoneNumber.isBlank() || gender == null || hobbies.isBlank() || hometown.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập đủ dữ liệu!", Toast.LENGTH_SHORT).show();
                return;
            }
            Person person = new Person(fullName, phoneNumber, gender, hobbies, hometown, imagePath);
            personList.add(person);
            /* -- Using ArrayAdapter
            ArrayAdapter<Person> personArrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    personList
            );
            lstVwInfo.setAdapter(personArrayAdapter);
            */

            /* -- Using Custom BaseAdapter
            MyCustomAdapter myCustomAdapter = new MyCustomAdapter(personList, this);
            lstVwInfo.setAdapter(myCustomAdapter);
             */

            // -- Using Custom ArrayAdapter
            MyCustomArrayAdapter myCustomArrayAdapter = new MyCustomArrayAdapter(personList, this);
            lstVwInfo.setAdapter(myCustomArrayAdapter);

        } else if (v.getId() == R.id.btn_add_image) {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            resultLauncher.launch(intent);
        }
    }

    private String getHobbies() {
        StringBuilder hobbyInfo = new StringBuilder();
        if (checkBoxHobbySoccer.isChecked()) {
            hobbyInfo.append(checkBoxHobbySoccer.getText()).append(" ");
        }

        if (checkBoxHobbyMovie.isChecked()) {
            hobbyInfo.append(checkBoxHobbyMovie.getText()).append(" ");
        }

        if (checkBoxHobbyBadminton.isChecked()) {
            hobbyInfo.append(checkBoxHobbyBadminton.getText()).append(" ");
        }

        return hobbyInfo.toString();
    }

    /**
     * Method to return the gender base on the current checked radio button
     *
     * @return String format of gender "Nam", "Nữ"
     */
    private String getGender() {
        if (rdoGrpGender.getCheckedRadioButtonId() == R.id.rdo_btn_male) {
            return "Nam";
        }

        if (rdoGrpGender.getCheckedRadioButtonId() == R.id.rdo_btn_female) {
            return "Nữ";
        }

        return null;
    }
}