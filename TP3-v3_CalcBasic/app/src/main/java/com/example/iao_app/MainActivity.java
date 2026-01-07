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

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText v1, v2, res;
    RadioGroup rg;
    Button calc, raz, quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.etValeur1);
        v2 = findViewById(R.id.etValeur2);
        res = findViewById(R.id.etResultat);
        rg = findViewById(R.id.rgOperation);
        calc = findViewById(R.id.btnCalc);
        raz = findViewById(R.id.btnRaz);
        quit = findViewById(R.id.btnQuit);

        calc.setOnClickListener(v -> calculer());
        raz.setOnClickListener(v -> reset());
        quit.setOnClickListener(v -> finish());
    }

    private void calculer() {
        double a = Double.parseDouble(v1.getText().toString());
        double b = Double.parseDouble(v2.getText().toString());
        double r = 0;

        int selectedId = rg.getCheckedRadioButtonId();

        if (selectedId == R.id.rbPlus) {
            r = a + b;
        }
        else if (selectedId == R.id.rbMoins) {
            r = a - b;
        }
        else if (selectedId == R.id.rbMult) {
            r = a * b;
        }
        else if (selectedId == R.id.rbDiv) {
            if (b != 0) {
                r = a / b;
            } else {
                res.setText("Erreur : division par zéro");
                return;
            }
        }
        res.setText(String.valueOf(r));
    }

    private void reset() {
        v1.setText("");
        v2.setText("");
        res.setText("");
        rg.clearCheck();
    }
}
