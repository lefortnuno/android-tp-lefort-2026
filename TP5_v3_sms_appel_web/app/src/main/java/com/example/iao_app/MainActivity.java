package com.example.iao_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

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

        textView = findViewById(R.id.textView2);
    }
    public void handleSMS(View view) {
        Button btn = (Button) view;

        // Toast avec le texte du bouton
        Toast.makeText(this, btn.getText(), Toast.LENGTH_SHORT).show();

        // Intent SMS -> ouvre directement l'application Messages
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto: 0642359584"));
        smsIntent.putExtra("sms_body", "Bonjour automatique depuis mon application");

        startActivity(smsIntent);
    }

    public void handleCall(View view) {
        Button btn = (Button) view;

        Toast.makeText(this, btn.getText(), Toast.LENGTH_SHORT).show();

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:0642359584"));

        startActivity(callIntent);
    }

    public void handleWeb(View view) {
        Button btn = (Button) view;

        Toast.makeText(this, btn.getText(), Toast.LENGTH_SHORT).show();

        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://google.com"));

        startActivity(webIntent);
    }
}
