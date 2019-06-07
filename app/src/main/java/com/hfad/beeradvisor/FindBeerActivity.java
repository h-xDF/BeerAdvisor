package com.hfad.beeradvisor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FindBeerActivity extends AppCompatActivity {
    Spinner color;
    TextView brands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickfindBeer(View view) {
        color = findViewById(R.id.color);
        brands = findViewById(R.id.brands);
        brands.setText("Gottle of geer");
        String beerType = String.valueOf(color.getSelectedItem());
        brands.setText(beerType);
    }
}
