package com.example.iao_app;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMenu;
    private ArrayList<Plat> platsList;
    private PlatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuration de la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialisation de la ListView
        listViewMenu = findViewById(R.id.listViewMenu);

        // Création de la liste des plats
        platsList = new ArrayList<>();
        initializePlats();

        // Création de l'adaptateur
        adapter = new PlatAdapter(this, platsList);
        listViewMenu.setAdapter(adapter);

        // Gestion du clic sur un élément
        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Plat plat = platsList.get(position);
                // Affichage du prix dans un Toast (Exercice 2)
                Toast.makeText(MainActivity.this,
                        plat.getNom() + " : " + plat.getPrix() + " dhs",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializePlats() {
        // Ajout des plats avec leurs informations
        platsList.add(new Plat(
                "Margarita",
                "25 dhs",
                "Sauce tomate parfumée aux herbes, mozzarella, basilic frais",
                R.drawable.margarita
        ));

        platsList.add(new Plat(
                "Aux légumes grillés",
                "30 dhs",
                "Sauce tomate parfumée aux herbes, mozzarella, aubergines, poivrons à l'huile d'olive, courgettes grillées",
                R.drawable.legumes
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

        // Si vous n'avez pas les images, utilisez un placeholder
        // R.drawable.ic_food_placeholder
    }

    // Exercice 3 : Menu d'options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            // Action "À propos"
            showAboutDialog();
            return true;
        } else if (id == R.id.action_quit) {
            // Action "Quitter"
            showQuitConfirmation();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("À propos")
                .setMessage("Application développée par :\n\n" +
                        "Nom : LEFORT N. Nuno\n" +
                        "Classe : M1 IAO\n" +
                        "Date : " + java.time.LocalDate.now() + "\n\n" +
                        "Application de gestion de menu restaurant")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Optionnel : Afficher aussi un Toast
                        Toast.makeText(MainActivity.this,
                                "Merci d'utiliser notre application !",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void showQuitConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous vraiment quitter l'application ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Ferme l'activité
                    }
                })
                .setNegativeButton("Non", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}