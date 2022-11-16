package com.proyecto.newshunterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {

    private TextView vusername, vpass, vmail, vocupacion;
    private MaterialButton btnRegistro;


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

        vusername = findViewById(R.id.vUserName);
        vpass = findViewById(R.id.vUserPass);
        vmail = findViewById(R.id.vUserMail);
        vocupacion = findViewById(R.id.dropdown);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String username = vusername.getText().toString();
                    String pass = vpass.getText().toString();
                    String mail = vmail.getText().toString();
                    String ocupacion = vocupacion.getText().toString();

                    SQLiteDatabase db = openOrCreateDatabase("BD_USER", Context.MODE_PRIVATE,null);

                    String sql = "insert into user(username,pass,mail,ocupacion)values(?,?,?,?)";
                    SQLiteStatement statement = db.compileStatement(sql);
                    statement.bindString(1,username);
                    statement.bindString(2,pass);
                    statement.bindString(3,mail);
                    statement.bindString(4,ocupacion);
                    statement.execute();
                    Toast.makeText(SignUpActivity.this,"Usuario Registrado", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(SignUpActivity.this,"Usuario no pudo ser Registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
