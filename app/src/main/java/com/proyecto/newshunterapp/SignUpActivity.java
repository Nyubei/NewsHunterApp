package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String[] type = new String[] {"Estudiante", "Trabajador"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        type);

        AutoCompleteTextView editTextFilledExposedDropdown =
                findViewById(R.id.dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);

    }
}