package com.example.iao_app;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class PlatsActivity extends BaseActivity {

    private ListView listViewMenu;
    private ArrayList<Plat> platsList;
    private PlatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plats);

        String categorie = getIntent().getStringExtra("categorie");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Activer le bouton retour dans la toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(categorie);
        }

        listViewMenu = findViewById(R.id.listViewMenu);
        platsList = new ArrayList<>();

        initializePlats(categorie);

        adapter = new PlatAdapter(this, platsList);
        listViewMenu.setAdapter(adapter);
    }

    // Pas besoin de redéfinir handleBackAction ici, la version de BaseActivity convient
    // (elle appelle simplement onBackPressed())

    private void initializePlats(String categorie) {
        if (categorie.equals("Sandwichs")) {
            platsList.add(new Plat("Sandwich Poulet", "25 dhs", "Poulet, fromage", R.drawable.margarita));
            platsList.add(new Plat(
                    "Margarita",
                    "25 dhs",
                    "Sauce tomate parfumée aux herbes, mozzarella, basilic frais",
                    R.drawable.margarita
            ));
            platsList.add(new Plat(
                    "Tropicale",
                    "43 dhs",
                    "Crème fraîche, ananas, jambon/poulet, raisins",
                    R.drawable.tropicale
            ));
            platsList.add(new Plat(
                    "Catania",
                    "45 dhs",
                    "Sauce tomate parfumée aux herbes, mozzarella, thon, persillade, tomates œuf, câpres, olives, poivrons",
                    R.drawable.catania
            ));
        }

        if (categorie.equals("Salades")) {
            platsList.add(new Plat("Salade César", "30 dhs", "Poulet, parmesan", R.drawable.legumes));
            platsList.add(new Plat(
                    "Aux légumes grillés",
                    "30 dhs",
                    "Sauce tomate parfumée aux herbes, mozzarella, aubergines, poivrons à l'huile d'olive, courgettes grillées",
                    R.drawable.legumes
            ));
        }

        if (categorie.equals("Desserts")) {
            platsList.add(new Plat("Tiramisu", "20 dhs", "Café, mascarpone", R.drawable.tropicale));
        }

        if (categorie.equals("Boissons")) {
            platsList.add(new Plat("Jus d'orange", "10 dhs", "Frais", R.drawable.catania));
        }
    }
}