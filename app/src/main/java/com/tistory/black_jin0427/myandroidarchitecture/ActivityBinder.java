package com.tistory.black_jin0427.myandroidarchitecture;

import com.tistory.black_jin0427.myandroidarchitecture.view.detail.DetailActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.detail.module.DetailModule;
import com.tistory.black_jin0427.myandroidarchitecture.view.main.MainActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.main.module.MainModule;
import com.tistory.black_jin0427.myandroidarchitecture.view.recent.RecentActivity;
import com.tistory.black_jin0427.myandroidarchitecture.view.recent.module.RecentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {DetailModule.class})
    abstract DetailActivity bindDetailActivity();

    @ContributesAndroidInjector(modules = {RecentModule.class})
    abstract RecentActivity bindRecentActivity();
}
