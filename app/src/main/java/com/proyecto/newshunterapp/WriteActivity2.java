package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class WriteActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write2);

        MaterialButton btnLocacion = (MaterialButton) findViewById(R.id.btnLocacion);

        btnLocacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WriteActivity2.this,LocationActivity.class));
            }
        });

    }
}