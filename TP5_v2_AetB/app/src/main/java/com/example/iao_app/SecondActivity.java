package com.example.iao_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextMessage = findViewById(R.id.editTextMessage);

        // Récupérer le message envoyé depuis MainActivity
        String receivedMessage = getIntent().getStringExtra("message");

        if (receivedMessage != null && !receivedMessage.isEmpty()) {
            editTextMessage.setText(receivedMessage);
        } else {
            editTextMessage.setText("rien reçu");
        }
    }

    public void validateModification(View view) {
        String modifiedText = editTextMessage.getText().toString().trim();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("modifiedText", modifiedText);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void goBack(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}