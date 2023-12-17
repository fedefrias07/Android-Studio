package com.example.zafiro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.zafiro.model.Persona;
import android.widget.Toast;

import java.util.UUID;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String TAG = "RegistroActivity";

    private EditText editTextCorreo;
    private EditText editTextContrasena;

    private EditText editTextusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasena = findViewById(R.id.editTextPassword);
        editTextusuario = findViewById(R.id.editTextUsername);


        TextView btnCrearCuenta = findViewById(R.id.btnRegistro);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextusuario.getText().toString();
                String email = editTextCorreo.getText().toString();
                String contrasena = editTextContrasena.getText().toString();

                if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    createAccount(nombre, email, contrasena);
                    Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        TextView btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAccount(final String nombre, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso, ahora vamos a almacenar información adicional en la base de datos
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Agregar información adicional a la base de datos
                                agregarInformacionAdicional(user.getUid(), nombre, email, password);
                            }

                            // Actualizar la interfaz de usuario
                            updateUI(user);
                        } else {
                            // Si el registro falla, muestra un mensaje de error al usuario
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistroActivity.this, "Error en el registro: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void agregarInformacionAdicional(String userId, String nombre, String email, String contrasena) {
        // Agregar información adicional del usuario (como el rol) en la base de datos
        Persona usuario = new Persona();
        usuario.setId(userId);
        usuario.setUsuario(nombre);
        usuario.setCorreo(email);
        usuario.setContrasena(contrasena);
        usuario.setIs_admin(false); // Puedes ajustar esto según tus necesidades

        // Guardar la información en la base de datos
        databaseReference.child("Usuarios").child(userId).setValue(usuario);
    }

    private void updateUI(FirebaseUser user) {
        // Puedes realizar acciones en la interfaz de usuario después de un registro exitoso
        // Por ejemplo, redirigir a otra actividad o mostrar un mensaje de éxito
        if (user != null) {
            Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            // Aquí puedes redirigir a otra actividad si es necesario
        } else {
            // Manejar el caso en el que el usuario es nulo
        }
    }
}
