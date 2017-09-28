package com.example.sumedh.searchapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by Sumedh on 9/28/2017.
 */
public enum NetworkManager {
    nManager;

    public static boolean isNetworkAvailable(Context context) {
        if(context == null)
            return false;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected =  activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return isConnected;
    }
}
