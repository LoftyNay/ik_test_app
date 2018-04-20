package com.example.wizte.ik_test_app.presenter;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.view.MapFragmentView;

@InjectViewState
public class MapFragmentPresenter extends MvpPresenter<MapFragmentView> implements IMapFragmentPresenter {

    public MapFragmentPresenter() {
    }

    @Override
    public void getAlertNeedGPS(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.enable_loc_inf)
                .setMessage(R.string.need_incl_gps)
                .setPositiveButton(R.string.go, (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    activity.startActivity(intent);
                })
                .setNegativeButton(R.string.close, (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
