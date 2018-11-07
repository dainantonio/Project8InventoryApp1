package com.example.therussells.project8inventoryapp1.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.therussells.project8inventoryapp1.EditorActivity;
import com.example.therussells.project8inventoryapp1.data.CarContract.CarsEntry;

/**
 * Database helper for Cars app. Manages database creation and version management.
 */
public class CarsDbHelper extends SQLiteOpenHelper{
    public static final String LOG_TAG = CarsDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "cardealership.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link CarsDbHelper}.
     *
     * @param context of the app
     */
    public CarsDbHelper(EditorActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the Cars table
        String SQL_CREATE_CARS_TABLE =  "CREATE TABLE " + CarsEntry.TABLE_NAME + " ("
                + CarsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CarsEntry.COLUMN_CAR_NAME + " TEXT NOT NULL, "
                + CarsEntry.COLUMN_CAR_PRICE + " INTEGER NOT NULL, "
                + CarsEntry.COLUMN_CAR_QUANTITY + " TEXT NOT NULL, "
                + CarsEntry.COLUMN_CAR_QUALITY + " TEXT NOT NULL, "
                + CarsEntry.COLUMN_CAR_SUPPLIER_NAME + " TEXT NOT NULL, "
                + CarsEntry.COLUMN_CAR_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CARS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}