package com.example.sumedh.searchapplication.ui.view;

import com.example.sumedh.searchapplication.domain.model.SearchItem;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface SearchView extends View {

    void onSearchResult(List<SearchItem> searchItems);
}
