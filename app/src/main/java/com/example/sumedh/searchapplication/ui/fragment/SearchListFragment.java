package com.example.sumedh.searchapplication.ui.fragment;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sumedh.searchapplication.R;
import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.domain.repository.HttpHelper;
import com.example.sumedh.searchapplication.ui.activity.MainActivity;
import com.example.sumedh.searchapplication.ui.adapter.SearchItemListAdapter;
import com.example.sumedh.searchapplication.ui.presenter.SearchItemPresenter;
import com.example.sumedh.searchapplication.ui.presenter.SearchItemPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchListFragment extends Fragment implements com.example.sumedh.searchapplication.ui.view.SearchView {

    List<SearchItem> searchItems;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    SearchItemListAdapter searchItemListAdapter;
    SearchView searchView;
    HttpHelper httpHelper;
    SearchItemPresenter searchItemPresenter;
    TextView noData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        noData = view.findViewById(R.id.noData);

        searchItems = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchItemListAdapter = new SearchItemListAdapter(searchItems);
        recyclerView.setAdapter(searchItemListAdapter);

        searchItemPresenter = new SearchItemPresenterImpl();
        searchItemPresenter.setView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                hideProgress();
                setItemsVisibility(menu, searchItem, true);
                recyclerView.setVisibility(View.GONE);
                searchItemListAdapter.clearAll();
                searchItemListAdapter.notifyDataSetChanged();
                showMessage("No data available");
                return false;
            }
        });

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchItemListAdapter.clearAll();
                    recyclerView.setVisibility(View.VISIBLE);
                    searchItemPresenter.getSearchResult(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (s.length() < 1) {
                        recyclerView.setVisibility(View.GONE);
                        searchItemListAdapter.clearAll();
                        searchItemListAdapter.notifyDataSetChanged();
                        showMessage("No data available");
                    }
                    return false;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void showProgress() {
        noData.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String errorMsg) {
        showMessage(errorMsg);
    }

    @Override
    public void onNoData() {
        showMessage("No data available");
    }

    @Override
    public void onNoNetwork() {
        showMessage("No network available");
    }

    private void showMessage(String messsage) {
        noData.setVisibility(View.VISIBLE);
        noData.setText(messsage);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onSearchResult(List<SearchItem> searchItems) {
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        this.searchItems.clear();
        this.searchItems.addAll(searchItems);
        recyclerView.setVisibility(View.VISIBLE);
        searchItemListAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(),"Data fetched",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchItemPresenter.onViewDestroy();
    }
}
