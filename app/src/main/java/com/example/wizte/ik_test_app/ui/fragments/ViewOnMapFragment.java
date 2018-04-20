package com.example.wizte.ik_test_app.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.Utils;
import com.example.wizte.ik_test_app.presenter.MapFragmentPresenter;
import com.example.wizte.ik_test_app.view.MapFragmentView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewOnMapFragment extends MvpAppCompatFragment implements MapFragmentView, OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    @InjectPresenter
    MapFragmentPresenter mapFragmentPresenter;

    @BindView(R.id.map_view)
    MapView mapView;

    public static ViewOnMapFragment newInstance() {
        return new ViewOnMapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_on_map_fragment, container, false);
        ButterKnife.bind(this, view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        MapsInitializer.initialize(Objects.requireNonNull(getActivity()));
        return view;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (!Utils.isGpsOn(Objects.requireNonNull(getContext())) & !Utils.isNetworkConnected(getContext()) & Build.VERSION.SDK_INT < 23 ||
                !Utils.isGpsOn(getContext()) & Build.VERSION.SDK_INT >= 23) {
            mapFragmentPresenter.getAlertNeedGPS(getActivity());
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMyLocationButtonClickListener(this);
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
