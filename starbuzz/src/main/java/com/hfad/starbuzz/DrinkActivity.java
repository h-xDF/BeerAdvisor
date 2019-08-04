package com.hfad.starbuzz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK = "drinkId";
    private ImageView photo;
    private TextView name;
    private TextView description;
    private CheckBox favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        int drinkId = (int) getIntent().getExtras().get(EXTRA_DRINK);

        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        favorite = findViewById(R.id.favorite);

        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.
                    query("DRINK",
                            new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                            "_id = ?", new String[] {Integer.toString(drinkId)},
                            null, null, null);

            if (cursor.moveToFirst()) { // одна запись в курсоре, поэтому какой угодно move
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageResourceId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1); //todo шляпа

                    name.setText(nameText);
                description.setText(descriptionText);
                photo.setImageResource(imageResourceId);
                photo.setContentDescription(nameText);
                favorite.setChecked(isFavorite);
            }

            cursor.close();
            db.close();

        } catch (SQLException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG).
                    show();
        }
    }

    public void onFavoriteClicked(View view) {
        int drinkId = (int) getIntent().getExtras().get(EXTRA_DRINK);

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());

            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("DRINK",
                        drinkValues,
                        "_id = ?",
                        new String[] {Integer.toString(drinkId)});
                db.close();
            } catch (SQLiteException e) {
                Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG).
                        show();
            }
    }
}
