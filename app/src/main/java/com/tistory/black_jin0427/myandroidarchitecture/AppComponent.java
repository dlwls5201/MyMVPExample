package com.tistory.black_jin0427.myandroidarchitecture;

import android.app.Application;

import com.tistory.black_jin0427.myandroidarchitecture.api.ApiModule;
import com.tistory.black_jin0427.myandroidarchitecture.room.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class, ApiModule.class, RoomModule.class,

        AndroidSupportInjectionModule.class,
        ActivityBinder.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application app);

        AppComponent build();
    }
}
