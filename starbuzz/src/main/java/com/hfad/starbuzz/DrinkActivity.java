package com.hfad.starbuzz;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK = "drinkId";
    private ImageView photo;
    private TextView name;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        int drinkId = (int) getIntent().getExtras().get(EXTRA_DRINK);

        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.
                    query("DRINK", new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?", new String[] {Integer.toString(drinkId)},
                    null, null, null);

            if (cursor.moveToFirst()) { // одна запись в курсоре, поэтому какой угодно move
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageResourceId = cursor.getInt(2);

                name.setText(nameText);
                description.setText(descriptionText);
                photo.setImageResource(imageResourceId);
                photo.setContentDescription(nameText);
            }

            cursor.close();
            db.close();

        } catch (SQLException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG).
                    show();
        }
    }
}
