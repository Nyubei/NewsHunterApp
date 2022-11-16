package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView vusername =(TextView) findViewById(R.id.vUserName);
        TextView vpassword =(TextView) findViewById(R.id.vUserPass);

        MaterialButton btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        MaterialButton btnCrear = (MaterialButton) findViewById(R.id.btnCrear);
        TextView vRecordar = (TextView) findViewById(R.id.vRecordar);

        //Boton ingresar
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("BD_USER", Context.MODE_PRIVATE, null);
                    final Cursor c = db.rawQuery("select * from user", null);
                    int id = c.getColumnIndex("id");
                    int username = c.getColumnIndex("username");
                    int pass = c.getColumnIndex("pass");
                    int mail = c.getColumnIndex("mail");
                    int ocupacion = c.getColumnIndex("ocupacion");

                    final ArrayList<User> lista = new ArrayList<>();

                    if (c.moveToFirst()) {
                        do {
                            User user = new User();
                            user.id = c.getString(id);
                            user.username = c.getString(username);
                            user.pass = c.getString(pass);
                            user.mail = c.getString(mail);
                            user.ocupacion = c.getString(ocupacion);
                            lista.add(user);
                        } while (c.moveToNext());
                    }

                    int validar = 0;
                    for (int i = 0; i < lista.size(); i++) {
                        User user = lista.get(i);

                        if (vusername.getText().toString().equals(user.username) && vpassword.getText().toString().equals(user.pass)) {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            validar = 1;
                            i = lista.size();
                        }
                    }
                    if (validar == 0) {
                        Toast.makeText(LoginActivity.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(LoginActivity.this, "Ha ocurrido un error, intentalo nuevamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Crear
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        //Text view recuperar contraseña
        vRecordar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RecoverActivity.class));
            }
        });
    }
}