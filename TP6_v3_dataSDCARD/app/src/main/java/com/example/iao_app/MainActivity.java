package com.example.iao_app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// ===============
// ligne de commande pour push un fichier
// adb push chaine.txt /sdcard/Android/data/com.example.iao_app/files/
// pour verifier:
// View > Tools Windows > Device Explorer > sdcard > Android > iao_app > files
// ===============

public class MainActivity extends AppCompatActivity {

    private EditText editTextSaisie;
    private Button btnLireExterne;

    private static final String FICHIER_EXTERNE = "chaine.txt";
    private static final int REQUEST_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSaisie = findViewById(R.id.editTextSaisie);
        btnLireExterne = findViewById(R.id.btnLireExterne);

        demanderPermission();

        btnLireExterne.setOnClickListener(v -> lireFichierExterne());
    }

    private void lireFichierExterne() {

        try {
            // Répertoire externe autorisé pour l’app (Android 11+)
            File dir = getExternalFilesDir(null);
            File fichier = new File(dir, FICHIER_EXTERNE);

            if (!fichier.exists()) {
                Toast.makeText(this,
                        "Fichier introuvable :\n" + fichier.getAbsolutePath(),
                        Toast.LENGTH_LONG).show();
                return;
            }

            FileInputStream fis = new FileInputStream(fichier);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            StringBuilder sb = new StringBuilder();
            String ligne;
            while ((ligne = br.readLine()) != null) {
                sb.append(ligne).append("\n");
            }

            br.close();
            fis.close();

            Toast.makeText(this,
                    "Contenu du fichier :\n" + sb.toString(),
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this,
                    "Erreur lecture : " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void demanderPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Permission accordée", Toast.LENGTH_SHORT).show();
        }
    }
}
