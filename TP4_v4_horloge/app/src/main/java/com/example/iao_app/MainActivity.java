package com.example.iao_app;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FrameLayout clockContainer;
    private AnalogClock analogClock;
    private DigitalClock digitalClock;
    private Switch switchClock;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        clockContainer = findViewById(R.id.clockContainer);
        analogClock = findViewById(R.id.analogClock);
        digitalClock = findViewById(R.id.digitalClock);
        switchClock = findViewById(R.id.switchClock);
        toggleButton = findViewById(R.id.toggleButton);

        // Par défaut : horloge digitale visible
        analogClock.setVisibility(View.GONE);
        digitalClock.setVisibility(View.VISIBLE);

        // Gestion du Switch
        switchClock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Switch ON = Horloge digitale
                digitalClock.setVisibility(View.VISIBLE);
                analogClock.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,
                        "Horloge digitale activée", Toast.LENGTH_SHORT).show();
            } else {
                // Switch OFF = Horloge analogique
                digitalClock.setVisibility(View.GONE);
                analogClock.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,
                        "Horloge analogique activée", Toast.LENGTH_SHORT).show();
            }
        });

        // Gestion du ToggleButton
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Toggle ON = Horloge digitale
                digitalClock.setVisibility(View.VISIBLE);
                analogClock.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,
                        "Toggle: Horloge digitale", Toast.LENGTH_SHORT).show();
            } else {
                // Toggle OFF = Horloge analogique
                digitalClock.setVisibility(View.GONE);
                analogClock.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,
                        "Toggle: Horloge analogique", Toast.LENGTH_SHORT).show();
            }
        });

        // Synchronisation Switch/ToggleButton
        switchClock.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleButton.setChecked(isChecked);
            updateClockVisibility(isChecked);
        });

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchClock.setChecked(isChecked);
            updateClockVisibility(isChecked);
        });

    }

    private void updateClockVisibility(boolean isDigital) {
        if (isDigital) {
            digitalClock.setVisibility(View.VISIBLE);
            analogClock.setVisibility(View.GONE);
        } else {
            digitalClock.setVisibility(View.GONE);
            analogClock.setVisibility(View.VISIBLE);
        }
    }

}