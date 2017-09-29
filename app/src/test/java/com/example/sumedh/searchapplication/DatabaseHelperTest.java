package com.example.sumedh.searchapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sumedh.searchapplication.domain.repository.dbHelper.DatabaseHelper;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sumedh on 9/29/2017.
 */

public class DatabaseHelperTest {

    @Test
    public void getDatabase() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getDatabase();
        assertTrue(sqLiteDatabase != null);
    }

    @Test
    public void insertToDatabase() {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, "Java");
        contentValue.put(DatabaseHelper.UP_VOTE_COUNT, 1000l);
        long intertedId = DatabaseHelper.getDatabase().insert(DatabaseHelper.TABLE_SEARCH_RESULT, null, contentValue);
        assertTrue(intertedId != -1);

        Cursor cursor = DatabaseHelper.getDatabase().query(
                DatabaseHelper.TABLE_SEARCH_RESULT,  // Table to Query
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertTrue( "Error: No Records returned from table", cursor.moveToFirst() );

        cursor.close();
        DatabaseHelper.getDatabase().close();
    }

}
