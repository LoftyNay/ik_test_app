package com.example.wizte.ik_test_app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.wizte.ik_test_app.callbacks.OnRecyclerItemClick;
import com.example.wizte.ik_test_app.R;
import com.example.wizte.ik_test_app.callbacks.RecyclerScroll;
import com.example.wizte.ik_test_app.adapters.RecyclerScrollListener;
import com.example.wizte.ik_test_app.Utils;
import com.example.wizte.ik_test_app.adapters.PhotoAdapter;
import com.example.wizte.ik_test_app.model.PhotoItem;
import com.example.wizte.ik_test_app.presenter.PhotoFragmentPresenter;
import com.example.wizte.ik_test_app.view.PhotoFragmentView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPhotoFragment extends MvpAppCompatFragment implements PhotoFragmentView, RecyclerScroll, OnRecyclerItemClick {

    @InjectPresenter
    PhotoFragmentPresenter photoFragmentPresenter;

    @ProvidePresenter
    PhotoFragmentPresenter photoFragmentPresenter() {
        return new PhotoFragmentPresenter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
    }

    @BindView(R.id.view_photo_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progress_view_photo)
    View progressBar;

    @BindView(R.id.no_net_connection)
    View noConnection;

    @BindView(R.id.no_results)
    View noResults;

    @BindView(R.id.get_connection_button)
    Button getConnectionButton;

    private String query = "";
    private PhotoAdapter photoAdapter = new PhotoAdapter(this);

    public static ViewPhotoFragment newInstance() {
        return new ViewPhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_photo_fragment, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            query = bundle.getString(Utils.Const.QUERY);
            photoFragmentPresenter.needClearAdapter();
        }
        photoFragmentPresenter.loadPagePhotos(Utils.Const.FIRST_PAGE, query);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), Utils.Const.SPAN_COUNTS, LinearLayoutManager.VERTICAL, false);
        RecyclerScrollListener recyclerScrollListener = new RecyclerScrollListener(gridLayoutManager, this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(recyclerScrollListener);
        recyclerView.setAdapter(photoAdapter);
    }

    @Override
    public void onRecyclerItemClick(View view, int position) {
        Toast.makeText(getActivity(), "pos: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadNextPage(int page) {
        photoFragmentPresenter.loadPagePhotos(page, this.query);
    }

    @Override
    public void addPhotoToRecycler(List<PhotoItem> listPhoto) {
        photoAdapter.addPhotoItems(listPhoto);
    }

    @Override
    public void clearPhotoItems() {
        photoAdapter.clearPhotoItems();
    }

    @OnClick(R.id.get_connection_button)
    public void onClick() {
        photoFragmentPresenter.needClearAdapter();
        photoFragmentPresenter.loadPagePhotos(Utils.Const.FIRST_PAGE, query);
    }

    @Override
    public void showNoConnection(String throwable) {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        noConnection.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoResults() {
        noResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoResults() {
        noResults.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noConnection.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        noConnection.setVisibility(View.GONE);
    }
}
