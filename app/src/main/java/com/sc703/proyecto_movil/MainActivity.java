package com.sc703.proyecto_movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sc703.proyecto_movil.ui.contactenosApp.Fragment_Contactenos;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText et_Correo, et_Contraseña;
    private Button bn_Registro, bn_Ingreso;
    final private int CODIGO_APP = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_Correo = findViewById(R.id.et_Correo);
        et_Contraseña = findViewById(R.id.et_Contraseña);
        mAuth = FirebaseAuth.getInstance();
        Permisos();
    }

    private void Permisos() {
        int Permiso_Internet = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET);
        int Permiso_Telefono = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
        if (Permiso_Internet != PackageManager.PERMISSION_GRANTED || Permiso_Telefono != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.CALL_PHONE}, CODIGO_APP);
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
            Intent intent = new Intent(this,Principal.class);
            startActivity(intent);
        }
    }

    public void Registro(View view){
        String Correo = et_Correo.getText().toString();
        String Contraseña = et_Contraseña.getText().toString();

        mAuth.createUserWithEmailAndPassword(Correo, Contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Registro", "Se creo el usuario con éxito");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Registro", "La creacion del usuario fallo", task.getException());
                            Toast.makeText(getApplicationContext(), "Creacion de usuario fallida",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void Ingreso (View view){
        String Correo = et_Correo.getText().toString();
        String Contraseña = et_Contraseña.getText().toString();
        mAuth.signInWithEmailAndPassword(Correo, Contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Ingreso", "Ingresaste con exito");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Ingreso", "Ingreso fallido", task.getException());
                            Toast.makeText(getApplicationContext(), "Ingreso fallido.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}