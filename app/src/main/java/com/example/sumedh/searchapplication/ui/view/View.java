package com.example.sumedh.searchapplication.ui.view;

import android.content.Context;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface View {

    Context getActivityContext();

    void showProgress();

    void hideProgress();

    void onError(String errorMsg);

    void onNoData();

    void onNoNetwork();

}
