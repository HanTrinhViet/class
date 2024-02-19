package net.braniumacademy;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import net.braniumacademy.adapter.MyCustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTxtFullName;
    EditText edTxtPhoneNumber;
    RadioGroup rdoGrpGender;
    Spinner spnHometown;
    Button btnAddInfo;
    ListView lstVwInfo;
    List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edTxtFullName = findViewById(R.id.ed_txt_full_name);
        edTxtPhoneNumber = findViewById(R.id.ed_txt_phone_number);
        rdoGrpGender = findViewById(R.id.rdo_grp_gender);
        spnHometown = findViewById(R.id.spn_hometown);
        btnAddInfo = findViewById(R.id.btn_add_info);
        lstVwInfo = findViewById(R.id.lst_vw_info);


        // Add item to spinner
        configSpinner(spnHometown);
    }

    private void configSpinner(Spinner spnHometown) {
        var homeTown = HomeTown.values();
        String[] homeTownValue = new String[homeTown.length];
        int count = 0;
        for (HomeTown town : homeTown) {
            homeTownValue[count++] = town.getValue();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                homeTownValue
        );

        spnHometown.setAdapter(adapter);
        btnAddInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_info) {
            String fullName = edTxtFullName.getText().toString();
            String phoneNumber = edTxtPhoneNumber.getText().toString();
            String gender = getGender();
            String hometown = spnHometown.getSelectedItem().toString();
            Person person = new Person(fullName, phoneNumber, gender, hometown);
            personList.add(person);
            /* -- Using ArrayAdapter
            ArrayAdapter<Person> personArrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    personList
            );
            lstVwInfo.setAdapter(personArrayAdapter);
            */

            /* -- Using Custom Adapter
            MyCustomAdapter myCustomAdapter = new MyCustomAdapter(personList, this);
            lstVwInfo.setAdapter(myCustomAdapter);
             */

            MyCustomArrayAdapter myCustomArrayAdapter = new MyCustomArrayAdapter(personList, this);
            lstVwInfo.setAdapter(myCustomArrayAdapter);

        }
    }

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