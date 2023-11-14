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


public class MainActivity2 extends AppCompatActivity {

    // Definir las claves para SharedPreferences
     static final String PREFS_NAME = "MisPreferencias";
     static final String KEY_NOMBRE = "nombre";
     static final String KEY_APELLIDO = "apellido";
     static final String KEY_USUARIO = "usuario";
     static final String KEY_EMAIL = "email";
     static final String KEY_CONTRASENA = "contrasena";
     static final String KEY_TIPO_USUARIO = "tipo_usuario";

    private EditText ingresarNombre, ingresarApellido, crearUsuario, ingresarEmail, ingresarContrasena;

    TextView textoIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializar vistas
        ingresarNombre = findViewById(R.id.ingresarNombre);
        ingresarApellido = findViewById(R.id.ingresarApellido);
        crearUsuario = findViewById(R.id.crearUsuario);
        ingresarEmail = findViewById(R.id.ingresarEmail);
        ingresarContrasena = findViewById(R.id.ingresarContrasena);
        textoIngresar = findViewById(R.id.textoIngresar);

        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Guardar datos en SharedPreferences al hacer clic en el botón
                guardarDatosEnSharedPreferences();
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);

            }
        });

        textoIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // Recuperar datos de SharedPreferences al iniciar la actividad
        cargarDatosDesdeSharedPreferences();
    }

    private void guardarDatosEnSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(KEY_NOMBRE, ingresarNombre.getText().toString());
        editor.putString(KEY_APELLIDO, ingresarApellido.getText().toString());
        editor.putString(KEY_USUARIO, crearUsuario.getText().toString());
        editor.putString(KEY_EMAIL, ingresarEmail.getText().toString());
        editor.putString(KEY_CONTRASENA, ingresarContrasena.getText().toString());

        // Guardar los cambios
        editor.apply();
    }

    private void cargarDatosDesdeSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        ingresarNombre.setText(prefs.getString(KEY_NOMBRE, ""));
        ingresarApellido.setText(prefs.getString(KEY_APELLIDO, ""));
        crearUsuario.setText(prefs.getString(KEY_USUARIO, ""));
        ingresarEmail.setText(prefs.getString(KEY_EMAIL, ""));
        ingresarContrasena.setText(prefs.getString(KEY_CONTRASENA, ""));
        ingresarContrasena.setText(prefs.getString(KEY_CONTRASENA, ""));

    }
}