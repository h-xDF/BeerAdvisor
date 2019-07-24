package com.hfad.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 1;

    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
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

    }
}
