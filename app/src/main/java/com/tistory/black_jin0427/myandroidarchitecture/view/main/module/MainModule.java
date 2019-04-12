package com.tistory.black_jin0427.myandroidarchitecture.view.main.module;

import com.tistory.black_jin0427.myandroidarchitecture.adapter.MainAdapter;
import com.tistory.black_jin0427.myandroidarchitecture.api.GithubApi;
import com.tistory.black_jin0427.myandroidarchitecture.room.UserDao;
import com.tistory.black_jin0427.myandroidarchitecture.view.main.MainActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.main.MainPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    MainAdapter provideMainAdapter(MainActivity activity) {
        MainAdapter adapter = new MainAdapter();
        adapter.setClickListener(activity);
        return adapter;
    }


    @Provides
    MainPresenter provideMainPresenter(MainActivity activity, GithubApi api, UserDao userDao, CompositeDisposable disposable) {
        return new MainPresenter(activity, api, userDao, disposable);
    }


    @Provides
    CompositeDisposable provideMainDisposable() {
        return new CompositeDisposable();
    }
}
