package com.tistory.black_jin0427.myandroidarchitecture.view.main;

import com.tistory.black_jin0427.myandroidarchitecture.api.GithubApi;
import com.tistory.black_jin0427.myandroidarchitecture.api.model.User;
import com.tistory.black_jin0427.myandroidarchitecture.constant.Constant;
import com.tistory.black_jin0427.myandroidarchitecture.room.UserDao;
import com.tistory.black_jin0427.myandroidarchitecture.rxeventbus.RxEventBus;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private GithubApi api;
    private UserDao userDao;

    private CompositeDisposable disposable;

    private RxEventBus rxEventBus = RxEventBus.getInstance();

    MainPresenter(MainContract.View view,
                  GithubApi api,
                  UserDao userDao,
                  CompositeDisposable disposable) {
        this.view = view;
        this.api = api;
        this.userDao = userDao;
        this.disposable = disposable;
    }

    @Override
    public void loadData() {

        disposable.add(api.getUserList(Constant.RANDOM_USER_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> {
                    view.showProgress();
                })
                .doOnTerminate(() -> {
                    view.hideProgress();
                })
                .subscribe(userResponse -> {
                    view.setItems((ArrayList<User>) userResponse.userList);
                }, error -> {
                    view.showViewToast(error.getMessage());
                })
        );

    }


    @Override
    public void addUser(User user) {

        disposable.add(
                Observable.just(user)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                item -> {
                                    userDao.add(item);
                                    view.goToDetailActivity(user);
                                },
                                error -> {
                                    view.showViewToast(error.getMessage());

                                }
                        )
        );

    }

    @Override
    public void initRxEvent() {

        Disposable mainThreadBus =
                rxEventBus
                        .getObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                object -> {
                                    if (object instanceof User) {
                                        view.updateView((User) object);
                                    }
                                }
                        );

        Disposable backThreadBus =
                rxEventBus
                        .getObservable()
                        .observeOn(Schedulers.newThread())
                        .subscribe(
                                object -> {
                                    if (object instanceof User) {
                                        userDao.update((User) object);
                                    }
                                }
                        );

        disposable.addAll(
                mainThreadBus, backThreadBus
        );
    }
}
