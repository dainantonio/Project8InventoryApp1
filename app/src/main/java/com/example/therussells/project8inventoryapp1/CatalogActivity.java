package com.example.therussells.project8inventoryapp1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.therussells.project8inventoryapp1.data.CarContract;
import com.example.therussells.project8inventoryapp1.data.CarsDbHelper;

/**
 * Displays list of cars that were entered and stored in the app.
 */

public class CatalogActivity extends AppCompatActivity {
    /** Database helper that will provide us access to the database */
    private CarsDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new CarsDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();

    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the cars database.
     */
    private void displayDatabaseInfo() {

        displayDatabaseInfo();
        // Create and/or open a database to read from it

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CarContract.CarsEntry._ID,
                CarContract.CarsEntry.COLUMN_CAR_NAME,
                CarContract.CarsEntry.COLUMN_CAR_PRICE,
                CarContract.CarsEntry.COLUMN_CAR_QUALITY,
                CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_NAME,
                CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_PHONE_NUMBER };

        // Perform a query on the cars table
        Cursor cursor = db.query(
                CarContract.CarsEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_cars);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The cars table contains <number of rows in Cursor> cars.
            // _id - name - price-quality-supplier name- supplier phone number
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The cars table contains " + cursor.getCount() + " cars.\n\n");
            displayView.append(CarContract.CarsEntry._ID + " - " +
                    CarContract.CarsEntry.COLUMN_CAR_NAME + " - " +
                    CarContract.CarsEntry.COLUMN_CAR_PRICE + " - " +
                    CarContract.CarsEntry.COLUMN_CAR_QUALITY + " - " +
                    CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_NAME + " - " +
                    CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_PHONE_NUMBER + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry.COLUMN_CAR_NAME);
            int priceColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry.COLUMN_CAR_PRICE);
            int qualityColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry.COLUMN_CAR_QUALITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_NAME);
            int supplierNumberColumnIndex = cursor.getColumnIndex(CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_PHONE_NUMBER);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPrice = cursor.getString(priceColumnIndex);
                int currentQuality = cursor.getInt(qualityColumnIndex);
                int currentSupplierName = cursor.getInt(supplierNameColumnIndex);
                int currentSupplierNumber = cursor.getInt(supplierNumberColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentPrice + " - " +
                        currentQuality + " - " +
                        currentSupplierName + " - " +
                        currentSupplierNumber));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded car data into the database. For debugging purposes only.
     */
    private void insertCars() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues values = new ContentValues();
        values.put(CarContract.CarsEntry.COLUMN_CAR_NAME, "Honda");
        values.put(CarContract.CarsEntry.COLUMN_CAR_PRICE, 30,000);
        values.put(CarContract.CarsEntry.COLUMN_CAR_QUALITY, CarContract.CarsEntry.QUALITY_NEW);
        values.put(CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_NAME, "Honda North America");
        values.put(CarContract.CarsEntry.COLUMN_CAR_SUPPLIER_PHONE_NUMBER, 1-800-123-4567);


        // Insert a new row for Honda in the database, returning the ID of that new row.
        // The first argument for db.insert() is the cars table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(CarContract.CarsEntry.TABLE_NAME, null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertCars();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

