package com.example.sumedh.searchapplication.domain.repository;

import com.example.sumedh.searchapplication.domain.interactor.SearchQueryItem;
import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.domain.repository.dbHelper.SearchResultQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchQueryItemRepositoryImpl implements SearchQueryItemRepository {

    HttpHelper httpHelper;
    public SearchQueryItemRepositoryImpl() {

    }

    @Override
    public void cancelApi() {

    }

    @Override
    public void searchResult(String query, final SearchQueryItem.Callback callback) {

        String url = "https://api.stackexchange.com/2.2/search/advanced?pagesize=100&order=desc&sort=relevance&q=%s&site=stackoverflow&filter=!.UE46pK5nV.kfAr.";
        url = String.format(url,query);

        httpHelper = new HttpHelper() {
            @Override
            public void onSuccess(String responce) {
                ArrayList<SearchItem> searchItems = new ArrayList<SearchItem>();
                try{
                    JSONObject jsonObject = new JSONObject(responce);
                    JSONArray result  = jsonObject.getJSONArray("items");
                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject obj = result.getJSONObject(i);
                        SearchItem searchItem = new SearchItem();
                        searchItem.setTitle(obj.optString("title", ""));
                        searchItem.setUp_vote_count(obj.optLong("up_vote_count", 0L));
                        searchItems.add(searchItem);
                    }
                    callback.onSearchQueryResult(searchItems);
                    SearchResultQuery.getInstance().deleteAllRecords();
                    SearchResultQuery.getInstance().insertResults(searchItems);
                }catch(Exception e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onFailure(Exception e) {
                callback.onError(e);
            }
        };
        httpHelper.execute(url,"GET");
    }
}
