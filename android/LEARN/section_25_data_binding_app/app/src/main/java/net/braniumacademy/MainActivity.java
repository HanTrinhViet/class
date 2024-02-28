package net.braniumacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import net.braniumacademy.databinding.ActivityMainBinding;
import net.braniumacademy.event.ClickHandler;
import net.braniumacademy.model.Person;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Person person = new Person("Han Trinh Viet");
        activityMainBinding.setPerson(person);


        ClickHandler clickHandler = new ClickHandler(this);
        activityMainBinding.setClickHandler(clickHandler);

        // 2 wway binding
    }
}