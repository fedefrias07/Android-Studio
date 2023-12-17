package com.example.zafiro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zafiro.model.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class VerProductosUsuario extends AppCompatActivity {

    private List<Producto> listaProductos = new ArrayList<>();
    private ArrayAdapter<Producto> arrayAdapterProducto;
    private ListView listViewProductos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos_usuario);

        listViewProductos = findViewById(R.id.listViewProductos);
        arrayAdapterProducto = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProductos);
        listViewProductos.setAdapter(arrayAdapterProducto);

        inicializarFirebase();
        obtenerDatosProductos();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Producto");
    }

    private void obtenerDatosProductos() {
        // Agregar un listener para escuchar los cambios en los productos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la lista antes de agregar los nuevos datos
                listaProductos.clear();

                // Iterar sobre los productos en la base de datos
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Producto producto = snapshot.getValue(Producto.class);
                    if (producto != null) {
                        listaProductos.add(producto);
                    }
                }

                // Notificar al adaptador que los datos han cambiado
                arrayAdapterProducto.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("VerProductosUsuario", "Error al obtener datos de la base de datos: " + databaseError.getMessage());
            }
        });
    }
}
