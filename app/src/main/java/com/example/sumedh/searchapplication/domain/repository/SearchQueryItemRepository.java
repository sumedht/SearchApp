package com.example.sumedh.searchapplication.domain.repository;

import com.example.sumedh.searchapplication.domain.interactor.SearchQueryItem;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface SearchQueryItemRepository extends ApiCancellable {
    void searchResult(String query, SearchQueryItem.Callback callback);
}
