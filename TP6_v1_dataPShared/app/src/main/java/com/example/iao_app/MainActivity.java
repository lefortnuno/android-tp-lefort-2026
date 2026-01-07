package com.example.iao_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNom, editTextEmail;
    private Button btnEnregistrer, btnCharger, btnEffacer;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MesPreferences";
    private static final String KEY_NOM = "nom";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les vues
        editTextNom = findViewById(R.id.editTextNom);
        editTextEmail = findViewById(R.id.editTextEmail);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        btnCharger = findViewById(R.id.btnCharger);
        btnEffacer = findViewById(R.id.btnEffacer);

        // Initialiser SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Configurer les listeners des boutons
        btnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrerDonnees();
            }
        });

        btnCharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chargerDonnees();
            }
        });

        btnEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effacerDonnees();
            }
        });
    }

    private void enregistrerDonnees() {
        String nom = editTextNom.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (nom.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validation basique de l'email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enregistrer dans SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NOM, nom);
        editor.putString(KEY_EMAIL, email);
        editor.apply();

        Toast.makeText(this, "Données enregistrées avec succès", Toast.LENGTH_SHORT).show();
        viderChamps();
    }

    private void chargerDonnees() {
        // Récupérer les données depuis SharedPreferences
        String nom = sharedPreferences.getString(KEY_NOM, "");
        String email = sharedPreferences.getString(KEY_EMAIL, "");

        if (nom.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Aucune donnée trouvée", Toast.LENGTH_SHORT).show();
            return;
        }

        // Afficher les données dans les EditText
        editTextNom.setText(nom);
        editTextEmail.setText(email);

        Toast.makeText(this, "Données chargées avec succès", Toast.LENGTH_SHORT).show();
    }

    private void effacerDonnees() {
        // Vérifier s'il y a des données à effacer
        String nom = sharedPreferences.getString(KEY_NOM, "");
        String email = sharedPreferences.getString(KEY_EMAIL, "");

        if (nom.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Aucune donnée à effacer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Effacer les données de SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        viderChamps();
        Toast.makeText(this, "Données effacées avec succès", Toast.LENGTH_SHORT).show();
    }

    private void viderChamps() {
        editTextNom.setText("");
        editTextEmail.setText("");
    }
}