package com.example.sumedh.searchapplication.domain.model;

/**
 * Created by Sumedh on 9/28/2017.
 */

public class SearchItem {
    private String title;
    private long up_vote_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUp_vote_count() {
        return up_vote_count;
    }

    public void setUp_vote_count(long up_vote_count) {
        this.up_vote_count = up_vote_count;
    }
}
