package net.branium;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.branium.adapter.StudentAdapter;
import net.branium.databinding.ActivityMainBinding;
import net.branium.db.StudentDB;
import net.branium.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<Student> students = new ArrayList<>();
    private static ActivityMainBinding binding;
    private static StudentAdapter adapter;
    private static StudentDB studentDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeData();
    }

    private void initializeData() {
        studentDB = new StudentDB(this, "ContactDB", null, 1);
        studentDB.addStudent(new Student("GHA01830", "Vũ Trường An", 7.5d, 7d, 8.5d));
        studentDB.addStudent(new Student("GHA01832", "Vũ Trường Hân", 7.5, 7, 8.5));
        studentDB.addStudent(new Student("GHA01833", "Vũ Trường Toàn", 7.5, 7, 8.5));
        students = studentDB.getAllStudents();
        adapter = new StudentAdapter(students, this);
        binding.recycleViewStudent.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleViewStudent.setAdapter(adapter);
    }
}