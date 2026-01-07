package com.example.iao_app;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    EditText etNom, etAge, etEcole, etId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        etNom = findViewById(R.id.etNom);
        etAge = findViewById(R.id.etAge);
        etEcole = findViewById(R.id.etEcole);
        etId = findViewById(R.id.etId);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnView = findViewById(R.id.btnView);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(v -> {
            db.insertEtudiant(
                    etNom.getText().toString(),
                    Integer.parseInt(etAge.getText().toString()),
                    etEcole.getText().toString()
            );
            Toast.makeText(this, "Étudiant ajouté", Toast.LENGTH_SHORT).show();
        });

        btnView.setOnClickListener(v -> {
            Cursor c = db.getAllEtudiants();
            StringBuilder sb = new StringBuilder();

            while (c.moveToNext()) {
                sb.append("ID: ").append(c.getInt(0)).append("\n");
                sb.append("Nom: ").append(c.getString(1)).append("\n");
                sb.append("Age: ").append(c.getInt(2)).append("\n");
                sb.append("École: ").append(c.getString(3)).append("\n\n");
            }

            new AlertDialog.Builder(this)
                    .setTitle("Liste des étudiants")
                    .setMessage(sb.toString())
                    .setPositiveButton("OK", null)
                    .show();
        });

        btnUpdate.setOnClickListener(v -> {
            db.updateEtudiant(
                    Integer.parseInt(etId.getText().toString()),
                    etNom.getText().toString(),
                    Integer.parseInt(etAge.getText().toString()),
                    etEcole.getText().toString()
            );
            Toast.makeText(this, "Étudiant modifié", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {
            db.deleteEtudiant(Integer.parseInt(etId.getText().toString()));
            Toast.makeText(this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
        });
    }
}
