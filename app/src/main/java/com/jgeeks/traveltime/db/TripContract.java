package com.jgeeks.traveltime.db;

import android.provider.BaseColumns;

/**
 * Created by alexgaluska on 04/04/15.
 */
public final class TripContract {
    public TripContract(){

    }

    public static abstract class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PATH = "path";
        public static final String COLUMN_NAME_START = "start_date";

        public static final String COLUMN_NAME_END = "end_date";

    }
}
