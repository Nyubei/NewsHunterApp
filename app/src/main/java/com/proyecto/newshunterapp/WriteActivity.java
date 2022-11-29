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
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class WriteActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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
                    Noticia n = new Noticia();
                    n.setIdNoticia(UUID.randomUUID().toString());
                    n.setTitular(strTitle);
                    n.setCuerpoNoticia(strBody);
                    n.setFecha(getFechaNormal(getFechaMili()));
                    n.setTimestamp(getFechaMili()*-1);

                    inicializarFirebase();

                    databaseReference.child("Noticias").child(n.getIdNoticia()).setValue(n);


                    startActivity(new Intent(WriteActivity.this, WriteActivity2.class));
                }
            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public long getFechaMili(){
        Calendar calendar = Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();
        return tiempounix;
    }

    public String getFechaNormal(long fechaMili){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        String fecha = sdf.format(fechaMili);
        return fecha;
    }
}