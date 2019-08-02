package com.hfad.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;

    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    // Support method for insert data table DRINK
    private static void insertDrink(SQLiteDatabase db, String name,
                                    String description, int imageResource) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("IMAGE_RESOURCE_ID", imageResource);
        db.insert("DRINK", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);

        ContentValues contentValues = new ContentValues();
        contentValues.put("DESCRIPTION", "Testy");
        sqLiteDatabase.update("DRINK", contentValues, "NAME = ?", new String[] {"Latte"});
    }

    public void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            sqLiteDatabase.execSQL("CREATE TABLE DRINK (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "DESCRIPTION TEXT," +
                    "IMAGE_RESOURCE_ID INTEGER);");

            insertDrink(sqLiteDatabase, "Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte);
            insertDrink(sqLiteDatabase, "Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino);
            insertDrink(sqLiteDatabase, "Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter);
        }
        if (oldVersion < 2) {
            sqLiteDatabase.execSQL("ALTER TABLE DRINK " +
                    "ADD COLUMN FAVORITE NUMERIC");
        }
    }
}
