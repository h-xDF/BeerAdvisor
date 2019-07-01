package com.hfad.beeradvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class FindBeerActivity extends AppCompatActivity {
    Spinner color;
    TextView brands;
    private BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
        //setContentView(R.layout.activity_main); // Constrain layout
    }

    public void onClickfindBeer(View view) {
        color = findViewById(R.id.color);
        brands = findViewById(R.id.brands);
        brands.setText("Gottle of geer");
        String beerType = String.valueOf(color.getSelectedItem());

        List<String> brandBeerList = beerExpert.getBrands(beerType);
        StringBuilder brandsFormatted = new StringBuilder();
        for (String brand: brandBeerList) {
            brandsFormatted.append("\n").append(brand);
        }
        brands.setText(brandsFormatted);
    }
}
