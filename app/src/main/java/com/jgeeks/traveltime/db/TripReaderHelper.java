package com.jgeeks.traveltime.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.jgeeks.traveltime.model.Trip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexgaluska on 04/04/15.
 */
public class TripReaderHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TravelTime.db";
    private Context context;


    public TripReaderHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TripEntry.TABLE_TRIP_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //crud operations

    public void addTrip(Trip trip){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(TripEntry.COLUMN_NAME_TITLE,trip.getTitle());
        cv.put(TripEntry.COLUMN_NAME_PATH,trip.getPath());

        if(getTrip(trip.getTitle())==null) {
            db.insert(TripEntry.TABLE_NAME, null, cv);
        }
        else {
            //trip is already in db
            Toast.makeText(context,"Trip is already created.",Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public Trip getTrip(String title){
        Trip t = null;
        String query = "SELECT * FROM "+ TripEntry.TABLE_NAME + " WHERE " +TripEntry.COLUMN_NAME_TITLE + "=" + title;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TripEntry.TABLE_NAME, // a. table
                        new String[] {TripEntry.COLUMN_NAME_ENTRY_ID,TripEntry.COLUMN_NAME_TITLE,TripEntry.COLUMN_NAME_PATH}, // b. column names
                        " title = ?", // c. selections
                        new String[] { title.toString() }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        t = new Trip();
        t.setId(Integer.parseInt(cursor.getString(0)));
        t.setTitle(cursor.getString(1));
        t.setPath(cursor.getString(2));
        return t;
    }

    public List<Trip> getAllTrips(){
        List<Trip> list = new LinkedList<>();

        String query = "SELECT * FROM "+ TripEntry.TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build trip and add it to list
        Trip trip = null;
        if (cursor.moveToFirst()) {
            do {
                trip = new Trip();
                trip.setId(Integer.parseInt(cursor.getString(0)));
                trip.setTitle(cursor.getString(1));
                trip.setPath(cursor.getString(2));

                // Add book to books
                list.add(trip);
            } while (cursor.moveToNext());
        }

        return list;

    }

    public void backupDataBase() {
        try {
            File backupStorageDir = Environment.getExternalStorageDirectory();

            if (backupStorageDir.canWrite()) {
                String backupDBPath = getDatabaseName();
                File currentDB = context.getDatabasePath(DATABASE_NAME);
                File backupDB = new File(backupStorageDir, backupDBPath);

                if (currentDB.exists()) {
                    FileInputStream inputStream = new FileInputStream(currentDB);
                    FileOutputStream outputStream = new FileOutputStream(backupDB);
                    FileChannel src = inputStream.getChannel();
                    FileChannel dst = outputStream.getChannel();
                    dst.transferFrom(src, 0, src.size());
                    inputStream.close();
                    outputStream.close();
                    src.close();
                    dst.close();
                }
            } else {
                Log.w("TripReader", "Can not write to backup directory.");
            }
        } catch (Exception e) {
            Log.w("TripReader", "Something went wrong while backup-ing the database!");
        }
    }

    public static abstract class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PATH = "path";
        public static final String COLUMN_NAME_START = "start_date";
        public static final String COLUMN_NAME_END = "end_date";

        public static final String TABLE_TRIP_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_NAME_ENTRY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_TITLE
                + " STRING, " + COLUMN_NAME_PATH + " STRING, " + COLUMN_NAME_START + " STRING, " + COLUMN_NAME_END + "STRING );";

    }
}
