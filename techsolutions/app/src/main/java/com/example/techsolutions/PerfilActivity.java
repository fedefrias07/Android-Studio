package com.example.techsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;

public class PerfilActivity extends AppCompatActivity {


    private TextView textViewNombreApellido;
    private TextView textViewEmail;
    private TextView textViewNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializar vistas
        textViewNombreApellido = findViewById(R.id.nombreApellido);
        textViewEmail = findViewById(R.id.email);
        textViewNombreUsuario = findViewById(R.id.nombreUsuario);

        // Cargar información desde SharedPreferences
        cargarInformacionDesdeSharedPreferences();

        Button btnEditarPerfil = findViewById(R.id.btnIngresar);
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes agregar la lógica para ir a la actividad de edición de perfil si es necesario
                // Ejemplo: Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
                // startActivity(intent);
            }
        });
    }

    private void cargarInformacionDesdeSharedPreferences() {
        // Aquí debes recuperar la información almacenada en SharedPreferences y asignarla a las vistas
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // Ejemplo: Obtener el nombre almacenado
        String nombre = prefs.getString("KEY_NOMBRE", "");
        // Ejemplo: Obtener el apellido almacenado
        String apellido = prefs.getString("KEY_APELLIDO", "");
        // Ejemplo: Obtener el email almacenado
        String email = prefs.getString("KEY_EMAIL", "");
        // Ejemplo: Obtener el nombre de usuario almacenado
        String nombreUsuario = prefs.getString("KEY_USUARIO", "");

        // Asignar la información a las vistas
        textViewNombreApellido.setText("Nombre y Apellido: " + nombre + " " + apellido);
        textViewEmail.setText("Email: " + email);
        textViewNombreUsuario.setText("Nombre de Usuario: " + nombreUsuario);
    }
}