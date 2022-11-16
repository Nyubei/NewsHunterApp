package com.proyecto.newshunterapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (!doesDatabaseExist(MainActivity.this,"BD_USER")){
                    crear();
                }

                Intent i = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(i);

                finish();
            }
        }, 2000);

    }

    public void crear()
    {
        try
        {
            String username = "admin";
            String pass = "1234";
            String mail = "admin@mail.com";
            String ocupacion = "Trabajador";

            SQLiteDatabase db = openOrCreateDatabase("BD_USER", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, pass VARCHAR, mail VARCHAR, ocupacion VARCHAR)");

            String sql = "insert into user(username,pass,mail,ocupacion)values(?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,username);
            statement.bindString(2, pass);
            statement.bindString(3, mail);
            statement.bindString(4,ocupacion);
            statement.execute();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error inicio db",Toast.LENGTH_LONG).show();
        }
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}