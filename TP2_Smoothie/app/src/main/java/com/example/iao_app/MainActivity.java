package com.example.iao_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ITEM 1
        setSmoothieItem(
                R.id.item1,
                "Choco-banana",
                "Boisson de soya, banane, cacao et datte",
                "7.00 €",
                R.drawable.smoothie1
        );

        // ITEM 2
        setSmoothieItem(
                R.id.item2,
                "Tape dans le dos",
                "Graines de lin moulues, pacanes, sirop d’érable, yogourt nature",
                "5.50 €",
                R.drawable.smoothie2
        );

        // ITEM 3
        setSmoothieItem(
                R.id.item3,
                "Poil de carotte",
                "Carotte, orange, mangue, gingembre, eau de coco et ananas",
                "5.50 €",
                R.drawable.smoothie3
        );

        // ITEM 4
        setSmoothieItem(
                R.id.item4,
                "Menthe religieuse",
                "Mangue, épinard, gingembre, menthe et eau de coco",
                "6.70 €",
                R.drawable.smoothie4
        );
    }

    private void setSmoothieItem(int id, String title, String desc, String price, int imgRes) {
        TextView t = findViewById(id).findViewById(R.id.title);
        TextView d = findViewById(id).findViewById(R.id.description);
        TextView p = findViewById(id).findViewById(R.id.price);
        ImageView img = findViewById(id).findViewById(R.id.image);

        t.setText(title);
        d.setText(desc);
        p.setText(price);
        img.setImageResource(imgRes);
    }
}
