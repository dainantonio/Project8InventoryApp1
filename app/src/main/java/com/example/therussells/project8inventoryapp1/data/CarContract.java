

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.therussells.project8inventoryapp1.data;


import android.provider.BaseColumns;

/**
 * API Contract for the Cars app.
 */
public final class CarContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private CarContract() {}

    /**
     * Inner class that defines constant values for the cars database table.
     * Each entry in the table represents a single car.
     */
    public static final class CarsEntry implements BaseColumns {

        /** Name of database table for cars */
        public final static String TABLE_NAME = "cars";

        /**
         * Unique ID number for the car (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the car.
         *
         * Type: TEXT
         */
        public final static String COLUMN_CAR_NAME ="name";

        /**
         * car quality
         *
         * Type: INTEGER
         */
        public final static String COLUMN_CAR_QUALITY = "quality";

        /**
         * Possible values for the car quality.
         */
        public static final int QUALITY_NEW = 0;
        public static final int QUALITY_CERTIFIED_PRE_OWNED = 1;
        public static final int QUALITY_USED = 2;

        /**
         * Car price.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_CAR_PRICE= "price";


        /**
         * Car quantity.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_CAR_QUANTITY= "quantity";


        /**
         * Car supplier name
         *
         * Type: TEXT
         */
        public final static String COLUMN_CAR_SUPPLIER_NAME= "car supplier's name";


        /**
         * Car supplier phone number.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_CAR_SUPPLIER_PHONE_NUMBER= "car supplier's phone number";


    }

}


