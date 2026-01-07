package com.example.iao_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNom, editTextEmail, editTextSaisie;
    private Button btnEnregistrer, btnCharger, btnEffacer;
    private Button btnEcrireInterne, btnLireInterne;

    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "MesPreferences";
    private static final String KEY_NOM = "nom";
    private static final String KEY_EMAIL = "email";

    private static final String FICHIER_INTERNE = "saisie.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les vues pour Stockage Interne
        editTextSaisie = findViewById(R.id.editTextSaisie);
        btnEcrireInterne = findViewById(R.id.btnEcrireInterne);
        btnLireInterne = findViewById(R.id.btnLireInterne);


        // Configurer les listeners des boutons (Stockage Interne)
        btnEcrireInterne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ecrireFichierInterne();
            }
        });

        btnLireInterne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lireFichierInterne();
            }
        });
    }

    // Méthodes pour Stockage Interne
    private void ecrireFichierInterne() {
        String saisie = editTextSaisie.getText().toString().trim();

        if (saisie.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir du texte", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            FileOutputStream fos = openFileOutput(FICHIER_INTERNE, MODE_PRIVATE);
            fos.write(saisie.getBytes());
            fos.close();

            editTextSaisie.setText("");
            Toast.makeText(this, "Texte enregistré dans le fichier", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Erreur lors de l'écriture: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void lireFichierInterne() {
        try {
            FileInputStream fis = openFileInput(FICHIER_INTERNE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String ligne;

            while ((ligne = br.readLine()) != null) {
                sb.append(ligne).append("\n");
            }

            fis.close();

            String contenu = sb.toString().trim();
            if (contenu.isEmpty()) {
                Toast.makeText(this, "Le fichier est vide", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contenu du fichier:\n" + contenu, Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Erreur lors de la lecture: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}