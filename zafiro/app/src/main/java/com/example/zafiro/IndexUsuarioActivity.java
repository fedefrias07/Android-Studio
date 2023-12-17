package com.example.zafiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class IndexUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_usuario);

        LinearLayout VerProductos = findViewById(R.id.layoutVerProductos);
        VerProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexUsuarioActivity.this, VerProductosUsuario.class);
                startActivity(intent);
            }
        });

        LinearLayout VerCarrito = findViewById(R.id.layoutVerCarrito);
        VerCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexUsuarioActivity.this, CarritoActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout AtencionCliente = findViewById(R.id.layoutAtencionCliente);
        AtencionCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexUsuarioActivity.this, AtencionClienteActivity.class);
                startActivity(intent);
            }
        });



    }
}