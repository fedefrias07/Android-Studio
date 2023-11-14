package com.example.techsolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // Definir las claves para SharedPreferences
    private static final String PREFS_NAME = "MisPreferencias";
    private static final String KEY_USUARIO = "usuario";
    private static final String KEY_CONTRASENA = "contrasena";

    private EditText ingresarEmailUsuario, ingresarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        ingresarEmailUsuario = findViewById(R.id.ingresarEmailUsuario);
        ingresarContrasena = findViewById(R.id.ingresarContrasena);

        Button btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar los datos ingresadas por el usuario
                if (verificarCredenciales()) {
                    // Inicio de sesión exitoso, redirigir a MainActivity3
                    Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                } else {
                    // Credenciales incorrectas, mostrar mensaje de error
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private boolean verificarCredenciales() {
        // Recuperar las credenciales almacenadas en SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usuarioAlmacenado = prefs.getString(KEY_USUARIO, "");
        String contrasenaAlmacenada = prefs.getString(KEY_CONTRASENA, "");

        // Verificar las credenciales ingresadas por el usuario
        String usuarioIngresado = ingresarEmailUsuario.getText().toString().trim();
        String contrasenaIngresada = ingresarContrasena.getText().toString().trim();

        return usuarioIngresado.equals(usuarioAlmacenado) && contrasenaIngresada.equals(contrasenaAlmacenada);
    }
}