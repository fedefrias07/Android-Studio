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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.zafiro.model.Persona;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static final String TAG = "LoginActivity";

    private EditText editTextCorreoo;
    private EditText editTextContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        editTextCorreoo = findViewById(R.id.editTextUsername);
        editTextContrasena = findViewById(R.id.editTextPassword);

        TextView btnIniciarSesion = findViewById(R.id.btnLogin);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextCorreoo.getText().toString();
                String contrasena = editTextContrasena.getText().toString();


                if (email.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    signIn(email, contrasena);
                }
            }
        });


        TextView btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso, ahora obtenemos información adicional del usuario
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                obtenerInformacionUsuario(user.getUid());
                            }
                            // Actualizar la interfaz de usuario
                            updateUI(user);
                        } else {
                            // Si el inicio de sesión falla, muestra un mensaje de error al usuario
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Error en el inicio de sesión: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void obtenerInformacionUsuario(String userId) {
        // Obtener información adicional del usuario desde la base de datos
        databaseReference.child("Usuarios").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // El nodo del usuario existe en la base de datos
                            Persona usuario = dataSnapshot.getValue(Persona.class);
                            // Puedes acceder a la información adicional aquí, por ejemplo, el rol (is_admin)
                            boolean isAdmin = usuario != null && usuario.getIs_admin();
                            // Puedes realizar acciones en la interfaz de usuario según el rol
                            // Por ejemplo, redirigir a una actividad de administrador o de usuario normal
                            redirigirSegunRol(isAdmin);
                        } else {
                            // El nodo del usuario no existe en la base de datos
                            Log.d(TAG, "No se encontró información adicional del usuario en la base de datos.");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Manejar el caso en el que la lectura desde la base de datos es cancelada
                        Log.e(TAG, "Error al obtener información del usuario: " + databaseError.getMessage());
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        // Puedes realizar acciones en la interfaz de usuario después de un inicio de sesión exitoso
        // Por ejemplo, redirigir a otra actividad o mostrar un mensaje de éxito
        if (user != null) {
            Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            // Aquí puedes redirigir a otra actividad si es necesario
        } else {
            // Manejar el caso en el que el usuario es nulo
        }
    }

    private void redirigirSegunRol(boolean isAdmin) {
        if (isAdmin) {
            // Redirigir a la actividad de administrador
            Intent intent = new Intent(LoginActivity.this, IndexAdminActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Redirigir a la actividad de usuario normal
            Intent intent = new Intent(LoginActivity.this, IndexUsuarioActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
