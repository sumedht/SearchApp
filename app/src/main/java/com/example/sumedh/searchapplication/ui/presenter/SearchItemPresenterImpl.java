package com.example.sumedh.searchapplication.ui.presenter;

import com.example.sumedh.searchapplication.domain.interactor.SearchQueryItem;
import com.example.sumedh.searchapplication.domain.interactor.SearchQueryItemImpl;
import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.executor.InteractorExecutor;
import com.example.sumedh.searchapplication.executor.MainThreadExecutor;
import com.example.sumedh.searchapplication.executor.MainThreadExecutorImp;
import com.example.sumedh.searchapplication.executor.ThreadExecutor;
import com.example.sumedh.searchapplication.ui.view.SearchView;
import com.example.sumedh.searchapplication.utils.NetworkManager;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchItemPresenterImpl implements SearchItemPresenter {

    SearchQueryItem searchQueryItem;
    SearchView searchView;

    public SearchItemPresenterImpl() {
        InteractorExecutor interactorExecutor = new ThreadExecutor();
        MainThreadExecutor mainThreadExecutor = new MainThreadExecutorImp();
        searchQueryItem = new SearchQueryItemImpl(interactorExecutor, mainThreadExecutor);
    }

    @Override
    public void onViewDestroy() {
        searchQueryItem.cancelApi();
    }

    @Override
    public void setView(SearchView view) {
        searchView = view;
    }

    @Override
    public void getSearchResult(String query) {
        if (!NetworkManager.nManager.isNetworkAvailable(searchView.getActivityContext())) {
            searchView.onNoNetwork();
            return;
        }
        searchView.showProgress();
        searchQueryItem.execute(query, new SearchQueryItem.Callback() {
            @Override
            public void onSearchQueryResult(List<SearchItem> searchItems) {
                if (searchItems.size() < 1) {
                    searchView.onNoData();
                    return;
                }
                searchView.onSearchResult(searchItems);
                searchView.hideProgress();
            }

            @Override
            public void onError(Exception e) {
                searchView.hideProgress();
                searchView.onError(e.getLocalizedMessage());
            }
        });
    }
}
