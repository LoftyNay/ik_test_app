package com.example.wizte.ik_test_app.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.adapters.PagerAdapter;
import com.example.wizte.ik_test_app.presenter.MainActivityPresenter;
import com.example.wizte.ik_test_app.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView, TabLayout.OnTabSelectedListener, MaterialSearchView.OnQueryTextListener, MaterialSearchView.SearchViewListener {

    @InjectPresenter
    MainActivityPresenter mainActivityPresenter;

    @ProvidePresenter
    MainActivityPresenter mainActivityPresenter() {
        return new MainActivityPresenter();
    }

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchViewListener(this);
        tabLayout.setOnTabSelectedListener(this);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mainActivityPresenter.reloadMapFragment(getSupportFragmentManager());
                    return;
                }
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 1) {
            askPermissionsAndShowMyLocation();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mainActivityPresenter.reloadMapFragment(getSupportFragmentManager());
        mainActivityPresenter.sendQuery(getSupportFragmentManager(), query);
        searchView.closeSearch();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showQuery(String s) {
        toolbar.setTitle(s);
    }

    @Override
    public void onSearchViewShown() {
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onSearchViewClosed() {
        searchView.setFocusable(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void askPermissionsAndShowMyLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int accessCoarsePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED || accessFinePermission != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }
}

