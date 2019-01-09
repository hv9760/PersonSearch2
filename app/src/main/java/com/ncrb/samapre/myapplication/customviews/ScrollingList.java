package com.ncrb.samapre.myapplication.customviews;


import android.widget.AbsListView;

public class ScrollingList implements AbsListView.OnScrollListener {
    private static final String TAG = "CacheToDBActivity.EndlessScrollListener";
    private boolean loading = true;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
        if (!(loading) && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
            //Log.d(TAG, "Load Next Page!");




            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {}

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

}
