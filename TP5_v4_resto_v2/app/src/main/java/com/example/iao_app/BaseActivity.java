package com.example.iao_app;

import android.content.DialogInterface;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            // Action "Retour"
            handleBackAction();
            return true;
        } else if (id == R.id.action_about) {
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

    protected void handleBackAction() {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void showAboutDialog() {
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
                        Toast.makeText(BaseActivity.this,
                                "Merci d'utiliser notre application !",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    protected void showQuitConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quitter")
                .setMessage("Voulez-vous vraiment quitter l'application ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("Non", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}