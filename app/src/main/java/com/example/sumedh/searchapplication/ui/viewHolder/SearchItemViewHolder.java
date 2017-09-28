package com.example.sumedh.searchapplication.ui.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sumedh.searchapplication.R;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView upVoteCount;

    public SearchItemViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        upVoteCount = (TextView) itemView.findViewById(R.id.upVoteCount);
    }
}
