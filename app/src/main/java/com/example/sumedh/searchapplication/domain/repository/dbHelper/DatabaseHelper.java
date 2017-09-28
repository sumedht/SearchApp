package com.example.sumedh.searchapplication.domain.repository.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sumedh on 9/28/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static SQLiteDatabase database;

    public static final String DATABASE_NAME    = "searchApp.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String TABLE_SEARCH_RESULT = "tblSearchResult";

    // Table columns
    public static final String TITLE = "title";
    public static final String UP_VOTE_COUNT = "up_vote_count";

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_SEARCH_RESULT + "(" + TITLE
            + " TEXT, " + UP_VOTE_COUNT + " LONG"
            +");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+TABLE_SEARCH_RESULT);
        onCreate(db);
    }


    private static DatabaseHelper databaseHelper = null;
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }
}
