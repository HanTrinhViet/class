package net.braniumacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1;
    RadioGroup radioGroup;
    Spinner spinner;
    TimePicker timePicker;
    DatePicker datePicker;
    Button btnShowDatePicker;
    ProgressBar progressBar;
    Button btnProgressBar;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* == CheckBox ==
        checkBox1 = findViewById(R.id.checkbox1);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "The checkbox1 is check", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "The checkbox1 is not check", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */


        /* == RadioGroup and RadioButton ==
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(
                (radioGroup, checkedId) -> {
                    RadioButton radioButton = findViewById(checkedId);
                    Toast.makeText(this, "You select: " + radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }
        );
         */

        /* == Spinner ==
        spinner = findViewById(R.id.spinner1);
        String[] courses = {"C++", "Java", "Kotlin", "Python"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        spinner.setAdapter(adapter);
        */

        /* == TimePicker ==
        timePicker = findViewById(R.id.timePicker1);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(MainActivity.this,
                        "Hour: " + hourOfDay + " minute: " + minute,
                        Toast.LENGTH_SHORT).show();
            }
        });
         */

        /* == DatePicker ==
        datePicker = findViewById(R.id.datePicker1);
        btnShowDatePicker = findViewById(R.id.btnShowDatePicker);

        btnShowDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = "Day " + datePicker.getDayOfMonth();
                String month = "Month " + (datePicker.getMonth() + 1);
                String year = "Year " + datePicker.getYear();

                Toast.makeText(MainActivity.this, String.format("%s - %s - %s", day, month, year), Toast.LENGTH_SHORT).show();
            }
        });
         */



        btnProgressBar = findViewById(R.id.btnProgressBar);
        progressBar = findViewById(R.id.progressBar);
        btnProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress += 10;
                progressBar.setProgress(progress);
            }
        });

    }
}