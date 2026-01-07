package com.example.iao_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

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

        editText = findViewById(R.id.editTextText2);
    }

    public void displayMessage(View view) {
        String message = editText.getText().toString().trim();

        if (message.isEmpty()) {
            message = "saisie vide";
        }

        Toast.makeText(this, "Envoi du message: " + message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}