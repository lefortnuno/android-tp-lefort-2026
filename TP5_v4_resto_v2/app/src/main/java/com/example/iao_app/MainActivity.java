package com.example.iao_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends BaseActivity {

    private ListView listViewCategories;

    private String[] categories = {
            "Sandwichs",
            "Pizzas",
            "Salades",
            "Desserts",
            "Boissons"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Désactiver le bouton retour dans la toolbar (car c'est l'activité principale)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        listViewCategories = findViewById(R.id.listViewCategories);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categories
        );

        listViewCategories.setAdapter(adapter);

        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PlatsActivity.class);
                intent.putExtra("categorie", categories[position]);
                startActivity(intent);
            }
        });
    }

    // Redéfinir handleBackAction pour MainActivity
    @Override
    protected void handleBackAction() {
        // Dans MainActivity, on ne peut pas revenir en arrière, on affiche un message
        Toast.makeText(this, "Vous êtes déjà sur l'écran principal", Toast.LENGTH_SHORT).show();
    }

}