package com.community_life.SQLLite_DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Community_Life_Local {

    // database columns
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FavouritesDatabase";
    private static final String KEY_ROWID = "_id";
    private static final String DATABASE_TABLE = "Favourites";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_VIDEO_PATH = "video_path";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_FAVOURITED = "favourited";

    // SQL statement to create the database. RowId auto incremented and drawing title must be unique.
    private static final String DATABASE_CREATE =
            "create table Favourites (_id integer primary key autoincrement," +
                    "title text not null , " +
                    "date text not null, " +
                    "time text not null, " +
                    "video_path text not null, " +
                    "description text not null, " +
                    "category text not null, " +
                    "favourited text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Constructor
    public Community_Life_Local(Context ctx) {
        //
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public Community_Life_Local open() {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // nested dB helper class
    private static class DatabaseHelper extends SQLiteOpenHelper {
        //
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        //
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // dB structure change..

        }
    }

    public void close()
    {
        DBHelper.close();
    }

    //Inserts row into table
    public long insertFaveEvent(String title, String date, String time, String video_path, String description, String category, String favourited)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_DATE, String.valueOf(date));
        initialValues.put(KEY_TIME, String.valueOf(time));
        initialValues.put(KEY_VIDEO_PATH, video_path);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_CATEGORY, category);
        initialValues.put(KEY_FAVOURITED, favourited);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //Returns all drawings when queried
    public Cursor getAllFaveEvents()
    {
        return db.query(DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                KEY_TITLE,
                                KEY_DATE,
                                KEY_TIME,
                                KEY_VIDEO_PATH,
                                KEY_DESCRIPTION,
                                KEY_CATEGORY,
                                KEY_FAVOURITED
                        },
                null, null, null, null, null);
    }

    public boolean removeFaveEvent(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID +
                "=" + rowId, null) > 0;
    }
}