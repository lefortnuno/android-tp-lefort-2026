package com.example.iao_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class PlatAdapter extends ArrayAdapter<Plat> {

    public PlatAdapter(Context context, ArrayList<Plat> plats) {
        super(context, 0, plats);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_menu, parent, false);
        }

        Plat plat = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageViewPlat);
        TextView textViewNom = convertView.findViewById(R.id.textViewNom);
        TextView textViewPrix = convertView.findViewById(R.id.textViewPrix);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);

        if (plat != null) {
            imageView.setImageResource(plat.getImageResourceId());
            textViewNom.setText(plat.getNom());
            textViewPrix.setText(plat.getPrix());
            textViewDescription.setText(plat.getDescription());
        }

        return convertView;
    }
}