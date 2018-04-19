package com.example.wizte.ik_test_app.adapters;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wizte.ik_test_app.callbacks.RecyclerScroll;

public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 18;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private GridLayoutManager gridLayoutManager;

    private RecyclerScroll recyclerScroll;
    private int current_page = 1;

    public RecyclerScrollListener(GridLayoutManager gridLayoutManager, RecyclerScroll recyclerScroll) {
        this.gridLayoutManager = gridLayoutManager;
        this.recyclerScroll = recyclerScroll;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        initVar();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            current_page++;
            recyclerScroll.onLoadNextPage(current_page);
            loading = true;
        }
    }

    private void initVar() {
        visibleItemCount = gridLayoutManager.getChildCount();
        totalItemCount = gridLayoutManager.getItemCount();
        firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
    }
}