package com.example.zafiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zafiro.model.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AgregarProductoActivity extends AppCompatActivity {

    EditText nombre, precio;

    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        nombre = findViewById(R.id.nombreProducto);
        precio = findViewById(R.id.precioProducto);

        inicializarFirebase();

        // AGREGAR PRODUCTO
        Button AgregarProducto = findViewById(R.id.btnAgregarProducto);
        AgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreTexto = nombre.getText().toString();
                String precioTexto = precio.getText().toString();

                if (nombreTexto.isEmpty() || precioTexto.isEmpty()) {
                    validation();
                } else {
                    try {
                        Double precioValor = Double.parseDouble(precioTexto);
                        if (precioValor > 0) {
                            Producto producto = new Producto();
                            producto.setId(UUID.randomUUID().toString());
                            producto.setNombre(nombreTexto);
                            producto.setPrecio(precioValor);

                            databaseReference.child("Producto").child(producto.getId()).setValue(producto);
                            Toast.makeText(AgregarProductoActivity.this, "Producto agregado", Toast.LENGTH_SHORT).show();
                            limpiarCajas();
                        } else {
                            // Precio no válido (menor o igual a cero)
                            Toast.makeText(AgregarProductoActivity.this, "El precio debe ser mayor que cero", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        // Error al convertir el texto del precio a Double
                        Toast.makeText(AgregarProductoActivity.this, "Error en el formato del precio", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void limpiarCajas() {
        nombre.setText("");
        precio.setText("");

    }

    private void validation() {
        String textoNombre = nombre.getText().toString();
        String textoPrecio = precio.getText().toString();

        if (textoNombre.isEmpty()) {
            nombre.setError("Requerido");
        }
        if (textoPrecio.isEmpty()) {
            precio.setError("Requerido");
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDataBase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDataBase.getReference();

    }
}
