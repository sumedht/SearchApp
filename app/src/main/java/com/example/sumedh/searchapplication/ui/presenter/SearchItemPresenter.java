package com.example.sumedh.searchapplication.ui.presenter;

import com.example.sumedh.searchapplication.ui.view.SearchView;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface SearchItemPresenter extends Presenter<SearchView> {

    void getSearchResult(String query);
}
