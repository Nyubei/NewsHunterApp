package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username =(TextView) findViewById(R.id.vUserName);
        TextView password =(TextView) findViewById(R.id.vUserPass);

        MaterialButton btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        MaterialButton btnCrear = (MaterialButton) findViewById(R.id.btnCrear);
        TextView vRecordar = (TextView) findViewById(R.id.vRecordar);

        //Boton ingresar
        //Prueba user=javier pass=1234
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("Javier") && password.getText().toString().equals("1234")){
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }else
                    Toast.makeText(LoginActivity.this,"Datos Incorrectos",Toast.LENGTH_SHORT).show();
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