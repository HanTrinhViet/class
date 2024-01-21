package net.braniumacademy.lab_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import net.braniumacademy.lab_01.domain.Gender;
import net.braniumacademy.lab_01.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Views
    EditText edTxtFullName;
    EditText edTxtPhoneNumber;
    RadioGroup rdnGroGender;
    Spinner spnHomeTown;
    Button btnAddStudent;
    ListView listViewStudents;

    // Objects
    List<Student> studentList = new ArrayList<>();
    String[] homeTowns = Constants.HOMETOWNS; // Define an array of hometowns in Viet Nam

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Select the element from view
        edTxtFullName = findViewById(R.id.ed_txt_full_name);
        edTxtPhoneNumber = findViewById(R.id.ed_txt_phone_number);
        rdnGroGender = findViewById(R.id.rdo_grp_gender);
        spnHomeTown = findViewById(R.id.spn_hometown);
        btnAddStudent = findViewById(R.id.btn_add_student);
        listViewStudents = findViewById(R.id.lst_vw_students);

        // Register the click event for button
        btnAddStudent.setOnClickListener(this);

        // Config list of items in spinner
        configSpinnerHomeTownItems();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_student) {
            createStudent();
            showStudentToListView();
        }
    }

    /**
     * Method to show the list of students to ListView each time user create a new student.
     */
    private void showStudentToListView() {
        ArrayAdapter<Student> adStudentList = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                studentList
        );
        listViewStudents.setAdapter(adStudentList);
    }

    /**
     * Method to show the list of spinner's items - the item is hometown in Viet Nam
     */
    private void configSpinnerHomeTownItems() {
        ArrayAdapter<String> adHomeTowns = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                homeTowns
        );
        adHomeTowns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHomeTown.setAdapter(adHomeTowns);
    }

    /**
     * Method to create a new student and add to the studentList object
     */
    private void createStudent() {
        String fullName = edTxtFullName.getText().toString();
        String phoneNumber = edTxtPhoneNumber.getText().toString();
        Gender gender = getGenderFromView(rdnGroGender);
        String homeTown = spnHomeTown.getSelectedItem().toString();
        Student student = new Student(fullName, phoneNumber, gender, homeTown);
        studentList.add(student);
//        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }


    /**
     * Method to get the gender of student base on user's checked
     *
     * @param rdoGrpGender RadioGroup contain radioButton
     * @return the Gender enum type, MALE or FEMALE
     */
    private Gender getGenderFromView(RadioGroup rdoGrpGender) {
        if (rdoGrpGender.getCheckedRadioButtonId() == R.id.rdo_btn_male) { // MALE case
            return Gender.MALE;
        } else {  // FEMALE case
            return Gender.FEMALE;
        }
    }
}