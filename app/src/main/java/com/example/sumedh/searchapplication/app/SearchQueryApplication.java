package com.example.sumedh.searchapplication.app;

import android.app.Application;
import android.os.StrictMode;

import com.example.sumedh.searchapplication.domain.repository.dbHelper.DatabaseHelper;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchQueryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance(this);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
