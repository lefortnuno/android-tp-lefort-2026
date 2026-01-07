package com.example.iao_app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iao_app.R;

public class MainActivity extends AppCompatActivity {

    EditText etNombre1, etNombre2, etResultat;
    Button btnSomme, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre1 = findViewById(R.id.etNombre1);
        etNombre2 = findViewById(R.id.etNombre2);
        etResultat = findViewById(R.id.etResultat);
        btnSomme = findViewById(R.id.btnSomme);
        btnReset = findViewById(R.id.btnReset);

        // Bouton SOMME
        btnSomme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double n1 = Double.parseDouble(etNombre1.getText().toString());
                double n2 = Double.parseDouble(etNombre2.getText().toString());
                double somme = n1 + n2;
                etResultat.setText(String.valueOf(somme));
            }
        });

        // Bouton RESET
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNombre1.setText("");
                etNombre2.setText("");
                etResultat.setText("");
            }
        });
    }
}
