package com.example.iao_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView tvMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvMessage = findViewById(R.id.tvMessage);

        // Récupérer le message envoyé depuis MainActivity
        String receivedMessage = getIntent().getStringExtra("message");

        if (receivedMessage != null && !receivedMessage.isEmpty()) {
            tvMessage.setText(receivedMessage);
        } else {
            tvMessage.setText("rien reçu");
        }
    }

    public void goBack(View view) {
        finish(); // Ferme l'activité et retourne à MainActivity
    }
}