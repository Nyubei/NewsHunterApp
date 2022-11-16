package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class WriteActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        MaterialButton btnContinuar = (MaterialButton) findViewById(R.id.btnContinuar);

        EditText txtTitle = (EditText) findViewById(R.id.vNewsTittle);
        EditText txtBody = (EditText) findViewById(R.id.vBodyNews);

        txtBody.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(txtBody.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strTitle = txtTitle.getText().toString();
                String strBody = txtBody.getText().toString();

                if((TextUtils.isEmpty(strTitle) || TextUtils.isEmpty(strBody))){
                    Toast.makeText(WriteActivity.this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(WriteActivity.this, WriteActivity2.class));
                }
            }
        });
    }
}