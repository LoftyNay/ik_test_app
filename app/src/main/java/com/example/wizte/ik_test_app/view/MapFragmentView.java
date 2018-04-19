package com.example.wizte.ik_test_app.view;

import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.MvpView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public interface MapFragmentView extends MvpView  {
    void showAlertNeedGPS(AlertDialog dialog);
}
