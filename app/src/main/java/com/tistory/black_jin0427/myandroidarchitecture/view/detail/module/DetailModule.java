package com.tistory.black_jin0427.myandroidarchitecture.view.detail.module;

import com.tistory.black_jin0427.myandroidarchitecture.view.detail.DetailActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.detail.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    @Provides
    DetailPresenter provideDetailPresenter(DetailActivity activity) {
        return new DetailPresenter(activity);
    }
}
