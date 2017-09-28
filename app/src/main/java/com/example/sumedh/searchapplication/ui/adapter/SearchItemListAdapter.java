package com.example.sumedh.searchapplication.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sumedh.searchapplication.R;
import com.example.sumedh.searchapplication.domain.model.SearchItem;
import com.example.sumedh.searchapplication.ui.viewHolder.SearchItemViewHolder;

import java.util.List;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchItemListAdapter extends RecyclerView.Adapter<SearchItemViewHolder> {

    List<SearchItem> searchItems;

    public SearchItemListAdapter(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
    }

    @Override
    public SearchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_search_items, parent, false);
        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchItemViewHolder holder, int position) {
        holder.title.setText(searchItems.get(position).getTitle());
        holder.upVoteCount.setText("Up Votes : "+String.valueOf(searchItems.get(position).getUp_vote_count()));
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public void clearAll(){
        searchItems.clear();
    }
}
