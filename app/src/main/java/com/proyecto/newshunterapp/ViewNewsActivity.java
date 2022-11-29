package com.proyecto.newshunterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewNewsActivity extends AppCompatActivity {

    private ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();
    ArrayAdapter<Noticia> arrayAdapterNoticia;
    ListViewNoticiasAdapter listViewNoticiasAdapter;
    ListView listViewNoticias;
    Noticia noticiaSeleccionada;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        listViewNoticias = findViewById(R.id.listViewNoticias);

        inicializarFirebase();
        listarNoticias();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarNoticias(){
        databaseReference.child("Noticias").orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaNoticias.clear();
                for(DataSnapshot objSnapshot : snapshot.getChildren()){
                    Noticia n = objSnapshot.getValue(Noticia.class);
                    listaNoticias.add(n);
                }
                //Inicia adaptador propio
                listViewNoticiasAdapter = new ListViewNoticiasAdapter(ViewNewsActivity.this,listaNoticias);
                //arrayAdapterNoticia = new ArrayAdapter<Noticia>(ViewNewsActivity.this,android.R.layout.simple_list_item_1,listaNoticias);
                listViewNoticias.setAdapter(listViewNoticiasAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}