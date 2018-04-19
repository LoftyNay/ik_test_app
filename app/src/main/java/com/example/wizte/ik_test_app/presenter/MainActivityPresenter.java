package com.example.wizte.ik_test_app.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.Utils;
import com.example.wizte.ik_test_app.model.PhotoItem;
import com.example.wizte.ik_test_app.view.MainView;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainView> implements IMainActivityPresenter {

    private Disposable disposable;

    public MainActivityPresenter() {
    }

    @Override
    public void sendForMarkers(List<PhotoItem> photoItemList, FragmentManager fragmentManager) {
        disposable = Observable.just(photoItemList)
                .flatMapIterable(list -> list)
                .map(photoItem -> {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.title(photoItem.getTitle()).position(new LatLng(photoItem.getLatitude(), photoItem.getLongitude()));
                    return markerOptions;
                })
                .subscribe(markerOptions -> {
                    Fragment fragment = fragmentManager.findFragmentByTag("android:switcher:" + R.id.view_pager + ":1");
                    MapView map = Objects.requireNonNull(fragment.getView()).findViewById(R.id.map_view);
                    map.getMapAsync(googleMap -> googleMap.addMarker(markerOptions));
                }, throwable -> {
                });
    }

    @Override
    public void sendQuery(FragmentManager manager, String query) {
        Fragment fragment = manager.findFragmentByTag("android:switcher:" + R.id.view_pager + ":0");
        Bundle bundle = new Bundle();
        bundle.putString(Utils.Const.QUERY, query);
        fragment.setArguments(bundle);
        manager.beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
        getViewState().showQuery(query);
    }

    @Override
    public void reloadMapFragment(FragmentManager manager) {
        Fragment fragment = manager.findFragmentByTag("android:switcher:" + R.id.view_pager + ":1");
        manager.beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
