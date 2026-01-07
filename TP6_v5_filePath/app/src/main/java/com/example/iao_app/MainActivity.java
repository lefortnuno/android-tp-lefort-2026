package com.example.iao_app;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView txtPath;

    private File currentDir;
    private File rootDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        txtPath = findViewById(R.id.txtPath);

        // Gestion du bouton retour
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (currentDir != null && !currentDir.equals(rootDir)) {
                            loadFiles(currentDir.getParentFile());
                        } else {
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissionAndOpen();
    }

    private void checkPermissionAndOpen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(
                        Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                        Uri.parse("package:" + getPackageName())
                );
                startActivity(intent);
            } else {
                openRoot();
            }
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1
            );
            openRoot();
        }
    }

    private void openRoot() {
        rootDir = Environment.getExternalStorageDirectory();
        loadFiles(rootDir);
    }
    private void loadFiles(File dir) {
        currentDir = dir;
        txtPath.setText(dir.getAbsolutePath());

        List<FileItem> items = new ArrayList<>();

        // Ajouter ".." si pas à la racine
        if (!currentDir.equals(rootDir)) {
            items.add(new FileItem(currentDir.getParentFile(), true));
        }

        File[] files = dir.listFiles();
        if (files != null) {
            Arrays.sort(files, (a, b) -> {
                if (a.isDirectory() && !b.isDirectory()) return -1;
                if (!a.isDirectory() && b.isDirectory()) return 1;
                return a.getName().compareToIgnoreCase(b.getName());
            });

            for (File f : files) {
                items.add(new FileItem(f, false));
            }
        }

        FileAdapter adapter = new FileAdapter(this, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            FileItem item = items.get(position);

            if (item.isParent || item.file.isDirectory()) {
                loadFiles(item.file);
            }
        });
    }

}
