package com.example.sumedh.searchapplication.ui.presenter;

import com.example.sumedh.searchapplication.ui.view.View;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface Presenter<T extends View> {
    void onViewDestroy();

    void setView(T view);

}
