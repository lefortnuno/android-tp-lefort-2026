package com.example.iao_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        Button zoomIn = findViewById(R.id.btnZoomIn);
        Button zoomOut = findViewById(R.id.btnZoomOut);
        Button btnChangeType = findViewById(R.id.btnChangeType);

        // Zoom +
        zoomIn.setOnClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomIn()));

        // Zoom -
        zoomOut.setOnClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomOut()));

        // ----- BOUTON CHANGEMENT TYPE DE CARTE -----
        btnChangeType.setOnClickListener(v -> showMapTypeSelector());

        // Clic → Ajouter marqueur
        mMap.setOnMapClickListener(latLng ->
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marqueur ajouté"))
        );

        enableLocation();
    }

    private void showMapTypeSelector() {
        final String[] types = {"Normal", "Satellite", "Terrain", "Hybride"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisir le type de carte");

        builder.setItems(types, (dialog, which) -> {
            switch (which) {
                case 0:
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
                case 1:
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    break;
                case 2:
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    break;
                case 3:
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    break;
            }
        });

        builder.show();
    }

    private void enableLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST);
            return;
        }

        mMap.setMyLocationEnabled(true);

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
            if (location != null)
                moveToLocation(location);
            else
                Toast.makeText(this, "Position non disponible", Toast.LENGTH_SHORT).show();
        });
    }

    private void moveToLocation(Location location) {
        LatLng userPos = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userPos, 15));
        mMap.addMarker(new MarkerOptions().position(userPos).title("Vous êtes ici"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableLocation();
        }
    }
}
