package com.example.lab04_propuesto_java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements RegistroFragment.OnRegisterListener{
    private AccountEntity accountEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnAddAccount = findViewById(R.id.btnAddAccount);

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedorFragment, RegistroFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("login") // Name can be null
                        .commit();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AccountEntity", "Nombre: " + accountEntity.getFirstname() + "\n"
                + "Usuario: " + accountEntity.getUsername() + "\nContraseña: " + accountEntity.getPassword());
            }
        });
    }
    @Override
    public void onRegister(AccountEntity account) {
        obtenerDatosUsuario(account);
    }
    private void obtenerDatosUsuario(AccountEntity account) {
        accountEntity = account;
    }
}