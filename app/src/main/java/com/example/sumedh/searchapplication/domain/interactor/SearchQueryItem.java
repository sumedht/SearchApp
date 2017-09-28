package com.example.sumedh.searchapplication.domain.interactor;

import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.domain.repository.ApiCancellable;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public interface SearchQueryItem  extends ApiCancellable {

    void execute(String query, Callback callback);

    interface Callback {
        void onSearchQueryResult(List<SearchItem> searchItems);

        void onError(Exception e);
    }
}
