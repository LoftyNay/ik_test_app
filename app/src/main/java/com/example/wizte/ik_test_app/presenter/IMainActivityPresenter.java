package com.example.wizte.ik_test_app.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.example.wizte.ik_test_app.model.PhotoItem;

import java.util.List;

public interface IMainActivityPresenter {
    void sendForMarkers(List<PhotoItem> photoItemList, FragmentManager fragmentManager);
    void sendQuery(FragmentManager manager, String query);
    void reloadMapFragment(FragmentManager manager);
}
