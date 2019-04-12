package com.tistory.black_jin0427.myandroidarchitecture.view.recent.module;

import com.tistory.black_jin0427.myandroidarchitecture.adapter.MainAdapter;
import com.tistory.black_jin0427.myandroidarchitecture.room.UserDao;
import com.tistory.black_jin0427.myandroidarchitecture.view.recent.RecentActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.recent.RecentPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class RecentModule {

    @Provides
    MainAdapter provideMainAdapter(RecentActivity activity) {
        MainAdapter adapter = new MainAdapter();
        adapter.setClickListener(activity);
        return adapter;
    }

    @Provides
    RecentPresenter provideRecentPresenter(RecentActivity activity, UserDao userDao, CompositeDisposable disposable) {
        return new RecentPresenter(activity, userDao, disposable);
    }

    @Provides
    CompositeDisposable provideRecentDisposable() {
        return new CompositeDisposable();
    }
}
