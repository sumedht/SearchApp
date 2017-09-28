package com.example.sumedh.searchapplication.domain.repository.dbHelper;

import android.content.ContentValues;
import android.util.Log;

import com.example.sumedh.searchapplication.domain.model.SearchItem;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchResultQuery {

    private static SearchResultQuery searchResultQuery;

    private SearchResultQuery() {
    }

    public static synchronized SearchResultQuery getInstance() {
        if (searchResultQuery == null) {
            searchResultQuery = new SearchResultQuery();
        }
        return searchResultQuery;
    }

    /**
     * Method to inserts fetch search results to db.
     * @param searchItems
     */
    public void insertResults(List<SearchItem> searchItems) {
        try {
            for (int i=0;i<searchItems.size();i++) {
                ContentValues contentValue = new ContentValues();
                contentValue.put(DatabaseHelper.TITLE, searchItems.get(i).getTitle());
                contentValue.put(DatabaseHelper.UP_VOTE_COUNT, searchItems.get(i).getUp_vote_count());
                DatabaseHelper.getDatabase().insert(DatabaseHelper.TABLE_SEARCH_RESULT, null, contentValue);
            }
            Log.d("SearchResult:insert","Items inserted into db");
        } catch (Exception e) {
            Log.e("SearchResult:insert",e.getLocalizedMessage().toString());
        }
    }

    /**
     * Method to deleted table search result data.
     */
    public void deleteAllRecords() {
        try {
            DatabaseHelper.getDatabase().delete(DatabaseHelper.TABLE_SEARCH_RESULT, null, null);
            Log.d("SearchResult:insert","Items deleted from db");
        } catch (Exception e) {
            Log.e("SearchResult:delete",e.getLocalizedMessage().toString());
        }
    }
}
