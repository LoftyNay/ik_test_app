package com.example.wizte.ik_test_app.view;

import com.arellomobile.mvp.MvpView;
import com.example.wizte.ik_test_app.model.PhotoItem;

import java.util.List;

public interface PhotoFragmentView extends MvpView {
    void addPhotoToRecycler(List<PhotoItem> listPhoto);
    void clearPhotoItems();
    void showNoConnection(String throwable);
    void showNoResults();
    void hideNoResults();
    void showProgress();
    void hideProgress();
}
