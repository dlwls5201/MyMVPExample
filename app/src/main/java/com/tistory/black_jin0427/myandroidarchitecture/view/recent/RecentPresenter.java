package com.tistory.black_jin0427.myandroidarchitecture.view.recent;


import android.util.Log;

import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;
import com.tistory.black_jin0427.myandroidarchitecture.room.UserDao;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RecentPresenter implements RecentContract.Presenter {

    private RecentContract.View view;

    private UserDao userDao;

    private CompositeDisposable disposable;

    RecentPresenter(RecentContract.View view, UserDao userDao, CompositeDisposable disposable) {
        this.view = view;
        this.userDao = userDao;
        this.disposable = disposable;
    }

    @Override
    public void loadData() {
        disposable.add(
                userDao.getAllUser()
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(__ -> {
                            view.showProgress();
                        })
                        .subscribe(
                                users -> {
                                    view.setRoomItems((ArrayList<User>) users);
                                    view.hideProgress();
                                },
                                error -> {
                                    Log.e("MyTag", error.getMessage());
                                })
        );
    }

    @Override
    public void deleteUser(User user) {
        disposable.add(
                Observable.just(user)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                item -> {
                                    userDao.delete(item);
                                },
                                error -> {
                                    Log.e("MyTag", error.getMessage());
                                }
                        )
        );
    }

    @Override
    public void clearAll() {
        disposable.add(
                Observable.just("clear ALl")
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                item -> {
                                    userDao.clearAll();
                                },
                                error -> {
                                    Log.e("MyTag", error.getMessage());
                                }
                        )
        );
    }
}
