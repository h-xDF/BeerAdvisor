package com.hfad.bitsandpizzas;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class PastaDetailActivity extends AppCompatActivity {
    public final static String EXTRA_PASTA_ID = "pastaId";

    private TextView pastaName;
    private ImageView pastaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int pastaId = (int) getIntent().getExtras().get(EXTRA_PASTA_ID);
        Pasta pasta = Pasta.pastas[pastaId];

        pastaName = findViewById(R.id.pasta_text);
        pastaImage = findViewById(R.id.pasta_image);

        pastaName.setText(pasta.getName());
        pastaImage.setImageDrawable(ContextCompat.getDrawable(this, pasta.getImageResourceId()));
        pastaImage.setContentDescription(pasta.getName());
    }
}
