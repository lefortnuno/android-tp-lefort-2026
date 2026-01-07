package com.example.iao_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables
    private EditText etTitre, etAuteur, etAnnee, etISBN;
    private RatingBar ratingBar;
    private TextView tvRatingValue, tvResultat;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        initViews();

        // Configuration du RatingBar
        setupRatingBar();

        // Configuration du bouton de validation
        setupValidationButton();
    }

    private void initViews() {
        etTitre = findViewById(R.id.etTitre);
        etAuteur = findViewById(R.id.etAuteur);
        etAnnee = findViewById(R.id.etAnnee);
        etISBN = findViewById(R.id.etISBN);
        ratingBar = findViewById(R.id.ratingBar);
        tvRatingValue = findViewById(R.id.tvRatingValue);
        tvResultat = findViewById(R.id.tvResultat);
        btnValider = findViewById(R.id.btnValider);
    }

    private void setupRatingBar() {
        // Écouteur pour les changements de notation
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Mettre à jour l'affichage de la valeur
                tvRatingValue.setText(String.format("%.1f", rating));
            }
        });
    }

    private void setupValidationButton() {
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des données
                String titre = etTitre.getText().toString().trim();
                String auteur = etAuteur.getText().toString().trim();
                String annee = etAnnee.getText().toString().trim();
                String isbn = etISBN.getText().toString().trim();
                float notation = ratingBar.getRating();

                // Validation des champs obligatoires
                if (titre.isEmpty() || auteur.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Veuillez remplir les champs obligatoires (*)",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validation de l'année (optionnelle mais si remplie, doit être valide)
                if (!annee.isEmpty()) {
                    try {
                        int anneeInt = Integer.parseInt(annee);
                        if (anneeInt < 0 || anneeInt > 2100) {
                            Toast.makeText(MainActivity.this,
                                    "Année invalide",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this,
                                "Année doit être un nombre",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Affichage des données dans le TextView résultat
                afficherResultat(titre, auteur, annee, isbn, notation);

                // Toast de confirmation
                Toast.makeText(MainActivity.this,
                        "Livre enregistré avec succès!",
                        Toast.LENGTH_SHORT).show();

                // Optionnel: Réinitialiser le formulaire
                // resetFormulaire();
            }
        });
    }

    private void afficherResultat(String titre, String auteur, String annee, String isbn, float notation) {
        StringBuilder resultat = new StringBuilder();
        resultat.append("📚 Livre enregistré :\n\n");
        resultat.append("Titre : ").append(titre).append("\n");
        resultat.append("Auteur : ").append(auteur).append("\n");

        if (!annee.isEmpty()) {
            resultat.append("Année : ").append(annee).append("\n");
        }

        if (!isbn.isEmpty()) {
            resultat.append("ISBN : ").append(isbn).append("\n");
        }

        resultat.append("Note : ").append(String.format("%.1f", notation)).append("/5");

        tvResultat.setText(resultat.toString());
        tvResultat.setVisibility(View.VISIBLE);
    }

    // Méthode optionnelle pour réinitialiser le formulaire
    private void resetFormulaire() {
        etTitre.setText("");
        etAuteur.setText("");
        etAnnee.setText("");
        etISBN.setText("");
        ratingBar.setRating(0);
        tvRatingValue.setText("0.0");
        tvResultat.setVisibility(View.GONE);
        etTitre.requestFocus();
    }

    // Optionnel: Validation en temps réel
    private void setupRealTimeValidation() {
        etTitre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && etAuteur.getText().length() > 0) {
                    btnValider.setEnabled(true);
                    btnValider.setBackgroundTintList(getResources().getColorStateList(R.color.button_enabled));
                } else {
                    btnValider.setEnabled(false);
                    btnValider.setBackgroundTintList(getResources().getColorStateList(R.color.button_disabled));
                }
            }
        });

        etAuteur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && etTitre.getText().length() > 0) {
                    btnValider.setEnabled(true);
                    btnValider.setBackgroundTintList(getResources().getColorStateList(R.color.button_enabled));
                } else {
                    btnValider.setEnabled(false);
                    btnValider.setBackgroundTintList(getResources().getColorStateList(R.color.button_disabled));
                }
            }
        });
    }
}