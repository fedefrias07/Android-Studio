package com.example.zafiro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zafiro.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class VerUsuarios extends AppCompatActivity {

    private List<Persona> listaPersonas = new ArrayList<>();
    private ArrayAdapter<Persona> arrayAdapterPersona;
    private ListView listViewPersonas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios);

        listViewPersonas = findViewById(R.id.listViewPersonas);
        arrayAdapterPersona = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPersonas);
        listViewPersonas.setAdapter(arrayAdapterPersona);

        inicializarFirebase();
        obtenerDatosPersonas();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Usuarios");
    }

    private void obtenerDatosPersonas() {
        // Agregar un listener para escuchar los cambios en los usuarios
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la lista antes de agregar los nuevos datos
                listaPersonas.clear();

                // Iterar sobre los usuarios en la base de datos
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Persona persona = snapshot.getValue(Persona.class);
                    if (persona != null) {
                        listaPersonas.add(persona);
                    }
                }

                // Notificar al adaptador que los datos han cambiado
                arrayAdapterPersona.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("VerUsuarios", "Error al obtener datos de la base de datos: " + databaseError.getMessage());
            }
        });
    }
}
