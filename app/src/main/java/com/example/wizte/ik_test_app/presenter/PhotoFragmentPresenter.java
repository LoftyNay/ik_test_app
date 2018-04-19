package com.example.wizte.ik_test_app.presenter;

import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.wizte.ik_test_app.App;
import com.example.wizte.ik_test_app.Utils;
import com.example.wizte.ik_test_app.model.PhotoItem;
import com.example.wizte.ik_test_app.view.PhotoFragmentView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PhotoFragmentPresenter extends MvpPresenter<PhotoFragmentView> implements IPhotoFragmentPresenter {

    private Disposable disposable;
    private FragmentManager fragmentManager;

    private MainActivityPresenter mainActivityPresenter() {
        return new MainActivityPresenter();
    }

    public PhotoFragmentPresenter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void loadPagePhotos(int page, String query) {
        if (page == Utils.Const.FIRST_PAGE) getViewState().showProgress();
        disposable = App.getFlickrApi().getPhotos(Utils.Const.PER_PAGE, page, query, 1, 1, "interestingness-desk")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    List<PhotoItem> photoItemList = response.getPhotos().getPhoto();
                    if (photoItemList.size() == 0 & page == Utils.Const.FIRST_PAGE)
                        getViewState().showNoResults();
                    else getViewState().hideNoResults();
                    if (photoItemList.size() != 0 & !query.isEmpty())
                        mainActivityPresenter().sendForMarkers(photoItemList, fragmentManager);
                    getViewState().addPhotoToRecycler(photoItemList);
                    getViewState().hideProgress();
                }, throwable -> {
                    getViewState().showNoConnection(throwable.getMessage());
                });
    }

    @Override
    public void needClearAdapter() {
        getViewState().clearPhotoItems();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
