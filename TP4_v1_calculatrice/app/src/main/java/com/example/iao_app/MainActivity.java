package com.example.iao_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etResultat;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewCalculation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etResultat = findViewById(R.id.etResultat);

        // Configuration des boutons numériques
        int[] numberButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button button = (Button) v;
                if (isNewCalculation) {
                    currentNumber = "";
                    isNewCalculation = false;
                }
                currentNumber += button.getText().toString();
                updateDisplay();
            });
        }

        // Configuration des opérateurs
        int[] operatorButtonIds = {R.id.btnPlus, R.id.btnMoins, R.id.btnMoins2, R.id.btnMoins3, R.id.btnDiv};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                if (!currentNumber.isEmpty()) {
                    firstNumber = Double.parseDouble(currentNumber);
                    operator = ((Button) v).getText().toString();
                    currentNumber = "";
                    updateDisplay();
                }
            });
        }

        // Bouton C (Clear)
        findViewById(R.id.btnC).setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            etResultat.setText("0");
            isNewCalculation = true;
        });

        // Bouton =
        findViewById(R.id.btnEgal).setOnClickListener(v -> calculate());
    }

    private void updateDisplay() {
        if (currentNumber.isEmpty() && firstNumber == 0) {
            etResultat.setText("0");
        } else if (currentNumber.isEmpty()) {
            etResultat.setText(String.valueOf(firstNumber) + " " + operator);
        } else {
            etResultat.setText(currentNumber);
        }
    }

    private void calculate() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        etResultat.setText("Erreur");
                        resetCalculator();
                        return;
                    }
                    break;
            }

            // Affichage du résultat
            if (result == (int) result) {
                etResultat.setText(String.valueOf((int) result));
            } else {
                etResultat.setText(String.valueOf(result));
            }

            // Réinitialisation pour le prochain calcul
            currentNumber = String.valueOf(result);
            operator = "";
            firstNumber = 0;
            isNewCalculation = true;
        }
    }

    private void resetCalculator() {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        isNewCalculation = true;
    }
}