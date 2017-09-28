package com.example.sumedh.searchapplication.domain.interactor;

import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.domain.repository.SearchQueryItemRepository;
import com.example.sumedh.searchapplication.domain.repository.SearchQueryItemRepositoryImpl;
import com.example.sumedh.searchapplication.executor.InteractorExecutor;
import com.example.sumedh.searchapplication.executor.MainThreadExecutor;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchQueryItemImpl extends AbstractInteractor implements SearchQueryItem, SearchQueryItem.Callback {

    String query;
    SearchQueryItem.Callback callback;
    SearchQueryItemRepository searchQueryItemRepository;

    public SearchQueryItemImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
        searchQueryItemRepository = new SearchQueryItemRepositoryImpl();
    }

    @Override
    public void cancelApi() {
        callback = null;
        searchQueryItemRepository.cancelApi();
    }

    @Override
    public void execute(String query, Callback callback) {
        this.query = query;
        this.callback = callback;
        getInteractorExecutor().run(this);
    }

    @Override
    public void onSearchQueryResult(final List<SearchItem> searchItems) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSearchQueryResult(searchItems);
                }
            }
        });
    }

    @Override
    public void onError(final Exception e) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            searchQueryItemRepository.searchResult(query,this);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
