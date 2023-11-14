package com.example.techsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageView imageViewUsuario = findViewById(R.id.usuario);

        imageViewUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity3.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout layoutVerPedidos = findViewById(R.id.layoutVerPedidos);
        LinearLayout layoutCitas = findViewById(R.id.layoutCitas);
        LinearLayout layoutAtencionCliente = findViewById(R.id.layoutAtencionCliente);

        layoutVerPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, VerPedidos.class);
                startActivity(intent);
            }
        });

        layoutCitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, GestionarCitas.class);
                startActivity(intent);
            }
        });

        layoutAtencionCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, AtencionCliente.class);
                startActivity(intent);
            }
        });


    }
}


