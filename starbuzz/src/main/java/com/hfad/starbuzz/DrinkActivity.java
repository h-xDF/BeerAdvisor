package com.hfad.starbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK = "drinkId";
    private ImageView photo;
    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (int) getIntent().getExtras().get(EXTRA_DRINK);
        Drink drink = Drink.drinks[drinkId];

        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());
        name.setText(drink.getName());
        description.setText(drink.getDescription());
    }
}
