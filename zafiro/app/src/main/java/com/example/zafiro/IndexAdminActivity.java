package com.example.zafiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class IndexAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_admin);

        LinearLayout verProductos = findViewById(R.id.layoutVerProductos);
        verProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexAdminActivity.this, VerProductosAdmin.class);
                startActivity(intent);
            }
        });

        LinearLayout AgregarProductos = findViewById(R.id.layoutAgregarProducto);
        AgregarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexAdminActivity.this, AgregarProductoActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout BuscarUsuario = findViewById(R.id.layoutBuscarUsuario);
        BuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexAdminActivity.this, VerUsuarios.class);
                startActivity(intent);
            }
        });




    }
}